package com.example.test.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.service.SalutaService;

@RestController
public class SalutaController {

  private final SalutaService salutaService;

  public SalutaController(SalutaService salutaService) {
    this.salutaService = salutaService;
  }

  @PostMapping("/saluta")
  public String saluta(@RequestBody String nome) {
    return salutaService.saluta(nome);
  }
}