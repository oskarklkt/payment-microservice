package com.griddynamics.gridhub.payment.mapper.modelToDto;

import com.griddynamics.gridhub.payment.dto.CreditCardDto;
import com.griddynamics.gridhub.payment.model.CreditCard;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CreditCardDtoMapper implements Function<CreditCard, CreditCardDto> {

  @Override
  public CreditCardDto apply(CreditCard creditCard) {
    return CreditCardDto.builder()
        .cardHolderName(creditCard.getCardHolderName())
        .paymentType(creditCard.getPaymentType())
        .cardNumber(creditCard.getCardNumber())
        .expirationDate(creditCard.getExpirationDate())
        .cvv(creditCard.getCvv())
        .build();
  }
}
