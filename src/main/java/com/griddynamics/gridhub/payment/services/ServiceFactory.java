package com.griddynamics.gridhub.payment.services;

import com.griddynamics.gridhub.payment.exceptions.UnknownPaymentTypeException;
import com.griddynamics.gridhub.payment.mappers.CreditCardDtoMapper;
import com.griddynamics.gridhub.payment.mappers.PaypalDtoMapper;
import com.griddynamics.gridhub.payment.repositories.CreditCardRepository;
import com.griddynamics.gridhub.payment.repositories.PaypalRepository;

public class ServiceFactory {
    public PaymentService getService(String paymentType) {
        if (paymentType.equalsIgnoreCase("creditCard")) {
      return new CreditCardService(new CreditCardRepository(), new CreditCardDtoMapper());
        } else if (paymentType.equalsIgnoreCase("paypal")) {
      return new PaypalService(new PaypalRepository(), new PaypalDtoMapper());
        }
        throw new UnknownPaymentTypeException("Unknown payment type: %s".formatted(paymentType));
    }
}
