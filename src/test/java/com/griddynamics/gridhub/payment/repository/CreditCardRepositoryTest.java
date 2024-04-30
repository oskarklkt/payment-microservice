package com.griddynamics.gridhub.payment.repository;

import com.griddynamics.gridhub.payment.enumeration.PaymentType;
import com.griddynamics.gridhub.payment.model.CreditCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardRepositoryTest {

    private CreditCardRepository repository;

    @BeforeEach
    public void setup() {
        repository = new CreditCardRepository();
        CreditCardRepository.getDb().clear();
    }

    @Test
    public void testSaveNewCard() {
        CreditCard newCard = CreditCard.builder()
                .id(1L)
                .userId(1L)
                .paymentType(PaymentType.CREDIT_CARD)
                .cardHolderName("Test")
                .cardNumber("1234567890123456")
                .expirationDate("12/24")
                .cvv("123")
                .build();
        CreditCard savedCard = repository.save(newCard);
        assertEquals(newCard, savedCard);
        assertEquals(1, CreditCardRepository.getDb().size());
    }

    @Test
    public void testDeleteCard() {
        CreditCard card = CreditCard.builder()
                .id(1L)
                .userId(1L)
                .paymentType(PaymentType.CREDIT_CARD)
                .cardHolderName("Test")
                .cardNumber("1234567890123456")
                .expirationDate("12/24")
                .cvv("123")
                .build();
        repository.save(card);
        repository.delete(1L);
        assertFalse(CreditCardRepository.getDb().containsKey(1L));
    }

    @Test
    public void testUpdateCard() {
        CreditCard card = CreditCard.builder()
                .id(1L)
                .userId(1L)
                .paymentType(PaymentType.CREDIT_CARD)
                .cardHolderName("Test")
                .cardNumber("1234567890123456")
                .expirationDate("12/24")
                .cvv("123")
                .build();
        repository.save(card);
        card.setExpirationDate("12/25");  // Change expiry date
        CreditCard updatedCard = repository.update(card);
        assertEquals("12/25", updatedCard.getExpirationDate());
        assertEquals(card, updatedCard);
    }

    @Test
    public void testGetCardsByUserId() {
        repository.save(CreditCard.builder()
                .id(1L)
                .userId(1L)
                .paymentType(PaymentType.CREDIT_CARD)
                .cardHolderName("Test")
                .cardNumber("1234567890123456")
                .expirationDate("12/24")
                .cvv("123")
                .build());
        repository.save(CreditCard.builder()
                .id(2L)
                .userId(1L)
                .paymentType(PaymentType.CREDIT_CARD)
                .cardHolderName("Test")
                .cardNumber("1234567890123456")
                .expirationDate("12/24")
                .cvv("123")
                .build());
        repository.save(CreditCard.builder()
                .id(3L)
                .userId(1L)
                .paymentType(PaymentType.CREDIT_CARD)
                .cardHolderName("Test")
                .cardNumber("1234567890123456")
                .expirationDate("12/24")
                .cvv("123")
                .build());

        Optional<List<CreditCard>> result = repository.get(1L);
        assertTrue(result.isPresent());
        assertEquals(3, result.get().size());
    }

    @Test
    public void testGetNextId() {
        repository.save(CreditCard.builder()
                .id(1L)
                .userId(1L)
                .paymentType(PaymentType.CREDIT_CARD)
                .cardHolderName("Test")
                .cardNumber("1234567890123456")
                .expirationDate("12/24")
                .cvv("123")
                .build());
        assertEquals(Long.valueOf(2), CreditCardRepository.getNextId());
    }
}