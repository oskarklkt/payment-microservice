package com.griddynamics.gridhub.payment.controller;

import com.griddynamics.gridhub.payment.dto.CreditCardDto;
import com.griddynamics.gridhub.payment.enumeration.PaymentType;
import com.griddynamics.gridhub.payment.service.CreditCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CreditCardControllerTest {

    CreditCardController creditCardController;
    CreditCardService creditCardService;
    CreditCardDto creditCardDto;


    @BeforeEach
    void setUp() {
        creditCardService = Mockito.mock(CreditCardService.class);
        creditCardController = new CreditCardController(creditCardService);
        creditCardDto = CreditCardDto.builder()
                .paymentType(PaymentType.CREDIT_CARD)
                .cardHolderName("John Doe")
                .cardNumber("1234567890123456")
                .expirationDate("12/24")
                .cvv("321")
                .build();
    }

    @Test
    void save() {
        //when
        creditCardController.save(1L, creditCardDto);
        //then
        Mockito.verify(creditCardService).save(1L, creditCardDto);
    }

    @Test
    void delete() {
        //when
        creditCardController.delete(1L);
        //then
        Mockito.verify(creditCardService).delete(1L);
    }

    @Test
    void update() {
        //when
        creditCardController.update(1L, 1L, creditCardDto);
        //then
        Mockito.verify(creditCardService).update(1L, 1L, creditCardDto);
    }

    @Test
    void get() {
        //when
        creditCardController.get(1L);
        //then
        Mockito.verify(creditCardService).get(1L);
    }
}