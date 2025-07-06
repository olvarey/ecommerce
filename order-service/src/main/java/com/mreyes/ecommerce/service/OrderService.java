package com.mreyes.ecommerce.service;

import com.mreyes.ecommerce.dto.order.OrderRequest;
import com.mreyes.ecommerce.dto.order.OrderResponse;
import java.util.List;

public interface OrderService {

  OrderResponse save(OrderRequest orderRequest);

  OrderResponse findById(Long id);

  List<OrderResponse> findAll();

  List<OrderResponse> findAllWithClient();

  OrderResponse update(Long id, OrderRequest orderRequest);

  void delete(Long id);

}
