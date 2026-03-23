-- ==========================================================
-- OPERAZIONI DI SELEZIONE (DQL - Data Query Language)
-- ==========================================================

-- Seleziona tutte le colonne (*) di tutti i record presenti nella tabella 'country' 
-- dello schema 'world'. Utile per una panoramica veloce dei dati.
SELECT * FROM world.country;

-- Seleziona solo le colonne specifiche 'Name' e 'Continent'
-- Riduce il carico di dati e rende la lettura più pulita.
SELECT Name, Continent
FROM world.country;



-- ==========================================================
-- GESTIONE DATABASE (DDL - Data Definition Language)
-- ==========================================================

-- Crea un nuovo database (schema) chiamato 'corsojava'
CREATE DATABASE corsojava;

-- Elimina definitivamente il database 'corsojava' e tutto il suo contenuto
-- Attenzione: questa operazione non è reversibile!
DROP DATABASE corsojava;

-- Imposta 'corsojava' come database predefinito per le query successive,
-- così non dovrai scrivere 'corsojava.tabella' ogni volta.
USE corsojava;



-- ==========================================================
-- CREAZIONE TABELLA
-- ==========================================================

-- Crea la tabella 'utenti' definendo le colonne e i tipi di dato
CREATE TABLE utenti(
    -- 'id' è la chiave primaria: identifica univocamente ogni riga e non può essere duplicato
    id int PRIMARY KEY,
    
    -- 'nome', 'cognome' e 'indirizzo' usano VARCHAR(50), 
    -- ovvero stringhe di testo con un massimo di 50 caratterI
    nome VARCHAR(50),
    cognome VARCHAR(50),
    indirizzo VARCHAR(50)
);



-- ==========================================================
-- CREAZIONE TABELLA DA UNA ESISTENTE (CTAS)
-- ==========================================================

-- Crea la tabella 'utenti2' selezionando solo le colonne 'id' e 'nome'
-- dalla tabella 'utenti' già esistente. 
-- Questo comando copia sia la struttura che i dati (i record)
CREATE TABLE utenti2 AS 
SELECT id, nome 
FROM utenti;



-- ==========================================================
-- INSERIMENTO DATI (DML - Data Manipulation Language)
-- ==========================================================

-- Inserisce nuovi record (righe) nella tabella 'utenti'
-- L'ordine dei valori deve corrispondere esattamente all'ordine delle colonne 
-- definite nella CREATE TABLE (id, nome, cognome, indirizzo)
INSERT INTO utenti
VALUES
    (1, "Michela", "Della Gatta", "Via Pizza"), -- Utente 1 con ID 1
    (2, "Mario", "Rossi", "Via Roma"),          -- Utente 2 con ID 2
    (3, "Laura", "Bianchi", "Via Napoli");      -- Utente 3 con ID 3



-- ==========================================================
-- RIMOZIONE E PULIZIA (DDL - Data Definition Language)
-- ==========================================================

-- Elimina DEFINITIVAMENTE la tabella 'utenti2' dal database
-- Scompare tutto: i dati, la struttura, le colonne e gli indici
-- È come se la tabella non fosse mai esistita
DROP TABLE utenti2;

-- Svuota completamente la tabella 'utenti' eliminando tutti i record (righe)
-- A differenza di DROP, la STRUTTURA della tabella rimane intatta:
-- potrai continuare a inserire nuovi utenti perché le colonne (id, nome...) esistono ancora
-- Nota: TRUNCATE è molto più veloce di una DELETE senza filtri
TRUNCATE TABLE utenti;



-- ==========================================================
-- MODIFICA STRUTTURA (DDL - Alter Table)
-- ==========================================================

-- 1. ADD COLUMN: Aggiunge una nuova colonna alla tabella
-- Qui aggiungiamo la colonna 'email' che può contenere fino a 100 caratteri
ALTER TABLE utenti 
ADD email VARCHAR(100);


-- 2. MODIFY COLUMN: Cambia il tipo di dato o i vincoli di una colonna esistente
-- Esempio: portiamo la colonna 'indirizzo' da 50 a 100 caratteri perché alcuni
-- indirizzi sono troppo lunghi e venivano tagliati
ALTER TABLE utenti 
MODIFY COLUMN indirizzo VARCHAR(100);


-- 3. DROP COLUMN: Elimina definitivamente una colonna e tutti i dati in essa contenuti
-- Esempio: decidiamo che la colonna 'cognome' non ci serve più
-- Attenzione: i cognomi già salvati verranno persi per sempre!
ALTER TABLE utenti 
DROP COLUMN cognome;



-- ==========================================================
-- ESEMPIO CON CONSTRAINTS (Vincoli di Integrità)
-- ==========================================================

CREATE TABLE utenti_PRO (
    -- PRIMARY KEY: Identifica ogni riga. AUTO_INCREMENT fa aumentare il numero da solo
    id INT AUTO_INCREMENT PRIMARY KEY,

    -- NOT NULL: Impedisce di lasciare il campo vuoto (il nome è obbligatorio)
    nome VARCHAR(50) NOT NULL,

    -- UNIQUE: Impedisce che ci siano due utenti con la stessa email
    email VARCHAR(100) NOT NULL UNIQUE,

    -- CHECK: Verifica che il dato rispetti una condizione (es. l'età deve essere >= 18)
    eta INT CHECK (eta >= 18),

    -- DEFAULT: Se non specifichi un valore, assegna quello predefinito
    nazione VARCHAR(50) DEFAULT 'Italia',

    -- data_creazione: inserisce automaticamente l'ora esatta del momento dell'iscrizione
    data_iscrizione TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- Trova l'utente che ha esattamente l'ID numero 1
SELECT * FROM utenti 
WHERE id = 1;

-- Trova tutti gli utenti che NON vivono in "Via Roma"
SELECT * FROM utenti 
WHERE indirizzo != 'Via Roma';

-- Dallo schema world: nazioni con popolazione superiore a 100 milioni
SELECT Name, Population FROM world.country 
WHERE Population > 100000000;
