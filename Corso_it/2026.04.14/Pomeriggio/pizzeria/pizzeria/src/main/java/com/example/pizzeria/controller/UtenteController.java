package com.example.pizzeria.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.pizzeria.model.Utente;
import com.example.pizzeria.service.UtenteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/utenti")
@RequiredArgsConstructor
public class UtenteController {

  private final UtenteService utenteService;

  @GetMapping
  public List<Utente> findAll() {
    return utenteService.findAll();
  }

  @GetMapping("/{id}")
  public Utente findById(@PathVariable Long id) {
    return utenteService.findById(id);
  }

  @GetMapping("/email/{email}")
  public Utente findByEmail(@PathVariable String email) {
    return utenteService.findByEmail(email);
  }

  @PostMapping
  public Utente save(@RequestBody Utente utente) {
    return utenteService.save(utente);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    utenteService.deleteById(id);
  }
}