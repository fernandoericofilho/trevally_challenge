package com.trevally_challenge.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Represents an attribute of a contact extracted from a CSV file.
 *
 * - {@code id}: Unique identifier for the attribute.
 * - {@code attributeName}: The name of the attribute.
 * - {@code attributeValue}: The value of the attribute.
 * - {@code contact}: The contact to which this attribute belongs.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "csv_contact_attribute")
public class ContactAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String attributeName;
    private String attributeValue;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;
}
