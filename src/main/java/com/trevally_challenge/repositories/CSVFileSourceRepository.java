package com.trevally_challenge.repositories;

import com.trevally_challenge.domain.entities.CSVFileSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CSVFileSourceRepository extends JpaRepository<CSVFileSource, UUID> {
}