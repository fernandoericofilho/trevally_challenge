package com.trevally_challenge.controllers;

import com.trevally_challenge.domain.request.CSVRequest;
import com.trevally_challenge.domain.response.MappedColumnsResponse;
import com.trevally_challenge.services.CSVFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/csv")
public class CSVController {

    @Autowired
    private CSVFileService csvFileService;

    @PostMapping("/headers")
    public ResponseEntity<MappedColumnsResponse> collectHeaderFromCSV(@RequestBody CSVRequest request) {
        return ResponseEntity.ok().body(csvFileService.extractHeaders(request));
    }

    @PostMapping("/persist")
    public ResponseEntity<MappedColumnsResponse> persistDataFromCSV(@RequestBody CSVRequest request) {
        csvFileService.processSource(request);
        return ResponseEntity.ok().body(null);
    }

}
