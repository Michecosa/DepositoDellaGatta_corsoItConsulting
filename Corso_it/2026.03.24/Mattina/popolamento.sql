-- ============================================================
--  POPOLAMENTO: e_commerce_db — Negozio di Sogni Lucidi
-- ============================================================

USE e_commerce_db;

-- ------------------------------------------------------------
-- CATEGORIA
-- ------------------------------------------------------------
INSERT INTO CATEGORIA (nome) VALUES
('Sogni Lucidi Base'),
('Sogni Avanzati & Ricorrenti'),
('Ambientazioni Oniriche'),
('Accessori del Sognatore'),
('Sogni Collezionabili — Edizione Limitata'),
('Esperienze Sensoriali Notturne'),
('Incubi Addomesticati');

-- ------------------------------------------------------------
-- PRODOTTO
-- ------------------------------------------------------------
INSERT INTO PRODOTTO (nome, prezzo, descrizione, id_categoria) VALUES

-- Sogni Lucidi Base (id_categoria = 1)
('Il Primo Volo',
 9.99,
 'Il classico intramontabile. Ti ritrovi sul cornicione di un palazzo anni ''50, poi ti accorgi che puoi volare. Venduto con cuscino di atterraggio morbido e nessuna sensazione di caduta libera al risveglio.',
 1),

('Stanza Infinita',
 12.50,
 'Una stanza bianca con porte che aprono su altre porte. Perfetto per chi vuole esplorare senza uscire di casa. Durata media: 7 ore soggettive (45 minuti reali).',
 1),

('Esame che Non Hai Studiato',
 4.99,
 'Un classico del repertorio ansioso, rielaborato in chiave comica. Questa versione include la variante in cui improvvisamente *sai tutto* e stupefaci il professore. Catartico.',
 1),

-- Sogni Avanzati & Ricorrenti (id_categoria = 2)
('Il Labirinto di Vetro Liquido',
 34.90,
 'Architettura impossibile: pareti che si piegano come acqua, pavimenti che riflettono cieli alternativi. Include un filo d''Arianna che cambia colore in base all''umore del sognatore.',
 2),

('Ricordo Falso — Infanzia al Mare',
 27.00,
 'Un''estate che non hai vissuto ma che sentirai tua. Odore di salsedine, un gelato alla menta, una barca rossa. Certificato di autenticità emotiva incluso.',
 2),

('L''Altro Te',
 45.00,
 'Incontri una versione alternativa di te stesso. Cosa fa di diverso? Perché sembra più a suo agio? Il sogno non risponde, ma fa le domande giuste. Adatto a sognatori con almeno 6 mesi di esperienza.',
 2),

-- Ambientazioni Oniriche (id_categoria = 3)
('Città Sommersa — Edizione Atlantide Minore',
 19.99,
 'Metropoli anni ''30 sotto venti metri d''acqua turchese. Gli abitanti non si sono accorti di niente. I tram passano regolari, i bar sono aperti, il sindaco tiene un discorso.',
 3),

('Foresta dei Numeri Primi',
 22.00,
 'Ogni albero corrisponde a un numero primo. Il 2 è un pino solitario. Il 7.919 è una sequoia immensa. Consigliato ai matematici e ai curiosi esistenziali.',
 3),

('Il Mercato Notturno dei Sogni Usati',
 15.50,
 'Un bazar caotico dove si vendono sogni di seconda mano. Trovi frammenti di sogni altrui, ricordi orfani, emozioni smarrite. Attenzione: alcuni sogni potrebbero appartenerti già.',
 3),

('Biblioteca di Alessandria — Ala Segreta',
 29.90,
 'La parte che non bruciò. Scaffali alti quanto il cielo, libri scritti in lingue che capisci solo mentre dormi. Un bibliotecario che non ha mai dormito.',
 3),

-- Accessori del Sognatore (id_categoria = 4)
('Bussola dei Sogni (non indica il nord)',
 8.00,
 'Punta verso il punto più interessante del sogno in corso. Non funziona nella veglia. Non funziona nemmeno in tutti i sogni. Questo fa parte del design.',
 4),

('Taccuino dell''Alba — Versione Onirica',
 11.00,
 'Un quaderno che appare accanto a te nel sogno subito dopo un momento importante. Puoi scriverci dentro. Al risveglio troverai il taccuino reale sul comodino, vuoto — ma con la sensazione giusta.',
 4),

('Occhiali per Leggere i Cartelli Onirici',
 17.99,
 'Nei sogni i testi cambiano se li guardi troppo a lungo. Questi occhiali li stabilizzano per 3-4 secondi. Utili per decifrare nomi di vie, insegne di negozi, istruzioni di emergenza.',
 4),

-- Sogni Collezionabili — Edizione Limitata (id_categoria = 5)
('Sogno N.0 — La Notte Prima dell''Invenzione della Ruota',
 199.00,
 'Edizione limitata a 77 esemplari. Sei nell''Anatolia preistorica. Qualcuno ha appena avuto QUELL''IDEA. Tu sei lì. L''umanità non è ancora la stessa.',
 5),

('Sogno Ospite: La Caduta di Costantinopoli (29 Maggio 1453, ore 3:14)',
 149.00,
 'Sogno storico certificato. Non sei un combattente. Sei un fornaio nel quartiere genovese che non capisce ancora cosa stia succedendo. Lingua: latino con sottotitoli.',
 5),

('Il Sogno che Nikola Tesla Non Annotò',
 249.00,
 'Ricostruzione speculativa. Una bobina che ronza nel buio, una formula che quasi si capisce. Non sono responsabili per eventuali invenzioni derivate.',
 5),

-- Esperienze Sensoriali Notturne (id_categoria = 6)
('Sinestesia da Concerto',
 38.00,
 'Senti i colori e vedi la musica. Un''orchestra suona una sinfonia in do maggiore e il cielo diventa arancione con venature di rame. Durata: un intero terzo movimento.',
 6),

('Pioggia che Sale',
 24.99,
 'Fisica invertita, solo per la pioggia. Tutto il resto funziona normalmente. L''effetto è stranamente calmante.',
 6),

('Gravità Variabile — Opzione Lunare',
 31.00,
 'Pesare un sesto. Ogni passo è un salto. Ottimo per chi ha problemi alle ginocchia nella vita reale. Non indicato per chi soffre di mal di mare.',
 6),

-- Incubi Addomesticati (id_categoria = 7)
('L''Inseguitore Stanco',
 13.99,
 'C''è qualcosa che ti insegue, ma si ferma spesso a riprendere fiato. Alla fine si siede e ti offre una ciambella. Versione addomesticata dell''incubo classico. Consigliato come primo approccio terapeutico.',
 7),

('Casa che Cambia Planimetria',
 18.00,
 'La tua casa ha stanze che non ricordavi. Alcune sono belle. Una è un po'' inquietante ma in modo che puoi gestire. C''è anche un giardino che non esiste, con un pozzo decorativo.',
 7),

('Denti che Cadono (Edizione Gioielleria)',
 21.50,
 'Il sogno più comune al mondo, rielaborato. I denti non cadono: si trasformano in pietre preziose. Puoi raccoglierle. Al risveglio non ci sono, ma per un secondo ci hai creduto.',
 7);

-- ------------------------------------------------------------
-- CLIENTE
-- ------------------------------------------------------------
INSERT INTO CLIENTE (nome, cognome, email, telefono) VALUES
('Morfeo',      'Ipnos',         'morfeo@olimpo.gr',           '+30 210 0001111'),
('Salvador',    'Dalì',          'orologimorbidi@surreal.cat',  '+34 972 000 001'),
('Lucia',       'Wanderer',      'lucia.wanderer@sogni.io',     '+39 02 1234567'),
('Carl',        'Jung',          'archetypes@animus.ch',        '+41 44 000 0001'),
('Hypnagogica', 'De Luca',       'hypna@insonnia.it',           '+39 06 9876543'),
('Boris',       'Oneirov',       'boris.oneirov@kremlin.ru',    '+7 495 0000000'),
('Penelope',    'Tessitrice',    'penelope@itaca.gr',           '+30 210 0002222'),
('Noctua',      'Ferri',         'noctua.ferri@buio.it',        '+39 055 1112233');

-- ------------------------------------------------------------
-- ORDINE
-- ------------------------------------------------------------
INSERT INTO ORDINE (data_ordine, stato, id_cliente) VALUES
('2024-01-15 02:33:00', 'Consegnato',     1),   -- Morfeo
('2024-02-28 03:17:00', 'Consegnato',     2),   -- Dalì
('2024-03-10 00:05:00', 'Consegnato',     3),   -- Lucia
('2024-04-01 04:44:00', 'Spedito',        4),   -- Jung
('2024-05-20 01:11:00', 'In elaborazione',5),   -- Hypnagogica
('2024-06-06 06:06:00', 'Consegnato',     6),   -- Boris
('2024-07-14 22:59:00', 'Cancellato',     7),   -- Penelope (ha tessuto qualcos''altro)
('2024-08-03 03:00:00', 'Spedito',        8),   -- Noctua
('2024-09-09 09:09:00', 'Consegnato',     3),   -- Lucia (secondo ordine)
('2024-10-31 23:59:59', 'In elaborazione',1);   -- Morfeo (notte di Halloween — stagione alta)

-- ------------------------------------------------------------
-- ORDINE_PRODOTTO
-- ------------------------------------------------------------

-- Ordine 1 — Morfeo: si rifornisce di base
INSERT INTO ORDINE_PRODOTTO (id_ordine, id_prodotto, quantita) VALUES
(1, 1,  50),   -- Il Primo Volo x50 (uso professionale)
(1, 2,  20),   -- Stanza Infinita x20
(1, 14, 3);    -- Sogno N.0 — La Notte Prima dell''Invenzione della Ruota x3

-- Ordine 2 — Dalì: ovviamente acquista cose strane
INSERT INTO ORDINE_PRODOTTO (id_ordine, id_prodotto, quantita) VALUES
(2, 10, 1),   -- Biblioteca di Alessandria — Ala Segreta
(2, 17, 2),   -- Pioggia che Sale x2
(2, 21, 1),   -- Denti che Cadono (Edizione Gioielleria)
(2, 7,  1);   -- Città Sommersa

-- Ordine 3 — Lucia: primo acquisto, curiosa
INSERT INTO ORDINE_PRODOTTO (id_ordine, id_prodotto, quantita) VALUES
(3, 1,  1),   -- Il Primo Volo
(3, 11, 1),   -- Bussola dei Sogni
(3, 12, 1);   -- Taccuino dell''Alba

-- Ordine 4 — Jung: acquisti tematici sull''inconscio
INSERT INTO ORDINE_PRODOTTO (id_ordine, id_prodotto, quantita) VALUES
(4, 6,  1),   -- L''Altro Te
(4, 5,  1),   -- Ricordo Falso — Infanzia al Mare
(4, 4,  1),   -- Labirinto di Vetro Liquido
(4, 9,  1);   -- Mercato Notturno dei Sogni Usati

-- Ordine 5 — Hypnagogica: soffre di insonnia, cerca esperienze sensoriali
INSERT INTO ORDINE_PRODOTTO (id_ordine, id_prodotto, quantita) VALUES
(5, 16, 1),   -- Sinestesia da Concerto
(5, 18, 3),   -- Gravità Variabile x3
(5, 13, 2);   -- Occhiali per Leggere i Cartelli x2

-- Ordine 6 — Boris: collezionista serioso
INSERT INTO ORDINE_PRODOTTO (id_ordine, id_prodotto, quantita) VALUES
(6, 14, 1),   -- Sogno N.0 — Invenzione della Ruota
(6, 15, 1),   -- Sogno Ospite: Costantinopoli
(6, 16, 1);   -- Tesla

-- Ordine 7 — Penelope (cancellato — stava aspettando qualcuno)
INSERT INTO ORDINE_PRODOTTO (id_ordine, id_prodotto, quantita) VALUES
(7, 5,  1),   -- Ricordo Falso — Infanzia al Mare
(7, 17, 1);   -- Pioggia che Sale

-- Ordine 8 — Noctua: un po'' di tutto, inclusi gli incubi addomesticati
INSERT INTO ORDINE_PRODOTTO (id_ordine, id_prodotto, quantita) VALUES
(8, 19, 2),   -- L''Inseguitore Stanco x2
(8, 20, 1),   -- Casa che Cambia Planimetria
(8, 8,  1),   -- Foresta dei Numeri Primi
(8, 3,  1);   -- Esame che Non Hai Studiato

-- Ordine 9 — Lucia (secondo ordine, ora è esperta)
INSERT INTO ORDINE_PRODOTTO (id_ordine, id_prodotto, quantita) VALUES
(9, 6,  1),   -- L''Altro Te
(9, 4,  1),   -- Labirinto di Vetro Liquido
(9, 21, 1);   -- Denti che Cadono (Edizione Gioielleria)

-- Ordine 10 — Morfeo (Halloween, scorte stagionali)
INSERT INTO ORDINE_PRODOTTO (id_ordine, id_prodotto, quantita) VALUES
(10, 19, 100),  -- L''Inseguitore Stanco x100 (domanda altissima stanotte)
(10, 20, 75),   -- Casa che Cambia Planimetria x75
(10, 21, 60),   -- Denti che Cadono x60
(10, 3,  200);  -- Esame che Non Hai Studiato x200 (sessione universitaria autunnale)