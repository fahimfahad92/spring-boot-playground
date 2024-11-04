package com.rnd.java21springboot3jpa.common.exception;

public class InsufficientBalanceException extends RuntimeException {
  public InsufficientBalanceException(String message) {
    super(message);
  }

  public InsufficientBalanceException() {
    super();
  }
}
