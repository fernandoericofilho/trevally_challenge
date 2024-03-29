package com.trevally_challenge.infrastructure.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents an attribute of a contact extracted from a CSV file.
 *
 * - {@code id}: Unique identifier for the attribute.
 * - {@code attributeName}: The name of the attribute.
 * - {@code attributeValue}: The value of the attribute.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "contact_attributes")
public class ContactAttribute {

    @Id
    private String id;
    private String attributeName;
    private String attributeValue;
}
