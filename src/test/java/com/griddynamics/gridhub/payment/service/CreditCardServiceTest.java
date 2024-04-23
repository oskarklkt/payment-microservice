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
        creditCardDto = new CreditCardDto("Credit Card", "John Doe", "1234567890123456", "12/24", "123");
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
        CreditCardDto updatedCreditCardDto = new CreditCardDto("Credit Card", "John Doe", "1234567890123456", "12/24", "321");
        //when
        creditCardService.update(1L, 1L, updatedCreditCardDto);
        //then
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