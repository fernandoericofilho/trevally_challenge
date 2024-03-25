package com.trevally_challenge.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * Represents a contact extracted from a CSV file.
 *
 * - {@code id}: Unique identifier for the contact.
 * - {@code email}: Email address of the contact.
 * - {@code attributes}: List of attributes associated with the contact.
 * - {@code csvFileSource}: The CSV file source to which this contact belongs.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "csv_contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String email;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
    private List<ContactAttribute> attributes;

    @ManyToOne
    @JoinColumn(name = "csv_file_source_id")
    private CSVFileSource csvFileSource;
}
