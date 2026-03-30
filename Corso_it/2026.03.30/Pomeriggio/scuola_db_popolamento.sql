CREATE DATABASE scuola;
USE scuola;

CREATE TABLE studenti (
    id INT PRIMARY KEY AUTO_INCREMENT, -- Genera l'ID univoco automaticamente
    nome VARCHAR(50) NOT NULL, -- Testo fino a 50 caratteri, obbligatorio
    voto INT CHECK (voto >= 0 AND voto <= 10) -- Vincolo: il voto deve essere tra 0 e 10
);

INSERT INTO studenti (nome, voto) VALUES ('Miche', 10);
INSERT INTO studenti (nome, voto) VALUES ('Mario', 7);
INSERT INTO studenti (nome, voto) VALUES ('Andrea', 6);


SELECT * FROM studenti;