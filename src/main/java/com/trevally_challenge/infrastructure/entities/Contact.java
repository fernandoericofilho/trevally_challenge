package com.trevally_challenge.infrastructure.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

/**
 * Represents a contact extracted from a CSV file.
 *
 * - {@code id}: Unique identifier for the contact.
 * - {@code email}: Email address of the contact.
 * - {@code attributes}: List of attributes associated with the contact.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "contacts")
public class Contact {

    @Id
    private UUID id;
    private String email;
    private List<ContactAttribute> attributes;
}
