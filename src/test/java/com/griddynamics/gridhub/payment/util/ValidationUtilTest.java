package com.griddynamics.gridhub.payment.util;

import com.griddynamics.gridhub.payment.dto.CreditCardDto;
import com.griddynamics.gridhub.payment.enumeration.PaymentType;
import com.griddynamics.gridhub.payment.model.CreditCard;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilTest {
    private final ValidationUtil validator = new ValidationUtil();

    @Test
    public void testValidateCreditCard() {
        CreditCardDto validCard = CreditCardDto.builder()
                .paymentType(PaymentType.CREDIT_CARD)
                .cardHolderName("John Doe")
                .cardNumber("1234567890123456")
                .expirationDate("12/24")
                .cvv("123")
                .build();
        CreditCardDto invalidCard = CreditCardDto.builder()
                .paymentType(PaymentType.CREDIT_CARD)
                .cardHolderName("John Doe")
                .cardNumber("1234")
                .expirationDate("12/24")
                .cvv("123")
                .build();

        assertTrue(validator.validateCreditCard(validCard));
        assertFalse(validator.validateCreditCard(invalidCard));
    }

    @Test
    public void testIsElementInDatabase() {
        Map<Long, CreditCard> database = new HashMap<>();
        database.put(1L, CreditCard.builder()
                .id(1L)
                .userId(123L)
                .paymentType(PaymentType.CREDIT_CARD)
                .cardHolderName("John Doe")
                .cardNumber("1234567890123456")
                .expirationDate("12/24")
                .cvv("123")
                .build());
        assertTrue(database.containsKey(1L));
        assertFalse(database.containsKey(2L));
    }
}
