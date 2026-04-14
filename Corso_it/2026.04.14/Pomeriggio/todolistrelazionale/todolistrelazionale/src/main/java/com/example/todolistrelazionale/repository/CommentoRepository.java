package com.example.todolistrelazionale.repository;

import com.example.todolistrelazionale.model.Commento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentoRepository extends JpaRepository<Commento, Long> {
  List<Commento> findByTodoId(Long todoId);
}