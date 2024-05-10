package com.griddynamics.gridhub.payment.database;

import com.griddynamics.gridhub.payment.mapper.resultSetToModel.ResultSetCreditCardMapper;
import com.griddynamics.gridhub.payment.mapper.resultSetToModel.ResultSetPaypalMapper;
import com.griddynamics.gridhub.payment.model.CreditCard;
import com.griddynamics.gridhub.payment.model.Paypal;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QueryHandlerTest {


    private static final String SAVE_CREDIT_CARD_QUERY = "INSERT INTO credit_cards (user_id, payment_type, card_holder_name, card_number, expiration_date, cvv) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_CREDIT_CARD_QUERY = "DELETE FROM credit_cards WHERE id = ?";
    private static final String UPDATE_CREDIT_CARD_QUERY = "UPDATE credit_cards SET card_holder_name = ?, card_number = ?, expiration_date = ?, cvv = ? WHERE id = ?";
    private static final String GET_CREDIT_CARDS_BY_USER_ID_QUERY = "SELECT * FROM credit_cards WHERE user_id = ?";
    private static final String GET_CREDIT_CARD_BY_ID_QUERY = "SELECT * FROM credit_cards WHERE id = ?";
    private static final String GET_NEXT_ID_QUERY = "SELECT MAX(id) FROM credit_cards";

    QueryHandler<CreditCard> ccQueryHandler = new QueryHandler<>();
    QueryHandler<Paypal> paypalQueryHandler = new QueryHandler<>();
    ResultSetPaypalMapper resultSetPaypalMapper = new ResultSetPaypalMapper();
    ResultSetCreditCardMapper resultSetCreditCardMapper = new ResultSetCreditCardMapper();

    @BeforeAll
    static void setUp() {
        Dotenv dotenv = Dotenv.load();
        //setting testing database
        DataSource.initialize(dotenv.get("TEST_DB_URL"),
                dotenv.get("DB_USER"),
                dotenv.get("DB_PASSWORD"));
    }

    @AfterAll
    static void tearDown() {
        Dotenv dotenv = Dotenv.load();
        //setting back to the original database
        DataSource.initialize(dotenv.get("DB_URL"),
                dotenv.get("DB_USER"),
                dotenv.get("DB_PASSWORD"));
    }

    @Test
    void findOneCreditCard() {
        CreditCard creditCard = ccQueryHandler.findOne(GET_CREDIT_CARD_BY_ID_QUERY, resultSetCreditCardMapper, 1L);
        assertEquals("123", creditCard.getCvv());
    }

    @Test
    void findManyCreditCard() {
        List<CreditCard> creditCards = ccQueryHandler.findMany(GET_CREDIT_CARDS_BY_USER_ID_QUERY, resultSetCreditCardMapper, 1L);
        assertEquals(1, creditCards.size());
    }

    @Test
    void insertCreditCard() {
        int initialSize = ccQueryHandler.findMany("SELECT * FROM credit_cards", resultSetCreditCardMapper).size();
        ccQueryHandler.execute(SAVE_CREDIT_CARD_QUERY,
                1L, "CREDIT_CARD", "test", "123456789", "2023-01-01", "123");
        int finalSize = ccQueryHandler.findMany("SELECT * FROM credit_cards", resultSetCreditCardMapper).size();
        assertEquals(initialSize + 1, finalSize);
    }

    @Test
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
    void insertPaypal() {
        int initialSize = paypalQueryHandler.findMany("SELECT * FROM paypal_accounts", resultSetPaypalMapper).size();
        paypalQueryHandler.execute(SAVE_PAYPAL_QUERY, 3L, 2L, "PAYPAL", "ok@gmail.com");
        int finalSize = paypalQueryHandler.findMany("SELECT * FROM paypal_accounts", resultSetPaypalMapper).size();
        assertEquals(initialSize + 1, finalSize);
    }


    @Test
    void findManyPaypal() {
        List<Paypal> paypalList = paypalQueryHandler.findMany(GET_PAYPAL_BY_USER_ID_QUERY, resultSetPaypalMapper, 3L);
        paypalList.stream().forEach(paypal -> System.out.println(paypal.getId()));
        assertEquals(1, paypalList.size());
    }


    @Test
    void deletePaypal() {
        int initialSize = paypalQueryHandler.findMany("SELECT * FROM paypal_accounts", resultSetPaypalMapper).size();
        paypalQueryHandler.execute(DELETE_PAYPAL_QUERY, 1L);
        int finalSize = paypalQueryHandler.findMany("SELECT * FROM paypal_accounts", resultSetPaypalMapper).size();
        assertEquals(initialSize - 1, finalSize);
    }
}