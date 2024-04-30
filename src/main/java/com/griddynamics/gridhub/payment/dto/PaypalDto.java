package com.griddynamics.gridhub.payment.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public non-sealed class PaypalDto extends PaymentMethodDto {
    private String email;
}
