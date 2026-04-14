package com.example.pizzeria.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.pizzeria.model.Ordine;
import com.example.pizzeria.model.Ordine.StatoOrdine;
import com.example.pizzeria.service.OrdineService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ordini")
@RequiredArgsConstructor
public class OrdineController {

  private final OrdineService ordineService;

  @GetMapping
  public List<Ordine> findAll() {
    return ordineService.findAll();
  }

  @GetMapping("/{id}")
  public Ordine findById(@PathVariable Long id) {
    return ordineService.findById(id);
  }

  @GetMapping("/utente/{utenteId}")
  public List<Ordine> findByUtenteId(@PathVariable Long utenteId) {
    return ordineService.findByUtenteId(utenteId);
  }

  @GetMapping("/stato/{stato}")
  public List<Ordine> findByStato(@PathVariable StatoOrdine stato) {
    return ordineService.findByStato(stato);
  }

  @PostMapping
  public Ordine save(@RequestBody Ordine ordine) {
    return ordineService.save(ordine);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    ordineService.deleteById(id);
  }
}