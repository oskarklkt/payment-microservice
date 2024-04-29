package com.griddynamics.gridhub.payment.model;

import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
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
