-- Data for table client (client_number, passport_number, user_id), bank_account (account_number, balance, active), client_bank_account (client_id, bank_account_id), debit_card (card_number, cvv, expiration_date, active, balance), bank_account_debit_card (bank_account_id, debit_card_id):
INSERT INTO client_t (client_number, passport_number, user_id) VALUES ('CL-0001', '1234567890', 2);

INSERT INTO bank_account_t (account_number, balance, active) VALUES ('40817810099910004321', 100000.00, TRUE);

INSERT INTO client_bank_account_t (client_id, bank_account_id) VALUES (1, 1);

INSERT INTO debit_card_t (card_number, cvv, expiration_date, active, balance) VALUES ('2200222233334444', '123', '2025-12-31', TRUE, 100000.00);
INSERT INTO debit_card_t (card_number, cvv, expiration_date, active, balance) VALUES ('2200222233335555', '123', '2025-12-31', TRUE, 100000.00);

INSERT INTO bank_account_debit_card_t (bank_account_id, debit_card_id) VALUES (1, 1);
INSERT INTO bank_account_debit_card_t (bank_account_id, debit_card_id) VALUES (1, 2);

INSERT INTO manager_t (manager_number, user_id) VALUES ('MN-0001', 1);

UPDATE client_t SET manager_id = 1 WHERE id = 1;

INSERT INTO transaction_t (transaction_number, amount, transaction_date, bank_account_id, client_id, manager_id, bank_account_id_to) VALUES ('TR-0001', 1000.00, '2021-01-01 00:00:00', 1, 1, 1, 1);
INSERT INTO transaction_t (transaction_number, amount, transaction_date, bank_account_id, client_id, manager_id, bank_account_id_to) VALUES ('TR-0002', 2000.00, '2021-01-02 00:00:00', 1, 1, 1, 1);