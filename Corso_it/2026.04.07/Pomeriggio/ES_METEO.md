# **Esercizio Facile – Sistema di Notifica Meteo**

**Obiettivo:** Simulare un sistema dove una stazione meteo (subject) notifica più display (observer) quando cambia la temperatura.

**Requisiti:**
* Creare un'interfaccia **Display** con il metodo **aggiorna(float temperatura)**.
* Implementare la classe **StazioneMeteo** con:
    * una lista di display registrati;
    * metodi **aggiungiDisplay**, **rimuoviDisplay**, **notificaDisplay**;
    * un metodo **setTemperatura(float t)** che aggiorna la temperatura e notifica.
* Implementare almeno due display (es. **DisplayConsole**, **DisplayMobile**) che stampano il valore ricevuto.