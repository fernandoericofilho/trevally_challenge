package com.trevally_challenge.api.controllers;

import com.trevally_challenge.business.services.SourceService;
import com.trevally_challenge.infrastructure.dto.SourceDTO;
import com.trevally_challenge.infrastructure.entities.Source;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sources")
public class SourceController {

    private final SourceService sourceService;

    @GetMapping
    public ResponseEntity<List<SourceDTO>> getAllSources() {
        List<SourceDTO> sources = sourceService.getAllSources();
        return new ResponseEntity<>(sources, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Source> getSourceById(@PathVariable("id") String id) {
        Source source = sourceService.getSourceById(id);
        return new ResponseEntity<>(source, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSource(@PathVariable("id") String id) {
        sourceService.deleteSource(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
