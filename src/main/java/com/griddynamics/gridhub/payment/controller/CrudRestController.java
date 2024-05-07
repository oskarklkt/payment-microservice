package com.griddynamics.gridhub.payment.controller;

import com.griddynamics.gridhub.payment.dto.PaymentMethodDto;
import com.griddynamics.gridhub.payment.enumeration.PaymentType;

import java.util.List;

public interface CrudRestController {
  List<PaymentMethodDto> get(Long userId);

  PaymentMethodDto update(Long paymentId, Long userId, PaymentMethodDto dto);

  void delete(PaymentType paymentType, Long paymentMethodId);

  PaymentMethodDto save(Long userId, PaymentMethodDto dto);
}
