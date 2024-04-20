package com.griddynamics.gridhub.payment.dtos;

public sealed class PaymentMethodDto permits PaypalDto, CreditCardDto {}