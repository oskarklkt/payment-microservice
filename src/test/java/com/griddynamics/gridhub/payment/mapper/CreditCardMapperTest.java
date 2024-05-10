package com.griddynamics.gridhub.payment.mapper;

import com.griddynamics.gridhub.payment.dto.CreditCardDto;
import com.griddynamics.gridhub.payment.enumeration.PaymentType;
import com.griddynamics.gridhub.payment.mapper.dtoToModel.CreditCardMapper;
import com.griddynamics.gridhub.payment.model.CreditCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardMapperTest {

    @Test
    public void testDtoToCreditCardMapping() {

        CreditCardDto creditCardDto = CreditCardDto.builder()
                .paymentType(PaymentType.CREDIT_CARD)
                .cardHolderName("Jane Doe")
                .cardNumber("9876543210987654")
                .expirationDate("01/26")
                .cvv("321")
                .build();


        Long id = 10L;
        Long userId = 100L;

        CreditCardMapper mapper = new CreditCardMapper();

        CreditCard creditCard = mapper.apply(id, userId, creditCardDto);

        assertEquals(id, creditCard.getId());
        assertEquals(userId, creditCard.getUserId());
        assertEquals(creditCardDto.getPaymentType(), creditCard.getPaymentType());
        assertEquals(creditCardDto.getCardHolderName(), creditCard.getCardHolderName());
        assertEquals(creditCardDto.getCardNumber(), creditCard.getCardNumber());
        assertEquals(creditCardDto.getExpirationDate(), creditCard.getExpirationDate());
        assertEquals(creditCardDto.getCvv(), creditCard.getCvv());
    }
}
