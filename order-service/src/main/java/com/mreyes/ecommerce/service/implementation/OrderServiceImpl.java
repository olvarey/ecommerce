package com.mreyes.ecommerce.service.implementation;

import com.mreyes.ecommerce.dto.client.ClientResponse;
import com.mreyes.ecommerce.dto.order.OrderRequest;
import com.mreyes.ecommerce.dto.order.OrderResponse;
import com.mreyes.ecommerce.enums.OrderStatus;
import com.mreyes.ecommerce.exception.client.ClientNotFoundException;
import com.mreyes.ecommerce.exception.order.OrderNotFoundException;
import com.mreyes.ecommerce.feign.ClientService;
import com.mreyes.ecommerce.mapper.OrderMapper;
import com.mreyes.ecommerce.repository.OrderRepository;
import com.mreyes.ecommerce.service.OrderService;
import feign.FeignException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
  public OrderResponse save(OrderRequest request) {
    var client = clientExists(request.getClientId());
    var order = orderMapper.toEntity(request);
    order.setStatus(OrderStatus.PENDING);
    var response = orderMapper.toDto(orderRepository.save(order));
    response.setClientResponse(client);
    return response;
  }

  @Override
  public OrderResponse findById(Long id) {
    var order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    var response = orderMapper.toDto(order);
    var client = clientExists(order.getClientId());
    response.setClientResponse(client);
    return response;
  }

  @Override
  public List<OrderResponse> findAll() {
    return orderRepository.findAll().stream().map(orderMapper::toDto).toList();
  }

  @Override
  public List<OrderResponse> findAllWithClient() {
    Map<Long, ClientResponse> clientCache = new HashMap<>();

    return orderRepository.findAll().stream().map(order -> {
      var clientId = order.getClientId();
      var client = clientCache.computeIfAbsent(clientId, id -> {
        try {
          return clientService.getClientById(id);
        } catch (Exception e) {
          throw new ClientNotFoundException(order.getId());
        }
      });

      return OrderResponse.builder().id(order.getId()).clientId(order.getClientId())
          .status(order.getStatus().name()).createdAt(order.getCreatedAt())
          .updatedAt(order.getUpdatedAt()).clientResponse(client).build();
    }).toList();
  }

  @Override
  public OrderResponse update(Long id, OrderRequest request) {
    var existing = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    var updated = orderMapper.update(request, existing);
    var saved = orderRepository.save(updated);
    var response = orderMapper.toDto(saved);
    var client = clientExists(request.getClientId());
    response.setClientResponse(client);
    return response;
  }

  @Override
  public void delete(Long id) {
    var order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    orderRepository.delete(order);
  }

  public ClientResponse clientExists(Long id) {
    try {
      return clientService.getClientById(id);
    } catch (FeignException | HttpClientErrorException e) {
      throw new ClientNotFoundException(id);
    }
  }
}
