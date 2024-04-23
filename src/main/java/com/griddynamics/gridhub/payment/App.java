package com.griddynamics.gridhub.payment;

import com.griddynamics.gridhub.payment.controller.CreditCardController;
import com.griddynamics.gridhub.payment.mapper.CreditCardDtoMapper;
import com.griddynamics.gridhub.payment.mapper.CreditCardMapper;
import com.griddynamics.gridhub.payment.repository.CreditCardRepository;
import com.griddynamics.gridhub.payment.service.CreditCardService;
import com.griddynamics.gridhub.payment.ui.UserInterface;
import com.griddynamics.gridhub.payment.util.ValidationUtil;

public class App {
  public static void main(String[] args) {
    CreditCardRepository creditCardRepository = new CreditCardRepository();
    CreditCardMapper creditCardMapper = new CreditCardMapper();
    CreditCardDtoMapper creditCardDtoMapper = new CreditCardDtoMapper();
    CreditCardService creditCardService =
        new CreditCardService(
            creditCardRepository, creditCardDtoMapper, new ValidationUtil(), creditCardMapper);
    CreditCardController creditCardController = new CreditCardController(creditCardService);
    UserInterface userInterface = new UserInterface(creditCardController);
    userInterface.start();
  }
}
