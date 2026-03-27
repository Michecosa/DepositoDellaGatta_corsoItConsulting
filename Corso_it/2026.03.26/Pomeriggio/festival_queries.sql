-- Query n.7
-- Mostrare gli sponsor che hanno sponsorizzato performance in almeno 3 giorni diversi.
SELECT s.nome, COUNT(DISTINCT p.data_esibizione) AS giorni_distinti
FROM Sponsor s
	JOIN Sponsorizzazione sp ON s.id_sponsor = sp.id_sponsor
	JOIN Performance p ON sp.id_performance = p.id_performance
GROUP BY s.id_sponsor, s.nome
HAVING COUNT(DISTINCT p.data_esibizione) >= 3;


-- Query n.8
-- Mostrare per ogni giorno il palco con l’incasso più alto.
-- (subquery o window function)
SELECT 
    t1.data_esibizione, 
    t1.nome_palco, 
    t1.incasso_totale
FROM (
    -- Calcolo l'incasso di ogni palco per ogni giorno
    SELECT 
        p.data_esibizione, 
        pa.nome AS nome_palco, 
        SUM(b.prezzo) AS incasso_totale
    FROM Performance p
			JOIN Palco pa ON p.id_palco = pa.id_palco
			JOIN Biglietto b ON p.id_performance = b.id_performance
    GROUP BY p.data_esibizione, pa.id_palco, pa.nome
) AS t1
WHERE t1.incasso_totale = (
    -- Trovo l'incasso massimo di quel giorno specifico
    SELECT MAX(incasso_giornaliero)
    FROM (
        SELECT 
            p2.data_esibizione, 
            SUM(b2.prezzo) AS incasso_giornaliero
        FROM Performance p2
        JOIN Biglietto b2 ON p2.id_performance = b2.id_performance
        GROUP BY p2.data_esibizione, p2.id_palco
    ) AS t2
    WHERE t2.data_esibizione = t1.data_esibizione
);


-- Query n.9
-- Mostrare per ogni giorno il palco con l’incasso più alto.
-- (subquery o window function)
WITH IncassiGiornalieri AS (
    SELECT 
        p.data_esibizione,
        SUM(b.prezzo) AS incasso_odierno
    FROM Performance p
    JOIN Biglietto b ON p.id_performance = b.id_performance
    GROUP BY p.data_esibizione
)
SELECT 
    data_esibizione,
    incasso_odierno,
    LAG(incasso_odierno) OVER (ORDER BY data_esibizione) AS incasso_precedente,
    ROUND(((incasso_odierno - LAG(incasso_odierno) OVER (ORDER BY data_esibizione)) / LAG(incasso_odierno) OVER (ORDER BY data_esibizione)) * 100, 2) 
			AS variazione_percentuale
FROM IncassiGiornalieri;


-- Query n.10
-- Mostrare gli artisti che
-- * hanno fatto almeno 3 performance
-- * hanno collaborato con almeno 2 artisti diversi
-- * hanno generato incasso sopra la media degli artisti
-- (subquery annidate multiple + aggregazioni)
SELECT 
    a.id_artista, 
    a.nome, 
    COUNT(DISTINCT p.id_performance) AS numero_performance,
    (
        SELECT COUNT(DISTINCT CASE 
            WHEN c.id_artista1 = a.id_artista THEN c.id_artista2 
            ELSE c.id_artista1 
        END)
        FROM Collaborazione c
        WHERE c.id_artista1 = a.id_artista OR c.id_artista2 = a.id_artista
    ) AS numero_collaboratori,
    SUM(b.prezzo) AS incasso_totale
FROM Artista a
JOIN Performance p ON a.id_artista = p.id_artista
JOIN Biglietto b ON p.id_performance = b.id_performance
GROUP BY a.id_artista, a.nome
HAVING 
    -- 1. Almeno 3 performance
    COUNT(DISTINCT p.id_performance) >= 3 
		
    -- 2. Almeno 2 artisti diversi con cui ha collaborato
    AND numero_collaboratori >= 2
    
    -- 3. Incasso sopra la media calcolata su tutti gli artisti che hanno venduto biglietti
    AND SUM(b.prezzo) > (
        SELECT AVG(incasso_artista)
        FROM (
            SELECT SUM(b2.prezzo) AS incasso_artista
            FROM Biglietto b2
            JOIN Performance p2 ON b2.id_performance = p2.id_performance
            GROUP BY p2.id_artista
        ) AS medie_incassi
    );