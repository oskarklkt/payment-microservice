package com.griddynamics.gridhub.payment.datasource;

import com.griddynamics.gridhub.payment.mapper.resultSetToModel.ResultSetCreditCardMapper;
import com.griddynamics.gridhub.payment.mapper.resultSetToModel.ResultSetPaypalMapper;
import com.griddynamics.gridhub.payment.model.CreditCard;
import com.griddynamics.gridhub.payment.model.Paypal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(QueryHandlerTest.TestConfiguration.class)
@ActiveProfiles("local")
@TestPropertySource(locations = "classpath:application-local.properties")
class QueryHandlerTest {

    @ComponentScan("com.griddynamics.gridhub.payment")
    @Configuration
    @Profile("local")
    static class TestConfiguration{}

    private static final String SAVE_CREDIT_CARD_QUERY = "INSERT INTO credit_cards (user_id, payment_type, card_holder_name, card_number, expiration_date, cvv) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_CREDIT_CARD_QUERY = "DELETE FROM credit_cards WHERE id = ?";
    private static final String UPDATE_CREDIT_CARD_QUERY = "UPDATE credit_cards SET card_holder_name = ?, card_number = ?, expiration_date = ?, cvv = ? WHERE id = ?";
    private static final String GET_CREDIT_CARDS_BY_USER_ID_QUERY = "SELECT * FROM credit_cards WHERE user_id = ?";
    private static final String GET_CREDIT_CARD_BY_ID_QUERY = "SELECT * FROM credit_cards WHERE id = ?";
    private static final String GET_NEXT_ID_QUERY = "SELECT MAX(id) FROM credit_cards";

    @Autowired
    QueryHandler<CreditCard> ccQueryHandler;

    @Autowired
    QueryHandler<Paypal> paypalQueryHandler;

    @Autowired
    ResultSetPaypalMapper resultSetPaypalMapper;

    @Autowired
    ResultSetCreditCardMapper resultSetCreditCardMapper;



    @Test
    @Sql(scripts = "classpath:test_db_drop_init.sql")
    void findOneCreditCard() {
        CreditCard creditCard = ccQueryHandler.findOne(GET_CREDIT_CARD_BY_ID_QUERY, resultSetCreditCardMapper, 1L);
        assertEquals("123", creditCard.getCvv());
    }

    @Test
    @Sql(scripts = "classpath:test_db_drop_init.sql")
    void findManyCreditCard() {
        List<CreditCard> creditCards = ccQueryHandler.findMany(GET_CREDIT_CARDS_BY_USER_ID_QUERY, resultSetCreditCardMapper, 1L);
        assertEquals(1, creditCards.size());
    }

    @Test
    @Sql(scripts = "classpath:test_db_drop_init.sql")
    void insertCreditCard() {
        int initialSize = ccQueryHandler.findMany("SELECT * FROM credit_cards", resultSetCreditCardMapper).size();
        ccQueryHandler.execute(SAVE_CREDIT_CARD_QUERY,
                1L, "CREDIT_CARD", "test", "123456789", "2023-01-01", "123");
        int finalSize = ccQueryHandler.findMany("SELECT * FROM credit_cards", resultSetCreditCardMapper).size();
        assertEquals(initialSize + 1, finalSize);
    }

    @Test
    @Sql(scripts = "classpath:test_db_drop_init.sql")
    void deleteCreditCard() {
        int initialSize = ccQueryHandler.findMany("SELECT * FROM credit_cards", resultSetCreditCardMapper).size();
        ccQueryHandler.execute(DELETE_CREDIT_CARD_QUERY, 3L);
        int finalSize = ccQueryHandler.findMany("SELECT * FROM credit_cards", resultSetCreditCardMapper).size();
        assertEquals(initialSize - 1, finalSize);
    }

    private static final String SAVE_PAYPAL_QUERY = "INSERT INTO paypal_accounts (id, user_id, payment_type, email) VALUES (?, ?, ?, ?)";
    private static final String DELETE_PAYPAL_QUERY = "DELETE FROM paypal_accounts WHERE id = ?";
    private static final String UPDATE_PAYPAL_QUERY = "UPDATE paypal_accounts SET email = ? WHERE id = ?";
    private static final String GET_PAYPAL_BY_ID_QUERY = "SELECT * FROM paypal_accounts WHERE id = ?";
    private static final String GET_PAYPAL_BY_USER_ID_QUERY = "SELECT * FROM paypal_accounts WHERE user_id = ?";
    private static final String GET_NEXT_PAYPAL_ID_QUERY = "SELECT MAX(id) FROM paypal_accounts";

    @Test
    @Sql(scripts = "classpath:test_db_drop_init.sql")
    void insertPaypal() {
        int initialSize = paypalQueryHandler.findMany("SELECT * FROM paypal_accounts", resultSetPaypalMapper).size();
        paypalQueryHandler.execute(SAVE_PAYPAL_QUERY, 3L, 2L, "PAYPAL", "ok@gmail.com");
        int finalSize = paypalQueryHandler.findMany("SELECT * FROM paypal_accounts", resultSetPaypalMapper).size();
        assertEquals(initialSize + 1, finalSize);
    }


    @Test
    @Sql(scripts = "classpath:test_db_drop_init.sql")
    void findManyPaypal() {
        List<Paypal> paypalList = paypalQueryHandler.findMany(GET_PAYPAL_BY_USER_ID_QUERY, resultSetPaypalMapper, 3L);
        paypalList.forEach(paypal -> System.out.println(paypal.getId()));
        assertEquals(1, paypalList.size());
    }


    @Test
    @Sql(scripts = "classpath:test_db_drop_init.sql")
    void deletePaypal() {
        int initialSize = paypalQueryHandler.findMany("SELECT * FROM paypal_accounts", resultSetPaypalMapper).size();
        paypalQueryHandler.execute(DELETE_PAYPAL_QUERY, 1L);
        int finalSize = paypalQueryHandler.findMany("SELECT * FROM paypal_accounts", resultSetPaypalMapper).size();
        assertEquals(initialSize - 1, finalSize);
    }



}