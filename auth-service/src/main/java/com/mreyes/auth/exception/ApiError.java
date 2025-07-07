package com.mreyes.auth.exception;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * ApiError is a data transfer object (DTO) used to represent error details in API responses. It
 * contains information about the error, including timestamp, HTTP status, error type, message, and
 * request path.
 */
@Data
@AllArgsConstructor
public class ApiError {

  private LocalDateTime timestamp;
  private int status;
  private String error;
  private String message;
  private String path;

}
