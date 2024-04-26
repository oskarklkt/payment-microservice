package com.griddynamics.gridhub.payment.util;

import com.griddynamics.gridhub.payment.dto.CreditCardDto;
import com.griddynamics.gridhub.payment.model.CreditCard;

import java.util.Map;

public class ValidationUtil {
  private boolean isValidCardNumber(String cardNumber) {
    return cardNumber != null && cardNumber.matches("^[0-9]{16}$");
  }

  private boolean isValidCardHolder(String cardHolder) {
    return cardHolder != null && cardHolder.matches("^[a-zA-Z ]+$");
  }

  private boolean isValidExpirationDate(String expirationDate) {
    return expirationDate != null && expirationDate.matches("^(0[1-9]|1[0-2])/[0-9]{2}$");
  }

  private boolean isValidType(String type) {
    return type != null
        && (type.equalsIgnoreCase("credit card") || type.equalsIgnoreCase("paypal"));
  }

  private boolean isValidCvv(String cvv) {
    return cvv != null && cvv.matches("^[0-9]{3}$");
  }

  public boolean validateCreditCard(CreditCardDto creditCardDto) {
    return isValidCardNumber(creditCardDto.getCardNumber())
        && isValidCardHolder(creditCardDto.getCardHolderName())
        && isValidExpirationDate(creditCardDto.getExpirationDate())
        && isValidCvv(creditCardDto.getCvv())
        && isValidType(creditCardDto.getPaymentType());
  }

  public boolean isElementInDatabase(Long id, Map<Long, CreditCard> database) {
    return database.containsKey(id);
  }
}
