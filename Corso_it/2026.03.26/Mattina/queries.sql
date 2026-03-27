/*
 * ESERCIZI MATTINA: 26/03/2026
 */

USE world;

-- Si vogliono recuperare dal database "world" la lingua e la nazione di ogni città
SELECT 
    city.Name AS "Nome Città", 
    country.Name AS "Nome Nazione", 
    countrylanguage.Language AS "Lingua Parlata"
FROM city JOIN country ON city.CountryCode = country.Code
					JOIN countrylanguage ON country.Code = countrylanguage.CountryCode;


-- Si vuole recuperare il numero di città per nazione dal database "world" 
-- mostrare anche il nome della nazione e ordinarli in base al numero di città
SELECT 
    country.Name AS "Nome Nazione", 
    COUNT(city.ID) AS "Numero di Città"
FROM country JOIN city ON country.Code = city.CountryCode
GROUP BY country.Code, country.Name
ORDER BY COUNT(city.ID) DESC;


-- Si vuole conoscere la lista di repubbliche con aspettativa di vita maggiore dei 70 anni, 
-- inoltre si vuole visualizzare anche la lingua parlata
SELECT 
    country.Name AS "Nome Nazione", 
    country.LifeExpectancy AS "Aspettativa di Vita", 
    countrylanguage.Language AS "Lingua Parlata"
FROM country JOIN countrylanguage ON country.Code = countrylanguage.CountryCode
WHERE country.GovernmentForm LIKE '%Republic%' 
  AND country.LifeExpectancy > 70
  AND countrylanguage.isOfficial = "T";


-- Ottenere una lista unica di città (con popolazione > 5 milioni) capitali dei paesi... utilizza UNION
SELECT city.Name AS "Città"
FROM city
WHERE city.Population > 5000000

UNION

SELECT city.Name
FROM country JOIN city ON country.Capital = city.ID;


-- Si vuole recuperare dal database WORLD le lingue parlate per nazione con la rispettiva percentuale di utilizzo
SELECT 
    country.Name AS "Nazione", 
    countrylanguage.Language AS "Lingua", 
    countrylanguage.Percentage AS "Percentuale"
FROM country JOIN countrylanguage ON country.Code = countrylanguage.CountryCode;
-- WHERE countrylanguage.isOfficial = "T";


-- Si vuole recuperare dal database WORLD le nazioni e la percentuale della lingua più parlata
SELECT 
    country.Name AS "Nazione", 
    MAX(countrylanguage.Percentage) AS "Percentuale Max"
FROM country JOIN countrylanguage ON country.Code = countrylanguage.CountryCode
GROUP BY country.Name;


-- Unire gli esercizi 1e2 facendole diventare subQuery per mostrare la lingua più parlata di una nazione con la percentuale
SELECT 
    country.Name AS "Nazione", 
    countrylanguage.Language AS "Lingua più Parlata", 
    countrylanguage.Percentage AS "Percentuale"
FROM country
JOIN countrylanguage ON country.Code = countrylanguage.CountryCode
WHERE (countrylanguage.CountryCode, countrylanguage.Percentage) IN (
    -- Estraggo le coppie Codice - Percentuale Max
    SELECT CountryCode, MAX(Percentage)
    FROM countrylanguage
    GROUP BY CountryCode
)
ORDER BY country.Name;


/* 
	Scrivere una query che vada a prendere le nazioni mostrando come riposta
  se hanno o meno una superficie di 100.000 e se hanno registrato un IndepYear, 
	se è presente l'anno va mostrato, altrimenti si indica che non è presente
*/
SELECT 
    country.Name AS "Nazione",
    IF(country.SurfaceArea > 100000, 'Superiore a 100.000', 'Inferiore o uguale a 100.000') AS "Controllo Superficie",
    IF(country.IndepYear IS NOT NULL, country.IndepYear, 'Non presente') AS "Anno Indipendenza"
FROM country;


/*
	Recuperare e mostrare le città con il codice della nazione che indica l'utente. 
  Inoltre l'utente può decidere:
  - l'ordine con cui ordinare le città in maniera decrescente o meno
  - se filtrare le città con un minimo di popolazione
  - se mostrare il nome della nazione a cui fa riferimento il code
*/
SELECT 
    city.Name AS "Città", 
    city.Population AS "Popolazione",
    country.Name AS "Nazione"
FROM city JOIN country ON city.CountryCode = country.Code
WHERE city.CountryCode = 'ITA' AND city.Population >= 500000
ORDER BY city.Population DESC;

/*
	Creare una view di city del database workd su una query che mostra la città italiana
	Su questa view eseguire una query che mostra solo le città con una popolazione inferiore ai 100k
*/
CREATE OR REPLACE VIEW v_citta_italiane AS
SELECT 
    city.Name AS "Città", 
    city.Population AS "Popolazione"
FROM city
WHERE city.CountryCode = 'ITA';

SELECT * 
FROM v_citta_italiane
WHERE Popolazione < 100000;

-- -- -- -- -- -- -- -- -- -- -- -- -- 

WITH citta_italiane AS (
    SELECT 
        city.Name AS "Citta", 
        city.Population AS "Popolazione"
    FROM city
    WHERE city.CountryCode = 'ITA'
)

SELECT * 
FROM citta_italiane
WHERE Popolazione < 100000;