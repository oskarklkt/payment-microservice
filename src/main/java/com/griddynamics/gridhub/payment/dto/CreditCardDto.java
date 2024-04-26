package com.griddynamics.gridhub.payment.dto;

import com.google.gson.GsonBuilder;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Getter
@EqualsAndHashCode(callSuper = true)
public non-sealed class CreditCardDto extends PaymentMethodDto {
  private String cardHolderName;
  private String cardNumber;
  private String expirationDate;
  private String cvv;

  @Override
  public String toString() {
    return new GsonBuilder().setPrettyPrinting().create().toJson(this);
  }
}
