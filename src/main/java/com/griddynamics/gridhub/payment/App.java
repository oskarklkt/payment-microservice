package com.griddynamics.gridhub.payment;

import com.griddynamics.gridhub.payment.controller.PaymentController;
import com.griddynamics.gridhub.payment.database.DataSource;
import com.griddynamics.gridhub.payment.database.QueryHandler;
import com.griddynamics.gridhub.payment.dto.CreditCardDto;
import com.griddynamics.gridhub.payment.dto.PaypalDto;
import com.griddynamics.gridhub.payment.enumeration.PaymentType;
import com.griddynamics.gridhub.payment.mapper.dtoToModel.CreditCardMapper;
import com.griddynamics.gridhub.payment.mapper.dtoToModel.PaypalMapper;
import com.griddynamics.gridhub.payment.mapper.modelToDto.CreditCardDtoMapper;
import com.griddynamics.gridhub.payment.mapper.modelToDto.PaypalDtoMapper;
import com.griddynamics.gridhub.payment.mapper.resultSetToModel.ResultSetCreditCardMapper;
import com.griddynamics.gridhub.payment.mapper.resultSetToModel.ResultSetPaypalMapper;
import com.griddynamics.gridhub.payment.model.CreditCard;
import com.griddynamics.gridhub.payment.model.Paypal;
import com.griddynamics.gridhub.payment.repository.CreditCardRepository;
import com.griddynamics.gridhub.payment.repository.PaypalRepository;
import com.griddynamics.gridhub.payment.service.CreditCardService;
import com.griddynamics.gridhub.payment.service.PaypalService;
import com.griddynamics.gridhub.payment.service.ServiceFactory;
import com.griddynamics.gridhub.payment.util.ValidationUtil;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
  @Generated
  public static void main(String[] args) {
    Dotenv dotenv = Dotenv.load();
    DataSource.initialize(dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PASSWORD"));
    ResultSetCreditCardMapper resultSetCreditCardMapper = new ResultSetCreditCardMapper();
    QueryHandler<CreditCard> creditCardQueryHandler = new QueryHandler<>();
    CreditCardRepository creditCardRepository = new CreditCardRepository(resultSetCreditCardMapper, creditCardQueryHandler);
    CreditCardMapper creditCardMapper = new CreditCardMapper();
    CreditCardDtoMapper creditCardDtoMapper = new CreditCardDtoMapper();
    QueryHandler<Paypal> paypalQueryHandler = new QueryHandler<>();
    ResultSetPaypalMapper resultSetPaypalMapper = new ResultSetPaypalMapper();
    PaypalRepository paypalRepository = new PaypalRepository(paypalQueryHandler, resultSetPaypalMapper);
    PaymentController paymentController =
        new PaymentController(
            new ServiceFactory(
                new PaypalService(
                    paypalRepository,
                    new PaypalDtoMapper(),
                    new ValidationUtil(),
                    new PaypalMapper()),
                new CreditCardService(
                    creditCardRepository,
                    creditCardDtoMapper,
                    new ValidationUtil(),
                    creditCardMapper)));
    paymentController.save(
        1L,
        CreditCardDto.builder()
            .paymentType(PaymentType.CREDIT_CARD)
            .cardNumber("1234567890123456")
            .cardHolderName("John Doe")
            .expirationDate("12/23")
            .cvv("123")
            .build());
    paymentController.save(
        2L,
        CreditCardDto.builder()
            .paymentType(PaymentType.CREDIT_CARD)
            .cardNumber("1234567890123457")
            .cardHolderName("John Deere")
            .expirationDate("11/23")
            .cvv("123")
            .build());
    paymentController.save(
        3L,
        CreditCardDto.builder()
            .paymentType(PaymentType.CREDIT_CARD)
            .cardNumber("1234567898123456")
            .cardHolderName("Joaquin Phoenix")
            .expirationDate("07/23")
            .cvv("123")
            .build());
    paymentController.save(
        1L,
        CreditCardDto.builder()
            .paymentType(PaymentType.CREDIT_CARD)
            .cardNumber("1234567898123456")
            .cardHolderName("Adam")
            .expirationDate("07/23")
            .cvv("123")
            .build());
    log.info("{}", paymentController.get(1L));
    log.info("{}", paymentController.get(2L));
    log.info("{}", paymentController.get(3L));
    paymentController.delete(PaymentType.CREDIT_CARD,2L);
    paymentController.delete(PaymentType.CREDIT_CARD,3L);
    paymentController.update(
        1L,
        1L,
        CreditCardDto.builder()
            .paymentType(PaymentType.CREDIT_CARD)
            .cardNumber("1234567890123456")
            .cardHolderName("Oskar Doe")
            .expirationDate("12/23")
            .cvv("123")
            .build());
    log.info("{}", paymentController.get(1L));
    paymentController.save(1L , PaypalDto.builder()
            .paymentType(PaymentType.PAYPAL)
            .email("oskar@gmail.com")
            .build());
    log.info("{}", paymentController.get(1L));
  }
}
