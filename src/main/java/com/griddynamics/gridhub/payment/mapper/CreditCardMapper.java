package com.griddynamics.gridhub.payment.mapper;

import com.griddynamics.gridhub.payment.dto.CreditCardDto;
import com.griddynamics.gridhub.payment.model.CreditCard;
import org.apache.commons.lang3.function.TriFunction;

public class CreditCardMapper implements TriFunction<Long, Long, CreditCardDto, CreditCard> {

  @Override
  public CreditCard apply(Long id, Long userId, CreditCardDto creditCardDto) {
    return CreditCard.builder()
        .id(id)
        .userId(userId)
        .paymentType(creditCardDto.getPaymentType())
        .cardHolderName(creditCardDto.getCardHolderName())
        .cardNumber(creditCardDto.getCardNumber())
        .expirationDate(creditCardDto.getExpirationDate())
        .cvv(creditCardDto.getCvv())
        .build();
  }
}
