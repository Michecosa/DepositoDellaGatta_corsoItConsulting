package com.example.pizzeria.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.pizzeria.model.Pizza;
import com.example.pizzeria.service.PizzaService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/pizze")
@RequiredArgsConstructor
public class PizzaController {

    private final PizzaService pizzaService;

    @GetMapping
    public List<Pizza> findAll() {
        return pizzaService.findAll();
    }

    @GetMapping("/{id}")
    public Pizza findById(@PathVariable Long id) {
        return pizzaService.findById(id);
    }

    @GetMapping("/nome/{nome}")
    public Pizza findByNome(@PathVariable String nome) {
        return pizzaService.findByNome(nome);
    }

    @PostMapping
    public Pizza save(@RequestBody Pizza pizza) {
        return pizzaService.save(pizza);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        pizzaService.deleteById(id);
    }
}