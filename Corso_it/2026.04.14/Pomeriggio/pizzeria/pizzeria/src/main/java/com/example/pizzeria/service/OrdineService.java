package com.example.pizzeria.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pizzeria.model.Ordine;
import com.example.pizzeria.model.Ordine.StatoOrdine;
import com.example.pizzeria.repository.OrdineRepository;
import com.example.pizzeria.repository.UtenteRepository;
import com.example.pizzeria.repository.PizzaRepository;
import com.example.pizzeria.repository.ExtraRepository;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdineService {

  private final OrdineRepository ordineRepository;
  private final UtenteRepository utenteRepository;
  private final PizzaRepository pizzaRepository;
  private final ExtraRepository extraRepository;

  public List<Ordine> findAll() {
    return ordineRepository.findAll();
  }

  public Ordine findById(Long id) {
    return ordineRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Ordine non trovato"));
  }

  public List<Ordine> findByUtenteId(Long utenteId) {
    return ordineRepository.findByUtenteId(utenteId);
  }

  public List<Ordine> findByStato(StatoOrdine stato) {
    return ordineRepository.findByStato(stato);
  }

  public Ordine save(Ordine ordine) {
    try {
      if (ordine.getDataOra() == null) {
        ordine.setDataOra(java.time.LocalDateTime.now());
      }

      // Se è un nuovo ordine, aggancio le entità reali dal database
      if (ordine.getUtente() != null && ordine.getUtente().getId() != null) {
        ordine.setUtente(utenteRepository.findById(ordine.getUtente().getId())
            .orElseThrow(() -> new RuntimeException("Utente non trovato")));
      }

      if (ordine.getPizze() != null) {
        ordine.setPizze(ordine.getPizze().stream()
            .filter(p -> p.getId() != null)
            .map(p -> pizzaRepository.findById(p.getId())
                .orElseThrow(() -> new RuntimeException("Pizza non trovata: " + p.getId())))
            .collect(Collectors.toList()));
      }

      if (ordine.getExtra() != null) {
        ordine.setExtra(ordine.getExtra().stream()
            .filter(e -> e.getId() != null)
            .map(e -> extraRepository.findById(e.getId())
                .orElseThrow(() -> new RuntimeException("Extra non trovato: " + e.getId())))
            .collect(Collectors.toList()));
      }

      return ordineRepository.save(ordine);
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  public void deleteById(Long id) {
    ordineRepository.deleteById(id);
  }
}
