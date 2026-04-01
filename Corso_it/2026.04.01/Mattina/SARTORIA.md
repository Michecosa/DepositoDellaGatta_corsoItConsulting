## ESERCIZIO – GESTIONE DI UNA SARTORIA PER SUITE ELEGANTI

Scrivi un programma in Java che simuli un piccolo sistema di gestione per una sartoria che realizza suite eleganti, come smoking, completi classici e coordinati formali.

Il sistema deve gestire **due famiglie di oggetti** diverse:
* Una famiglia per i **Capi Principali**
* Una famiglia per i **Componenti di Finitura**

---

### 1. Prima Famiglia di Oggetti: CAPI PRINCIPALI
Crea una classe base chiamata **`CapoPrincipale`**. Ogni capo principale deve avere almeno questi attributi:
* Codice
* Nome
* Tessuto
* Colore
* Taglia
* Prezzo

Da questa classe devono derivare almeno queste classi figlie, ognuna con almeno una **caratteristica propria**:
* **`Giacca`** → numeroBottoni
* **`Pantalone`** → tipoTaglio
* **`Gilet`** → reverPresente

---

### 2. Seconda Famiglia di Oggetti: COMPONENTI DI FINITURA
Crea una seconda classe base chiamata **`ComponenteFinitura`**. Ogni componente di finitura deve avere almeno questi attributi:
* Codice
* Nome
* Materiale
* Colore
* Prezzo

Da questa classe devono derivare almeno queste classi figlie, ognuna con almeno una **caratteristica propria**:
* **`Cravatta`** → larghezza
* **`Papillon`** → tipoChiusura
* **`Pochette`** → piegaDecorativa

---

### VINCOLI SULLE CLASSI

#### **Incapsulamento**
* Tutti gli attributi devono essere **private**.
* Ogni classe deve avere metodi **Getter** e **Setter**.
* Nei Setter devono esserci **controlli sui dati**. Ad esempio:
    * Il prezzo deve essere maggiore di 0.
    * Il nome e il colore non devono essere vuoti.
    * La taglia non deve essere vuota (quando presente).

#### **Ereditarietà**
* Le classi figlie devono ereditare dalle rispettive classi base:
    * `Giacca`, `Pantalone`, `Gilet` ereditano da `CapoPrincipale`.
    * `Cravatta`, `Papillon`, `Pochette` ereditano da `ComponenteFinitura`.
* Le classi base contengono le caratteristiche comuni, le sottoclassi aggiungono i dettagli specifici.

#### **Polimorfismo**
* Ogni classe base deve avere un metodo, ad esempio: **`mostraDettagli()`**.
* Questo metodo deve essere **ridefinito (Override)** nelle sottoclassi, in modo che ogni oggetto stampi le proprie informazioni in modo personalizzato.

#### **Gestione nel Main**
* I capi principali devono essere gestiti in modo polimorfico in una **struttura comune** (es. ArrayList).
* Lo stesso deve avvenire per i componenti di finitura.
* Utilizzare un unico oggetto che usa i metodi polimorfici per la stampa o gestione.