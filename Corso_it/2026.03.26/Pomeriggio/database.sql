-- ============================================================
--  SISTEMA DI GESTIONE CLINICA PRIVATA
--  Schema + Dati + Query
-- ============================================================
 CREATE DATABASE ospedale;
 
 USE ospedale;
 
-- ============================================================
--  1. CREAZIONE TABELLE
-- ============================================================
 
 
CREATE TABLE Specializzazione (
    id_specializzazione INT PRIMARY KEY AUTO_INCREMENT,
    nome                VARCHAR(100) NOT NULL UNIQUE
);
 
CREATE TABLE Medico (
    id_medico           INT PRIMARY KEY AUTO_INCREMENT,
    nome                VARCHAR(50)  NOT NULL,
    cognome             VARCHAR(50)  NOT NULL,
    id_specializzazione INT          NOT NULL,
    stipendio           DECIMAL(10,2) NOT NULL,
    data_assunzione     DATE         NOT NULL,
    CONSTRAINT fk_medico_spec  FOREIGN KEY (id_specializzazione)
        REFERENCES Specializzazione(id_specializzazione) ON DELETE RESTRICT,
    CONSTRAINT chk_stipendio   CHECK (stipendio > 0)
);
 
CREATE TABLE Paziente (
    id_paziente         INT PRIMARY KEY AUTO_INCREMENT,
    nome                VARCHAR(50)  NOT NULL,
    cognome             VARCHAR(50)  NOT NULL,
    data_nascita        DATE         NOT NULL,
    codice_fiscale      CHAR(16)     NOT NULL UNIQUE,
    telefono            VARCHAR(20),
    email               VARCHAR(100),
    data_registrazione  DATE         NOT NULL DEFAULT (CURRENT_DATE)
);
 
CREATE TABLE Reparto (
    id_reparto          INT PRIMARY KEY AUTO_INCREMENT,
    nome                VARCHAR(100) NOT NULL,
    numero_posti        INT          NOT NULL,
    CONSTRAINT chk_posti CHECK (numero_posti > 0)
);
 
CREATE TABLE Appuntamento (
    id_appuntamento     INT PRIMARY KEY AUTO_INCREMENT,
    id_paziente         INT          NOT NULL,
    id_medico           INT          NOT NULL,
    data_appuntamento   DATETIME     NOT NULL,
    stato               ENUM('prenotato','completato','annullato') NOT NULL DEFAULT 'prenotato',
    costo               DECIMAL(10,2) NOT NULL DEFAULT 0,
    CONSTRAINT fk_app_paziente FOREIGN KEY (id_paziente)
        REFERENCES Paziente(id_paziente) ON DELETE CASCADE,
    CONSTRAINT fk_app_medico   FOREIGN KEY (id_medico)
        REFERENCES Medico(id_medico) ON DELETE RESTRICT,
    CONSTRAINT chk_costo       CHECK (costo >= 0)
);
 
CREATE TABLE Prescrizione (
    id_prescrizione     INT PRIMARY KEY AUTO_INCREMENT,
    id_appuntamento     INT          NOT NULL,
    descrizione         TEXT         NOT NULL,
    data_prescrizione   DATE         NOT NULL,
    CONSTRAINT fk_pres_app FOREIGN KEY (id_appuntamento)
        REFERENCES Appuntamento(id_appuntamento) ON DELETE CASCADE
);
 
CREATE TABLE Ricovero (
    id_ricovero         INT PRIMARY KEY AUTO_INCREMENT,
    id_paziente         INT          NOT NULL,
    id_reparto          INT          NOT NULL,
    data_ingresso       DATE         NOT NULL,
    data_dimissione     DATE,
    CONSTRAINT fk_ric_paziente FOREIGN KEY (id_paziente)
        REFERENCES Paziente(id_paziente) ON DELETE CASCADE,
    CONSTRAINT fk_ric_reparto  FOREIGN KEY (id_reparto)
        REFERENCES Reparto(id_reparto) ON DELETE RESTRICT,
    CONSTRAINT chk_dimissione  CHECK (data_dimissione IS NULL OR data_dimissione > data_ingresso)
);
 
CREATE TABLE Pagamento (
    id_pagamento        INT PRIMARY KEY AUTO_INCREMENT,
    id_appuntamento     INT          NOT NULL,
    importo             DECIMAL(10,2) NOT NULL,
    metodo_pagamento    ENUM('contanti','carta','bonifico','assicurazione') NOT NULL,
    data_pagamento      DATE         NOT NULL,
    CONSTRAINT fk_pag_app FOREIGN KEY (id_appuntamento)
        REFERENCES Appuntamento(id_appuntamento) ON DELETE CASCADE,
    CONSTRAINT chk_importo CHECK (importo > 0)
);
 
 
-- ============================================================
--  2. POPOLAMENTO DATI
-- ============================================================
 
-- Specializzazioni (8)
INSERT INTO Specializzazione (nome) VALUES
('Cardiologia'),
('Neurologia'),
('Ortopedia'),
('Pediatria'),
('Oncologia'),
('Dermatologia'),
('Ginecologia'),
('Medicina Generale');
 
-- Reparti (5)
INSERT INTO Reparto (nome, numero_posti) VALUES
('Cardiologia',    20),
('Neurologia',     15),
('Ortopedia',      18),
('Pediatria',      12),
('Oncologia',      10);
 
-- Medici (10)
INSERT INTO Medico (nome, cognome, id_specializzazione, stipendio, data_assunzione) VALUES
('Marco',    'Ferrari',    1, 5200.00, '2018-03-01'),
('Laura',    'Ricci',      1, 4800.00, '2020-06-15'),
('Giovanni', 'Esposito',   2, 5500.00, '2016-09-10'),
('Chiara',   'Bianchi',    3, 4600.00, '2019-01-20'),
('Antonio',  'Conti',      4, 4200.00, '2021-04-05'),
('Sofia',    'Marino',     5, 6000.00, '2015-07-22'),
('Luca',     'Greco',      6, 3900.00, '2022-02-14'),
('Elena',    'Lombardi',   7, 4700.00, '2017-11-30'),
('Roberto',  'Moretti',    8, 3500.00, '2023-01-10'),
('Valeria',  'Fontana',    2, 5100.00, '2019-08-18');
 
-- Pazienti (20)
INSERT INTO Paziente (nome, cognome, data_nascita, codice_fiscale, telefono, email, data_registrazione) VALUES
('Alice',     'Russo',      '1990-04-12', 'RSSLCA90D52F205X', '3201234561', 'alice.russo@email.it',      '2022-01-10'),
('Bruno',     'Mancini',    '1978-08-23', 'MNCBRN78M23H501Y', '3312345672', 'bruno.mancini@email.it',    '2022-03-05'),
('Carla',     'Sorrentino', '1985-11-03', 'SRRCRL85S43F839Z', '3473456783', 'carla.s@email.it',          '2022-04-18'),
('Davide',    'De Luca',    '1962-01-30', 'DLCDVD62A30D612A', '3284567894', 'davide.dl@email.it',        '2021-11-22'),
('Emma',      'Barbieri',   '1995-07-07', 'BRBEMM95L47H501B', '3395678905', 'emma.b@email.it',           '2023-01-15'),
('Fabio',     'Rizzo',      '1980-12-15', 'RZZFBA80T15I533C', '3406789016', 'fabio.rizzo@email.it',      '2021-06-30'),
('Giulia',    'Ferrara',    '2001-03-22', 'FRRGLI01C62D612D', '3517890127', 'giulia.f@email.it',         '2023-03-08'),
('Haruto',    'Tanaka',     '1975-09-18', 'TNKHRT75P18Z336E', '3628901238', 'h.tanaka@email.it',         '2020-09-12'),
('Irene',     'Cattaneo',   '1988-06-05', 'CTTIRN88H45F205F', '3739012349', 'irene.c@email.it',          '2022-07-20'),
('Jacopo',    'Pellegrini', '1970-02-28', 'PLLJCP70B28H501G', '3840123450', 'jacopo.p@email.it',         '2021-02-14'),
('Katia',     'Galli',      '1993-10-10', 'GLLKTA93R50F205H', '3901234561', 'katia.g@email.it',          '2022-10-01'),
('Lorenzo',   'Martini',    '1967-05-17', 'MRTLRZ67E17H501I', '3312345672', 'lorenzo.m@email.it',        '2020-05-25'),
('Miriam',    'Costa',      '1999-01-25', 'CSTMRM99A65H264L', '3423456783', 'miriam.c@email.it',         '2023-06-12'),
('Nicola',    'Santoro',    '1984-07-04', 'SNTNCL84L04F839M', '3534567894', 'nicola.s@email.it',         '2021-08-09'),
('Olimpia',   'Marini',     '1956-03-31', 'MRNLMP56C71H501N', '3645678905', 'olimpia.m@email.it',        '2020-03-17'),
('Paolo',     'Bruno',      '1972-11-08', 'BRNPLA72S08D612O', '3756789016', 'paolo.b@email.it',          '2022-11-30'),
('Rossella',  'Vitale',     '2005-08-14', 'VTLRSL05M54H501P', '3867890127', 'rossella.v@email.it',       '2023-09-05'),
('Simone',    'Orlando',    '1991-04-21', 'RLNSMN91D21F205Q', '3978901238', 'simone.o@email.it',         '2022-02-28'),
('Teresa',    'Amato',      '1963-12-09', 'MTATRS63T49H501R', '3489012349', 'teresa.a@email.it',         '2021-04-03'),
('Ugo',       'Caruso',     '1948-06-30', 'CRSUGU48H30H264S', '3590123450', 'ugo.caruso@email.it',       '2020-12-01');
 
-- Appuntamenti (30)
INSERT INTO Appuntamento (id_paziente, id_medico, data_appuntamento, stato, costo) VALUES
(1,  1, '2024-01-10 09:00', 'completato', 120.00),
(1,  2, '2024-02-15 10:30', 'completato', 110.00),
(1,  3, '2024-04-20 14:00', 'completato', 150.00),
(1,  6, '2024-07-05 11:00', 'completato', 200.00),
(1,  9, '2024-11-12 09:30', 'completato', 80.00),
(2,  1, '2024-01-22 08:30', 'completato', 120.00),
(2,  4, '2024-03-10 15:00', 'completato', 130.00),
(2,  8, '2024-06-18 10:00', 'completato', 140.00),
(2,  9, '2024-09-25 14:30', 'annullato',  80.00),
(3,  3, '2024-02-08 11:00', 'completato', 150.00),
(3,  5, '2024-05-14 09:00', 'completato', 100.00),
(3,  6, '2024-08-22 16:00', 'completato', 200.00),
(4,  1, '2024-01-30 08:00', 'completato', 120.00),
(4,  2, '2024-04-12 13:00', 'completato', 110.00),
(4,  7, '2024-07-19 10:00', 'completato', 90.00),
(4,  9, '2024-10-05 09:00', 'completato', 80.00),
(5,  5, '2024-03-25 14:00', 'completato', 100.00),
(5,  6, '2024-06-30 11:30', 'completato', 200.00),
(6,  3, '2024-02-20 10:00', 'completato', 150.00),
(6,  8, '2024-05-08 15:00', 'completato', 140.00),
(7,  7, '2024-04-02 09:00', 'completato', 90.00),
(8,  4, '2024-06-10 10:30', 'completato', 130.00),
(9,  2, '2024-07-15 14:00', 'completato', 110.00),
(10, 1, '2024-08-20 08:30', 'completato', 120.00),
(11, 5, '2024-09-10 11:00', 'completato', 100.00),
(12, 6, '2024-09-28 13:00', 'completato', 200.00),
(13, 9, '2024-10-15 09:30', 'prenotato',  80.00),
(14, 3, '2024-10-22 10:00', 'completato', 150.00),
(15, 8, '2024-11-01 14:30', 'completato', 140.00),
(16, 7, '2024-11-18 11:00', 'prenotato',  90.00);
 
-- Prescrizioni (15)
INSERT INTO Prescrizione (id_appuntamento, descrizione, data_prescrizione) VALUES
(1,  'Cardioaspirina 100mg 1 cp/die. Controllo ECG fra 3 mesi.',          '2024-01-10'),
(3,  'Riposo assoluto per 2 settimane. Antinfiammatori se necessario.',    '2024-02-08'),
(6,  'Monitoraggio pressione quotidiano. Beta-bloccante 50mg.',           '2024-01-22'),
(10, 'RMN cerebrale urgente. Levetiracetam 500mg x2/die.',                '2024-02-08'),
(12, 'Chemioterapia ciclo 3. Antiemetico pre-trattamento.',               '2024-08-22'),
(13, 'Radiografia anca dx. Antidolorifico FANS.',                         '2024-01-30'),
(15, 'Crema cortisonica 1%. Evitare esposizione solare diretta.',         '2024-07-19'),
(17, 'Vaccino influenzale. Paracetamolo al bisogno.',                     '2024-03-25'),
(19, 'Fisioterapia 10 sedute. Ibuprofene 400mg se dolore acuto.',         '2024-02-20'),
(20, 'Ecografia addome. Dieta iposodica. Omeprazolo 20mg/die.',           '2024-05-08'),
(22, 'Controllo glicemia. Metformina 500mg x2/die.',                      '2024-06-10'),
(24, 'Holter cardiaco 24h. Bisoprololo 5mg.',                             '2024-08-20'),
(26, 'PET scan. Secondo ciclo chemioterapico.',                           '2024-09-28'),
(28, 'Elettromiografia. Pregabalin 75mg x2/die.',                        '2024-10-22'),
(29, 'Ecografia ginecologica. Acido folico 400mcg/die.',                  '2024-11-01');
 
-- Ricoveri (10)
INSERT INTO Ricovero (id_paziente, id_reparto, data_ingresso, data_dimissione) VALUES
(4,  1, '2024-01-05', '2024-01-12'),
(1,  1, '2024-02-20', '2024-03-02'),
(12, 2, '2024-03-10', '2024-03-18'),
(8,  3, '2024-04-01', '2024-04-08'),
(15, 2, '2024-04-15', '2024-04-25'),
(3,  5, '2024-05-20', '2024-06-10'),
(20, 1, '2024-06-05', '2024-06-15'),
(4,  3, '2024-07-01', '2024-07-09'),  -- paziente 4 secondo ricovero
(1,  1, '2024-09-10', '2024-09-20'),  -- paziente 1 secondo ricovero
(19, 4, '2024-10-01', NULL);          -- ancora ricoverato
 
-- Pagamenti (20)
INSERT INTO Pagamento (id_appuntamento, importo, metodo_pagamento, data_pagamento) VALUES
(1,  120.00, 'carta',         '2024-01-10'),
(2,  110.00, 'contanti',      '2024-02-15'),
(3,  150.00, 'carta',         '2024-04-20'),
(4,  200.00, 'assicurazione', '2024-07-05'),
(5,   80.00, 'contanti',      '2024-11-12'),
(6,  120.00, 'carta',         '2024-01-22'),
(7,  130.00, 'bonifico',      '2024-03-10'),
(8,  140.00, 'carta',         '2024-06-18'),
(10, 150.00, 'assicurazione', '2024-02-08'),
(11, 100.00, 'contanti',      '2024-05-14'),
(12, 200.00, 'assicurazione', '2024-08-22'),
(13, 120.00, 'carta',         '2024-01-30'),
(14, 110.00, 'contanti',      '2024-04-12'),
(17, 100.00, 'carta',         '2024-03-25'),
(18, 200.00, 'assicurazione', '2024-06-30'),
(19, 150.00, 'bonifico',      '2024-02-20'),
(20, 140.00, 'carta',         '2024-05-08'),
(22, 130.00, 'contanti',      '2024-06-10'),
(24, 120.00, 'carta',         '2024-08-20'),
(26, 200.00, 'assicurazione', '2024-09-28');