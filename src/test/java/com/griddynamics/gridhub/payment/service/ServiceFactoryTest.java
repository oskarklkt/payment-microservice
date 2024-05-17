package com.griddynamics.gridhub.payment.service;

import com.griddynamics.gridhub.payment.dto.CreditCardDto;
import com.griddynamics.gridhub.payment.dto.PaypalDto;
import com.griddynamics.gridhub.payment.enumeration.PaymentType;
import com.griddynamics.gridhub.payment.service.implementation.CreditCardServiceImpl;
import com.griddynamics.gridhub.payment.service.implementation.PaypalServiceImpl;
import com.griddynamics.gridhub.payment.service.implementation.ServiceFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class ServiceFactoryTest {

    @Mock
    private PaypalServiceImpl paypalService;
    @Mock
    private CreditCardServiceImpl creditCardService;

    private ServiceFactoryImpl serviceFactory;

    @BeforeEach
    public void setUp() {
        serviceFactory = new ServiceFactoryImpl(paypalService, creditCardService);
    }

    @Test
    public void testGetPaypalService() {
        PaymentService<PaypalDto> service = serviceFactory.getService(PaymentType.PAYPAL);
        assertInstanceOf(PaypalServiceImpl.class, service, "Service should be an instance of PaypalService");
    }

    @Test
    public void testGetCreditCardService() {
        PaymentService<CreditCardDto> service = serviceFactory.getService(PaymentType.CREDIT_CARD);
        assertInstanceOf(CreditCardServiceImpl.class, service, "Service should be an instance of CreditCardService");
    }
}
