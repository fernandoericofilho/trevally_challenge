package com.trevally_challenge.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactAttributeDTO {
    private String id;
    private String attributeName;
    private String attributeValue;
}
