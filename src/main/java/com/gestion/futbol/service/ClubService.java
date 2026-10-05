package com.gestion.futbol.service;

import com.gestion.futbol.model.Club;
import com.gestion.futbol.repository.ClubRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubService {

    private final ClubRepository repository;

    public ClubService(ClubRepository repository) {
        this.repository = repository;
    }

    public List<Club> listarTodos() { return repository.findAll(); }

    public Optional<Club> buscarPorId(String id) { return repository.findById(id); }

    public Club guardar(Club club) { return repository.save(club); }

    public void eliminar(String id) { repository.deleteById(id); }
}
