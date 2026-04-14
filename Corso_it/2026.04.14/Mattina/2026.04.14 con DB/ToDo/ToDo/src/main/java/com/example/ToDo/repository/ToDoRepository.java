package com.example.ToDo.repository;

import com.example.ToDo.model.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends CrudRepository<Todo, Long> {
  // Tutti i metodi CRUD sono già pronti
}