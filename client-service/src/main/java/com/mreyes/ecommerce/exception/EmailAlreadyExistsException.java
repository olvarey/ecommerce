package com.mreyes.ecommerce.exception;

public class EmailAlreadyExistsException extends RuntimeException {

  public EmailAlreadyExistsException(String email) {
    super("The email address " + email + " is already in use.");
  }
}
