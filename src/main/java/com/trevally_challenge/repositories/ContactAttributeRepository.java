package com.trevally_challenge.repositories;

import com.trevally_challenge.domain.entities.ContactAttribute;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactAttributeRepository extends MongoRepository<ContactAttribute, UUID> {
}