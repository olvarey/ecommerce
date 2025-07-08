package com.mreyes.ecommerce.exception;

public class PaymentNotFoundException extends RuntimeException {

  public PaymentNotFoundException(Long id) {
    super("Payment with ID " + id + " not found");
  }
}
