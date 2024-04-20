package com.griddynamics.gridhub.payment.controllers;

import com.griddynamics.gridhub.payment.services.ServiceFactory;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaymentController {
    private final ServiceFactory serviceFactory;
}
