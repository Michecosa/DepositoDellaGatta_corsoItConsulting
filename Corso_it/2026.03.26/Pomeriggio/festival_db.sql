-- ============================================================
-- SISTEMA DI GESTIONE FESTIVAL MUSICALE INTERNAZIONALE
-- ============================================================

-- Creazione del database
DROP DATABASE IF EXISTS festival_musicale;
CREATE DATABASE festival_musicale;
USE festival_musicale;

-- ============================================================
-- TABELLE
-- ============================================================

-- Palchi
CREATE TABLE Palco (
    id_palco     INT AUTO_INCREMENT PRIMARY KEY,
    nome         VARCHAR(100) NOT NULL UNIQUE,
    capienza     INT NOT NULL CHECK (capienza > 0),
    tipo         ENUM('principale','secondario','acustico','elettronico') NOT NULL,
    copertura    ENUM('aperto','coperto') NOT NULL DEFAULT 'aperto'
);

-- Artisti (singoli o band)
CREATE TABLE Artista (
    id_artista   INT AUTO_INCREMENT PRIMARY KEY,
    nome         VARCHAR(150) NOT NULL,
    tipo         ENUM('singolo','band') NOT NULL,
    genere       VARCHAR(80),
    nazionalita  VARCHAR(80),
    cachet       DECIMAL(10,2) NOT NULL CHECK (cachet >= 0)
);

-- Performance
CREATE TABLE Performance (
    id_performance INT AUTO_INCREMENT PRIMARY KEY,
    id_artista     INT NOT NULL,
    id_palco       INT NOT NULL,
    data_esibizione DATE NOT NULL,
    ora_inizio     TIME NOT NULL,
    ora_fine       TIME NOT NULL,
    durata_min     INT GENERATED ALWAYS AS (TIMESTAMPDIFF(MINUTE, ora_inizio, ora_fine)) STORED,
    CONSTRAINT fk_perf_artista FOREIGN KEY (id_artista) REFERENCES Artista(id_artista) ON DELETE CASCADE,
    CONSTRAINT fk_perf_palco   FOREIGN KEY (id_palco)   REFERENCES Palco(id_palco)   ON DELETE RESTRICT,
    CONSTRAINT chk_orario      CHECK (ora_fine > ora_inizio)
);

-- Spettatori
CREATE TABLE Spettatore (
    id_spettatore INT AUTO_INCREMENT PRIMARY KEY,
    nome          VARCHAR(100) NOT NULL,
    cognome       VARCHAR(100) NOT NULL,
    email         VARCHAR(150) NOT NULL UNIQUE,
    telefono      VARCHAR(20),
    data_nascita  DATE
);

-- Biglietti
CREATE TABLE Biglietto (
    id_biglietto   INT AUTO_INCREMENT PRIMARY KEY,
    id_performance INT NOT NULL,
    id_spettatore  INT NOT NULL,
    prezzo         DECIMAL(8,2) NOT NULL CHECK (prezzo >= 0),
    tipo_posto     ENUM('standard','vip','golden_circle') NOT NULL DEFAULT 'standard',
    CONSTRAINT fk_big_performance FOREIGN KEY (id_performance) REFERENCES Performance(id_performance) ON DELETE CASCADE,
    CONSTRAINT fk_big_spettatore  FOREIGN KEY (id_spettatore)  REFERENCES Spettatore(id_spettatore)  ON DELETE CASCADE,
    CONSTRAINT uq_big UNIQUE (id_performance, id_spettatore)
);

-- Pagamenti
CREATE TABLE Pagamento (
    id_pagamento   INT AUTO_INCREMENT PRIMARY KEY,
    id_biglietto   INT NOT NULL UNIQUE,
    importo        DECIMAL(8,2) NOT NULL CHECK (importo >= 0),
    metodo         ENUM('carta','contanti','bonifico','paypal') NOT NULL,
    data_pagamento DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    stato          ENUM('completato','in_attesa','fallito') NOT NULL DEFAULT 'completato',
    CONSTRAINT fk_pag_biglietto FOREIGN KEY (id_biglietto) REFERENCES Biglietto(id_biglietto) ON DELETE CASCADE
);

-- Sponsor
CREATE TABLE Sponsor (
    id_sponsor  INT AUTO_INCREMENT PRIMARY KEY,
    nome        VARCHAR(150) NOT NULL UNIQUE,
    settore     VARCHAR(100),
    budget      DECIMAL(12,2) CHECK (budget >= 0),
    contatto    VARCHAR(150)
);

-- Sponsorizzazioni (Sponsor - Performance N-M)
CREATE TABLE Sponsorizzazione (
    id_sponsor     INT NOT NULL,
    id_performance INT NOT NULL,
    importo        DECIMAL(10,2) NOT NULL CHECK (importo > 0),
    PRIMARY KEY (id_sponsor, id_performance),
    CONSTRAINT fk_spon_sponsor     FOREIGN KEY (id_sponsor)     REFERENCES Sponsor(id_sponsor)         ON DELETE CASCADE,
    CONSTRAINT fk_spon_performance FOREIGN KEY (id_performance) REFERENCES Performance(id_performance) ON DELETE CASCADE
);

-- Collaborazioni tra artisti (N-M auto-referenziale)
CREATE TABLE Collaborazione (
    id_artista1 INT NOT NULL,
    id_artista2 INT NOT NULL,
    tipo        VARCHAR(100),
    anno        YEAR,
    PRIMARY KEY (id_artista1, id_artista2),
    CONSTRAINT fk_collab_a1 FOREIGN KEY (id_artista1) REFERENCES Artista(id_artista) ON DELETE CASCADE,
    CONSTRAINT fk_collab_a2 FOREIGN KEY (id_artista2) REFERENCES Artista(id_artista) ON DELETE CASCADE,
    CONSTRAINT chk_collab_diversi CHECK (id_artista1 <> id_artista2)
);

-- Staff tecnico
CREATE TABLE Staff (
    id_staff  INT AUTO_INCREMENT PRIMARY KEY,
    nome      VARCHAR(100) NOT NULL,
    cognome   VARCHAR(100) NOT NULL,
    ruolo     ENUM('fonico','luci','sicurezza','logistica','regia') NOT NULL,
    email     VARCHAR(150) UNIQUE,
    tariffa   DECIMAL(8,2) CHECK (tariffa >= 0)
);

-- Assegnazione Staff - Palco
CREATE TABLE AssegnazioneStaff (
    id_staff  INT NOT NULL,
    id_palco  INT NOT NULL,
    data      DATE NOT NULL,
    ore_lavorate DECIMAL(4,1) CHECK (ore_lavorate > 0),
    PRIMARY KEY (id_staff, id_palco, data),
    CONSTRAINT fk_as_staff FOREIGN KEY (id_staff) REFERENCES Staff(id_staff) ON DELETE CASCADE,
    CONSTRAINT fk_as_palco FOREIGN KEY (id_palco) REFERENCES Palco(id_palco) ON DELETE CASCADE
);