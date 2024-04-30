package com.griddynamics.gridhub.payment.model;

import com.google.gson.GsonBuilder;
import com.griddynamics.gridhub.payment.enumeration.PaymentType;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public sealed class PaymentMethod permits CreditCard, Paypal {
  private Long id;
  private Long userId;
  private PaymentType paymentType;

  @Override
  public String toString() {
    return new GsonBuilder().setPrettyPrinting().create().toJson(this);
  }
}
