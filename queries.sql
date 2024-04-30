--- Credit cards

--- save
INSERT INTO credit_cards (user_id, payment_type, card_holder_name, card_number, expiration_date, cvv)
VALUES (2, 'credit_card', 'Aaa BBB', '1234598790123456', '09/2027', '456');
--- delete
DELETE FROM credit_cards WHERE user_id = 2 AND id = 4;
--- update
UPDATE credit_cards SET card_holder_name = 'John' WHERE user_id = 1 AND id = 1;
--- select
SELECT * FROM credit_cards WHERE user_id = 2;
--- get next id
SELECT MAX(id) + 1 FROM credit_cards;

--- PayPal

--- save
INSERT INTO paypal_accounts (user_id, payment_type, email)
VALUES (1, 'paypal', 'doe@gmail.com');
--- delete
DELETE FROM paypal_accounts WHERE user_id = 1 AND id = 3;
--- update
UPDATE paypal_accounts SET email = 'james@gmail.com' WHERE user_id = 3 AND id = 2;
--- select
SELECT * FROM paypal_accounts WHERE user_id = 3;
--- get next id
SELECT MAX(id) + 1 FROM paypal_accounts;

--- Joined

--- get all payment methods for user from credit cards and paypal accounts
SELECT * FROM credit_cards WHERE user_id = 2
UNION
SELECT id, user_id, payment_type, email, null, null, null FROM paypal_accounts WHERE user_id = 2;