/*
 * ESERCIZI: 25/03/2026
 * Database: classicmodels
 */

-- Selezione del database di riferimento
USE classicmodels;



-- Query che restituisce solo i record dalla tabella "products" con il prezzo superiore a 50
SELECT *
FROM products
WHERE buyPrice > 50;


-- Query che restituisce tutti i record della tabella orders ordinati per data (di vendita/dell'ordine) in maniera DECRESCENTE
SELECT *
FROM orders
ORDER BY orderDate DESC;


-- Query che aggiorna il prezzo di tutti i prodotti nella tabella"products" aumentandolo del 10%
SET SQL_SAFE_UPDATES = 0; -- Disattiva la modalità sicura

UPDATE products
SET buyPrice = buyPrice + (buyPrice * 0.10);

SET SQL_SAFE_UPDATES = 1; -- Riattiva la modalità sicura


-- Query per inserire un nuovo utente nella tabella customers
INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city, country) 
VALUES (497, 'Pizzeria Vesuvio d''Oro', 'Esposito', 'Gennaro', '+39 081 5551234', 'Via dei Tribunali 1', 'Napoli', 'Italy');


-- Queries per eliminare tutti gli ordini nella tabella "orders" con lo stato di "Cancelled"
DELETE FROM orderdetails 
WHERE orderNumber IN (
    SELECT orderNumber 
    FROM orders 
    WHERE status = 'Cancelled'
);

DELETE FROM orders 
WHERE status = 'Cancelled';


-- Query che restituisce tutti gli utenti dalla tabella "customers" il cui nome inizia con la S e vivono in California
SELECT *
FROM customers
WHERE customerName LIKE 'S%' 
  AND state = 'CA';