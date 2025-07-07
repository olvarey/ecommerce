package com.mreyes.ecommerce.exception;

import com.mreyes.ecommerce.exception.order.OrderDetailNotFoundException;
import com.mreyes.ecommerce.exception.order.OrderNotFoundException;
import com.mreyes.ecommerce.exception.product.ProductNotFoundException;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * GlobalExceptionHandler is a controller advice that handles exceptions globally across the
 * application. It intercepts exceptions thrown by controllers and provides a standardized error
 * response.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handles ClientNotFoundException and returns a 404 Not Found response.
   *
   * @param exception The ProductNotFoundException thrown when a product is not found.
   * @param request   The HttpServletRequest object containing request details.
   * @return A ResponseEntity containing an ApiError object with error details.
   */
  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<ApiError> handleProductNotFoundException(ProductNotFoundException exception,
      HttpServletRequest request) {
    return new ResponseEntity<>(new ApiError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
        "Product not found or there is something wrong with the service", exception.getMessage(),
        request.getRequestURI()), HttpStatus.NOT_FOUND);
  }

  /**
   * Handles ClientNotFoundException and returns a 404 Not Found response.
   *
   * @param exception The OrderNotFoundException thrown when an order is not found.
   * @param request   The HttpServletRequest object containing request details.
   * @return A ResponseEntity containing an ApiError object with error details.
   */
  @ExceptionHandler(OrderNotFoundException.class)
  public ResponseEntity<ApiError> handleOrderNotFoundException(OrderNotFoundException exception,
      HttpServletRequest request) {
    return new ResponseEntity<>(new ApiError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
        "Order not found or there is something wrong with the service", exception.getMessage(),
        request.getRequestURI()), HttpStatus.NOT_FOUND);
  }

  /**
   * Handles ClientNotFoundException and returns a 404 Not Found response.
   *
   * @param exception The OrderNotFoundException thrown when an order is not found.
   * @param request   The HttpServletRequest object containing request details.
   * @return A ResponseEntity containing an ApiError object with error details.
   */
  @ExceptionHandler(OrderDetailNotFoundException.class)
  public ResponseEntity<ApiError> handleOrderDetailNotFoundException(
      OrderDetailNotFoundException exception, HttpServletRequest request) {
    return new ResponseEntity<>(new ApiError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
        "Order detail not found or there is something wrong with the service",
        exception.getMessage(), request.getRequestURI()), HttpStatus.NOT_FOUND);
  }

  /**
   * Handles FeignException and returns a 502 Bad Gateway response.
   *
   * @param exception The FeignException thrown during communication with external services.
   * @param request   The HttpServletRequest object containing request details.
   * @return A ResponseEntity containing an ApiError object with error details.
   */
  @ExceptionHandler(FeignException.class)
  public ResponseEntity<ApiError> handleFeignException(FeignException exception,
      HttpServletRequest request) {
    return new ResponseEntity<>(
        new ApiError(LocalDateTime.now(), HttpStatus.BAD_GATEWAY.value(), "Feign client error - detail service",
            exception.getMessage(), request.getRequestURI()), HttpStatus.BAD_GATEWAY);
  }

  /**
   * Handles generic exceptions and returns a 500 Internal Server Error response.
   *
   * @param exception The generic Exception thrown during application execution.
   * @param request   The HttpServletRequest object containing request details.
   * @return A ResponseEntity containing an ApiError object with error details.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleException(Exception exception, HttpServletRequest request) {
    return new ResponseEntity<>(
        new ApiError(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal server error", exception.getMessage(), request.getRequestURI()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

}