package com.griddynamics.gridhub.payment.services;

import com.griddynamics.gridhub.payment.mappers.PaymentMethodDtoPaypalDtoMapper;
import com.griddynamics.gridhub.payment.mappers.PaypalDtoMapper;
import com.griddynamics.gridhub.payment.models.PaymentMethod;
import com.griddynamics.gridhub.payment.repositories.PaypalRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class PaypalService implements PaymentService {
    private final PaypalRepository paypalRepository;
    private final PaypalDtoMapper paypalDtoMapper;
    private final PaymentMethodDtoPaypalDtoMapper paymentMethodDtoPaypalDtoMapper;

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
