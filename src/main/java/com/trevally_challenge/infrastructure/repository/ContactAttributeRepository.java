package com.trevally_challenge.infrastructure.repository;

import com.trevally_challenge.infrastructure.entities.ContactAttribute;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactAttributeRepository extends MongoRepository<ContactAttribute, UUID> {
}