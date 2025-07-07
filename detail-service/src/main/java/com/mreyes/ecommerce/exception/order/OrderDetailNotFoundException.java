package com.mreyes.ecommerce.exception.order;

public class OrderDetailNotFoundException extends RuntimeException {

  public OrderDetailNotFoundException(Long id) {
    super("Order detail with ID " + id + " not found");
  }
}
