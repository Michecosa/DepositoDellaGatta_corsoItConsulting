-- Crea il database (se non esiste)
CREATE DATABASE IF NOT EXISTS magazzino;

USE magazzino;

-- Crea la tabella ordini
CREATE TABLE ordini (
    id INT NOT NULL AUTO_INCREMENT,
    cliente VARCHAR(100) NOT NULL,
    prodotto VARCHAR(100) NOT NULL,
    quantita INT NOT NULL DEFAULT 1,
    stato VARCHAR(20) NOT NULL DEFAULT 'CREATO',
    PRIMARY KEY (id),
    CONSTRAINT chk_stato CHECK (
        stato IN ('CREATO', 'IN PREPARAZIONE', 'SPEDITO', 'CONSEGNATO')
    ),
    CONSTRAINT chk_quantita CHECK (quantita > 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dati di esempio
INSERT INTO ordini (cliente, prodotto, quantita, stato) VALUES
    ('Mario Rossi', 'Laptop Dell XPS', 2, 'CREATO'),
    ('Laura Bianchi', 'Mouse Logitech MX', 5, 'IN PREPARAZIONE'),
    ('Giuseppe Verdi', 'Monitor LG 27"', 1, 'SPEDITO'),
    ('Anna Ferrari', 'Tastiera Meccanica', 3, 'CONSEGNATO');