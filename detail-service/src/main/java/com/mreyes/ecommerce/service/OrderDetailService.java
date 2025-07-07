package com.mreyes.ecommerce.service;

import com.mreyes.ecommerce.dto.OrderDetailRequest;
import com.mreyes.ecommerce.dto.OrderDetailResponse;
import java.util.List;

public interface OrderDetailService {

  OrderDetailResponse save(OrderDetailRequest orderDetailRequest);

  OrderDetailResponse findById(Long id);

  List<OrderDetailResponse> findAll();

  OrderDetailResponse update(Long id, OrderDetailRequest orderDetailRequest);

  void delete(Long id);

}
