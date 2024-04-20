package com.griddynamics.gridhub.payment.services;

import com.griddynamics.gridhub.payment.mappers.CreditCardDtoMapper;
import com.griddynamics.gridhub.payment.mappers.PaymentMethodDtoCreditCardDtoMapper;
import com.griddynamics.gridhub.payment.models.PaymentMethod;
import com.griddynamics.gridhub.payment.repositories.CreditCardRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class CreditCardService implements PaymentService {
    private final CreditCardRepository creditCardRepository;
    private final CreditCardDtoMapper creditCardDtoMapper;
    private final PaymentMethodDtoCreditCardDtoMapper paymentMethodDtoCreditCardDtoMapper;

    @Override
    public void save() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    public Optional<PaymentMethod> get() {
        return Optional.empty();
    }
}
