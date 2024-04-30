package com.griddynamics.gridhub.payment.exception;

public class PaypalException extends BaseException {
    public PaypalException(String message) {
    super(message, 400);
  }
}
