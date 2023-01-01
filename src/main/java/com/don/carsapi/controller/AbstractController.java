package com.don.carsapi.controller;

import com.don.carsapi.model.BaseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractController<T extends BaseModel<I>, I extends Serializable, R extends JpaRepository<T, I>> {

    @Autowired
   R repository;

    @GetMapping("/all")
    public ResponseEntity<List<T>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> findById(@PathVariable I id) {
        return repository.findById(id)
                .map(data -> ResponseEntity.ok().body(data))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T t) {
        if (t.getId() != null && repository.existsById(t.getId()))
            throw new DataIntegrityViolationException("ERR_DUPLICATE_KEY_EXCEPTION");
        return ResponseEntity.ok(repository.save(t));
    }

    @PutMapping
    public ResponseEntity<T> update(@RequestBody T t) {
        return repository.findById(t.getId())
                .map(data -> {
                    T updated = repository.save(t);
                    return ResponseEntity.ok(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable I id) {
        return repository.findById(id)
                .map(data -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
