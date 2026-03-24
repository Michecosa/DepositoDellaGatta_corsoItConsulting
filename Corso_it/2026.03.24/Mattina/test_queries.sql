USE e_commerce_db;


-- ============================================================
--  1. SELECT / FROM — proiezione semplice
-- ============================================================

-- 1.1  Tutti i prodotti con nome e prezzo
SELECT nome, prezzo
FROM PRODOTTO;

-- 1.2  Tutti i clienti (nome, cognome, email)
SELECT nome, cognome, email
FROM CLIENTE;

-- 1.3  Tutti gli ordini con id, data e stato
SELECT id_ordine, data_ordine, stato
FROM ORDINE;


-- ============================================================
--  2. WHERE — operatori di confronto (=, <>, <, >, <=, >=)
-- ============================================================

-- 2.1  Prodotti che costano esattamente 9.99 euro
SELECT nome, prezzo
FROM PRODOTTO
WHERE prezzo = 9.99;

-- 2.2  Prodotti che NON appartengono alla categoria 1
SELECT nome, id_categoria
FROM PRODOTTO
WHERE id_categoria <> 1;

-- 2.3  Prodotti con prezzo inferiore a 15.00 euro
SELECT nome, prezzo
FROM PRODOTTO
WHERE prezzo < 15.00;

-- 2.4  Prodotti con prezzo maggiore di 100.00 euro (sogni rari)
SELECT nome, prezzo
FROM PRODOTTO
WHERE prezzo > 100.00;

-- 2.5  Prodotti con prezzo minore o uguale a 20.00 euro
SELECT nome, prezzo
FROM PRODOTTO
WHERE prezzo <= 20.00;

-- 2.6  Prodotti con prezzo maggiore o uguale a 30.00 euro
SELECT nome, prezzo
FROM PRODOTTO
WHERE prezzo >= 30.00;

-- 2.7  Ordini con stato 'Consegnato'
SELECT id_ordine, data_ordine, id_cliente
FROM ORDINE
WHERE stato = 'Consegnato';

-- 2.8  Ordini che NON sono stati cancellati
SELECT id_ordine, stato, id_cliente
FROM ORDINE
WHERE stato <> 'Cancellato';


-- ============================================================
--  3. WHERE — operatori logici (AND, OR, NOT)
-- ============================================================

-- 3.1  Prodotti economici (< 15 euro) nella categoria Incubi Addomesticati (id = 7)
SELECT nome, prezzo
FROM PRODOTTO
WHERE prezzo < 15.00 AND id_categoria = 7;

-- 3.2  Prodotti nelle Ambientazioni Oniriche (id = 3) OPPURE negli Accessori (id = 4)
SELECT nome, id_categoria, prezzo
FROM PRODOTTO
WHERE id_categoria = 3 OR id_categoria = 4;

-- 3.3  Prodotti che NON sono nella categoria 'Sogni Lucidi Base' (id = 1)
SELECT nome, id_categoria
FROM PRODOTTO
WHERE NOT id_categoria = 1;

-- 3.4  Sogni costosi MA non collezionabili (fuori dalla categoria 5)
SELECT nome, prezzo, id_categoria
FROM PRODOTTO
WHERE prezzo > 30.00 AND NOT id_categoria = 5;

-- 3.5  Clienti italiani O greci (prefisso +39 o +30)
SELECT nome, cognome, telefono
FROM CLIENTE
WHERE telefono LIKE '+39%' OR telefono LIKE '+30%';


-- ============================================================
--  4. WHERE — altri operatori (BETWEEN, IN, LIKE, IS NULL)
-- ============================================================

-- 4.1  BETWEEN — prodotti nella fascia 15.00-35.00 euro
SELECT nome, prezzo
FROM PRODOTTO
WHERE prezzo BETWEEN 15.00 AND 35.00;

-- 4.2  IN — ordini con stato 'Spedito' o 'In elaborazione'
SELECT id_ordine, stato, data_ordine
FROM ORDINE
WHERE stato IN ('Spedito', 'In elaborazione');

-- 4.3  IN — prodotti delle categorie Esperienze Sensoriali (6) e Incubi Addomesticati (7)
SELECT nome, id_categoria
FROM PRODOTTO
WHERE id_categoria IN (6, 7);

-- 4.4  NOT IN — prodotti che NON sono base ne avanzati (esclude categorie 1 e 2)
SELECT nome, id_categoria
FROM PRODOTTO
WHERE id_categoria NOT IN (1, 2);

-- 4.5  LIKE — prodotti il cui nome inizia per 'Il'
SELECT nome
FROM PRODOTTO
WHERE nome LIKE 'Il%';

-- 4.6  LIKE — prodotti il cui nome contiene la parola 'Sogno'
SELECT nome
FROM PRODOTTO
WHERE nome LIKE '%Sogno%';

-- 4.7  LIKE — clienti la cui email contiene il dominio '.it'
SELECT nome, cognome, email
FROM CLIENTE
WHERE email LIKE '%.it%';

-- 4.8  IS NULL — clienti senza numero di telefono registrato
SELECT nome, cognome
FROM CLIENTE
WHERE telefono IS NULL;

-- 4.9  IS NOT NULL — clienti con numero di telefono registrato
SELECT nome, cognome, telefono
FROM CLIENTE
WHERE telefono IS NOT NULL;


-- ============================================================
--  5. ORDER BY
-- ============================================================

-- 5.1  Prodotti ordinati per prezzo crescente (dal piu economico)
SELECT nome, prezzo
FROM PRODOTTO
ORDER BY prezzo ASC;

-- 5.2  Prodotti ordinati per prezzo decrescente (dal piu costoso)
SELECT nome, prezzo
FROM PRODOTTO
ORDER BY prezzo DESC;

-- 5.3  Prodotti ordinati per categoria, poi per nome alfabetico
SELECT id_categoria, nome, prezzo
FROM PRODOTTO
ORDER BY id_categoria ASC, nome ASC;

-- 5.4  Ordini ordinati per data dal piu recente
SELECT id_ordine, data_ordine, stato
FROM ORDINE
ORDER BY data_ordine DESC;

-- 5.5  Clienti in ordine alfabetico per cognome, poi per nome
SELECT cognome, nome, email
FROM CLIENTE
ORDER BY cognome ASC, nome ASC;

-- 5.6  Prodotti economici (< 20 euro) ordinati per prezzo decrescente
SELECT nome, prezzo
FROM PRODOTTO
WHERE prezzo < 20.00
ORDER BY prezzo DESC;

-- 5.7  Ordini 'Consegnato' ordinati dal piu vecchio al piu recente
SELECT id_ordine, data_ordine, id_cliente
FROM ORDINE
WHERE stato = 'Consegnato'
ORDER BY data_ordine ASC;


-- ============================================================
--  6. GROUP BY + COUNT
-- ============================================================

-- 6.1  Numero di prodotti per categoria
SELECT id_categoria, COUNT(*) AS numero_prodotti
FROM PRODOTTO
GROUP BY id_categoria;

-- 6.2  Numero di ordini per stato
SELECT stato, COUNT(*) AS numero_ordini
FROM ORDINE
GROUP BY stato;

-- 6.3  Numero di ordini per cliente
SELECT id_cliente, COUNT(*) AS numero_ordini
FROM ORDINE
GROUP BY id_cliente;

-- 6.4  Numero di prodotti distinti per ordine
SELECT id_ordine, COUNT(*) AS prodotti_nell_ordine
FROM ORDINE_PRODOTTO
GROUP BY id_ordine;

-- 6.5  Quante volte ogni prodotto compare in un ordine
SELECT id_prodotto, COUNT(*) AS volte_ordinato
FROM ORDINE_PRODOTTO
GROUP BY id_prodotto;

-- 6.6  Numero di ordini per stato, dal piu frequente al meno frequente
SELECT stato, COUNT(*) AS totale
FROM ORDINE
GROUP BY stato
ORDER BY totale DESC;

-- 6.7  HAVING — categorie con piu di 2 prodotti
SELECT id_categoria, COUNT(*) AS numero_prodotti
FROM PRODOTTO
GROUP BY id_categoria
HAVING COUNT(*) > 2;

-- 6.8  HAVING — clienti con piu di un ordine effettuato
SELECT id_cliente, COUNT(*) AS numero_ordini
FROM ORDINE
GROUP BY id_cliente
HAVING COUNT(*) > 1;

-- 6.9  Numero di clienti per dominio email
SELECT
    SUBSTRING_INDEX(email, '@', -1) AS dominio,
    COUNT(*) AS numero_clienti
FROM CLIENTE
GROUP BY dominio
ORDER BY numero_clienti DESC;

-- 6.10 Prodotti mai ordinati (COUNT incrociato con sottoquery)
SELECT id_prodotto, nome
FROM PRODOTTO
WHERE id_prodotto NOT IN (
    SELECT DISTINCT id_prodotto
    FROM ORDINE_PRODOTTO
);