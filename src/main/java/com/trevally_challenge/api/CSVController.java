package com.trevally_challenge.api;

import com.trevally_challenge.api.request.CSVRequest;
import com.trevally_challenge.api.response.MappedColumnsResponse;
import com.trevally_challenge.business.CSVFileService;
import com.trevally_challenge.infrastructure.entities.Source;
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
    public ResponseEntity<Source> persistDataFromCSV(@RequestBody CSVRequest request) {
        return ResponseEntity.ok().body(csvFileService.processSource(request));
    }

}
