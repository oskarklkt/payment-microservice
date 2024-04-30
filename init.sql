DO
$$
BEGIN
        IF EXISTS (SELECT 1
                   FROM information_schema.tables
                   WHERE table_schema = 'public'
                     AND table_name IN ('credit_cards', 'paypal_accounts')) THEN
            RAISE NOTICE 'Tables already exist';
ELSE

CREATE TABLE credit_cards
(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id BIGINT NOT NULL check ( user_id > 0 ),
    payment_type VARCHAR(255) NOT NULL,
    card_holder_name VARCHAR(255) NOT NULL,
    card_number VARCHAR(255) NOT NULL,
    expiration_date VARCHAR(255) NOT NULL,
    cvv VARCHAR(3) NOT NULL
);

CREATE TABLE paypal_accounts
(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id BIGINT NOT NULL check ( user_id > 0 ),
    payment_type VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

--- just for testing purposes - will be deleted in the future
INSERT INTO credit_cards (user_id, payment_type, card_holder_name, card_number, expiration_date, cvv)
VALUES      (1, 'credit_card', 'John Doe', '1234567890123456', '12/2022', '123'),
            (2, 'credit_card', 'Oskar', '1234567890123456', '12/2025', '999'),
            (3, 'credit_card', 'James Bond', '1234567890123456', '12/2035', '007');
---
INSERT INTO paypal_accounts (user_id, payment_type, email)
VALUES      (2, 'paypal', 'oskar@gmail.com'),
            (3, 'paypal', 'bondjamesbond@agent.com');

END IF;
END
$$;