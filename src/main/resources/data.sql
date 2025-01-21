	-- CREATE CUSTOMER
	INSERT INTO customer (address, birth_date, first_name, last_name, phone) 
	VALUES ('110 Avenue des Champs-Élysées, 75008 Paris', '1987-05-02', 'Youness', 'ELASSAASSI', '0617731400');
	
	
	-- CREATE BANK_ACCOUNT
	INSERT INTO bank_account  (balance, creation_date, customer_id)
	VALUES ( 900, CURRENT_TIMESTAMP, 1);
	
	-- CREATE OPERATION
	INSERT INTO operation (amount, date, type, bank_account_id )
	VALUES(1000, CURRENT_TIMESTAMP, 'DEPOSIT', 1);
	INSERT INTO operation (amount, date, type, bank_account_id )
	VALUES( -100, CURRENT_TIMESTAMP, 'WITHDRAWAL', 1);
	
