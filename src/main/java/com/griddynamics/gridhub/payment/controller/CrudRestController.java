package com.griddynamics.gridhub.payment.controller;

import com.griddynamics.gridhub.payment.dto.PaymentMethodDto;


import java.util.List;

public interface CrudRestController<T extends PaymentMethodDto> {
  List<T> get(Long userId);

  T update(Long paymentId, Long userId, T t);

  void delete(Long paymentMethodId);

  T save(Long userId, T t);
}
