package com.example.todo.repository;

import com.example.todo.model.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
// Tutti i metodi CRUD già pronti!
}