# **TRACCIA**

**UN ORDINE PUÒ ESSERE GESTITO CON MODALITÀ DIVERSE DI EVASIONE, AD ESEMPIO:**
* EVASIONE NORMALE
* EVASIONE PRIORITARIA
* EVASIONE CONTROLLATA

**LA MODALITÀ DA USARE DIPENDE DALLO STATO DEL CENTROPRIORITA, CHE RAPPRESENTA LA SITUAZIONE OPERATIVA DEL SISTEMA.**

**GLI STATI POSSIBILI POSSONO ESSERE:**
* NORMALE
* PRIORITA
* CONTROLLO

**QUANDO CAMBIA QUESTO STATO, UN OBSERVER DEVE RICEVERE LA NOTIFICA E AGGIORNARE LA STRATEGY ASSOCIATA ALL'ORDINE.**

---

### PARTE 1 – STRATEGY
Crea un'interfaccia chiamata **StrategiaEvasione** con un metodo, ad esempio:
* **eseguiEvasione()**

**Crea almeno tre strategie concrete:**
* EVASIONENORMALE
* EVASIONEPRIORITARIA
* EVASIONECONTROLLATA

**Ogni strategia deve avere un comportamento diverso.**
**Esempio:**
* EVASIONENORMALE $\rightarrow$ stampa "ORDINE EVASO CON PROCEDURA STANDARD" e PREZZO + 5%
* EVASIONEPRIORITARIA $\rightarrow$ stampa "ORDINE EVASO CON CORSIA PRIORITARIA" e PREZZO + 15%
* EVASIONECONTROLLATA $\rightarrow$ stampa "ORDINE EVASO CON VERIFICA AGGIUNTIVA" e PREZZO - 5%

---

### PARTE 2 – CLASSE ORDINE
Crea una classe **Ordine** con almeno:
* ID
* CLIENTE
* PRODOTTO
* STRATEGIA CORRENTE
* PREZZO

**La classe deve permettere di:**
* IMPOSTARE UNA STRATEGY
* ESEGUIRE LA STRATEGY CORRENTE
* VISUALIZZARE I DATI DELL'ORDINE

---

### PARTE 3 – OBSERVER
**SUBJECT**
Crea una classe **CentroPriorita** che rappresenta il soggetto osservabile.
**Questa classe deve contenere:**
* LO STATO OPERATIVO CORRENTE
**E deve permettere di:**
* REGISTRARE OBSERVER
* RIMUOVERE OBSERVER
* NOTIFICARE OBSERVER QUANDO LO STATO CAMBIA

**REGOLE DI AGGIORNAMENTO DELLA STRATEGY**
Quando il CentroPriorita cambia stato:
* SE LO STATO È NORMALE $\rightarrow$ USA EVASIONENORMALE
* SE LO STATO È PRIORITA $\rightarrow$ USA EVASIONEPRIORITARIA
* SE LO STATO È CONTROLLO $\rightarrow$ USA EVASIONECONTROLLATA

**IL CAMBIO DELLA STRATEGY DEVE AVVENIRE DENTRO O TRAMITE L'OBSERVER, NON NEL MAIN.**

---

### FUNZIONALITÀ RICHIESTE
Il programma deve permettere di:
1. Creare almeno un ordine
2. Creare il Centro di Priorità
3. Registrare l'observer
4. Cambiare lo stato del centro
5. Aggiornare automaticamente la strategy dell'ordine
6. Eseguire l'evasione usando la strategy corrente