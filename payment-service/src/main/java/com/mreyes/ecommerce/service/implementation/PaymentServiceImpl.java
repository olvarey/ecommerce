package com.mreyes.ecommerce.service.implementation;

import com.mreyes.ecommerce.dto.PaymentRequest;
import com.mreyes.ecommerce.dto.PaymentResponse;
import com.mreyes.ecommerce.exception.PaymentNotFoundException;
import com.mreyes.ecommerce.mapper.PaymentMapper;
import com.mreyes.ecommerce.model.Payment;
import com.mreyes.ecommerce.repository.PaymentRepository;
import com.mreyes.ecommerce.service.PaymentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  private final PaymentRepository paymentRepository;
  private final PaymentMapper paymentMapper;

  @Override
  public PaymentResponse save(PaymentRequest clientRequest) {
    return paymentMapper.toDto(paymentRepository.save(paymentMapper.toEntity(clientRequest)));
  }

  @Override
  public PaymentResponse findById(Long id) {
    return paymentMapper.toDto(
        paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException(id)));
  }

  @Override
  public List<PaymentResponse> findAll() {
    return paymentRepository.findAll().stream().map(paymentMapper::toDto).toList();
  }

  @Override
  public PaymentResponse update(Long id, PaymentRequest clientRequest) {
    Payment existingPayment = paymentRepository.findById(id)
        .orElseThrow(() -> new PaymentNotFoundException(id));
    Payment updatedPayment = paymentMapper.update(clientRequest, existingPayment);
    return paymentMapper.toDto(paymentRepository.save(updatedPayment));
  }

  @Override
  public void delete(Long id) {
    if (!paymentRepository.existsById(id)) {
      throw new IllegalArgumentException("Payment not found with id: " + id);
    }
    paymentRepository.deleteById(id);

  }
}
