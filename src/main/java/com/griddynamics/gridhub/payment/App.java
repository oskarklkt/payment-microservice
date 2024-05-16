package com.griddynamics.gridhub.payment;

import com.griddynamics.gridhub.payment.database.DataSource;
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
    //ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
    DataSource.initialize(dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PASSWORD"));

  }
}
