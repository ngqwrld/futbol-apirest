package com.gestion.futbol.repository;

import com.gestion.futbol.model.Futbolista;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FutbolistaRepository extends MongoRepository<Futbolista, String> {
    List<Futbolista> findByClubId(String clubId);
}
