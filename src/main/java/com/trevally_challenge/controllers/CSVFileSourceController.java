package com.trevally_challenge.controllers;

import com.trevally_challenge.domain.dto.CSVMappedColumnsDTO;
import com.trevally_challenge.services.CSVFileSourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/csv")
public class CSVFileSourceController {

    private final CSVFileSourceService csvFileSourceService;

    public CSVFileSourceController(CSVFileSourceService csvFileSourceService) {
        this.csvFileSourceService = csvFileSourceService;
    }

    @PostMapping("/headers")
    public ResponseEntity<List<CSVMappedColumnsDTO>> getCSVHeaders(@RequestParam("file") MultipartFile file,
                                                                   @RequestParam("delimiter") String delimiter) {
        List<CSVMappedColumnsDTO> headers = csvFileSourceService.getCSVHeaders(file, delimiter);
        return ResponseEntity.ok().body(headers);
    }

}
