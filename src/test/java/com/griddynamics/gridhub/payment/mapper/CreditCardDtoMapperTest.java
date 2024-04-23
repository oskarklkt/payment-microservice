package com.griddynamics.gridhub.payment.mapper;

import com.griddynamics.gridhub.payment.dto.CreditCardDto;
import com.griddynamics.gridhub.payment.model.CreditCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardDtoMapperTest {
  private final CreditCardDtoMapper mapper = new CreditCardDtoMapper();

  @Test
  public void testMapping() {
    // Create a CreditCard object
    CreditCard creditCard = CreditCard.builder()
            .id(1L)
            .userId(123L)
            .paymentType("Credit Card")
            .cardHolderName("John Doe")
            .cardNumber("1234567890123456")
            .expirationDate("12/24")
            .cvv("123")
            .build();

    // Map CreditCard to CreditCardDto
    CreditCardDto creditCardDto = mapper.apply(creditCard);

    // Verify mapping
    assertEquals(creditCard.getPaymentType(), creditCardDto.getPaymentType());
    assertEquals(creditCard.getCardHolderName(), creditCardDto.getCardHolderName());
    assertEquals(creditCard.getCardNumber(), creditCardDto.getCardNumber());
    assertEquals(creditCard.getExpirationDate(), creditCardDto.getExpirationDate());
    assertEquals(creditCard.getCvv(), creditCardDto.getCvv());
  }
}

