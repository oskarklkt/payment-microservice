package com.griddynamics.gridhub.payment.exceptions;

public class UnknownPaymentTypeException extends RuntimeException {
    public UnknownPaymentTypeException(String message) {
        super(message);
    }

}
