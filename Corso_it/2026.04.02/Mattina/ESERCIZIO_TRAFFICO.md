# **ESERCIZIO – Sistema di gestione autostradale**

Scrivi un programma in Java che simuli un sistema semplificato di gestione del traffico su un'autostrada.

**Traccia**
Devi modellare un sistema in cui diversi tipi di veicoli possono percorrere l'autostrada e pagare un pedaggio.

### Classe astratta Veicolo
Crea una classe astratta chiamata **Veicolo** che rappresenta un generico mezzo.
Questa classe deve contenere attributi comuni come:
* targa
* velocità
* numero di assi

Gli attributi devono essere **private** e accessibili tramite **getter e setter**, con controlli sui valori (es. velocità $\ge$ 0).
La classe deve definire almeno un metodo astratto:
`calcolaPedaggio()`

### Classi derivate
Crea almeno tre classi che estendono Veicolo, ad esempio:
* Auto
* Camion
* Moto

Ogni classe deve:
* aggiungere almeno una caratteristica propria (es. peso, cilindrata, tipo carico)
* implementare il metodo **`calcolaPedaggio()`** con una logica diversa per ogni tipo di veicolo

### Polimorfismo
Nel programma principale devi gestire più veicoli in modo polimorfico, utilizzando una struttura comune (**array o ArrayList di tipo Veicolo**).
Quando richiami il metodo `calcolaPedaggio()`, ogni oggetto deve comportarsi in base al proprio tipo reale.

### Funzionalità richieste
Il programma deve permettere di:
* creare diversi veicoli di tipo differente
* inserire i dati dei veicoli
* calcolare e stampare il pedaggio per ogni veicolo
* stampare le informazioni complete dei veicoli

### Vincoli
* utilizzare una classe astratta
* utilizzare almeno 3 classi derivate
* utilizzare attributi private con getter e setter
* utilizzare almeno un metodo astratto
* utilizzare il polimorfismo nel main
* non utilizzare ereditarietà tra le classi derivate

**Consegna**
Realizza il programma completo in Java, organizzando correttamente le classi e mostrando nel codice l'utilizzo delle quattro caratteristiche fondamentali della programmazione orientata agli oggetti.