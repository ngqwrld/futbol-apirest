package com.gestion.futbol.repository;

import com.gestion.futbol.model.Club;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClubRepository extends MongoRepository<Club, String> {
}
