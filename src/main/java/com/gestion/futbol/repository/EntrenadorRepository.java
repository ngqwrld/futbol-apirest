package com.gestion.futbol.repository;

import com.gestion.futbol.model.Entrenador;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntrenadorRepository extends MongoRepository<Entrenador, String> {
}
