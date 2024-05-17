package com.griddynamics.gridhub.payment.service.implementation;

import com.griddynamics.gridhub.payment.dto.PaymentMethodDto;
import com.griddynamics.gridhub.payment.enumeration.PaymentType;
import com.griddynamics.gridhub.payment.service.CreditCardService;
import com.griddynamics.gridhub.payment.service.PaymentService;
import com.griddynamics.gridhub.payment.service.PaypalService;
import com.griddynamics.gridhub.payment.service.ServiceFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class ServiceFactoryImpl implements ServiceFactory {
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
