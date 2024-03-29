package com.trevally_challenge.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

/**
 * DTO (Data Transfer Object) representing the structure of mapped columns in a CSV file for display.
 * Each instance of this DTO represents a mapped column from the CSV file.
 *
 * - {@code from}: Relevant column from the CSV.
 * - {@code label}: How the column will be represented on the Contacts Card.
 * - {@code index}: Position at which the column will be represented on the contact card.
 *   The user can change the position from where the field will be displayed.
 *   For example, display the Last Name (or Full Name) first instead of First Name.
 */
@Data
@Builder
public class CSVMappedColumnsDTO {
    private String from;
    private String label;
    private int index;
}
