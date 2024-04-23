package com.griddynamics.gridhub.payment.ui;

import com.griddynamics.gridhub.payment.controller.CreditCardController;
import com.griddynamics.gridhub.payment.dto.CreditCardDto;
import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class UserInterface {
  CreditCardController creditCardController;

  public void start() {
    System.out.println("Welcome to the payment system!");
    Scanner scanner = new Scanner(System.in);
    int option;
    do {
      System.out.println("Please select an option:");
      System.out.println("1. Add a credit card");
      System.out.println("2. Delete a credit card");
      System.out.println("3. Update a credit card");
      System.out.println("4. View all credit cards");
      System.out.println("5. Exit");
      option = scanner.nextInt();

      switch (option) {
        case 1:
          System.out.println("Adding a credit card...");
          System.out.println("Please enter the user ID:");
          Long userId = scanner.nextLong();
          System.out.println("Please enter the payment type:");
          String paymentType = scanner.next() + " " + scanner.next();
          System.out.println("Please enter card holder name:");
          String cardHolderName = scanner.next() + " " + scanner.next();
          System.out.println("Please enter the credit card number:");
          String creditCardNumber = scanner.next();
          System.out.println("Please enter the expiration date:");
          String expirationDate = scanner.next();
          System.out.println("Please enter the CVV:");
          String cvv = scanner.next();
          System.out.println(
              creditCardController.save(
                  userId,
                  CreditCardDto.builder()
                          .paymentType(paymentType)
                      .cardHolderName(cardHolderName)
                      .cardNumber(creditCardNumber)
                      .expirationDate(expirationDate)
                      .cvv(cvv)
                      .build()));
          break;
        case 2:
          System.out.println("Deleting a credit card...");
          System.out.println("Please enter the payment method ID:");
          Long paymentMethodId = scanner.nextLong();
          creditCardController.delete(paymentMethodId);
          break;
        case 3:
          System.out.println("Updating a credit card...");
          System.out.println("Please enter the payment method ID:");
          Long paymentId = scanner.nextLong();
          System.out.println("Please enter the user ID:");
          Long userId1 = scanner.nextLong();
          System.out.println("Please enter card holder name:");
          String cardHolderName1 = scanner.next();
          System.out.println("Please enter the credit card number:");
          String creditCardNumber1 = scanner.next();
          System.out.println("Please enter the expiration date:");
          String expirationDate1 = scanner.next();
          System.out.println("Please enter the CVV:");
          String cvv1 = scanner.next();
          System.out.println(
              creditCardController.update(
                  paymentId,
                  userId1,
                  CreditCardDto.builder()
                      .cardHolderName(cardHolderName1)
                      .cardNumber(creditCardNumber1)
                      .expirationDate(expirationDate1)
                      .cvv(cvv1)
                      .build()));
          break;
        case 4:
          System.out.println("Viewing all credit cards...");
          System.out.println("Please enter the user ID:");
          Long userId2 = scanner.nextLong();
          System.out.println(creditCardController.get(userId2));
          break;
        case 5:
          System.out.println("Exiting...");
          break;
        default:
          System.out.println("Invalid option. Please try again.");
      }
    } while (option != 5);
  }
}
