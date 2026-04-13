package com.example.ToDo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ToDo.model.Todo;

@Service
public class TodoService {

  private final List<Todo> todos = new ArrayList<>();
  private Long idCounter = 1L;

  // Costruttore per popolare la lista all'avvio
  public TodoService() {
    todos.add(new Todo(idCounter++, "Studiare Spring Boot", false));
    todos.add(new Todo(idCounter++, "Fare la spesa", false));
    todos.add(new Todo(idCounter++, "Chiamare l'elettricista", true));
  }

  

  public List<Todo> getAll() {
    return todos;
  }

  public Todo crea(Todo todo) {
    todo.setId(idCounter++);
    todo.setCompletato(false);
    todos.add(todo);
    return todo;
  }

  public Todo completa(Long id) {
    return todos.stream()
        .filter(t -> t.getId().equals(id))
        .findFirst()
        .map(t -> {
          t.setCompletato(true);
          return t;
        })
        .orElse(null);
  }

  public String elimina(Long id) {
    boolean rimosso = todos.removeIf(t -> t.getId().equals(id));
    return rimosso ? "Todo eliminato" : "Todo non trovato";
  }
}