package com.griddynamics.gridhub.payment.service;

import com.griddynamics.gridhub.payment.dto.PaymentMethodDto;
import com.griddynamics.gridhub.payment.enumeration.PaymentType;

public interface ServiceFactory {
    public <T extends PaymentMethodDto> PaymentService<T> getService(PaymentType type);
}
