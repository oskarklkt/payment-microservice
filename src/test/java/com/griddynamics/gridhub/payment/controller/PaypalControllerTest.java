package com.griddynamics.gridhub.payment.controller;

import com.griddynamics.gridhub.payment.dto.PaypalDto;
import com.griddynamics.gridhub.payment.enumeration.PaymentType;
import com.griddynamics.gridhub.payment.service.PaypalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class PaypalControllerTest {
    PaypalController paypalController;
    PaypalService paypalService;
    PaypalDto paypalDto;


    @BeforeEach
    void setUp() {
        paypalService = Mockito.mock(PaypalService.class);
        paypalController = new PaypalController(paypalService);
        paypalDto = PaypalDto.builder()
                .paymentType(PaymentType.PAYPAL)
                .email("oskar@gmail.com")
                .build();
    }

    @Test
    void save() {
        //when
        paypalController.save(1L, paypalDto);
        //then
        Mockito.verify(paypalService).save(1L, paypalDto);
    }

    @Test
    void delete() {
        //when
        paypalController.delete(1L);
        //then
        Mockito.verify(paypalService).delete(1L);
    }

    @Test
    void update() {
        //when
        paypalController.update(1L, 1L, paypalDto);
        //then
        Mockito.verify(paypalService).update(1L, 1L, paypalDto);
    }

    @Test
    void get() {
        //when
        paypalController.get(1L);
        //then
        Mockito.verify(paypalService).get(1L);
    }
}
