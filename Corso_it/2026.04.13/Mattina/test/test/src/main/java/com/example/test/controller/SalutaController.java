package com.example.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.service.MessaggioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class SalutaController {
  private final MessaggioService messaggioService;

  // Constructor injection
  @Autowired
  public SalutaController(MessaggioService ms) {
    this.messaggioService = ms;
  }

  @GetMapping("/saluta")
  public String saluta() {
    messaggioService.saluta();
    return "Saluto inviato";
  }
}
