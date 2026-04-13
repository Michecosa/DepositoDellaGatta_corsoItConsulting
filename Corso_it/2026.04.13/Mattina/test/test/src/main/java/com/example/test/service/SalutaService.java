package com.example.test.service;

import org.springframework.stereotype.Service;

@Service
public class SalutaService {

  public String saluta(String nome) {
    return "Ciao " + nome + "!";
  }
}