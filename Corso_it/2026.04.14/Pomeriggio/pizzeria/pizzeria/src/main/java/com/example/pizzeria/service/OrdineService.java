package com.example.pizzeria.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pizzeria.model.Ordine;
import com.example.pizzeria.model.Ordine.StatoOrdine;
import com.example.pizzeria.repository.OrdineRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdineService {

  private final OrdineRepository ordineRepository;

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
    return ordineRepository.save(ordine);
  }

  public void deleteById(Long id) {
    ordineRepository.deleteById(id);
  }
}
