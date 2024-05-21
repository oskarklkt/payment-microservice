package com.griddynamics.gridhub.payment.controller;


import com.griddynamics.gridhub.payment.dto.PaymentMethodDto;
import com.griddynamics.gridhub.payment.enumeration.PaymentType;
import com.griddynamics.gridhub.payment.service.PaymentService;
import com.griddynamics.gridhub.payment.service.ServiceFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class PaymentController implements CrudRestController {

    private ServiceFactory paymentServiceFactory;

    public PaymentMethodDto save(Long userId, PaymentMethodDto dto) {
        PaymentService<PaymentMethodDto> service =  paymentServiceFactory.getService(dto.getPaymentType());
        return service.save(userId, dto);
    }

    public void delete(PaymentType type, Long paymentMethodId) {
        PaymentService<PaymentMethodDto> service = paymentServiceFactory.getService(type);
        service.delete(paymentMethodId);
    }

    public PaymentMethodDto update(Long paymentMethodId, Long userId, PaymentMethodDto dto) {
        PaymentService<PaymentMethodDto> service = paymentServiceFactory.getService(dto.getPaymentType());
        return service.update(paymentMethodId, userId, dto);
    }

    public List<PaymentMethodDto> get(Long userId) {
        PaymentService<PaymentMethodDto> serviceCard = paymentServiceFactory.getService(PaymentType.CREDIT_CARD);
        PaymentService<PaymentMethodDto> servicePaypal = paymentServiceFactory.getService(PaymentType.PAYPAL);
        List<PaymentMethodDto> paymentMethodsDto = new ArrayList<>(serviceCard.get(userId));
        paymentMethodsDto.addAll(servicePaypal.get(userId));
        return paymentMethodsDto;
    }
}