package com.griddynamics.gridhub.payment.controller;

import com.griddynamics.gridhub.payment.dto.CreditCardDto;
import com.griddynamics.gridhub.payment.exception.BaseException;
import com.griddynamics.gridhub.payment.exception.CreditCardException;
import com.griddynamics.gridhub.payment.exception.NoSuchElementException;
import com.griddynamics.gridhub.payment.service.CreditCardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class CreditCardController implements CrudRestController<CreditCardDto> {
  private final CreditCardService creditCardService;

  public CreditCardDto save(Long userId, CreditCardDto creditCardDto) {
    try {
      return creditCardService.save(userId, creditCardDto);
    } catch (CreditCardException e) {
      log.error("{}: {}", e.getMessage(), e.getStatusCode());
    }
    return null;
  }

  public void delete(Long paymentMethodId) {
    try {
      creditCardService.delete(paymentMethodId);
    } catch (NoSuchElementException e) {
      log.error("{}: {}", e.getMessage(), e.getStatusCode());
    }
  }

  // todo When we will add spring and @Controller, we will return 500 status code instead of null
  public CreditCardDto update(Long paymentId, Long userId, CreditCardDto creditCardDto) {
    try {
      return creditCardService.update(paymentId, userId, creditCardDto);
    } catch (BaseException e) {
      log.error("{}: {}", e.getMessage(), e.getStatusCode());
    }
    return null;
  }

  // todo When we will add spring and @Controller, we will return 404(?) status code instead of null
  public List<CreditCardDto> get(Long userId) {
    try {
      return creditCardService.get(userId);
    } catch (NoSuchElementException e) {
      log.error("{}: {}", e.getMessage(), e.getStatusCode());
    }
    return null;
  }
}
