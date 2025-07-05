package com.mreyes.ecommerce.service.implementation;

import com.mreyes.ecommerce.dto.client.ClientResponse;
import com.mreyes.ecommerce.dto.order.OrderRequest;
import com.mreyes.ecommerce.dto.order.OrderResponse;
import com.mreyes.ecommerce.enums.OrderStatus;
import com.mreyes.ecommerce.exception.client.ClientNotFoundException;
import com.mreyes.ecommerce.feign.ClientService;
import com.mreyes.ecommerce.mapper.OrderMapper;
import com.mreyes.ecommerce.model.Order;
import com.mreyes.ecommerce.repository.OrderRepository;
import com.mreyes.ecommerce.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final ClientService clientService;
  private final OrderRepository orderRepository;
  private final OrderMapper orderMapper;

  @Override
  public OrderResponse save(OrderRequest orderRequest) {
    try {
      clientService.getClientById(orderRequest.getClientId());
      Order order = orderMapper.toEntity(orderRequest);
      order.setStatus(OrderStatus.PENDING);
      return orderMapper.toDto(orderRepository.save(order));
    } catch (Exception e) {
      throw new ClientNotFoundException(orderRequest.getClientId());
    }
  }

  @Override
  public OrderResponse findById(Long id) {
    return orderMapper.toDto(orderRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id)));
  }

  @Override
  public List<OrderResponse> findAll() {
    return orderRepository.findAll().stream().map(orderMapper::toDto).toList();
  }

  @Override
  public OrderResponse update(Long id, OrderRequest orderRequest) {
    ClientResponse clientResponse = clientService.getClientById(orderRequest.getClientId());
    if (clientResponse == null || clientResponse.getId() == null) {
      throw new ClientNotFoundException(orderRequest.getClientId());
    }
    Order selectedOrder = orderRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    Order updatedOrder = orderMapper.update(orderRequest, selectedOrder);
    return orderMapper.toDto(orderRepository.save(updatedOrder));
  }

  @Override
  public void delete(Long id) {
    Order order = orderRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    orderRepository.delete(order);
  }
}
