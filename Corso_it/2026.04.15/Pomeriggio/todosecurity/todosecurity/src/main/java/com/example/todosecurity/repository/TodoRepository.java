package com.example.todosecurity.repository;

import com.example.todosecurity.model.Todo;
import com.example.todosecurity.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
  List<Todo> findByUtente(Utente utente);

  Optional<Todo> findByIdAndUtente(Long id, Utente utente);
}