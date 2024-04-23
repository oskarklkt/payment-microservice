package com.griddynamics.gridhub.payment.service;

import com.griddynamics.gridhub.payment.dto.PaymentMethodDto;

import java.util.List;

public interface PaymentService<T extends PaymentMethodDto> {
  T save(Long userId, T t);

  void delete(Long paymentMethodId);

  T update(Long paymentId, Long userId, T t);

  List<T> get(Long userId);
}
