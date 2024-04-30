package com.griddynamics.gridhub.payment.repository;

import com.griddynamics.gridhub.payment.enumeration.PaymentType;
import com.griddynamics.gridhub.payment.model.Paypal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
class PaypalRepositoryTest {

    PaypalRepository repository;
    @BeforeEach
    public void setup() {
        repository = new PaypalRepository();
        PaypalRepository.getDb().clear();
    }

    @AfterEach
    public void tearDown() {
        PaypalRepository.getDb().clear();
    }

    @Test
    void saveWhenAbsent() {
        //given
        Paypal paypal = Paypal.builder()
                .id(1L)
                .userId(1L)
                .paymentType(PaymentType.PAYPAL)
                .email("oskar@gmail.com")
                .build();
        //when
        Paypal savedPaypal = repository.save(paypal);
        //then
        assertEquals(savedPaypal, paypal);
        assertEquals(1, PaypalRepository.getDb().size());
    }

    @Test
    void shouldNotSaveWhenNotAbsent() {
        //given
        Paypal paypal = Paypal.builder()
                .id(1L)
                .userId(1L)
                .paymentType(PaymentType.PAYPAL)
                .email("oskar@gmail.com")
                .build();
        Paypal paypal2 = Paypal.builder()
                .id(1L)
                .userId(1L)
                .paymentType(PaymentType.PAYPAL)
                .email("oskar@gmail.com")
                .build();
        //when
        repository.save(paypal);
        repository.save(paypal2);
        //then
        assertEquals(1, PaypalRepository.getDb().size());
    }

    @Test
    void delete() {
        //given
        Paypal paypal = Paypal.builder()
                .id(1L)
                .userId(1L)
                .paymentType(PaymentType.PAYPAL)
                .email("oskar@gmail.com")
                .build();
        //when
        repository.save(paypal);
        repository.delete(1L);
        //then
        assertFalse(PaypalRepository.getDb().containsKey(1L));
    }

    @Test
    void update() {
        //given
        Paypal paypal = Paypal.builder()
                .id(1L)
                .userId(1L)
                .paymentType(PaymentType.PAYPAL)
                .email("oskar@gmail.com")
                .build();
        Paypal paypal2 = Paypal.builder()
                .id(1L)
                .userId(1L)
                .paymentType(PaymentType.PAYPAL)
                .email("oskarklkt@gmail.com")
                .build();
        //when
        repository.save(paypal);
        repository.update(paypal2);
        //then
        assertEquals(paypal2, PaypalRepository.getDb().get(1L));
    }

    @Test
    void get() {
        //given
        Paypal paypal = Paypal.builder()
                .id(1L)
                .userId(1L)
                .paymentType(PaymentType.PAYPAL)
                .email("oskar@gmail.com")
                .build();
        //when
        repository.save(paypal);
    // then
    assertEquals(Optional.of(List.of(paypal)), repository.get(1L));
      }
}