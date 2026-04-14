package com.example.pizzeria.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pizzeria.model.Utente;
import com.example.pizzeria.repository.UtenteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UtenteService {
  private final UtenteRepository utenteRepository;

  public List<Utente> findAll() {
    return utenteRepository.findAll();
  }

  public Utente findById(Long id) {
    return utenteRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Utente non trovato"));
  }

  public Utente findByEmail(String email) {
    return utenteRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Utente non trovato"));
  }

  public Utente save(Utente utente) {
    return utenteRepository.save(utente);
  }

  public void deleteById(Long id) {
    utenteRepository.deleteById(id);
  }
}
