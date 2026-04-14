package com.example.todolistrelazionale.repository;

import com.example.todolistrelazionale.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
}