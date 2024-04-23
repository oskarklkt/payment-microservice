package com.griddynamics.gridhub.payment.util;

import com.griddynamics.gridhub.payment.dto.CreditCardDto;
import com.griddynamics.gridhub.payment.model.CreditCard;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilTest {
    private final ValidationUtil validator = new ValidationUtil();

    @Test
    public void testValidateCreditCard() {
        CreditCardDto validCard = new CreditCardDto("Credit Card", "John Doe", "1234567890123456", "12/24", "123");
        CreditCardDto invalidCard = new CreditCardDto("Credit Card", "John Doe", "1234", "12/24", "123");

        assertTrue(validator.validateCreditCard(validCard));
        assertFalse(validator.validateCreditCard(invalidCard));
    }

    @Test
    public void testIsElementInDatabase() {
        Map<Long, CreditCard> database = new HashMap<>();
        database.put(1L, new CreditCard(1L, 123L, "Credit Card", "John Doe", "1234567890123456", "12/24", "123"));

        assertTrue(validator.isElementInDatabase(1L, database));
        assertFalse(validator.isElementInDatabase(2L, database));
    }
}
