package com.example.todolistrelazionale.repository;

import com.example.todolistrelazionale.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
  List<Todo> findByUtenteId(Long utenteId);
}