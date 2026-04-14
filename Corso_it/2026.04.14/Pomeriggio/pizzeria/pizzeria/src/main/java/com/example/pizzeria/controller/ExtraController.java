package com.example.pizzeria.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.pizzeria.model.Extra;
import com.example.pizzeria.service.ExtraService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/extra")
@RequiredArgsConstructor
public class ExtraController {

    private final ExtraService extraService;

    @GetMapping
    public List<Extra> findAll() {
        return extraService.findAll();
    }

    @GetMapping("/{id}")
    public Extra findById(@PathVariable Long id) {
        return extraService.findById(id);
    }

    @GetMapping("/nome/{nome}")
    public Extra findByNome(@PathVariable String nome) {
        return extraService.findByNome(nome);
    }

    @PostMapping
    public Extra save(@RequestBody Extra extra) {
        return extraService.save(extra);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        extraService.deleteById(id);
    }
}