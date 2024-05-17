package com.griddynamics.gridhub.payment.service;

import com.griddynamics.gridhub.payment.dto.CreditCardDto;
import com.griddynamics.gridhub.payment.exception.CreditCardException;
import com.griddynamics.gridhub.payment.exception.NoSuchElementException;
import com.griddynamics.gridhub.payment.mapper.dtoToModel.CreditCardMapper;
import com.griddynamics.gridhub.payment.mapper.modelToDto.CreditCardDtoMapper;
import com.griddynamics.gridhub.payment.model.CreditCard;
import com.griddynamics.gridhub.payment.repository.CreditCardRepository;
import com.griddynamics.gridhub.payment.service.implementation.CreditCardServiceImpl;
import com.griddynamics.gridhub.payment.util.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreditCardServiceTest {

    @Mock
    private CreditCardRepository creditCardRepository;
    @Mock
    private CreditCardDtoMapper creditCardDtoMapper;
    @Mock
    private ValidationUtil validationUtil;
    @Mock
    private CreditCardMapper creditCardMapper;
    @Mock
    private CreditCardDto creditCardDto;
    @Mock
    private CreditCard creditCard;

    private CreditCardServiceImpl creditCardService;

    @BeforeEach
    public void setUp() {
        creditCardService = new CreditCardServiceImpl(creditCardRepository, creditCardDtoMapper, validationUtil, creditCardMapper);
        lenient().when(creditCardMapper.apply(anyLong(), anyLong(), any())).thenReturn(creditCard);
        lenient().when(creditCardDtoMapper.apply(any())).thenReturn(creditCardDto);
    }

    @Test
    public void testSave() {
        when(validationUtil.validateCreditCard(creditCardDto)).thenReturn(true);
        CreditCardDto result = creditCardService.save(1L, creditCardDto);

        verify(creditCardRepository, times(1)).save(creditCard);
        assertEquals(creditCardDto, result);
    }

    @Test
    public void testSaveInvalidCreditCard() {
        when(validationUtil.validateCreditCard(creditCardDto)).thenReturn(false);

        assertThrows(CreditCardException.class, () -> creditCardService.save(1L, creditCardDto));
    }

    @Test
    public void testDelete() {
        when(creditCardRepository.isContains(1L)).thenReturn(false);

        creditCardService.delete(1L);

        verify(creditCardRepository, times(1)).delete(1L);
    }

    @Test
    public void testDeleteNoSuchElement() {
        when(creditCardRepository.isContains(1L)).thenReturn(true);

        assertThrows(NoSuchElementException.class, () -> creditCardService.delete(1L));
    }

    @Test
    public void testUpdate() {
        when(validationUtil.validateCreditCard(creditCardDto)).thenReturn(true);
        when(creditCardRepository.isContains(1L)).thenReturn(false);

        CreditCardDto result = creditCardService.update(1L, 1L, creditCardDto);

        verify(creditCardRepository, times(1)).update(creditCard);
        assertEquals(creditCardDto, result);
    }

    @Test
    public void testUpdateInvalidCreditCard() {
        when(validationUtil.validateCreditCard(creditCardDto)).thenReturn(false);

        assertThrows(CreditCardException.class, () -> creditCardService.update(1L, 1L, creditCardDto));
    }

    @Test
    public void testUpdateNoSuchElement() {
        when(creditCardRepository.isContains(1L)).thenReturn(true);

        assertThrows(NoSuchElementException.class, () -> creditCardService.update(1L, 1L, creditCardDto));
    }

    @Test
    public void testGet() {
        List<CreditCard> creditCards = Collections.singletonList(creditCard);
        when(creditCardRepository.get(1L)).thenReturn(creditCards);

        List<CreditCardDto> result = creditCardService.get(1L);

        verify(creditCardRepository, times(1)).get(1L);
        assertEquals(1, result.size());
        assertEquals(creditCardDto, result.get(0));
    }
}