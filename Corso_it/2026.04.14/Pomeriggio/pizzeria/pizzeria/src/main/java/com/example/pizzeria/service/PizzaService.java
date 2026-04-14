package com.example.pizzeria.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pizzeria.model.Pizza;
import com.example.pizzeria.repository.PizzaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PizzaService {

  private final PizzaRepository pizzaRepository;

  public List<Pizza> findAll() {
    return pizzaRepository.findAll();
  }

  public Pizza findById(Long id) {
    return pizzaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Pizza non trovata"));
  }

  public Pizza findByNome(String nome) {
    return pizzaRepository.findByNome(nome)
        .orElseThrow(() -> new RuntimeException("Pizza non trovata"));
  }

  public Pizza save(Pizza pizza) {
    return pizzaRepository.save(pizza);
  }

  public void deleteById(Long id) {
    pizzaRepository.deleteById(id);
  }
}