package com.trevally_challenge.api.controllers;

import com.trevally_challenge.api.request.CSVRequest;
import com.trevally_challenge.api.response.MappedColumnsResponse;
import com.trevally_challenge.business.services.CSVFileService;
import com.trevally_challenge.infrastructure.dto.SourceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/csv")
public class CSVController {

    private final CSVFileService csvFileService;

    @PostMapping("/headers")
    public ResponseEntity<MappedColumnsResponse> collectHeaderFromCSV(@RequestBody CSVRequest request) {
        return ResponseEntity.ok().body(csvFileService.extractHeaders(request));
    }

    @PostMapping("/persist")
    public ResponseEntity<SourceDTO> persistDataFromCSV(@RequestBody CSVRequest request) {
        return ResponseEntity.ok().body(csvFileService.processSource(request));
    }

}
