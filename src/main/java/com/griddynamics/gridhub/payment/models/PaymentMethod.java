package com.griddynamics.gridhub.payment.models;

public sealed class PaymentMethod permits Paypal, CreditCard {}
