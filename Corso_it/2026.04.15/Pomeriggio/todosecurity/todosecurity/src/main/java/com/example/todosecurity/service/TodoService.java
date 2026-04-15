package com.example.todosecurity.service;

import com.example.todosecurity.dto.TodoRequest;
import com.example.todosecurity.dto.TodoResponse;
import com.example.todosecurity.model.Todo;
import com.example.todosecurity.model.Utente;
import com.example.todosecurity.repository.TodoRepository;
import com.example.todosecurity.repository.UtenteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TodoService {

  private final TodoRepository todoRepository;
  private final UtenteRepository utenteRepository;

  public TodoService(TodoRepository todoRepository, UtenteRepository utenteRepository) {
    this.todoRepository = todoRepository;
    this.utenteRepository = utenteRepository;
  }

  // Recupera l'utente dal DB tramite username (preso dal JWT)
  private Utente getUtente(String username) {
    return utenteRepository.findByUsername(username)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato"));
  }

  // Converte Todo → TodoResponse
  private TodoResponse toResponse(Todo todo) {
    return TodoResponse.builder()
        .id(todo.getId())
        .titolo(todo.getTitolo())
        .descrizione(todo.getDescrizione())
        .completato(todo.isCompletato())
        .build();
  }

  public List<TodoResponse> getTodos(String username) {
    Utente utente = getUtente(username);
    return todoRepository.findByUtente(utente)
        .stream()
        .map(this::toResponse)
        .toList();
  }

  public TodoResponse creaTodo(String username, TodoRequest request) {
    Utente utente = getUtente(username);
    Todo todo = Todo.builder()
        .titolo(request.getTitolo())
        .descrizione(request.getDescrizione())
        .completato(false)
        .utente(utente)
        .build();
    return toResponse(todoRepository.save(todo));
  }

  public TodoResponse toggleCompletato(String username, Long id) {
    Utente utente = getUtente(username);
    Todo todo = todoRepository.findByIdAndUtente(id, utente)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo non trovato"));
    todo.setCompletato(!todo.isCompletato());
    return toResponse(todoRepository.save(todo));
  }

  public TodoResponse aggiornaTodo(String username, Long id, TodoRequest request) {
    Utente utente = getUtente(username);
    Todo todo = todoRepository.findByIdAndUtente(id, utente)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo non trovato"));
    todo.setTitolo(request.getTitolo());
    todo.setDescrizione(request.getDescrizione());
    return toResponse(todoRepository.save(todo));
  }

  public void eliminaTodo(String username, Long id) {
    Utente utente = getUtente(username);
    Todo todo = todoRepository.findByIdAndUtente(id, utente)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo non trovato"));
    todoRepository.delete(todo);
  }
}