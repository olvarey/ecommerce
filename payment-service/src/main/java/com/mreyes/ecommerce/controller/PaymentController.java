package com.mreyes.ecommerce.controller;

import com.mreyes.ecommerce.dto.PaymentRequest;
import com.mreyes.ecommerce.dto.PaymentResponse;
import com.mreyes.ecommerce.service.PaymentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PaymentController {

  private final PaymentService paymentService;

  @GetMapping(produces = "application/json")
  public ResponseEntity<List<PaymentResponse>> getAllPayments() {
    List<PaymentResponse> payments = paymentService.findAll();
    if (payments.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(payments);
  }

  @GetMapping(value = "/{id}", produces = "application/json")
  public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable("id") Long id) {
    PaymentResponse paymentResponse = paymentService.findById(id);
    return ResponseEntity.ok(paymentResponse);
  }

  @PostMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest) {
    PaymentResponse createdPayment = paymentService.save(paymentRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
  }

  @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
  public ResponseEntity<PaymentResponse> updatePayment(@PathVariable("id") Long id,
      @RequestBody PaymentRequest paymentRequest) {
    PaymentResponse updatedPayment = paymentService.update(id, paymentRequest);
    return ResponseEntity.ok(updatedPayment);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deletePayment(@PathVariable("id") Long id) {
    paymentService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
