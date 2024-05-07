package com.griddynamics.gridhub.payment.dto;

import com.griddynamics.gridhub.payment.enumeration.PaymentType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode
@Getter
public abstract class PaymentMethodDto {
  private PaymentType paymentType;
}
