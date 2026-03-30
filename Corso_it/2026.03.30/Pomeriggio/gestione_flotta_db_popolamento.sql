CREATE DATABASE gestione_flotta;
USE gestione_flotta;

-- 1. Tabella "Padre" (Compagnia)
CREATE TABLE compagnie (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL
);

-- 2. Tabella "Figlia" Aerei (Legata alla compagnia)
CREATE TABLE aerei (
    id INT PRIMARY KEY AUTO_INCREMENT,
    modello VARCHAR(100) NOT NULL,
    numero_posti INT NOT NULL CHECK (numero_posti > 0),
    codice VARCHAR(20) UNIQUE NOT NULL,
    id_compagnia INT, -- Chiave esterna
    FOREIGN KEY (id_compagnia) REFERENCES compagnie(id) ON DELETE CASCADE
);

-- 3. Tabella "Figlia" Piloti (Legata alla compagnia)
CREATE TABLE piloti (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    numero_brevetto VARCHAR(50) UNIQUE NOT NULL,
    ore_volo INT NOT NULL CHECK (ore_volo > 0),
    id_compagnia INT, -- Chiave esterna
    FOREIGN KEY (id_compagnia) REFERENCES compagnie(id) ON DELETE CASCADE
);


-- Inseriamo la compagnia
INSERT INTO compagnie (nome) VALUES ('Miche Airlines'), ('Walt Disney');

-- Inseriamo gli aerei collegandoli alla compagnia ID 1
INSERT INTO aerei (modello, numero_posti, codice, id_compagnia) 
VALUES ('Boeing 747', 416, 'B747-01', 1),
       ('Airbus A320', 150, 'A320-99', 1);

-- Inseriamo i piloti collegandoli alla compagnia ID 1
INSERT INTO piloti (nome, numero_brevetto, ore_volo, id_compagnia) 
VALUES ('Capitan Miche', 'IT12345', 5000, 1),
       ('Mario Rossi', 'IT67890', 1200, 1);