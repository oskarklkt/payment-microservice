package com.griddynamics.gridhub.payment.controller;

import com.griddynamics.gridhub.payment.dto.PaypalDto;

import java.util.List;

// todo: implement the controller
public class PaypalController implements CrudRestController<PaypalDto> {
  @Override
  public List<PaypalDto> get(Long userId) {
    return List.of();
  }

  @Override
  public PaypalDto update(Long paymentId, Long userId, PaypalDto paypalDto) {
    return null;
  }

  @Override
  public void delete(Long paymentMethodId) {}

  @Override
  public PaypalDto save(Long userId, PaypalDto paypalDto) {
    return null;
  }
}
