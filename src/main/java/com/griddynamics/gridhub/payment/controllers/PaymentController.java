package com.griddynamics.gridhub.payment.controllers;

import com.griddynamics.gridhub.payment.mappers.CreditCardDtoMapper;
import com.griddynamics.gridhub.payment.mappers.PaymentMethodDtoCreditCardDtoMapper;
import com.griddynamics.gridhub.payment.mappers.PaymentMethodDtoPaypalDtoMapper;
import com.griddynamics.gridhub.payment.mappers.PaypalDtoMapper;
import com.griddynamics.gridhub.payment.repositories.CreditCardRepository;
import com.griddynamics.gridhub.payment.repositories.PaypalRepository;
import com.griddynamics.gridhub.payment.services.ServiceFactory;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaymentController {
    private final ServiceFactory serviceFactory;

    public PaymentController() {
    serviceFactory =
        new ServiceFactory(
                new CreditCardRepository(),
                new CreditCardDtoMapper(),
                new PaymentMethodDtoCreditCardDtoMapper(),
                new PaypalRepository(),
                new PaypalDtoMapper(),
                new PaymentMethodDtoPaypalDtoMapper());
    }

    public void add() {

    }

}
