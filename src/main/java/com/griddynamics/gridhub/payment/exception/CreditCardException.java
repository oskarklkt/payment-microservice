package com.griddynamics.gridhub.payment.exception;

public class CreditCardException extends BaseException {
  public CreditCardException(String message) {
    super(message, 400);
  }
}
