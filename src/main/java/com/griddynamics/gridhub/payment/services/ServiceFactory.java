package com.griddynamics.gridhub.payment.services;

import com.griddynamics.gridhub.payment.exceptions.UnknownPaymentTypeException;
import com.griddynamics.gridhub.payment.mappers.CreditCardDtoMapper;
import com.griddynamics.gridhub.payment.mappers.PaymentMethodDtoCreditCardDtoMapper;
import com.griddynamics.gridhub.payment.mappers.PaymentMethodDtoPaypalDtoMapper;
import com.griddynamics.gridhub.payment.mappers.PaypalDtoMapper;
import com.griddynamics.gridhub.payment.repositories.CreditCardRepository;
import com.griddynamics.gridhub.payment.repositories.PaypalRepository;

public class ServiceFactory {
    CreditCardService creditCardService;
    PaypalService paypalService;

    public ServiceFactory(CreditCardRepository creditCardRepository,
                          CreditCardDtoMapper creditCardDtoMapper,
                          PaymentMethodDtoCreditCardDtoMapper paymentMethodDtoCreditCardDtoMapper,
                          PaypalRepository paypalRepository,
                          PaypalDtoMapper paypalDtoMapper,
                          PaymentMethodDtoPaypalDtoMapper paymentMethodDtoPaypalDtoMapper) {
        creditCardService = new CreditCardService(creditCardRepository, creditCardDtoMapper, paymentMethodDtoCreditCardDtoMapper);
        paypalService = new PaypalService(paypalRepository, paypalDtoMapper, paymentMethodDtoPaypalDtoMapper);
    }


    public PaymentService getService(String paymentType) {
        if (paymentType.equalsIgnoreCase("creditCard")) {
      return creditCardService;
        } else if (paymentType.equalsIgnoreCase("paypal")) {
      return paypalService;
        }
        throw new UnknownPaymentTypeException("Unknown payment type: %s".formatted(paymentType));
    }
}
