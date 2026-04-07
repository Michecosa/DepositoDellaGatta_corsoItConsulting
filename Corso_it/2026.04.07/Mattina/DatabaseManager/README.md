**Design pattern ES Singleton**

Realizza una classe **DatabaseManager** che segua il pattern Singleton. La classe deve:

1. Avere un **costruttore privato** e un **metodo statico getInstance()** che restituisce sempre la stessa istanza.
2. Simulare una connessione al database con un **metodo connect()** che:
    * Stampi un messaggio del tipo: "Connessione stabilita. Connessioni attive: X"
    * Incrementi un contatore interno ogni volta che viene chiamato.
3. Fornire un **metodo getConnectionCount()** che restituisca il numero totale di connessioni effettuate.

**Nel main:**
* Dimostra che l’oggetto utilizzato è sempre lo stesso.
* Stampa il numero totale di connessioni con `getConnectionCount()`.
* Crea il menu per la gestione del salvataggio e richiamo dei dati.

**Funzionalità:**
* Salvare un dato del DB
* Richiamare un dato dal DB
* Legare la possibilità di creare o richiamare un dato ad un Utente (Se volete singleton)