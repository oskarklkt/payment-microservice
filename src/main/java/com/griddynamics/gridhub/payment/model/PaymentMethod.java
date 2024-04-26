package com.griddynamics.gridhub.payment.model;

import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode
@Getter
@SuperBuilder
public sealed class PaymentMethod permits CreditCard {
  private Long id;
  private Long userId;
  private String paymentType;

  @Override
  public String toString() {
    return new GsonBuilder().setPrettyPrinting().create().toJson(this);
  }
}
