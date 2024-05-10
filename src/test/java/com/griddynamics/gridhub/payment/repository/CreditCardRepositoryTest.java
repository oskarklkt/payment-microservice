package com.griddynamics.gridhub.payment.repository;

import com.griddynamics.gridhub.payment.database.QueryHandler;
import com.griddynamics.gridhub.payment.enumeration.PaymentType;
import com.griddynamics.gridhub.payment.mapper.resultSetToModel.ResultSetCreditCardMapper;
import com.griddynamics.gridhub.payment.model.CreditCard;
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
public class CreditCardRepositoryTest {

    @Mock
    private QueryHandler<CreditCard> queryHandler;

    @Mock
    private ResultSetCreditCardMapper resultSetCreditCardMapper;
    @Mock
    private CreditCard creditCard;

    private CreditCardRepository creditCardRepository;

    @BeforeEach
    public void setUp() {
        creditCardRepository = new CreditCardRepository(resultSetCreditCardMapper, queryHandler);
        creditCard = CreditCard.builder()
                .id(1L)
                .userId(1L)
                .paymentType(PaymentType.CREDIT_CARD)
                .cardHolderName("John Doe")
                .cardNumber("1234567890123456")
                .expirationDate("12/2022")
                .cvv("123")
                .build();
    }

    @Test
    public void testSave() {
        creditCardRepository.save(creditCard);

        verify(queryHandler, times(1)).execute(anyString(), anyLong(), any(), anyString(), anyString(), anyString(), anyString());
    }

    @Test
    public void testUpdate() {
        creditCardRepository.update(creditCard);

        verify(queryHandler, times(1)).execute(anyString(), anyString(), anyString(), anyString(), anyString(), anyLong());
    }

    @Test
    public void testDelete() {
        creditCardRepository.delete(1L);

        verify(queryHandler, times(1)).execute(anyString(), anyLong());
    }

    @Test
    public void testGet() {
        List<CreditCard> expected = Collections.singletonList(creditCard);
        when(queryHandler.findMany(anyString(), any(), anyLong())).thenReturn(expected);

        List<CreditCard> result = creditCardRepository.get(1L);

        verify(queryHandler, times(1)).findMany(anyString(), any(), anyLong());
        assertEquals(expected, result);
    }

    @Test
    public void testIsContains() {
        when(queryHandler.findOne(anyString(), any(), anyLong())).thenReturn(creditCard);

        boolean result = creditCardRepository.isContains(1L);

        verify(queryHandler, times(1)).findOne(anyString(), any(), anyLong());
        assertTrue(result);
    }

    @Test
    public void testGetNextId() {
        when(queryHandler.findMany(anyString(), any())).thenReturn(Collections.singletonList(creditCard));

        Long result = creditCardRepository.getNextId();

        verify(queryHandler, times(1)).findMany(anyString(), any());
        assertEquals(2L, result);
    }
}