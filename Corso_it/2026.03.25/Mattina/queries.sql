/*
 * ESERCIZI MATTINA: 25/03/2026
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


/*
 * ESERCIZI POMERIGGIO: 25/03/2026
 * Database: world
 */

-- Selezione del database di riferimento
USE world;

-- Si vuole recuperare la lingua e la nazione di ogni città
SELECT 
    city.Name AS "Città", 
    country.Name AS "Nazione", 
    countrylanguage.Language AS "Lingua"
FROM city INNER JOIN country ON city.CountryCode = country.Code
					INNER JOIN countrylanguage ON country.Code = countrylanguage.CountryCode
ORDER BY city.Name, countrylanguage.Language;


-- Si vuole recuperare il numero di città per nazione mostrando anche il nome della nazione e ordinarli in base al numero di città
SELECT 
    country.Name AS "Nazione", 
    COUNT(city.ID) AS "Numero di Città"
FROM country LEFT JOIN city ON country.Code = city.CountryCode
GROUP BY country.Name
ORDER BY COUNT(city.ID);


-- Si vuole conoscere la lista di repubbliche con aspettativa di vita maggiore di 70 anni, inoltre si vuole visualizzare anche la lingua parlata
SELECT 
    country.Name AS "Nome Nazione", 
    country.LifeExpectancy AS "Aspettativa di Vita", 
    countrylanguage.Language AS "Lingua Parlata",
    country.GovernmentForm AS "Forma di Governo"
FROM country JOIN countrylanguage ON country.Code = countrylanguage.CountryCode
WHERE country.GovernmentForm LIKE '%Republic' 
			AND country.LifeExpectancy > 70
ORDER BY country.LifeExpectancy DESC;


-- Visualizza l'elenco di tutti i pagamenti effettuati dai clienti, mostrando il nome del cliente, la data del pagamento e l'importo
-- Ordina per data più recente
USE classicmodels;

SELECT 
	customers.customerName AS "Cliente",
	payments.paymentDate AS "Data Pagamento", 
  payments.amount AS "Importo"
FROM payments JOIN customers ON payments.customerNumber = customers.customerNumber
ORDER BY payments.paymentDate DESC;


-- Selezionare tutti i clienti che non hanno mai fatto un ordine
-- mostrare l'ID Cliente, Nome Cliente
-- Da risolvere con JOIN e poi con SUBQUERY
SELECT 
    customers.customerNumber AS "ID CLIENTE", 
    customers.customerName AS "NOME"
FROM customers LEFT JOIN orders ON customers.customerNumber = orders.customerNumber
WHERE orders.orderNumber IS NULL;


SELECT 
    customers.customerNumber AS "ID CLIENTE", 
    customers.customerName AS "NOME"
FROM customers
WHERE customers.customerNumber NOT IN (
    SELECT orders.customerNumber 
    FROM orders
);


-- Produci una lista di tutti i dipendenti che mostri il loro nome e cognome insieme al nome e cognome del loro diretto superiore
SELECT 
    e.firstName AS "Nome Dipendente", 
    e.lastName AS "Cognome Dipendente", 
    m.firstName AS "Nome Capo", 
    m.lastName AS "Cognome Capo"
FROM employees AS e LEFT JOIN employees AS m ON e.reportsTo = m.employeeNumber;

SELECT 
    CONCAT(e.firstName, ' ', e.lastName) AS "Dipendente",
    CONCAT(m.firstName, ' ', m.lastName) AS "Diretto Superiore"
FROM employees AS e LEFT JOIN employees AS m ON e.reportsTo = m.employeeNumber;


-- Produci per ogni cliente il totale dei suoi ordini
SELECT 
    orders.orderNumber AS "Codice Ordine", 
    customers.customerName AS "Nome Cliente", 
    SUM(orderdetails.quantityOrdered * orderdetails.priceEach) AS "Totale Ordine (€)"
FROM orders JOIN orderdetails ON orders.orderNumber = orderdetails.orderNumber
						JOIN customers ON orders.customerNumber = customers.customerNumber
GROUP BY orders.orderNumber, orders.orderDate, customers.customerName
ORDER BY orders.orderNumber DESC;