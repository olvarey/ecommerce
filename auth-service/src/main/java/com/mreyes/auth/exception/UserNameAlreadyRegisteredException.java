package com.mreyes.auth.exception;

public class UserNameAlreadyRegisteredException extends RuntimeException {

  public UserNameAlreadyRegisteredException(String message) {
    super(message);
  }
}
