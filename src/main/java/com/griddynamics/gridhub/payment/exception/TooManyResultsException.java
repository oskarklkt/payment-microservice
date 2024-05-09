package com.griddynamics.gridhub.payment.exception;

public class TooManyResultsException extends BaseException{

        public TooManyResultsException(String message) {
            super(message, 400);
        }
}
