package com.griddynamics.gridhub.payment.services;

import com.griddynamics.gridhub.payment.models.PaymentMethod;

import java.util.Optional;

public interface PaymentService {
    void save();
    void delete();
    void update();
    Optional<PaymentMethod> get();
}
