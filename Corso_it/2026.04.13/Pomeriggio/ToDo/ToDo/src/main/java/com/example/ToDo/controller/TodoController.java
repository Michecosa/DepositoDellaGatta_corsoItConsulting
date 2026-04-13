package com.example.ToDo.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.ToDo.model.StatoTask;
import com.example.ToDo.model.Todo;

@CrossOrigin("*")
@RestController
@RequestMapping("/todos")
public class TodoController {

  private final List<Todo> todos = new ArrayList<>();
  private Long idCounter = 1L;

  public TodoController() {
    todos.add(new Todo(idCounter++, "Studiare Spring Boot", StatoTask.TODO, 2));
    todos.add(new Todo(idCounter++, "Fare la spesa", StatoTask.IN_PROGRESS, 3));
    todos.add(new Todo(idCounter++, "Chiamare il dentista", StatoTask.DONE, 1));
    todos.add(new Todo(idCounter++, "Andare in palestra", StatoTask.TODO, 1));
  }

  // Restituisce tutti i todo, con filtri opzionali per stato e descrizione
  @GetMapping
  public List<Todo> getAll(
      @RequestParam(required = false) StatoTask stato,
      @RequestParam(required = false) String search) {

      return todos.stream()
          .filter(t -> stato == null || t.getStato() == stato)
          .filter(t -> search == null || t.getDescrizione().toLowerCase().contains(search.toLowerCase()))
          .sorted(Comparator.comparingInt((Todo t) -> t.getPriorita())
              .thenComparing(t -> t.getDescrizione()))
          .collect(Collectors.toList());
  }

  // Restituisce un singolo todo per id
  @GetMapping("/{id}")
  public Todo getById(@PathVariable Long id) {
    return todos.stream()
        .filter(t -> t.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  // Crea un nuovo todo, parte sempre da TODO
  @PostMapping
  public Todo crea(@RequestBody Todo todo) {
    todo.setId(idCounter++);
    todo.setStato(StatoTask.TODO);
    todos.add(todo);
    return todo;
  }

  // Aggiorna lo stato secondo il flusso permesso
  @PutMapping("/{id}")
  public Object aggiornaStato(@PathVariable Long id, @RequestBody Todo aggiornato) {
    Todo todo = todos.stream()
        .filter(t -> t.getId().equals(id))
        .findFirst()
        .orElse(null);

    if (todo == null) return "Todo non trovato";

    StatoTask statoAttuale = todo.getStato();
    StatoTask statoNuovo = aggiornato.getStato();

    /* 
      tasitioneValida è true SE
        sono in TODO & voglio andare a IN_PROGRESS oppure CANCELLED
          oppure
        sono in IN_PROGRESS & voglio andare a DONE oppure CENCELLED
    */
    boolean transitioneValida =
        (statoAttuale == StatoTask.TODO && (statoNuovo == StatoTask.IN_PROGRESS || statoNuovo == StatoTask.CANCELLED)) ||
        (statoAttuale == StatoTask.IN_PROGRESS && (statoNuovo == StatoTask.DONE || statoNuovo == StatoTask.CANCELLED));

    if (!transitioneValida) return "Transizione di stato non consentita";

    todo.setStato(statoNuovo);
    return todo;
  }

  // Elimina un todo per id
  @DeleteMapping("/{id}")
  public String elimina(@PathVariable Long id) {
    boolean rimosso = todos.removeIf(t -> t.getId().equals(id));
    return rimosso ? "Todo eliminato" : "Todo non trovato";
  }
}