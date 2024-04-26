package com.griddynamics.gridhub.payment.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode
@Getter
public sealed class PaymentMethodDto permits CreditCardDto {
  private String paymentType;
}
