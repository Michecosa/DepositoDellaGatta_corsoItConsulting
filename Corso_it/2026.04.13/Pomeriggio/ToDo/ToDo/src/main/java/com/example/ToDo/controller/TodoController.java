package com.example.ToDo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ToDo.model.Todo;
import com.example.ToDo.service.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {

  // Lista in memoria che simula un database
  private final List<Todo> todos = new ArrayList<>();

  // Contatore per assegnare id progressivi
  private Long idCounter = 1L;

  private final TodoService todoService;

  public TodoController(TodoService todoService) {
    todos.add(new Todo(idCounter++, "Studiare Spring Boot", false));
    todos.add(new Todo(idCounter++, "Fare la spesa", false));
    todos.add(new Todo(idCounter++, "Chiamare il dentista", true));
    this.todoService = todoService;
  }

  // Restituisce tutti i todo
  @GetMapping
  public List<Todo> getAll() {
    return todos;
  }

  // Aggiunge un nuovo todo
  @PostMapping
  public Todo crea(@RequestBody Todo todo) {
    todo.setId(idCounter++);
    todo.setCompletato(false);
    todos.add(todo);
    return todo;
  }

  // Restituisce un singolo todo per id
  @GetMapping("/{id}")
  public Todo getById(@PathVariable Long id) {
    return todos.stream()
        .filter(t -> t.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  // Segna un todo come completato
  @PatchMapping("/{id}/completa")
  public Todo completa(@PathVariable Long id) {
    return todos.stream()
        .filter(t -> t.getId().equals(id))
        .findFirst()
        .map(t -> {
          t.setCompletato(true);
          return t;
        })
        .orElse(null);
  }

  // Elimina un todo per id
  @DeleteMapping("/{id}")
  public String elimina(@PathVariable Long id) {
    boolean rimosso = todos.removeIf(t -> t.getId().equals(id));
    return rimosso ? "Todo eliminato" : "Todo non trovato";
  }
}