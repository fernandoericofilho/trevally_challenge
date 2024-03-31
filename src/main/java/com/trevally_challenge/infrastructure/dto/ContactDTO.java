package com.trevally_challenge.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {
    private String id;
    private String email;
    private List<ContactAttributeDTO> attributes;
}
