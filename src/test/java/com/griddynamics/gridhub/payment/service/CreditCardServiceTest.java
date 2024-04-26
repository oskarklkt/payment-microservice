package com.griddynamics.gridhub.payment.service;

import com.griddynamics.gridhub.payment.dto.CreditCardDto;
import com.griddynamics.gridhub.payment.exception.CreditCardException;
import com.griddynamics.gridhub.payment.exception.NoSuchElementException;
import com.griddynamics.gridhub.payment.mapper.CreditCardDtoMapper;
import com.griddynamics.gridhub.payment.mapper.CreditCardMapper;
import com.griddynamics.gridhub.payment.repository.CreditCardRepository;
import com.griddynamics.gridhub.payment.util.ValidationUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CreditCardServiceTest {

    ValidationUtil validationUtil;
    CreditCardService creditCardService;
    CreditCardRepository creditCardRepository;
    CreditCardDtoMapper creditCardDtoMapper;
    CreditCardMapper creditCardMapper;
    CreditCardDto creditCardDto;

    @BeforeEach
    void setUp() {
        creditCardRepository = new CreditCardRepository();
        creditCardDtoMapper = new CreditCardDtoMapper();
        creditCardMapper = new CreditCardMapper();
        validationUtil = new ValidationUtil();
        creditCardService = new CreditCardService(creditCardRepository, creditCardDtoMapper, validationUtil, creditCardMapper);
        creditCardDto = CreditCardDto.builder()
                .paymentType("Credit Card")
                .cardHolderName("John Doe")
                .cardNumber("1234567890123456")
                .expirationDate("12/24")
                .cvv("321")
                .build();
    }

    @AfterEach
    void tearDown() {
        CreditCardRepository.getDb().clear();
    }

    @Test
    void save() {
        //when
        creditCardService.save(1L, creditCardDto);
        //then
        assertEquals(1, CreditCardRepository.getDb().size());
        assertEquals(1L, CreditCardRepository.getDb().get(1L).getUserId());
    }

    @Test
    void saveThrows() {
        //given
        creditCardDto.setCardNumber("wrong");
        //then
        assertThrows(CreditCardException.class, () -> creditCardService.save(1L, creditCardDto));
    }

    @Test
    void delete() {
        //given
        creditCardService.save(1L, creditCardDto);
        //when
        creditCardService.delete(1L);
        //then
        assertEquals(0, CreditCardRepository.getDb().size());
    }

    @Test
    void deleteThrows() {
        //then
        assertThrows(NoSuchElementException.class, () -> creditCardService.delete(1L));
    }



    @Test
    void update() {
        //given
        creditCardService.save(1L, creditCardDto);
        CreditCardDto updatedCreditCardDto = CreditCardDto.builder()
                .paymentType("Credit Card")
                .cardHolderName("John Doe")
                .cardNumber("1234567890123456")
                .expirationDate("12/24")
                .cvv("321")
                .build();
        //when
        creditCardService.update(1L, 1L, updatedCreditCardDto);
    // then
    CreditCardDto creditCardDto = creditCardDtoMapper.apply(CreditCardRepository.getDb().get(1L));

        assertEquals(updatedCreditCardDto, creditCardDtoMapper.apply(CreditCardRepository.getDb().get(1L)));
    }

    @Test
    void updateThrows() {
        assertThrows(NoSuchElementException.class, () -> creditCardService.update(1L, 1L, creditCardDto));
    }

    @Test
    void get() {
        //given
        creditCardService.save(1L, creditCardDto);
        //when
        var creditCardDto1 = creditCardService.get(1L);
        //then
        assertEquals(creditCardDto, creditCardDto1.get(0));
    }
}