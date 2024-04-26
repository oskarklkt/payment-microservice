package com.griddynamics.gridhub.payment.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public non-sealed class CreditCard extends PaymentMethod {
  private Long userId;
  private String paymentType;
  private String cardHolderName;
  private String cardNumber;
  private String expirationDate;
  private String cvv;
}
