package com.griddynamics.gridhub.payment.dto;

import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class PaypalDto extends PaymentMethodDto {
  private String email;

  @Override
  public String toString() {
    return new GsonBuilder().setPrettyPrinting().create().toJson(this);
  }
}
