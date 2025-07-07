package com.mreyes.ecommerce.exception;

/**
 * ExternalServiceException is a custom runtime exception used to indicate failures when connecting
 * to external services.
 */
public class ExternalServiceException extends RuntimeException {

  /**
   * Constructs a new ExternalServiceException with a detailed error message.
   *
   * @param message The specific error message describing the failure.
   */
  public ExternalServiceException(String message) {
    super("Failed to connect to external service: " + message);
  }
}