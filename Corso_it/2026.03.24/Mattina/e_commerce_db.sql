-- CREAZIONE DABATASE 
CREATE DATABASE e_commerce_db;
USE e_commerce_db;

-- CREAZIONE TABELLE 

-- 1. CATEGORIA
CREATE TABLE CATEGORIA (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

-- 2. PRODOTTO
CREATE TABLE PRODOTTO (
    id_prodotto INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    prezzo DECIMAL(10,2) NOT NULL,
    descrizione TEXT,
    id_categoria INT NOT NULL,

    CONSTRAINT fk_prodotto_categoria
        FOREIGN KEY (id_categoria)
        REFERENCES CATEGORIA(id_categoria)
);

-- 3. CLIENTE
CREATE TABLE CLIENTE (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cognome VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefono VARCHAR(20)
);

-- 4. ORDINE
CREATE TABLE ORDINE (
    id_ordine INT AUTO_INCREMENT PRIMARY KEY,
    data_ordine TIMESTAMP NOT NULL DEFAULT NOW(),
    stato ENUM('In elaborazione', 'Spedito', 'Cancellato', 'Consegnato') NOT NULL DEFAULT 'In elaborazione',
    id_cliente INT NOT NULL,

    CONSTRAINT fk_ordine_cliente
        FOREIGN KEY (id_cliente)
        REFERENCES CLIENTE(id_cliente)
);

-- 5. ORDINE_PRODOTTO
CREATE TABLE ORDINE_PRODOTTO (
    id_ordine INT NOT NULL,
    id_prodotto INT NOT NULL,
    quantita SMALLINT NOT NULL CHECK (quantita > 0),

    CONSTRAINT pk_ordine_prodotto
        PRIMARY KEY (id_ordine, id_prodotto),

    CONSTRAINT fk_op_ordine
        FOREIGN KEY (id_ordine)
        REFERENCES ORDINE(id_ordine),

    CONSTRAINT fk_op_prodotto
        FOREIGN KEY (id_prodotto)
        REFERENCES PRODOTTO(id_prodotto)
);