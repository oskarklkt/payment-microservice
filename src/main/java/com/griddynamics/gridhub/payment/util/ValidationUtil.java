package com.griddynamics.gridhub.payment.util;

import com.griddynamics.gridhub.payment.dto.CreditCardDto;
import com.griddynamics.gridhub.payment.dto.PaypalDto;
import com.griddynamics.gridhub.payment.model.PaymentMethod;

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

  private boolean isValidCvv(String cvv) {
    return cvv != null && cvv.matches("^[0-9]{3}$");
  }

  public boolean validateCreditCard(CreditCardDto creditCardDto) {
    return isValidCardNumber(creditCardDto.getCardNumber())
        && isValidCardHolder(creditCardDto.getCardHolderName())
        && isValidExpirationDate(creditCardDto.getExpirationDate())
        && isValidCvv(creditCardDto.getCvv());
  }

  public boolean isElementInDatabase(Long id, Map<Long, ? extends PaymentMethod> database) {
    return database.containsKey(id);
  }

  private boolean isValidEmail(String email) {
    return email != null && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
  }

  public boolean validatePaypal(PaypalDto paypalDto) {
    return isValidEmail(paypalDto.getEmail());
  }
}
