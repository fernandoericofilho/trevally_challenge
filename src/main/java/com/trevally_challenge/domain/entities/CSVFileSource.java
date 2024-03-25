package com.trevally_challenge.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Represents a source of a CSV file, including its metadata and headers.
 *
 * - {@code id}: Unique identifier for the CSV file source.
 * - {@code importDate}: Date and time when the CSV file was imported.
 * - {@code success}: Indicates if the CSV file import was successful or not.
 * - {@code filePath}: Path to the CSV file.
 * - {@code contacts}: List of contacts extracted from the CSV file.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "csv_file_source")
public class CSVFileSource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private LocalDateTime importDate;
    private boolean success;
    private String filePath;

    @OneToMany(mappedBy = "csvFileSource", cascade = CascadeType.ALL)
    private List<Contact> contacts;
}
