package com.mreyes.ecommerce.service.implementation;

import com.mreyes.ecommerce.dto.OrderDetailRequest;
import com.mreyes.ecommerce.dto.OrderDetailResponse;
import com.mreyes.ecommerce.dto.order.OrderResponse;
import com.mreyes.ecommerce.dto.product.ProductResponse;
import com.mreyes.ecommerce.exception.order.OrderDetailNotFoundException;
import com.mreyes.ecommerce.exception.product.ProductNotFoundException;
import com.mreyes.ecommerce.feign.OrderService;
import com.mreyes.ecommerce.feign.ProductService;
import com.mreyes.ecommerce.mapper.OrderDetailMapper;
import com.mreyes.ecommerce.repository.OrderDetailRepository;
import com.mreyes.ecommerce.service.OrderDetailService;
import feign.FeignException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderDetailServiceImp implements OrderDetailService {

  private final OrderDetailRepository orderDetailRepository;
  private final OrderDetailMapper orderDetailMapper;
  private final OrderService orderService;
  private final ProductService productService;

  @Override
  public OrderDetailResponse save(OrderDetailRequest request) {
    var order = orderExists(request.getOrderId());
    var product = productExists(request.getProductId());

    var entity = orderDetailMapper.toEntity(request);
    entity.setPrice(product.getPrice());

    var dto = orderDetailMapper.toDto(orderDetailRepository.save(entity));
    dto.setOrder(order);
    dto.setProduct(product);

    return dto;
  }


  @Override
  public OrderDetailResponse findById(Long id) {
    return orderDetailRepository.findById(id).map(orderDetailMapper::toDto)
        .orElseThrow(() -> new OrderDetailNotFoundException(id));
  }

  @Override
  public List<OrderDetailResponse> findAll() {
    return orderDetailRepository.findAll().stream().map(orderDetailMapper::toDto).toList();
  }

  @Override
  public OrderDetailResponse update(Long id, OrderDetailRequest request) {
    var order = orderExists(request.getOrderId());
    var product = productExists(request.getProductId());

    var existing = orderDetailRepository.findById(id)
        .orElseThrow(() -> new OrderDetailNotFoundException(id));

    var updated = orderDetailMapper.update(request, existing);
    updated.setPrice(product.getPrice());

    var dto = orderDetailMapper.toDto(orderDetailRepository.save(updated));
    dto.setOrder(order);
    dto.setProduct(product);

    return dto;
  }


  @Override
  public void delete(Long id) {
    var orderDetail = orderDetailRepository.findById(id)
        .orElseThrow(() -> new OrderDetailNotFoundException(id));
    orderDetailRepository.delete(orderDetail);
  }

  public OrderResponse orderExists(Long orderId) {
    try {
      return orderService.getOrderById(orderId);
    } catch (FeignException e) {
      throw new OrderDetailNotFoundException(orderId);
    }
  }

  public ProductResponse productExists(Long productId) {
    try {
      return productService.getProductById(productId);
    } catch (FeignException e) {
      throw new ProductNotFoundException(productId);
    }
  }
}
