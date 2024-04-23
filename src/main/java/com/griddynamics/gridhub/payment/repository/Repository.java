package com.griddynamics.gridhub.payment.repository;

import com.griddynamics.gridhub.payment.model.PaymentMethod;

import java.util.List;
import java.util.Optional;

public interface Repository<T extends PaymentMethod> {
  T save(T paymentMethod);

  void delete(Long paymentMethodId);

  T update(T paymentMethod);

  Optional<List<T>> get(Long userId);
}
