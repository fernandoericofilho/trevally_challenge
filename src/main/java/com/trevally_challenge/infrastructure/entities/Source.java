package com.trevally_challenge.infrastructure.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "sources")
public class Source {

    @Id
    private String id;
    private LocalDateTime importDate;
    private boolean success;
    private String filePath;
    private List<Contact> contacts;
}
