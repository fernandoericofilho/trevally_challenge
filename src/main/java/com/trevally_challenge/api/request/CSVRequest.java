package com.trevally_challenge.api.request;

import com.trevally_challenge.infrastructure.dto.CSVMappedColumnsDTO;
import lombok.Data;

import java.util.List;

@Data
public class CSVRequest {
    private String filePath;
    private String delimiter;
    private List<CSVMappedColumnsDTO> mappedColumns;
}
