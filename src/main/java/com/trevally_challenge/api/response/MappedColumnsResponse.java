package com.trevally_challenge.api.response;

import com.trevally_challenge.infrastructure.dto.CSVMappedColumnsDTO;
import lombok.Data;

import java.util.List;

@Data
public class MappedColumnsResponse {
    private List<CSVMappedColumnsDTO> mappedColumns;

    public MappedColumnsResponse(List<CSVMappedColumnsDTO> mappedColumns) {
        this.mappedColumns = mappedColumns;
    }
}