package com.griddynamics.gridhub.payment.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public non-sealed class CreditCard extends PaymentMethod {
  private Long id;
  private Long userId;
  private String paymentType;
  private String cardHolderName;
  private String cardNumber;
  private String expirationDate;
  private String cvv;
}
