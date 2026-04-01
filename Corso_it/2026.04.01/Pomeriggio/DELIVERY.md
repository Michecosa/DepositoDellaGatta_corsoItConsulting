**Obiettivo:**
Comprendere la differenza tra **classe astratta** e **interfaccia**, il loro utilizzo e come si applica l'astrazione per modellare comportamenti comuni e specializzati tra classi diverse.

**Traccia:**
Progetta un sistema Java che simuli un servizio di consegna (delivery) in cui esistono diversi tipi di mezzi che effettuano le consegne in modo differente.

**1. Classe Astratta VeicoloConsegna**
* **Attributi:**
    * `targa` (String)
    * `caricoMassimo` (float)
* **Metodo astratto:**
    * `consegnaPacco(String destinazione)`
* **Metodi concreti:**
    * `stampaInfo()`: stampa i dati del veicolo

**2. Interfaccia Tracciabile**
* **Metodo:**
    * `tracciaConsegna(String codiceTracking)`: simula la tracciabilità della spedizione

**3. Classi Concrete**
Crea almeno due classi che estendono **VeicoloConsegna** e implementano **Tracciabile**:
* **Furgone**: stampa che sta consegnando via strada con targa e destinazione
* **Drone**: stampa che sta volando verso la destinazione e fornisce un tracking automatico

**4. Classe Principale ConsegnaManager**
* Crea una lista o array di oggetti `VeicoloConsegna`
* Permetti all'utente di aggiungere un veicolo (furgone o drone), impostare i dati, e chiamare i metodi `consegnaPacco()` e `tracciaConsegna()`
* Dimostra il concetto di polimorfismo e astrazione (lavorando con riferimenti astratti)

**Requisiti Tecnici:**
* Usare almeno una classe astratta con metodo astratto
* Usare un'interfaccia implementata da più classi

**Estensione Facoltativa:**
Aggiungi un controllo del carico: se la consegna supera il `caricoMassimo`, il metodo `consegnaPacco()` stampa un errore.