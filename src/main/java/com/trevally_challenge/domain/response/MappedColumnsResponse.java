package com.trevally_challenge.domain.response;

import com.trevally_challenge.domain.dto.CSVMappedColumnsDTO;
import lombok.Data;

import java.util.List;

@Data
public class MappedColumnsResponse {
    private List<CSVMappedColumnsDTO> mappedColumns;

    public MappedColumnsResponse(List<CSVMappedColumnsDTO> mappedColumns) {
        this.mappedColumns = mappedColumns;
    }
}