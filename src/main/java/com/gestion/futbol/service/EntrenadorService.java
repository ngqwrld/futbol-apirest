package com.gestion.futbol.service;

import com.gestion.futbol.model.Entrenador;
import com.gestion.futbol.repository.EntrenadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrenadorService {

    private final EntrenadorRepository repository;

    public EntrenadorService(EntrenadorRepository repository) {
        this.repository = repository;
    }

    public List<Entrenador> listarTodos() { return repository.findAll(); }

    public Optional<Entrenador> buscarPorId(String id) { return repository.findById(id); }

    public Entrenador guardar(Entrenador entrenador) { return repository.save(entrenador); }

    public void eliminar(String id) { repository.deleteById(id); }
}
