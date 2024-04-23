package com.griddynamics.gridhub.payment.repository;

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
        CreditCard newCard = new CreditCard(1L, 1L, "Credit Card", "Test", "1234567890123456", "12/24", "123");
        CreditCard savedCard = repository.save(newCard);
        assertEquals(newCard, savedCard);
        assertEquals(1, CreditCardRepository.getDb().size());
    }

    @Test
    public void testDeleteCard() {
        CreditCard card = new CreditCard(1L, 1L, "Credit Card", "Test", "1234567890123456", "12/24", "123");
        repository.save(card);
        repository.delete(1L);
        assertFalse(CreditCardRepository.getDb().containsKey(1L));
    }

    @Test
    public void testUpdateCard() {
        CreditCard card = new CreditCard(1L, 1L, "Credit Card", "Test", "1234567890123456", "12/24", "123");
        repository.save(card);
        card.setExpirationDate("12/25");  // Change expiry date
        CreditCard updatedCard = repository.update(card);
        assertEquals("12/25", updatedCard.getExpirationDate());
        assertEquals(card, updatedCard);
    }

    @Test
    public void testGetCardsByUserId() {
        repository.save(new CreditCard(1L, 1L, "Credit Card", "Test", "1234567890123456", "12/24", "123"));
        repository.save(new CreditCard(2L, 1L, "Credit Card", "Test", "1234567890127456", "12/24", "123"));
        repository.save(new CreditCard(3L, 1L, "Credit Card", "Test", "1234567890121456", "12/24", "123"));

        Optional<List<CreditCard>> result = repository.get(1L);
        assertTrue(result.isPresent());
        assertEquals(3, result.get().size());
    }

    @Test
    public void testGetNextId() {
        repository.save(new CreditCard(1L, 1L, "Credit Card", "Test", "1234567890123456", "12/24", "123"));
        assertEquals(Long.valueOf(2), CreditCardRepository.getNextId());
    }
}