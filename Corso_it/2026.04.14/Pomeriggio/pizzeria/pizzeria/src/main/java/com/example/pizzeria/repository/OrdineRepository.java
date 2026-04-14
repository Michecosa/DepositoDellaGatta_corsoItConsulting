package com.example.pizzeria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pizzeria.model.Ordine;
import com.example.pizzeria.model.Ordine.StatoOrdine;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long> {
    List<Ordine> findByUtenteId(Long utenteId);
    List<Ordine> findByStato(StatoOrdine stato);
}