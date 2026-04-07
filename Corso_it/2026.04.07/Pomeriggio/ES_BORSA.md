# **Esercizio Medio – Sistema di Notifica Borsa**

**Obiettivo:** Modellare un sistema in cui un'agenzia borsistica notifica più investitori su variazioni del valore azionario.

**Requisiti:**
* Creare un'interfaccia **Investitore** con il metodo **notifica(String azione, double valore)**.
* Implementare la classe **AgenziaBorsa** con:
    * una lista di investitori registrati;
    * metodi **aggiungiInvestitore**, **rimuoviInvestitore**, **notificaInvestitori**;
    * un metodo **aggiornaValoreAzione(String nome, double valore)** che notifica gli investitori.
* Implementare almeno due classi Investitore, es. **InvestitorePrivato** e **InvestitoreBancario**, che rispondono con messaggi personalizzati.