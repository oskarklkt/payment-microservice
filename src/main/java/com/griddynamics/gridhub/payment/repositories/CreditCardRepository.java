package com.griddynamics.gridhub.payment.repositories;

import com.griddynamics.gridhub.payment.models.PaymentMethod;
import com.griddynamics.gridhub.payment.models.Paypal;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class CreditCardRepository implements Repository{

    private final static Map<Long, Paypal> db = new ConcurrentHashMap<>();

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
