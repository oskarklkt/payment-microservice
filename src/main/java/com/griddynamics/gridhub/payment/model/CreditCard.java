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

  //I think lombok cannot generate all args constructor with super class fields. Con it?
  public CreditCard(Long id, Long userId, String paymentType, String cardHolderName, String cardNumber, String expirationDate, String cvv) {
    super(id);
    this.userId = userId;
    this.paymentType = paymentType;
    this.cardHolderName = cardHolderName;
    this.cardNumber = cardNumber;
    this.expirationDate = expirationDate;
    this.cvv = cvv;
  }
}
