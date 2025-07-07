package com.mreyes.ecommerce.service.implementation;

import com.mreyes.ecommerce.dto.OrderDetailRequest;
import com.mreyes.ecommerce.dto.OrderDetailResponse;
import com.mreyes.ecommerce.dto.order.OrderResponse;
import com.mreyes.ecommerce.dto.product.ProductResponse;
import com.mreyes.ecommerce.exception.order.OrderDetailNotFoundException;
import com.mreyes.ecommerce.feign.OrderService;
import com.mreyes.ecommerce.feign.ProductService;
import com.mreyes.ecommerce.mapper.OrderDetailMapper;
import com.mreyes.ecommerce.model.OrderDetail;
import com.mreyes.ecommerce.repository.OrderDetailRepository;
import com.mreyes.ecommerce.service.OrderDetailService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderDetailServiceImp implements OrderDetailService {

  private final Logger LOGGER = LoggerFactory.getLogger(OrderDetailServiceImp.class);

  private final OrderDetailRepository orderDetailRepository;
  private final OrderDetailMapper orderDetailMapper;
  private final OrderService orderService;
  private final ProductService productService;

  @Override
  public OrderDetailResponse save(OrderDetailRequest orderDetailRequest) {
    OrderResponse orderDetailResponse = orderService.getOrderById(orderDetailRequest.getOrderId());
    ProductResponse productResponse = productService.getProductById(
        orderDetailRequest.getProductId());
    OrderDetail orderDetail = orderDetailMapper.toEntity(orderDetailRequest);
    orderDetail.setPrice(productResponse.getPrice());
    OrderDetailResponse orderDetailResponseSaved = orderDetailMapper.toDto(
        orderDetailRepository.save(orderDetail));
    orderDetailResponseSaved.setOrder(orderDetailResponse);
    orderDetailResponseSaved.setProduct(productResponse);
    return orderDetailResponseSaved;
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
  public OrderDetailResponse update(Long id, OrderDetailRequest orderDetailRequest) {
    OrderDetail selectedOrderDetail = orderDetailRepository.findById(id)
        .orElseThrow(() -> new OrderDetailNotFoundException(id));
    OrderDetail orderDetailUpdated = orderDetailMapper.update(orderDetailRequest,
        selectedOrderDetail);
    return orderDetailMapper.toDto(orderDetailRepository.save(orderDetailUpdated));
  }

  @Override
  public void delete(Long id) {
    OrderDetail orderDetail = orderDetailRepository.findById(id)
        .orElseThrow(() -> new OrderDetailNotFoundException(id));
    orderDetailRepository.delete(orderDetail);
  }
}
