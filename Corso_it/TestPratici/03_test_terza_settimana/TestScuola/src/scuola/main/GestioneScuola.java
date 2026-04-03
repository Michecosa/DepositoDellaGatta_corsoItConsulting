package scuola.main;
import scuola.base.*;
import scuola.persone.*;

import java.util.ArrayList;
import java.util.Scanner;


// ======================
// CLASSE PRINCIPALE 
// ======================
public class GestioneScuola {
 
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Studente> studenti = new ArrayList<Studente>();
    static ArrayList<Docente>  docenti  = new ArrayList<Docente>();
 
    public static void main(String[] args) {
        System.out.println("\n=== SISTEMA DI GESTIONE SCUOLA ===");
 
        int scelta = -1;
        while (scelta != 0) {
            System.err.println("________________________________________");
            System.out.println("\n1. Aggiungi Studente");
            System.out.println("2. Aggiungi Docente");
            System.out.println("3. Assegna studente a un docente");
            System.out.println("4. Assegna voto a uno studente");
            System.out.println("5. Stampa voti di uno studente");
            System.out.println("6. Mostra ruoli di tutte le persone");
            System.out.println("7. Mostra registrazioni");
            System.out.println("0. Esci");
            System.err.println("________________________________________\n");
            System.out.print("Scelta: ");
            scelta = scanner.nextInt();
            scanner.nextLine(); // pulizia buffer

            switch (scelta) {
                case 1:
                    creaStudente();
                    break;
                case 2:
                    creaDocente();
                    break;
                case 3:
                    assegnaStudenteADocente();
                    break;
                case 4:
                    assegnaVoto();
                    break;
                case 5:
                    stampaVotiStudente();
                    break;
                case 6:
                    descriviTutti();
                    break;
                case 7:
                    mostraRegistrazioni();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("[ERRORE] Scelta non valida");
                    break;
            }
        }
 
        System.out.println("\nArrivederci!\n");
    }
 
    // CREAZIONE STUDENTE 
    static void creaStudente() {
        System.out.println("   [1] Studente Liceo\n   [2] Studente Professionale");
        System.out.print("Tipo: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();
 
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        // Controllo eta' studente: deve essere tra 11 e 117
        System.out.print("Eta': ");
        int eta = scanner.nextInt();
        scanner.nextLine();
        if (eta < 11 || eta > 117) {
            System.out.println("[ERRORE] Eta' non valida. Deve essere maggiore di 11");
            return;
        }

        System.out.print("Classe (es. 3A): ");
        String classe = scanner.nextLine();
 
        Studente s;
        if (tipo == 1) {
            System.out.print("Indirizzo (es. Scientifico): ");
            String indirizzo = scanner.nextLine();
            s = new StudenteLiceo(nome, eta, classe, indirizzo);
        } else {
            System.out.print("Specializzazione (es. Informatica): ");
            String spec = scanner.nextLine();
            s = new StudenteProfessionale(nome, eta, classe, spec);
        }
        studenti.add(s);
        s.registrazione();
        System.out.println("Studente aggiunto: " + s);
    }
 
    // CREAZIONE DOCENTE 
    static void creaDocente() {
        System.out.println("   [1] Docente Ordinario\n   [2] Docente di Sostegno");
        System.out.print("Tipo: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();
 
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        // Controllo eta' docente: deve essere tra 21 e 117
        System.out.print("Eta': ");
        int eta = scanner.nextInt();
        scanner.nextLine();
        if (eta < 21 || eta > 117) {
            System.out.println("[ERRORE] Eta' non valida. Deve essere compresa tra 21 e 117");
            return;
        }

        System.out.print("Materia: ");
        String materia = scanner.nextLine();
 
        Docente d;
        if (tipo == 1) {
            System.out.print("Anni di esperienza: ");
            int anni = scanner.nextInt();
            scanner.nextLine();

            // Controllo esperienza: non puo' superare eta - 21
            // (assumo che un docente inizi a lavorare al piu' presto a 21 anni)
            int maxAnni = eta - 21;
            if (anni < 0 || anni > maxAnni) {
                System.out.println("[ERRORE] Anni di esperienza non validi");
                System.out.println("         Per un docente di " + eta + " anni, il massimo e' " + maxAnni + " anni di esperienza");
                return;
            }

            d = new DocenteOrdinario(nome, eta, materia, anni);
        } else {
            System.out.print("Tipo di supporto (es. DSA): ");
            String supporto = scanner.nextLine();
            d = new DocenteSostegno(nome, eta, materia, supporto);
        }
        docenti.add(d);
        d.registrazione();
        System.out.println("Docente aggiunto: " + d);
    }
 
    // ASSEGNA STUDENTE A DOCENTE 
    static void assegnaStudenteADocente() {
        if (studenti.isEmpty() || docenti.isEmpty()) {
            System.out.println("[ERRORE] Servono almeno uno studente e un docente");
            return;
        }
        System.out.println("--- Studenti ---");
        for (int i = 0; i < studenti.size(); i++)
            System.out.println(i + ". " + studenti.get(i));
        System.out.print("Numero studente: ");
        int is = scanner.nextInt();
        scanner.nextLine();
 
        System.out.println("--- Docenti ---");
        for (int i = 0; i < docenti.size(); i++)
            System.out.println(i + ". " + docenti.get(i));
        System.out.print("Numero docente: ");
        int id = scanner.nextInt();
        scanner.nextLine();
 
        Studente s = studenti.get(is);
        Docente  d = docenti.get(id);
        d.aggiungiStudente(s);
        System.out.println(s.getNome() + " assegnato a " + d.getNome() + " (" + d.getMateria() + ")");
    }
 
    // ASSEGNA VOTO 
    static void assegnaVoto() {
        if (docenti.isEmpty()) {
            System.out.println("[ERRORE] Nessun docente presente");
            return;
        }
        System.out.println("--- Docenti ---");
        for (int i = 0; i < docenti.size(); i++)
            System.out.println(i + ". " + docenti.get(i));
        System.out.print("Numero docente: ");
        int id = scanner.nextInt();
        scanner.nextLine();
 
        Docente d = docenti.get(id);
        ArrayList<Studente> lista = d.getStudentiMateria();
 
        if (lista.isEmpty()) {
            System.out.println("[ERRORE] "+d.getNome() + " non ha studenti assegnati");
            return;
        }
        System.out.println("--- Studenti di " + d.getNome() + " ---");
        for (int i = 0; i < lista.size(); i++)
            System.out.println(i + ". " + lista.get(i));
        System.out.print("Numero studente: ");
        int is = scanner.nextInt();
        scanner.nextLine();

        // Controllo voto: deve essere compreso tra 0 e 10
        System.out.print("Voto (0-10): ");
        int voto = scanner.nextInt();
        scanner.nextLine();
        if (voto < 0 || voto > 10) {
            System.out.println("[ERRORE] Voto non valido. Deve essere compreso tra 0 e 10");
            return;
        }
 
        d.assegnaVoto(lista.get(is), voto);
    }
 
    // STAMPA VOTI STUDENTE 
    static void stampaVotiStudente() {
        if (studenti.isEmpty()) {
            System.out.println("[ERRORE] Nessuno studente presente");
            return;
        }
        System.out.println("--- Studenti ---");
        for (int i = 0; i < studenti.size(); i++)
            System.out.println(i + ". " + studenti.get(i));
        System.out.print("Numero studente: ");
        int is = scanner.nextInt();
        scanner.nextLine();
 
        studenti.get(is).stampaVoti();
    }
 
    // DESCRIVI RUOLI
    static void descriviTutti() {
        ArrayList<Persona> tutti = new ArrayList<Persona>();
        tutti.addAll(studenti);
        tutti.addAll(docenti);
 
        if (tutti.isEmpty()) {
            System.out.println("[ERRORE] Nessuna persona registrata");
            return;
        }
        for (Persona p : tutti) {
            System.out.print(p.getNome() + ": ");
            p.descriviRuolo();
        }
    }
 
    // MOSTRA REGISTRAZIONI 
    static void mostraRegistrazioni() {
        ArrayList<Registrabile> registrabili = new ArrayList<Registrabile>();
        registrabili.addAll(studenti);
        registrabili.addAll(docenti);
 
        if (registrabili.isEmpty()) {
            System.out.println("[ERRORE] Nessuna persona registrata");
            return;
        }
        for (Registrabile r : registrabili) {
            r.registrazione();
        }
    }
}