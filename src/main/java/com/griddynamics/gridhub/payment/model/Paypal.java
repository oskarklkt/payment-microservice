package com.griddynamics.gridhub.payment.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public non-sealed class Paypal extends PaymentMethod {
    private String email;
}
