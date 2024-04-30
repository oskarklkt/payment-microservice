package com.griddynamics.gridhub.payment.controller;

import com.griddynamics.gridhub.payment.dto.PaypalDto;
import com.griddynamics.gridhub.payment.exception.BaseException;
import com.griddynamics.gridhub.payment.exception.NoSuchElementException;
import com.griddynamics.gridhub.payment.service.PaypalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class PaypalController implements CrudRestController<PaypalDto> {
  private final PaypalService paypalService;

  @Override
  public List<PaypalDto> get(Long userId) {
    try {
      return paypalService.get(userId);
    } catch (NoSuchElementException e) {
      log.error("{}: {}", e.getMessage(), e.getStatusCode());
    }
    return null;
  }

  @Override
  public PaypalDto update(Long paymentId, Long userId, PaypalDto paypalDto) {
    try {
      return paypalService.update(paymentId, userId, paypalDto);
    } catch (BaseException e) {
      log.error("{}: {}", e.getMessage(), e.getStatusCode());
    }
    return null;
  }

  @Override
  public void delete(Long paymentMethodId) {
    try {
      paypalService.delete(paymentMethodId);
    } catch (NoSuchElementException e) {
      log.error("{}: {}", e.getMessage(), e.getStatusCode());
    }
  }

  @Override
  public PaypalDto save(Long userId, PaypalDto paypalDto) {
    try {
      return paypalService.save(userId, paypalDto);
    } catch (BaseException e) {
      log.error("{}: {}", e.getMessage(), e.getStatusCode());
    }
    return null;
  }
}
