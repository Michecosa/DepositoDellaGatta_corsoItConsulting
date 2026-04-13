package com.example.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.model.Prodotto;

@RestController
@RequestMapping("/prodotti")
public class ProdottoController {

  // Lista in memoria che simula un database
  private final List<Prodotto> prodotti = new ArrayList<>();

  // Contatore per assegnare id progressivi
  private Long idCounter = 1L;

  // Restituisce tutti i prodotti
  @GetMapping
  public List<Prodotto> getAll() {
    return prodotti;
  }

  // Aggiunge un nuovo prodotto assegnandogli un id automatico
  @PostMapping
  public Prodotto crea(@RequestBody Prodotto prodotto) {
    prodotto.setId(idCounter++);
    prodotti.add(prodotto);
    return prodotto;
  }

  // Restituisce un singolo prodotto per id
  @GetMapping("/{id}")
  public Prodotto getById(@PathVariable Long id) {
    return prodotti.stream() // 1. apre il flusso sulla lista
            .filter(p -> p.getId().equals(id)) // 2. tiene solo il prodotto con quell'id
            .findFirst() // 3. prende il primo risultato → Optional<Prodotto>
            .orElse(null); // 4. se non trovato restituisce null
  }

  // Aggiorna TUTTO (nome e prezzo) di un prodotto esistente
  @PutMapping("/{id}")
  public Prodotto sostituisci(@PathVariable Long id, @RequestBody Prodotto aggiornato) {
    return prodotti.stream()
        .filter(p -> p.getId().equals(id))
        .findFirst()
        .map(p -> {
          p.setNome(aggiornato.getNome());
          p.setPrezzo(aggiornato.getPrezzo());
          return p;
        })
        .orElse(null);
  }

  // Aggiorna solo i campi presenti
  @PatchMapping("/{id}")
  public Prodotto aggiorna(@PathVariable Long id, @RequestBody Prodotto aggiornato) {
    return prodotti.stream()
        .filter(p -> p.getId().equals(id))
        .findFirst()
        .map(p -> {
            if (aggiornato.getNome() != null) p.setNome(aggiornato.getNome());
            if (aggiornato.getPrezzo() != null) p.setPrezzo(aggiornato.getPrezzo());
            return p;
        })
        .orElse(null);
  }
  
  // Elimina un prodotto per id
  @DeleteMapping("/{id}")
  public String elimina(@PathVariable Long id) {
    boolean rimosso = prodotti.removeIf(p -> p.getId().equals(id));
    return rimosso ? "Prodotto eliminato." : "Prodotto non trovato.";
  }
}