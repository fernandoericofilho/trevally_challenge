package com.trevally_challenge.infrastructure.repository;

import com.trevally_challenge.infrastructure.entities.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactRepository extends MongoRepository<Contact, UUID> {
}