-- SCHEMA.SQL: Création des tables et des contraintes pour le script fourni

-- Création de la table CLIENT
CREATE TABLE customer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- Identifiant unique
    address VARCHAR(255) NOT NULL, -- Adresse du client
    birth_date DATE NOT NULL, -- Date de naissance
    first_name VARCHAR(50) NOT NULL, -- Prénom
    last_name VARCHAR(50) NOT NULL, -- Nom de famille
    phone VARCHAR(15) NOT NULL -- Numéro de téléphone
);

-- Création de la table BANK_ACCOUNT
CREATE TABLE bank_account (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- Identifiant unique
    balance DECIMAL(15, 2) NOT NULL, -- Solde
    creation_date DATETIME DEFAULT CURRENT_TIMESTAMP, -- Date de création
    customer_id INT NOT NULL, -- Référence vers le client
    CONSTRAINT fk_bank_account_customer FOREIGN KEY (customer_id) REFERENCES customer (id) -- Clé étrangère
);

-- Création de la table OPERATION
CREATE TABLE operation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- Identifiant unique
    amount DECIMAL(15, 2) NOT NULL, -- Montant de l'opération
    date DATETIME DEFAULT CURRENT_TIMESTAMP, -- Date de l'opération
    type ENUM('DEPOSIT', 'WITHDRAWAL') NOT NULL, -- Type d'opération
    bank_account_id INT NOT NULL, -- Référence vers le compte bancaire
    CONSTRAINT fk_operation_bank_account FOREIGN KEY (bank_account_id) REFERENCES bank_account (id) -- Clé étrangère
);

-- INSERTIONS POUR TESTS (OPTIONNEL)
-- Vous pouvez inclure vos scripts d'insertion ici pour tester.
