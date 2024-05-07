package com.griddynamics.gridhub.payment.service;

import com.griddynamics.gridhub.payment.dto.CreditCardDto;
import com.griddynamics.gridhub.payment.dto.PaypalDto;
import com.griddynamics.gridhub.payment.enumeration.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class ServiceFactoryTest {

    @Mock
    private PaypalService paypalService;
    @Mock
    private CreditCardService creditCardService;

    private ServiceFactory serviceFactory;

    @BeforeEach
    public void setUp() {
        serviceFactory = new ServiceFactory(paypalService, creditCardService);
    }

    @Test
    public void testGetPaypalService() {
        PaymentService<PaypalDto> service = serviceFactory.getService(PaymentType.PAYPAL);
        assertInstanceOf(PaypalService.class, service, "Service should be an instance of PaypalService");
    }

    @Test
    public void testGetCreditCardService() {
        PaymentService<CreditCardDto> service = serviceFactory.getService(PaymentType.CREDIT_CARD);
        assertInstanceOf(CreditCardService.class, service, "Service should be an instance of CreditCardService");
    }
}
