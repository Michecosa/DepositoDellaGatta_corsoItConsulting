package com.example.pizzeria.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pizzeria.model.Extra;
import com.example.pizzeria.repository.ExtraRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExtraService {

  private final ExtraRepository extraRepository;

  public List<Extra> findAll() {
    return extraRepository.findAll();
  }

  public Extra findById(Long id) {
    return extraRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Extra non trovato"));
  }

  public Extra findByNome(String nome) {
    return extraRepository.findByNome(nome)
        .orElseThrow(() -> new RuntimeException("Extra non trovato"));
  }

  public Extra save(Extra extra) {
    return extraRepository.save(extra);
  }

  public void deleteById(Long id) {
    extraRepository.deleteById(id);
  }
}