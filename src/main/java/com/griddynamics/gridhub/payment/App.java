package com.griddynamics.gridhub.payment;

import com.griddynamics.gridhub.payment.controller.PaymentController;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class App {
  @Generated
  public static void main(String[] args) {
    Dotenv dotenv = Dotenv.load();
    ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
    PaymentController paymentController = context.getBean(PaymentController.class);
    System.out.println(paymentController.get(2L));
  }
}
