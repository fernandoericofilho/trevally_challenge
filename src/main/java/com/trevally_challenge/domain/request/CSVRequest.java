package com.trevally_challenge.domain.request;

import com.trevally_challenge.domain.dto.CSVMappedColumnsDTO;
import lombok.Data;

import java.util.List;

@Data
public class CSVRequest {
    private String filePath;
    private String delimiter;
    private List<CSVMappedColumnsDTO> mappedColumns;
}
