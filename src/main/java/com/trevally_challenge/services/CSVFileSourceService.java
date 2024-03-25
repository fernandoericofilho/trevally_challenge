package com.trevally_challenge.services;

import com.trevally_challenge.config.ErrorMessages;
import com.trevally_challenge.domain.dto.CSVMappedColumnsDTO;
import com.trevally_challenge.enums.ValidFileFormat;
import com.trevally_challenge.exceptions.EmptyFileException;
import com.trevally_challenge.exceptions.FileProcessingException;
import com.trevally_challenge.exceptions.InvalidDelimiterException;
import com.trevally_challenge.exceptions.MissingHeaderException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVFileSourceService {

    private final ErrorMessages errorMessages;

    public CSVFileSourceService(ErrorMessages errorMessages) {
        this.errorMessages = errorMessages;
    }

    public List<CSVMappedColumnsDTO> getCSVHeaders(MultipartFile file, String delimiter) {
        try {
            return processHeaders(file, delimiter);
        } catch (IOException e) {
            throw new FileProcessingException(errorMessages.getProcessingFileError() + e.getMessage());
        }
    }

    private List<CSVMappedColumnsDTO> processHeaders(MultipartFile file, String delimiter) throws IOException {
        return getCsvMappedColumnsDTOS(file, delimiter);
    }

    private List<CSVMappedColumnsDTO> getCsvMappedColumnsDTOS(MultipartFile file, String delimiter) throws IOException {
        List<CSVMappedColumnsDTO> mappedColumns = new ArrayList<>();
        validateFileNotEmpty(file);
        validateFileExtension(file);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line = br.readLine();
            checkHeaderExists(line);
            checkDelimiter(delimiter, line);
            mapColumns(line.split(delimiter), mappedColumns);
        }
        return mappedColumns;
    }

    private void validateFileNotEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new EmptyFileException(errorMessages.getEmptyFileError());
        }
    }

    private void validateFileExtension(MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (!isValidFileExtension(filename)) {
            throw new FileProcessingException(errorMessages.getInvalidFileFormatError());
        }
    }

    private boolean isValidFileExtension(String filename) {
        return filename != null && filename.toLowerCase().endsWith(ValidFileFormat.VALID_CSV.getExtension());
    }

    private void checkHeaderExists(String line) {
        if (line == null || line.isEmpty()) {
            throw new MissingHeaderException(errorMessages.getMissingHeaderError());
        }
    }

    private void checkDelimiter(String delimiter, String headerLine) {
        if (!headerLine.contains(delimiter)) {
            throw new InvalidDelimiterException(errorMessages.getInvalidDelimiterError());
        }
    }

    private void mapColumns(String[] headers, List<CSVMappedColumnsDTO> mappedColumns) {
        for (int i = 0; i < headers.length; i++) {
            CSVMappedColumnsDTO mappedColumn = new CSVMappedColumnsDTO();
            mappedColumn.setFrom(headers[i]);
            mappedColumn.setLabel(headers[i]);
            mappedColumn.setIndex(i);
            mappedColumns.add(mappedColumn);
        }
    }
}
