package com.trevally_challenge.infrastructure.repository;

import com.trevally_challenge.infrastructure.entities.Source;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends MongoRepository<Source, String> {
}