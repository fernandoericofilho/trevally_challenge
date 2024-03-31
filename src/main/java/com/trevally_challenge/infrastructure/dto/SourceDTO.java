package com.trevally_challenge.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SourceDTO {
    private String id;
    private LocalDateTime importDate;
    private boolean success;
    private String filePath;
    private List<ContactDTO> contacts;
}
