package com.griddynamics.gridhub.payment.util;

import com.griddynamics.gridhub.payment.dto.CreditCardDto;
import com.griddynamics.gridhub.payment.dto.PaypalDto;

public class ValidationUtil {

  private final static String CARD_NUMBER_REGEX = "^[0-9]{16}$";
  private final static String CARD_HOLDER_REGEX = "^[a-zA-Z ]+$";
  private final static String CVV_REGEX = "^[0-9]{3}$";
  private final static String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
  private final static String EXPIRATION_DATE_REGEX = "^(0[1-9]|1[0-2])/[0-9]{2}$";
  private boolean isValidCardNumber(String cardNumber) {
    return cardNumber != null && cardNumber.matches(CARD_NUMBER_REGEX);
  }

  private boolean isValidCardHolder(String cardHolder) {
    return cardHolder != null && cardHolder.matches(CARD_HOLDER_REGEX);
  }

  private boolean isValidExpirationDate(String expirationDate) {
    return expirationDate != null && expirationDate.matches(EXPIRATION_DATE_REGEX);
  }

  private boolean isValidCvv(String cvv) {
    return cvv != null && cvv.matches(CVV_REGEX);
  }

  public boolean validateCreditCard(CreditCardDto creditCardDto) {
    return isValidCardNumber(creditCardDto.getCardNumber())
        && isValidCardHolder(creditCardDto.getCardHolderName())
        && isValidExpirationDate(creditCardDto.getExpirationDate())
        && isValidCvv(creditCardDto.getCvv());
  }

  private boolean isValidEmail(String email) {
    return email != null && email.matches(EMAIL_REGEX);
  }

  public boolean validatePaypal(PaypalDto paypalDto) {
    return isValidEmail(paypalDto.getEmail());
  }
}
