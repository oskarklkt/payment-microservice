package com.griddynamics.gridhub.payment.model;

import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
public sealed class PaymentMethod permits CreditCard {

  @Override
  public String toString() {
    return new GsonBuilder().setPrettyPrinting().create().toJson(this);
  }
}
