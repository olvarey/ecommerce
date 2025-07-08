package com.mreyes.ecommerce.service;

import com.mreyes.ecommerce.dto.PaymentRequest;
import com.mreyes.ecommerce.dto.PaymentResponse;
import java.util.List;

public interface PaymentService {

  PaymentResponse save(PaymentRequest request);

  PaymentResponse findById(Long id);

  List<PaymentResponse> findAll();

  PaymentResponse update(Long id, PaymentRequest request);

  void delete(Long id);
}
