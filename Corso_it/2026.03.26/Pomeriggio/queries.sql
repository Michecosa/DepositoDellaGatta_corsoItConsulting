-- ----------------------------- Query 1 -------------------------
--  Mostrare il numero di appuntamenti per ogni medico, ordinati dal più impegnato.

SELECT 
    m.cognome AS Cognome_Medico, 
    m.nome AS Nome_Medico, 
    COUNT(a.id_appuntamento) AS Numero_Appuntamenti
FROM 
    Medico m

LEFT JOIN 
    Appuntamento a ON m.id_medico = a.id_medico
GROUP BY 
    m.id_medico, m.cognome, m.nome
ORDER BY 
    Numero_Appuntamenti DESC;

-- --------------Query 2 ----------------
-- Mostrare i pazienti che hanno effettuato più di 3 appuntamenti.

SELECT 
    p.cognome AS Cognome_Paziente, 
    p.nome AS Nome_Paziente, 
    COUNT(a.id_appuntamento) AS Numero_Appuntamenti
FROM 
    Paziente p
JOIN 
    Appuntamento a ON p.id_paziente = a.id_paziente
GROUP BY 
    p.id_paziente, p.cognome, p.nome
HAVING 
    Numero_Appuntamenti > 3;

-- ---------------- Query 3 -------------------------
SELECT 
    m.id_medico,
    m.cognome AS Medico, 
    m.nome AS Nome,
    SUM(p.importo) AS Incasso_Totale
FROM 
    Medico m
JOIN 
    Appuntamento a ON m.id_medico = a.id_medico
JOIN 
    Pagamento p ON a.id_appuntamento = p.id_appuntamento
GROUP BY m.id_medico, m.cognome, m.nome
ORDER BY 
    Incasso_Totale DESC;



/*QUERY 4
Mostrare il medico con lo stipendio più alto per ogni specializzazione.
(richiede subquery o window function)*/
SELECT Medico.id_medico, Medico.nome, Medico.cognome, Specializzazione.nome AS specializzazione, Medico.stipendio
FROM Medico
JOIN Specializzazione ON Medico.id_specializzazione = Specializzazione.id_specializzazione
WHERE Medico.stipendio = (
    SELECT MAX(Medico.stipendio)
    FROM Medico
    WHERE Medico.id_specializzazione = Specializzazione.id_specializzazione
);


/*QUERY 5
Mostrare il paziente che ha speso di più in totale.
(somma pagamenti + subquery)*/
SELECT Paziente.id_paziente, Paziente.nome, Paziente.cognome, SUM(Pagamento.importo) AS totale_speso
FROM Paziente
	JOIN Appuntamento ON Paziente.id_paziente = Appuntamento.id_paziente
	JOIN Pagamento ON Appuntamento.id_appuntamento = Pagamento.id_appuntamento
GROUP BY Paziente.id_paziente, Paziente.nome, Paziente.cognome
HAVING SUM(Pagamento.importo) = (
    SELECT MAX(totale)
    FROM (
        SELECT SUM(Pagamento.importo) AS totale
        FROM Appuntamento JOIN Pagamento ON Appuntamento.id_appuntamento = Pagamento.id_appuntamento
        GROUP BY Appuntamento.id_paziente
    ) t
);

/*QUERY 6
Mostrare per ogni reparto il tasso di occupazione attuale.
(tot ricoverati attuali / numero_posti)
*/
SELECT 
    Reparto.nome AS reparto, COUNT(Ricovero.id_ricovero) AS ricoverati_attuali,
    Reparto.numero_posti, (COUNT(Ricovero.id_ricovero) * 100.0) / Reparto.numero_posti AS percentuale_occupazione
FROM Reparto LEFT JOIN Ricovero ON Reparto.id_reparto = Ricovero.id_reparto
    AND Ricovero.data_dimissione IS NULL
GROUP BY Reparto.id_reparto, Reparto.nome, Reparto.numero_posti;


-- Query n.7 
-- Mostrare i medici che non hanno avuto appuntamenti negli ultimi 30 giorni
-- (subquery con NOT EXISTS)

SELECT 
    m.id_medico,
    m.nome,
    m.cognome,
    s.nome AS specializzazione
FROM Medico m JOIN Specializzazione s ON m.id_specializzazione = s.id_specializzazione
WHERE NOT EXISTS (
    SELECT 1
    FROM Appuntamento a
    WHERE a.id_medico = m.id_medico
      AND a.data_appuntamento >= NOW() - INTERVAL 30 DAY
);


-- Query n.8
-- Specializzazione che ha generato più incasso totale
-- (JOIN + GROUP BY + subquery)

SELECT 
    s.nome AS specializzazione,
    SUM(p.importo) AS incasso_totale
FROM Specializzazione s
	JOIN Medico m ON m.id_specializzazione = s.id_specializzazione
	JOIN Appuntamento a ON a.id_medico = m.id_medico
	JOIN Pagamento p ON p.id_appuntamento = a.id_appuntamento
GROUP BY s.id_specializzazione, s.nome
HAVING SUM(p.importo) = (
    SELECT MAX(incasso_per_spec)
    FROM (
        SELECT SUM(p2.importo) AS incasso_per_spec
        FROM Specializzazione s2
        JOIN Medico m2 ON m2.id_specializzazione = s2.id_specializzazione
        JOIN Appuntamento a2 ON a2.id_medico = m2.id_medico
        JOIN Pagamento p2 ON p2.id_appuntamento = a2.id_appuntamento
        GROUP BY s2.id_specializzazione
    ) AS incassi
);


-- Query n.9
-- Incasso totale mensile e variazione percentuale rispetto al mese precedente

/*
SELECT
    DATE_FORMAT(p.data_pagamento, '%Y-%m') AS mese,
    SUM(p.importo) AS incasso_totale,
    (
        SELECT SUM(p2.importo)
        FROM Pagamento p2
        WHERE DATE_FORMAT(p2.data_pagamento, '%Y-%m') = 
              DATE_FORMAT(DATE_SUB(p.data_pagamento, INTERVAL 1 MONTH), '%Y-%m')
    ) AS incasso_precedente,
    ROUND(
        (SUM(p.importo) - (
            SELECT SUM(p2.importo)
            FROM Pagamento p2
            WHERE DATE_FORMAT(p2.data_pagamento, '%Y-%m') =
                  DATE_FORMAT(DATE_SUB(p.data_pagamento, INTERVAL 1 MONTH), '%Y-%m')
        )) / (
            SELECT SUM(p2.importo)
            FROM Pagamento p2
            WHERE DATE_FORMAT(p2.data_pagamento, '%Y-%m') =
                  DATE_FORMAT(DATE_SUB(p.data_pagamento, INTERVAL 1 MONTH), '%Y-%m')
        ) * 100
    , 2) AS variazione_percentuale
FROM Pagamento p
GROUP BY DATE_FORMAT(p.data_pagamento, '%Y-%m')
ORDER BY mese;
*/

SELECT 
	mese, incasso_totale, incasso_precedente,
  ROUND((incasso_totale - incasso_precedente) / incasso_precedente * 100, 2) AS variazione_percentuale
FROM (
	SELECT
		DATE_FORMAT(p.data_pagamento, '%Y-%m') AS mese, 
    SUM(p.importo) AS incasso_totale,
    LAG(SUM(p.importo)) OVER (ORDER BY DATE_FORMAT(p.data_pagamento, '%Y-%m')) as incasso_precedente
	FROM pagamento p
	GROUP BY DATE_FORMAT(p.data_pagamento, '%Y-%m')
) AS incassi_mensili
ORDER BY mese DESC;


-- Query n.10
-- Pazienti con almeno 2 ricoveri, almeno 5 appuntamenti
-- e spesa totale sopra la media dei pazienti

SELECT
    p.id_paziente,
    p.nome,
    p.cognome,
    COUNT(DISTINCT r.id_ricovero)     AS num_ricoveri,
    COUNT(DISTINCT a.id_appuntamento) AS num_appuntamenti,
    SUM(pg.importo)                   AS spesa_totale
FROM Paziente p
JOIN Ricovero    r  ON r.id_paziente      = p.id_paziente
JOIN Appuntamento a ON a.id_paziente      = p.id_paziente
JOIN Pagamento   pg ON pg.id_appuntamento = a.id_appuntamento
GROUP BY p.id_paziente, p.nome, p.cognome
HAVING
    COUNT(DISTINCT r.id_ricovero)     >= 2
    AND COUNT(DISTINCT a.id_appuntamento) >= 5
    AND SUM(pg.importo) > (
        -- Media della spesa totale per paziente
        SELECT AVG(spesa_paziente)
        FROM (
            SELECT SUM(pg2.importo) AS spesa_paziente
            FROM Paziente p2
            JOIN Appuntamento a2  ON a2.id_paziente      = p2.id_paziente
            JOIN Pagamento    pg2 ON pg2.id_appuntamento = a2.id_appuntamento
            GROUP BY p2.id_paziente
        ) AS spese
    )
ORDER BY spesa_totale DESC;