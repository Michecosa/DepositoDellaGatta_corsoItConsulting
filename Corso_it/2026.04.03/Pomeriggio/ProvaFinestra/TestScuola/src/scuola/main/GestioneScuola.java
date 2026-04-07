package scuola.main;

import scuola.base.*;
import scuola.persone.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class GestioneScuola {

    // ── dati ──────────────────────────────────────────────────────────────────
    static ArrayList<Studente> studenti = new ArrayList<>();
    static ArrayList<Docente>  docenti  = new ArrayList<>();

    // ── componenti GUI ────────────────────────────────────────────────────────
    static JFrame      frame;
    static JEditorPane vista;      // pannello HTML centrale

    // ── colori del tema ───────────────────────────────────────────────────────
    static final Color BG_SIDEBAR  = new Color(0x2C3E50);
    static final Color BG_MAIN     = new Color(0xF4F6F8);
    static final Color ACCENT      = new Color(0x3498DB);
    static final Color ACCENT_DARK = new Color(0x2980B9);
    static final Color TEXT_LIGHT  = Color.WHITE;

    // ── CSS condiviso per JEditorPane ─────────────────────────────────────────
    static final String CSS = ""
        + "body { font-family: Arial, sans-serif; background: #F4F6F8;"
        + "       margin: 24px 32px; color: #2C3E50; }"
        + "h1 { font-size: 22px; color: #2C3E50; border-bottom: 2px solid #3498DB;"
        + "     padding-bottom: 8px; margin-bottom: 16px; }"
        + "h2 { font-size: 16px; color: #555; margin-top: 24px; }"
        + "table { border-collapse: collapse; width: 100%; margin-top: 8px; }"
        + "th { background: #2C3E50; color: white; padding: 10px 14px;"
        + "     text-align: left; font-size: 13px; }"
        + "td { padding: 9px 14px; border-bottom: 1px solid #dde2e8; font-size: 13px; }"
        + ".info-box { background: white; border-left: 4px solid #3498DB;"
        + "            padding: 12px 16px; margin-top: 12px;"
        + "            font-size: 13px; line-height: 1.7; }"
        + ".empty { color: #999; font-style: italic; margin-top: 12px; font-size: 13px; }"
        + ".badge { padding: 2px 8px; font-size: 11px; font-weight: bold; }"
        + ".badge-liceo     { background:#dbeafe; color:#1e40af; }"
        + ".badge-prof      { background:#dcfce7; color:#166534; }"
        + ".badge-ordinario { background:#fef9c3; color:#854d0e; }"
        + ".badge-sostegno  { background:#fce7f3; color:#9d174d; }"
        + ".voto { padding: 2px 7px; font-weight: bold; font-size: 12px; }"
        + ".voto-alto  { background:#dcfce7; color:#166534; }"
        + ".voto-medio { background:#fef9c3; color:#854d0e; }"
        + ".voto-basso { background:#fee2e2; color:#991b1b; }";

    // ─────────────────────────────────────────────────────────────────────────
    // MAIN
    // ─────────────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GestioneScuola::costruisciGUI);
    }

    static void costruisciGUI() {
        frame = new JFrame("Sistema di Gestione Scuola");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 620);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // ── SIDEBAR ──
        JPanel sidebar = new JPanel();
        sidebar.setBackground(BG_SIDEBAR);
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(210, 0));
        sidebar.setBorder(new EmptyBorder(24, 12, 24, 12));

        JLabel titolo = new JLabel("<html><center>Gestione<br>Scuola</center></html>");
        titolo.setForeground(TEXT_LIGHT);
        titolo.setFont(new Font("Arial", Font.BOLD, 16));
        titolo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titolo.setBorder(new EmptyBorder(0, 0, 24, 0));
        sidebar.add(titolo);

        String[] voci = {
            "Aggiungi Studente",
            "Aggiungi Docente",
            "Assegna a Docente",
            "Assegna Voto",
            "Voti Studente",
            "Mostra Ruoli",
            "Registrazioni"
        };
        Runnable[] azioni = {
            GestioneScuola::creaStudente,
            GestioneScuola::creaDocente,
            GestioneScuola::assegnaStudenteADocente,
            GestioneScuola::assegnaVoto,
            GestioneScuola::stampaVotiStudente,
            GestioneScuola::descriviTutti,
            GestioneScuola::mostraRegistrazioni
        };

        for (int i = 0; i < voci.length; i++) {
            sidebar.add(creaBottone(voci[i], azioni[i], false));
            sidebar.add(Box.createRigidArea(new Dimension(0, 6)));
        }

        sidebar.add(Box.createVerticalGlue());
        sidebar.add(creaBottone("Esci", () -> System.exit(0), true));

        // ── AREA CENTRALE HTML ──
        vista = new JEditorPane();
        vista.setContentType("text/html");
        vista.setEditable(false);
        vista.setBackground(BG_MAIN);
        JScrollPane scroll = new JScrollPane(vista);
        scroll.setBorder(null);

        frame.add(sidebar, BorderLayout.WEST);
        frame.add(scroll,  BorderLayout.CENTER);

        mostraHome();
        frame.setVisible(true);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // UTILITY GUI
    // ─────────────────────────────────────────────────────────────────────────

    static JButton creaBottone(String testo, Runnable azione, boolean rosso) {
        JButton b = new JButton(testo);
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        Color coloreNormale = rosso ? new Color(0x922B21) : ACCENT;
        Color coloreHover   = rosso ? new Color(0x7B241C) : ACCENT_DARK;
        b.setBackground(coloreNormale);
        b.setForeground(TEXT_LIGHT);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setFont(new Font("Arial", Font.PLAIN, 13));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addActionListener(e -> azione.run());
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { b.setBackground(coloreHover); }
            public void mouseExited (java.awt.event.MouseEvent e) { b.setBackground(coloreNormale); }
        });
        return b;
    }

    static void aggiornaVista(String bodyHtml) {
        vista.setText("<html><head><style>" + CSS + "</style></head><body>"
                + bodyHtml + "</body></html>");
        vista.setCaretPosition(0);
    }

    static String chiediStringa(String domanda) {
        String s = JOptionPane.showInputDialog(frame, domanda);
        return (s == null || s.isBlank()) ? null : s.trim();
    }

    static int chiediIntero(String domanda) {
        String s = JOptionPane.showInputDialog(frame, domanda);
        if (s == null) return -1;
        try { return Integer.parseInt(s.trim()); }
        catch (NumberFormatException e) { errore("Inserisci un numero intero valido."); return -1; }
    }

    static int scegliDaLista(String messaggio, ArrayList<?> lista) {
        Object scelta = JOptionPane.showInputDialog(
            frame, messaggio, "Selezione",
            JOptionPane.PLAIN_MESSAGE, null,
            lista.toArray(), lista.get(0)
        );
        return scelta == null ? -1 : lista.indexOf(scelta);
    }

    static void errore(String msg) {
        JOptionPane.showMessageDialog(frame, msg, "Errore", JOptionPane.ERROR_MESSAGE);
    }

    static String classVoto(int v) {
        return v >= 7 ? "voto-alto" : v >= 5 ? "voto-medio" : "voto-basso";
    }

    // ─────────────────────────────────────────────────────────────────────────
    // HOME
    // ─────────────────────────────────────────────────────────────────────────
    static void mostraHome() {
        aggiornaVista(
            "<h1>Benvenuto nel Sistema di Gestione Scuola</h1>"
          + "<div class='info-box'>Usa il menu laterale per gestire studenti e docenti.</div>"
          + "<h2>Riepilogo rapido</h2>"
          + "<div class='info-box'>"
          + "Studenti registrati: <b>" + studenti.size() + "</b><br>"
          + "Docenti registrati: <b>"  + docenti.size()  + "</b>"
          + "</div>"
        );
    }

    // ─────────────────────────────────────────────────────────────────────────
    // CREAZIONE STUDENTE
    // ─────────────────────────────────────────────────────────────────────────
    static void creaStudente() {
        String[] tipi = {"Studente Liceo", "Studente Professionale"};
        int tipo = JOptionPane.showOptionDialog(frame, "Tipo di studente:", "Aggiungi Studente",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, tipi, tipi[0]);
        if (tipo == JOptionPane.CLOSED_OPTION) return;

        String nome = chiediStringa("Nome:");
        if (nome == null) return;

        int eta = chiediIntero("Eta':");
        if (eta == -1) return;
        if (eta < 11 || eta > 117) { errore("Eta' non valida (11-117)."); return; }

        String classe = chiediStringa("Classe (es. 3A):");
        if (classe == null) return;

        Studente s;
        if (tipo == 0) {
            String ind = chiediStringa("Indirizzo (es. Scientifico):");
            if (ind == null) return;
            s = new StudenteLiceo(nome, eta, classe, ind);
        } else {
            String spec = chiediStringa("Specializzazione (es. Informatica):");
            if (spec == null) return;
            s = new StudenteProfessionale(nome, eta, classe, spec);
        }

        studenti.add(s);
        s.registrazione();
        mostraTabStudenti();
    }

    // ─────────────────────────────────────────────────────────────────────────
    // CREAZIONE DOCENTE
    // ─────────────────────────────────────────────────────────────────────────
    static void creaDocente() {
        String[] tipi = {"Docente Ordinario", "Docente di Sostegno"};
        int tipo = JOptionPane.showOptionDialog(frame, "Tipo di docente:", "Aggiungi Docente",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, tipi, tipi[0]);
        if (tipo == JOptionPane.CLOSED_OPTION) return;

        String nome = chiediStringa("Nome:");
        if (nome == null) return;

        int eta = chiediIntero("Eta':");
        if (eta == -1) return;
        if (eta < 21 || eta > 117) { errore("Eta' non valida (21-117)."); return; }

        String materia = chiediStringa("Materia:");
        if (materia == null) return;

        Docente d;
        if (tipo == 0) {
            int anni = chiediIntero("Anni di esperienza:");
            if (anni == -1) return;
            int maxAnni = eta - 21;
            if (anni < 0 || anni > maxAnni) {
                errore("Anni non validi. Massimo per " + eta + " anni: " + maxAnni + ".");
                return;
            }
            d = new DocenteOrdinario(nome, eta, materia, anni);
        } else {
            String supporto = chiediStringa("Tipo di supporto (es. DSA):");
            if (supporto == null) return;
            d = new DocenteSostegno(nome, eta, materia, supporto);
        }

        docenti.add(d);
        d.registrazione();
        mostraTabDocenti();
    }

    // ─────────────────────────────────────────────────────────────────────────
    // ASSEGNA STUDENTE A DOCENTE
    // ─────────────────────────────────────────────────────────────────────────
    static void assegnaStudenteADocente() {
        if (studenti.isEmpty() || docenti.isEmpty()) {
            errore("Servono almeno uno studente e un docente."); return;
        }
        int is = scegliDaLista("Scegli lo studente:", studenti);
        if (is == -1) return;
        int id = scegliDaLista("Scegli il docente:", docenti);
        if (id == -1) return;

        Studente s = studenti.get(is);
        Docente  d = docenti.get(id);
        d.aggiungiStudente(s);

        StringBuilder html = new StringBuilder("<h1>Assegnazioni</h1>");
        for (Docente doc : docenti) {
            html.append("<h2>").append(doc.getNome())
                .append(" &mdash; ").append(doc.getMateria()).append("</h2>");
            ArrayList<Studente> lista = doc.getStudentiMateria();
            if (lista.isEmpty()) {
                html.append("<p class='empty'>Nessuno studente assegnato.</p>");
            } else {
                html.append("<table><tr><th>Studente</th><th>Classe</th></tr>");
                for (Studente st : lista)
                    html.append("<tr><td>").append(st.getNome())
                        .append("</td><td>").append(st.getClasseFrequentata())
                        .append("</td></tr>");
                html.append("</table>");
            }
        }
        aggiornaVista(html.toString());
    }

    // ─────────────────────────────────────────────────────────────────────────
    // ASSEGNA VOTO
    // ─────────────────────────────────────────────────────────────────────────
    static void assegnaVoto() {
        if (docenti.isEmpty()) { errore("Nessun docente presente."); return; }

        int id = scegliDaLista("Scegli il docente:", docenti);
        if (id == -1) return;

        Docente d = docenti.get(id);
        ArrayList<Studente> lista = d.getStudentiMateria();
        if (lista.isEmpty()) { errore(d.getNome() + " non ha studenti assegnati."); return; }

        int is = scegliDaLista("Scegli lo studente:", lista);
        if (is == -1) return;

        int voto = chiediIntero("Voto (0-10):");
        if (voto == -1) return;
        if (voto < 0 || voto > 10) { errore("Voto non valido (0-10)."); return; }

        d.assegnaVoto(lista.get(is), voto);
        mostraVotiStudente(lista.get(is));
    }

    // ─────────────────────────────────────────────────────────────────────────
    // STAMPA VOTI STUDENTE
    // ─────────────────────────────────────────────────────────────────────────
    static void stampaVotiStudente() {
        if (studenti.isEmpty()) { errore("Nessuno studente presente."); return; }
        int is = scegliDaLista("Scegli lo studente:", studenti);
        if (is == -1) return;
        mostraVotiStudente(studenti.get(is));
    }

    static void mostraVotiStudente(Studente s) {
        ArrayList<Integer> voti = s.getVoti();
        StringBuilder html = new StringBuilder();
        html.append("<h1>Voti di ").append(s.getNome()).append("</h1>");
        html.append("<div class='info-box'>Classe: <b>").append(s.getClasseFrequentata())
            .append("</b> &nbsp;|&nbsp; Eta': <b>").append(s.getEta()).append("</b></div>");

        if (voti.isEmpty()) {
            html.append("<p class='empty'>Nessun voto ancora assegnato.</p>");
        } else {
            int somma = 0;
            html.append("<table><tr><th>#</th><th>Voto</th></tr>");
            for (int i = 0; i < voti.size(); i++) {
                int v = voti.get(i);
                somma += v;
                html.append("<tr><td>").append(i + 1)
                    .append("</td><td><span class='voto ").append(classVoto(v))
                    .append("'>").append(v).append("</span></td></tr>");
            }
            html.append("</table>");
            double media = (double) somma / voti.size();
            html.append("<div class='info-box' style='margin-top:16px'>")
                .append("Media: <b><span class='voto ").append(classVoto((int) Math.round(media)))
                .append("'>").append(String.format("%.2f", media))
                .append("</span></b></div>");
        }
        aggiornaVista(html.toString());
    }

    // ─────────────────────────────────────────────────────────────────────────
    // MOSTRA RUOLI
    // ─────────────────────────────────────────────────────────────────────────
    static void descriviTutti() {
        StringBuilder html = new StringBuilder("<h1>Ruoli di tutte le persone</h1>");

        html.append("<h2>Studenti</h2>");
        if (studenti.isEmpty()) {
            html.append("<p class='empty'>Nessuno studente registrato.</p>");
        } else {
            html.append("<table><tr><th>Nome</th><th>Eta'</th><th>Tipo</th><th>Dettaglio</th></tr>");
            for (Studente s : studenti) {
                String badge, dettaglio;
                if (s instanceof StudenteLiceo sl) {
                    badge = "<span class='badge badge-liceo'>Liceo</span>";
                    dettaglio = "Indirizzo: " + sl.getIndirizzo();
                } else if (s instanceof StudenteProfessionale sp) {
                    badge = "<span class='badge badge-prof'>Professionale</span>";
                    dettaglio = "Specializzazione: " + sp.getSpecializzazione();
                } else { badge = ""; dettaglio = ""; }
                html.append("<tr><td>").append(s.getNome())
                    .append("</td><td>").append(s.getEta())
                    .append("</td><td>").append(badge)
                    .append("</td><td>").append(dettaglio).append("</td></tr>");
            }
            html.append("</table>");
        }

        html.append("<h2>Docenti</h2>");
        if (docenti.isEmpty()) {
            html.append("<p class='empty'>Nessun docente registrato.</p>");
        } else {
            html.append("<table><tr><th>Nome</th><th>Eta'</th><th>Materia</th><th>Tipo</th><th>Dettaglio</th></tr>");
            for (Docente d : docenti) {
                String badge, dettaglio;
                if (d instanceof DocenteOrdinario dord) {
                    badge = "<span class='badge badge-ordinario'>Ordinario</span>";
                    dettaglio = "Esperienza: " + dord.getAnniEsperienza() + " anni";
                } else if (d instanceof DocenteSostegno ds) {
                    badge = "<span class='badge badge-sostegno'>Sostegno</span>";
                    dettaglio = "Supporto: " + ds.getTipoSupporto();
                } else { badge = ""; dettaglio = ""; }
                html.append("<tr><td>").append(d.getNome())
                    .append("</td><td>").append(d.getEta())
                    .append("</td><td>").append(d.getMateria())
                    .append("</td><td>").append(badge)
                    .append("</td><td>").append(dettaglio).append("</td></tr>");
            }
            html.append("</table>");
        }
        aggiornaVista(html.toString());
    }

    // ─────────────────────────────────────────────────────────────────────────
    // MOSTRA REGISTRAZIONI
    // ─────────────────────────────────────────────────────────────────────────
    static void mostraRegistrazioni() {
        ArrayList<Registrabile> tutti = new ArrayList<>();
        tutti.addAll(studenti);
        tutti.addAll(docenti);

        if (tutti.isEmpty()) { errore("Nessuna persona registrata."); return; }

        StringBuilder html = new StringBuilder("<h1>Registrazioni</h1>");
        html.append("<table><tr><th>Nome</th><th>Tipo</th><th>Modalita'</th></tr>");
        for (Registrabile r : tutti) {
            String nome, badge, modalita;
            if (r instanceof Studente s) {
                nome = s.getNome();
                badge = "<span class='badge badge-liceo'>Studente</span>";
                modalita = "Modulo online";
            } else {
                Docente d = (Docente) r;
                nome = d.getNome();
                badge = "<span class='badge badge-ordinario'>Docente</span>";
                modalita = "Segreteria didattica";
            }
            html.append("<tr><td>").append(nome)
                .append("</td><td>").append(badge)
                .append("</td><td>").append(modalita).append("</td></tr>");
            r.registrazione();
        }
        html.append("</table>");
        aggiornaVista(html.toString());
    }

    // ─────────────────────────────────────────────────────────────────────────
    // VISTE TABELLA POST-INSERIMENTO
    // ─────────────────────────────────────────────────────────────────────────
    static void mostraTabStudenti() {
        StringBuilder html = new StringBuilder("<h1>Studenti</h1>");
        html.append("<table><tr><th>Nome</th><th>Eta'</th><th>Classe</th><th>Tipo</th></tr>");
        for (Studente s : studenti) {
            String badge;
            if (s instanceof StudenteLiceo sl)
                badge = "<span class='badge badge-liceo'>Liceo &ndash; " + sl.getIndirizzo() + "</span>";
            else if (s instanceof StudenteProfessionale sp)
                badge = "<span class='badge badge-prof'>Professionale &ndash; " + sp.getSpecializzazione() + "</span>";
            else badge = "";
            html.append("<tr><td>").append(s.getNome())
                .append("</td><td>").append(s.getEta())
                .append("</td><td>").append(s.getClasseFrequentata())
                .append("</td><td>").append(badge).append("</td></tr>");
        }
        html.append("</table>");
        aggiornaVista(html.toString());
    }

    static void mostraTabDocenti() {
        StringBuilder html = new StringBuilder("<h1>Docenti</h1>");
        html.append("<table><tr><th>Nome</th><th>Eta'</th><th>Materia</th><th>Tipo</th></tr>");
        for (Docente d : docenti) {
            String badge;
            if (d instanceof DocenteOrdinario dord)
                badge = "<span class='badge badge-ordinario'>Ordinario &ndash; " + dord.getAnniEsperienza() + " anni</span>";
            else if (d instanceof DocenteSostegno ds)
                badge = "<span class='badge badge-sostegno'>Sostegno &ndash; " + ds.getTipoSupporto() + "</span>";
            else badge = "";
            html.append("<tr><td>").append(d.getNome())
                .append("</td><td>").append(d.getEta())
                .append("</td><td>").append(d.getMateria())
                .append("</td><td>").append(badge).append("</td></tr>");
        }
        html.append("</table>");
        aggiornaVista(html.toString());
    }
}