package com.griddynamics.gridhub.payment.models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public sealed class PaymentMethod permits Paypal, CreditCard {
}
