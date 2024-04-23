package com.griddynamics.gridhub.payment.controller;

import com.griddynamics.gridhub.payment.dto.CreditCardDto;
import com.griddynamics.gridhub.payment.service.CreditCardService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CreditCardController implements RestController {
  CreditCardService creditCardService;

  public CreditCardDto save(Long userId, CreditCardDto creditCardDto) {
    return creditCardService.save(userId, creditCardDto);
  }

  public void delete(Long paymentMethodId) {
    creditCardService.delete(paymentMethodId);
  }

  public CreditCardDto update(Long paymentId, Long userId, CreditCardDto creditCardDto) {
    return creditCardService.update(paymentId, userId, creditCardDto);
  }

  public List<CreditCardDto> get(Long userId) {
    return creditCardService.get(userId);
  }
}
