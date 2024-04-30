package com.griddynamics.gridhub.payment.service;

import com.griddynamics.gridhub.payment.dto.PaypalDto;
import com.griddynamics.gridhub.payment.enumeration.PaymentType;
import com.griddynamics.gridhub.payment.exception.NoSuchElementException;
import com.griddynamics.gridhub.payment.exception.PaypalException;
import com.griddynamics.gridhub.payment.mapper.PaypalDtoMapper;
import com.griddynamics.gridhub.payment.mapper.PaypalMapper;
import com.griddynamics.gridhub.payment.repository.PaypalRepository;
import com.griddynamics.gridhub.payment.util.ValidationUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaypalServiceTest {

    ValidationUtil validationUtil;
    PaypalService service;
    PaypalRepository repository;
    PaypalDtoMapper paypalDtoMapper;
    PaypalMapper paypalMapper;
    PaypalDto paypalDto;

    @BeforeEach
    void setUp() {
        repository = new PaypalRepository();
        paypalDtoMapper = new PaypalDtoMapper();
        paypalMapper = new PaypalMapper();
        validationUtil = new ValidationUtil();
        service = new PaypalService(repository, paypalDtoMapper, validationUtil, paypalMapper);
        paypalDto = PaypalDto.builder()
                .paymentType(PaymentType.PAYPAL)
                .email("oskar@gmail.com")
                .build();
    }

    @AfterEach
    void tearDown() {
        PaypalRepository.getDb().clear();
    }

    @Test
    void save() {
        //when
        service.save(1L, paypalDto);
        //then
        assertEquals(1, PaypalRepository.getDb().size());
        assertEquals(1L, PaypalRepository.getDb().get(1L).getUserId());
    }

    @Test
    void saveThrows() {
        //given
        paypalDto.setEmail("");
        //then
        assertThrows(PaypalException.class, () -> service.save(1L, paypalDto));
    }

    @Test
    void delete() {
        //given
        service.save(1L, paypalDto);
        //when
        service.delete(1L);
        //then
        assertEquals(0, PaypalRepository.getDb().size());
    }

    @Test
    void deleteThrows() {
        //then
        assertThrows(NoSuchElementException.class, () -> service.delete(1L));
    }



    @Test
    void update() {
        //given
        service.save(1L, paypalDto);
        PaypalDto updatedPaypalDto = PaypalDto.builder()
                .paymentType(PaymentType.PAYPAL)
                .email("new@gmail.com")
                .build();
        //when
        service.update(1L, 1L, updatedPaypalDto);
        // then
        assertEquals(updatedPaypalDto, paypalDtoMapper.apply(PaypalRepository.getDb().get(1L)));
    }

    @Test
    void updateThrows() {
        assertThrows(NoSuchElementException.class, () -> service.update(1L, 1L, paypalDto));
    }

    @Test
    void get() {
        //given
        service.save(1L, paypalDto);
        //when
        var paypalDto1 = service.get(1L);
        //then
        assertEquals(paypalDto, paypalDto1.get(0));
    }
}