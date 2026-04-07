# **Titolo: Gestione di una Scuola – Esercizio OOP completo**

**Obiettivo:**
Progettare un sistema che rappresenti una scuola con più tipologie di persone (studenti e docenti), applicando tutte le regole dell'OOP:
* **Incapsulamento:** dati privati con metodi getter e setter
* **Ereditarietà:** classi che estendono una classe base comune
* **Polimorfismo:** metodo sovrascritto chiamato su riferimenti generici
* **Astrazione:** uso di classe astratta e interfaccia

**Traccia:**
**1. Classe Astratta Persona**
* Attributi privati: **nome (String)**, **età (int)**
* Costruttore con parametri
* Metodi getter/setter
* Metodo astratto **descriviRuolo()**

**2. Interfaccia Registrabile**
* Metodo: **registrazione()** che stampa la modalità di registrazione della persona

**3. Classe Studente (extends Persona, implements Registrabile)**
Ogni Studente deve avere poi due figli specifici
* Attributo: **classeFrequentata (String)**
* Override di **descriviRuolo()** che stampa "Sono uno studente della classe ..."
* Implementazione di **registrazione()** che stampa "Registrazione tramite modulo online"
* Metodo di stampa dei voti ricevuti

**4. Classe Docente (extends Persona, implements Registrabile)**
Ogni docente deve avere poi due figli specifici
* Attributo: **materia (String)**
* Override di **descriviRuolo()** che stampa "Sono un docente di ..."
* Implementazione di **registrazione()** che stampa "Registrazione tramite segreteria didattica"
* Una **Lista StudentiMateria(Studente)**
* Un metodo per assegnare voti

**5. Classe Principale GestioneScuola**
* Un menu per gestire la creazione di studenti specifici e di docenti specifici
* Poter scegliere con quale docente assegnare voto a quale studente
* Poter scegliere da quale studente stampare i voti ricevuti

**Requisiti Obbligatori:**
* I campi devono essere privati $\rightarrow$ incapsulamento
* L'interfaccia e la classe astratta devono essere usate $\rightarrow$ astrazione
* Ogni studente sarà assegnato a un docente in una lista di studentiSpecificiX e solo il docenteSpecificoX potrà avere accesso a quella lista