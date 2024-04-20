package com.griddynamics.gridhub.payment.repositories;

import com.griddynamics.gridhub.payment.models.PaymentMethod;

import java.util.Optional;

public interface Repository {
    void save();
    void delete();
    void update();
    Optional<PaymentMethod> get();
}
