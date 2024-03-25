package com.trevally_challenge.repositories;

import com.trevally_challenge.domain.entities.ContactAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactAttributeRepository extends JpaRepository<ContactAttribute, UUID> {
}