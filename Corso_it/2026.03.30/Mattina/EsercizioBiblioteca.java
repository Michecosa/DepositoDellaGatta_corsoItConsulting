import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate; // per gestire le date
import java.time.temporal.ChronoUnit; // per calcolare i giorni tra date

class Book {
  String title;
  String author;
  boolean isAvailable;
  LocalDate loanDate; // per tracciare la scadenza

  Book(String title, String author) {
    this.title = title;
    this.author = author;
    this.isAvailable = true;
  }

  void displayBookInfo() {
    String stato = isAvailable ? "Disponibile":"In prestito";
    System.out.println(title+" ["+stato+"]\nAutore: "+author+"\n");
  }
}

class User {
  String name;
  ArrayList<Book> borrowedBooks = new ArrayList<>();

  User(String name) {
    this.name = name;
  }

  void displayBorrowedBooks() {
    System.out.println("\nLibri in prestito a "+name+":");
    if (borrowedBooks.isEmpty()) {
      System.out.println("Non hai nessun libro in prestito");
    } else {
      for (Book b : borrowedBooks) {
        System.out.println("- " + b.title + " (Preso il: "+b.loanDate+")");
      }
    }
  }
}

class Library {
  ArrayList<Book> books = new ArrayList<>();
  // Lista degli utenti registrati nel sistema
  ArrayList<User> users = new ArrayList<>();

  void addBook(Book book) {
    books.add(book);
  }

  void displayBooks() {
    System.out.println("\n--- ELENCO COMPLETO LIBRI ---");
    for (Book b : books) {
      b.displayBookInfo();
    }
  }

  void searchBook(String keyword) {
    System.out.println("\nRisultati ricerca per '"+keyword+"':");
    boolean trovato = false;
    for (Book b : books) {
      if (b.title.toLowerCase().contains(keyword.toLowerCase()) || 
          b.author.toLowerCase().contains(keyword.toLowerCase())) {
        b.displayBookInfo();
        trovato = true;
      }
    }
    if (!trovato) System.out.println("Nessun libro trovato");
  }

  void borrowBook(String title, User user) {
    if (user.borrowedBooks.size() >= 3) {
      System.out.println("Errore: Hai già raggiunto il limite di 3 prestiti");
      return;
    }
    for (Book b : books) {
      if (b.title.equalsIgnoreCase(title)) {
        if (b.isAvailable) {
          b.isAvailable = false;
          b.loanDate = LocalDate.now();
          user.borrowedBooks.add(b);
          System.out.println("Prestito effettuato con successo il "+b.loanDate);
          return;
        } else {
          System.out.println("Il libro è già in prestito");
          return;
        }
      }
    }
    System.out.println("Libro non trovato");
  }

  void returnBook(String title, User user) {
    for (Book b : user.borrowedBooks) {
      if (b.title.equalsIgnoreCase(title)) {
        // Calcolo penalità
        long giorniTrascorsi = ChronoUnit.DAYS.between(b.loanDate, LocalDate.now());
        if (giorniTrascorsi > 14) {
          double penale = (giorniTrascorsi - 14) * 0.50; // Esempio: 50 cent al giorno
          System.out.println("ATTENZIONE: Restituzione in ritardo di "+giorniTrascorsi+" giorni");
          System.out.println("Penale da pagare: " + penale + " EUR");
        }

        b.isAvailable = true;
        b.loanDate = null;
        user.borrowedBooks.remove(b);
        System.out.println("Il libro è stato restituito");
        return;
      }
    }
    System.out.println("Questo libro non risulta tra i tuoi prestiti");
  }
}

public class EsercizioBiblioteca {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Library biblio = new Library();
    
    biblio.users.add(new User("admin"));
    biblio.users.add(new User("Miche"));
    biblio.users.add(new User("Mario"));

    System.out.print("Inserisci il tuo nome utente per accedere: ");
    String nomeLogin = input.nextLine();
    User utenteAttivo = null;

    // Controllo accesso
    for (User u : biblio.users) {
      if (u.name.equalsIgnoreCase(nomeLogin)) {
        utenteAttivo = u;
        break;
      }
    }

    if (utenteAttivo == null) {
      System.out.println("Utente non trovato. Accesso negato");
    } else {
      System.out.println("Ciao " + utenteAttivo.name);
      boolean continua = true;
      while (continua) {
        System.out.println("\n--- BIBLIOTECA (Utente: " + utenteAttivo.name + ") ---");
        System.out.println("1. Aggiungi nuovo libro (solo admin)");
        System.out.println("2. Visualizza tutti i libri");
        System.out.println("3. Cerca un libro");
        System.out.println("4. Prendi in prestito");
        System.out.println("5. Restituisci libro");
        System.out.println("6. Visualizza i miei prestiti");
        System.out.println("0. Esci");
        System.out.print("Scelta: ");
        int scelta = input.nextInt();
        input.nextLine();

        switch (scelta) {
          case 1:
            // Controllo admin
            if (utenteAttivo.name.equalsIgnoreCase("admin")) {
              System.out.print("Titolo: "); 
              String t = input.nextLine();
              System.out.print("Autore: "); 
              String a = input.nextLine();
              biblio.addBook(new Book(t, a));
            } else {
              System.out.println("Accesso negato: Solo l'amministratore può aggiungere libri");
            }
            break;
          
          case 2: 
            biblio.displayBooks(); 
            break;
          
          case 3:
            System.out.print("Cerca: ");
            biblio.searchBook(input.nextLine());
            break;
          
          case 4:
            System.out.print("Titolo da prendere: ");
            biblio.borrowBook(input.nextLine(), utenteAttivo);
            break;
          
          case 5:
            System.out.print("Titolo da restituire: ");
            biblio.returnBook(input.nextLine(), utenteAttivo);
            break;
          
          case 6: 
            utenteAttivo.displayBorrowedBooks(); 
            break;
          
          case 0: 
            continua = false; 
            break;
          
          default: 
            System.out.println("Scelta non valida");
        }
      }
    }
    input.close();
  }
}