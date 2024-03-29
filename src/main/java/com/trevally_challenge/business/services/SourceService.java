package com.trevally_challenge.business.services;

import com.trevally_challenge.business.util.ErrorMessages;
import com.trevally_challenge.infrastructure.entities.Source;
import com.trevally_challenge.infrastructure.exceptions.SourceNotFoundException;
import com.trevally_challenge.infrastructure.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceService {

    @Autowired
    private ErrorMessages errorMessages;

    @Autowired
    private SourceRepository sourceRepository;

    public Source getSourceById(String id) {
        return sourceRepository.findById(id)
                .orElseThrow(() -> new SourceNotFoundException(errorMessages.getSourceNotFoundExceptionMessageError()));
    }

    public void deleteSource(String id) {
        sourceRepository.deleteById(id);
    }

    public List<Source> getAllSources() {
        return sourceRepository.findAll();
    }
}
