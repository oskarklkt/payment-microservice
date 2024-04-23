package com.griddynamics.gridhub.payment.dto;

import com.google.gson.GsonBuilder;
import lombok.*;

@Data
@Builder
@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CreditCardDto extends PaymentMethodDto {
  private String paymentType;
  private String cardHolderName;
  private String cardNumber;
  private String expirationDate;
  private String cvv;

  @Override
  public String toString() {
    return new GsonBuilder().setPrettyPrinting().create().toJson(this);
  }
}
