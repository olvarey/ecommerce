package com.mreyes.ecommerce.exception.client;

public class ClientNotFoundException extends RuntimeException {

  public ClientNotFoundException(Long id) {
    super("Client with ID " + id + " not found");
  }
}
