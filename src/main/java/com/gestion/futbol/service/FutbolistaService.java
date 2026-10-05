package com.gestion.futbol.service;

import com.gestion.futbol.model.Futbolista;
import com.gestion.futbol.repository.FutbolistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FutbolistaService {

    private final FutbolistaRepository repository;

    public FutbolistaService(FutbolistaRepository repository) {
        this.repository = repository;
    }

    public List<Futbolista> listarTodos() { return repository.findAll(); }

    public Optional<Futbolista> buscarPorId(String id) { return repository.findById(id); }

    public List<Futbolista> buscarPorClub(String clubId) { return repository.findByClubId(clubId); }

    public Futbolista guardar(Futbolista futbolista) { return repository.save(futbolista); }

    public void eliminar(String id) { repository.deleteById(id); }
}
