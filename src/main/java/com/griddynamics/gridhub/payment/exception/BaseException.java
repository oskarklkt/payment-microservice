package com.griddynamics.gridhub.payment.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
  private final int statusCode;

  public BaseException(final String message, final int statusCode) {
    super(message);
    this.statusCode = statusCode;
  }
}
