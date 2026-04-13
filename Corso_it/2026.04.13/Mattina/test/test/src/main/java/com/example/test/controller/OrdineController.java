package com.example.test.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.service.OrdineService;

@RestController
public class OrdineController {

  private final OrdineService ordineService;

  public OrdineController(OrdineService ordineService) {
    this.ordineService = ordineService;
  }

  @PostMapping("/ordina")
  public String ordina() {
    ordineService.creaOrdine();
    return "Ordine effettuato.";
  }
}