package com.trevally_challenge.business.services;

import com.trevally_challenge.business.util.ErrorMessages;
import com.trevally_challenge.infrastructure.dto.SourceDTO;
import com.trevally_challenge.infrastructure.entities.Source;
import com.trevally_challenge.infrastructure.exceptions.SourceNotFoundException;
import com.trevally_challenge.infrastructure.mappers.SourceMapper;
import com.trevally_challenge.infrastructure.repository.SourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SourceService {

    private final ErrorMessages errorMessages;

    private final SourceRepository sourceRepository;

    private final SourceMapper sourceMapper;

    public Source getSourceById(String id) {
        return sourceRepository.findById(id)
                .orElseThrow(() -> new SourceNotFoundException(errorMessages.getSourceNotFoundExceptionMessageError()));
    }

    public void deleteSource(String id) {
        sourceRepository.deleteById(id);
    }

    public List<SourceDTO> getAllSources() {
        List<Source> sources = sourceRepository.findAll();
        return sources.stream()
                .map(sourceMapper::sourceToSourceDTO)
                .collect(Collectors.toList());
    }
}
