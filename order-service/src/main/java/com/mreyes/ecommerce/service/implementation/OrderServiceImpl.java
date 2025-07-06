package com.mreyes.ecommerce.service.implementation;

import com.mreyes.ecommerce.dto.client.ClientResponse;
import com.mreyes.ecommerce.dto.order.OrderRequest;
import com.mreyes.ecommerce.dto.order.OrderResponse;
import com.mreyes.ecommerce.enums.OrderStatus;
import com.mreyes.ecommerce.exception.client.ClientNotFoundException;
import com.mreyes.ecommerce.exception.order.OrderNotFoundException;
import com.mreyes.ecommerce.feign.ClientService;
import com.mreyes.ecommerce.mapper.OrderMapper;
import com.mreyes.ecommerce.model.Order;
import com.mreyes.ecommerce.repository.OrderRepository;
import com.mreyes.ecommerce.service.OrderService;
import feign.FeignException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final ClientService clientService;
  private final OrderRepository orderRepository;
  private final OrderMapper orderMapper;

  @Override
  public OrderResponse save(OrderRequest orderRequest) {
    try {
      ClientResponse client = clientService.getClientById(orderRequest.getClientId());
      Order order = orderMapper.toEntity(orderRequest);
      order.setStatus(OrderStatus.PENDING);
      OrderResponse orderResponse = orderMapper.toDto(orderRepository.save(order));
      orderResponse.setClientResponse(client);
      return orderResponse;
    } catch (FeignException | HttpClientErrorException e) {
      throw new ClientNotFoundException(orderRequest.getId());
    } catch (Exception e) {
      throw new RuntimeException("Unexpected error during order creation", e);
    }
  }

  @Override
  public OrderResponse findById(Long id) {
    try {
      Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
      OrderResponse orderResponse = orderMapper.toDto(order);
      ClientResponse client = clientService.getClientById(orderResponse.getClientId());
      orderResponse.setClientResponse(client);
      return orderResponse;
    } catch (FeignException | HttpClientErrorException e) {
      throw new ClientNotFoundException(id);
    } catch (Exception e) {
      throw new RuntimeException("Unexpected error during order update", e);
    }
  }

  @Override
  public List<OrderResponse> findAll() {
    return orderRepository.findAll().stream().map(orderMapper::toDto).toList();
  }

  @Override
  public List<OrderResponse> findAllWithClient() {
    List<Order> orders = orderRepository.findAll();

    // Step 1: Cache to avoid duplicate Feign calls
    Map<Long, ClientResponse> clientCache = new HashMap<>();

    return orders.stream().map(order -> {
      Long clientId = order.getClientId();
      ClientResponse client;

      // Step 2: Use cached client if already fetched
      if (clientCache.containsKey(clientId)) {
        client = clientCache.get(clientId);
      } else {
        try {
          client = clientService.getClientById(clientId);
          clientCache.put(clientId, client);
        } catch (Exception e) {
          throw new ClientNotFoundException(order.getId());
        }
      }

      return OrderResponse.builder().id(order.getId()).clientId(order.getClientId())
          .status(order.getStatus().name()).clientResponse(client).build();
    }).toList();
  }


  @Override
  public OrderResponse update(Long id, OrderRequest orderRequest) {
    try {
      Order selectedOrder = orderRepository.findById(id)
          .orElseThrow(() -> new OrderNotFoundException(id));
      Order updatedOrder = orderMapper.update(orderRequest, selectedOrder);
      Order savedOrder = orderRepository.save(updatedOrder);
      OrderResponse response = orderMapper.toDto(savedOrder);
      ClientResponse client = clientService.getClientById(response.getClientId());
      response.setClientResponse(client);
      return response;
    } catch (FeignException | HttpClientErrorException e) {
      throw new ClientNotFoundException(orderRequest.getClientId());
    } catch (Exception e) {
      throw new RuntimeException("Unexpected error during order update", e);
    }
  }


  @Override
  public void delete(Long id) {
    Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    orderRepository.delete(order);
  }
}
