package com.griddynamics.gridhub.payment.repository;

import com.griddynamics.gridhub.payment.model.PaymentMethod;

import java.util.List;

public interface Repository<T extends PaymentMethod> {
  T save(T paymentMethod);

  void delete(Long paymentMethodId);

  T update(T paymentMethod);

  List<T> get(Long userId);

  boolean isContains(Long paymentMethodId);
}
