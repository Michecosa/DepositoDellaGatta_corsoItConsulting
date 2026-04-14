package com.example.ToDo.service;

import com.example.ToDo.model.Todo;
import com.example.ToDo.repository.ToDoRepository; // Importa il repository
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    // 1. Inietto il repository invece della lista in memoria
    private final ToDoRepository repository;

    public ToDoService(ToDoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> getAll() {
        // 2. Uso i metodi pronti di CrudRepository
        return (List<Todo>) repository.findAll();
    }

    public Optional<Todo> getById(Long id) {
        return repository.findById(id);
    }

    public Todo create(Todo nuovo) {
        // 3. Non serve più idCounter++, lo fa il DB (GenerationType.IDENTITY)
        return repository.save(nuovo);
    }

    public Optional<Todo> update(Long id, Todo modificato) {
        return repository.findById(id).map(todo -> {
            todo.setDescrizione(modificato.getDescrizione());
            todo.setStato(modificato.getStato());
            todo.setPriorita(modificato.getPriorita());
            return repository.save(todo); // Salvo le modifiche nel DB
        });
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}