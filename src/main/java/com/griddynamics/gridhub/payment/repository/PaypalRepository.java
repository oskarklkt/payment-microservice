package com.griddynamics.gridhub.payment.repository;

import com.griddynamics.gridhub.payment.model.Paypal;

import java.util.List;
import java.util.Optional;

//todo: implement the repository
public class PaypalRepository implements Repository<Paypal> {
    @Override
    public Paypal save(Paypal paymentMethod) {
        return null;
    }

    @Override
    public void delete(Long paymentMethodId) {

    }

    @Override
    public Paypal update(Paypal paymentMethod) {
        return null;
    }

    @Override
    public Optional<List<Paypal>> get(Long userId) {
        return Optional.empty();
    }
}
