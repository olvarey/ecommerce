package com.mreyes.ecommerce.exception;

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
   * Handles UserNameAlreadyRegisteredException and returns a 404 Not Found response.
   *
   * @param exception The UserNameAlreadyRegisteredException thrown when a client is not found.
   * @param request   The HttpServletRequest object containing request details.
   * @return A ResponseEntity containing an ApiError object with error details.
   */
  @ExceptionHandler(UserNameAlreadyRegisteredException.class)
  public ResponseEntity<ApiError> handleUserNameAlreadyRegisteredException(
      UserNameAlreadyRegisteredException exception, HttpServletRequest request) {
    return new ResponseEntity<>(
        new ApiError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "User authentication error",
            exception.getMessage(), request.getRequestURI()), HttpStatus.NOT_FOUND);
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