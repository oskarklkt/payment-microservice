package com.griddynamics.gridhub.payment;

import com.griddynamics.gridhub.payment.controller.CreditCardController;
import com.griddynamics.gridhub.payment.dto.CreditCardDto;
import com.griddynamics.gridhub.payment.mapper.CreditCardDtoMapper;
import com.griddynamics.gridhub.payment.mapper.CreditCardMapper;
import com.griddynamics.gridhub.payment.repository.CreditCardRepository;
import com.griddynamics.gridhub.payment.service.CreditCardService;
import com.griddynamics.gridhub.payment.util.ValidationUtil;
import lombok.Generated;

public class App {
  @Generated
  // added @Generated annotation to not count this method in test coverage
  public static void main(String[] args) {
    CreditCardRepository creditCardRepository = new CreditCardRepository();
    CreditCardMapper creditCardMapper = new CreditCardMapper();
    CreditCardDtoMapper creditCardDtoMapper = new CreditCardDtoMapper();
    CreditCardService creditCardService =
        new CreditCardService(
            creditCardRepository, creditCardDtoMapper, new ValidationUtil(), creditCardMapper);
    CreditCardController creditCardController = new CreditCardController(creditCardService);
    creditCardController.save(
        1L,
        CreditCardDto.builder()
            .paymentType("credit card")
            .cardNumber("1234567890123456")
            .cardHolderName("John Doe")
            .expirationDate("12/23")
            .cvv("123")
            .build());
    creditCardController.save(
        2L,
        CreditCardDto.builder()
            .paymentType("credit card")
            .cardNumber("1234567890123457")
            .cardHolderName("John Deere")
            .expirationDate("11/23")
            .cvv("123")
            .build());
    creditCardController.save(
        3L,
        CreditCardDto.builder()
            .paymentType("credit card")
            .cardNumber("1234567898123456")
            .cardHolderName("Joaquin Phoenix")
            .expirationDate("07/23")
            .cvv("123")
            .build());
    creditCardController.save(
        1L,
        CreditCardDto.builder()
            .paymentType("credit card")
            .cardNumber("1234567898123456")
            .cardHolderName("Adam")
            .expirationDate("07/23")
            .cvv("123")
            .build());
    System.out.println(creditCardController.get(1L));
    System.out.println(creditCardController.get(2L));
    System.out.println(creditCardController.get(3L));
    creditCardController.delete(2L);
    creditCardController.delete(3L);
    creditCardController.update(
        1L,
        1L,
        CreditCardDto.builder()
            .paymentType("credit card")
            .cardNumber("1234567890123456")
            .cardHolderName("Oskar Doe")
            .expirationDate("12/23")
            .cvv("123")
            .build());
    System.out.println(creditCardController.get(1L));
  }
}
