package com.griddynamics.gridhub.payment.service;

import com.griddynamics.gridhub.payment.dto.PaymentMethodDto;
import com.griddynamics.gridhub.payment.enumeration.PaymentType;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class ServiceFactory {
    private final PaypalService paypalService;
    private final CreditCardService creditCardService;

    @SuppressWarnings("unchecked")
    public <T extends PaymentMethodDto> PaymentService<T> getService(PaymentType type) {
        return switch (type) {
            case PAYPAL -> (PaymentService<T>) paypalService;
            case CREDIT_CARD -> (PaymentService<T>) creditCardService;
        };
    }
}
