package com.example.todosecurity.controller;

import com.example.todosecurity.dto.TodoRequest;
import com.example.todosecurity.dto.TodoResponse;
import com.example.todosecurity.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos") // protetto (richiede JWT)
public class TodoController {

  private final TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  // GET /todos => lista tutti i todo dell'utente autenticato
  @GetMapping
  public List<TodoResponse> getTodos(Authentication auth) {
    return todoService.getTodos(auth.getName());
  }

  // POST /todos => crea un nuovo todo
  @PostMapping
  public ResponseEntity<TodoResponse> creaTodo(
      @Valid @RequestBody TodoRequest request,
      Authentication auth) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(todoService.creaTodo(auth.getName(), request));
  }

  // PUT /todos/{id} => aggiorna titolo/descrizione
  @PutMapping("/{id}")
  public TodoResponse aggiornaTodo(
      @PathVariable Long id,
      @Valid @RequestBody TodoRequest request,
      Authentication auth) {
    return todoService.aggiornaTodo(auth.getName(), id, request);
  }

  // PATCH /todos/{id}/toggle => segna come completato/non completato
  @PatchMapping("/{id}/toggle")
  public TodoResponse toggleCompletato(@PathVariable Long id, Authentication auth) {
    return todoService.toggleCompletato(auth.getName(), id);
  }

  // DELETE /todos/{id} => elimina un todo
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminaTodo(@PathVariable Long id, Authentication auth) {
    todoService.eliminaTodo(auth.getName(), id);
    return ResponseEntity.noContent().build();
  }
}