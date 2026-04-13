package com.example.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.service.CalcolatriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class CalcolatriceController {
  private final CalcolatriceService calcolatriceService;

  // Constructor injection
  @Autowired
  public CalcolatriceController(CalcolatriceService cc) {
    this.calcolatriceService = cc;
  }

  @GetMapping("/somma")
  public int somma(@RequestParam int a, @RequestParam int b) {
      return calcolatriceService.somma(a, b);
  }
  
}
