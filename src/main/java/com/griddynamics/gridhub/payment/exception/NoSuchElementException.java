package com.griddynamics.gridhub.payment.exception;

public class NoSuchElementException extends BaseException {
  public NoSuchElementException(String message) {
    super(message, 404);
  }
}
