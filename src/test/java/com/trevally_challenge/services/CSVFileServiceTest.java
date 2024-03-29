package com.trevally_challenge.services;

import com.trevally_challenge.api.request.CSVRequest;
import com.trevally_challenge.business.CSVFileService;
import com.trevally_challenge.business.util.ErrorMessages;
import com.trevally_challenge.infrastructure.dto.CSVMappedColumnsDTO;
import com.trevally_challenge.infrastructure.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class CSVFileServiceTest {

    @Mock
    private ErrorMessages errorMessages;

    @InjectMocks
    private CSVFileService csvFileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void extractHeaders_NonExistentFile_ThrowsFileExistException() {

        String filePath = "non_existent_file.csv";
        String delimiter = ";";
        CSVRequest request = new CSVRequest();
        request.setFilePath(filePath);
        request.setDelimiter(delimiter);

        assertThrows(FileExistException.class, () -> csvFileService.extractHeaders(request));
    }

    @Test
    void extractHeaders_InvalidFileExtension_ThrowsFileExtensionException() {

        String filePath = "src/test/resources/files/invalidFile.txt";
        String delimiter = ";";
        CSVRequest request = new CSVRequest();
        request.setFilePath(filePath);
        request.setDelimiter(delimiter);

        assertThrows(FileExtensionException.class, () -> csvFileService.extractHeaders(request));
    }

    @Test
    void extractHeaders_MissingHeaderInCSVFile_ThrowsMissingHeaderException() {
        String filePath = "src/test/resources/files/missingHeader.csv";
        String delimiter = ";";
        CSVRequest request = new CSVRequest();
        request.setFilePath(filePath);
        request.setDelimiter(delimiter);

        assertThrows(MissingHeaderException.class, () -> csvFileService.extractHeaders(request));
    }

    @Test
    void extractHeaders_InvalidDelimiterInCSVFile_ThrowsInvalidDelimiterException() {

        String filePath = "src/test/resources/files/invalidDelimiter.csv";
        String delimiter = ";"; // Different delimiter used in the file
        CSVRequest request = new CSVRequest();
        request.setFilePath(filePath);
        request.setDelimiter(delimiter);

        assertThrows(InvalidDelimiterException.class, () -> csvFileService.extractHeaders(request));
    }

    @Test
    void extractHeaders_InvalidColumnIndexInCSVFile_ThrowsIndexOutOfRangeException() {

        String filePath = "src/test/resources/files/exampleFile.csv";
        String delimiter = ";";
        List<String> headers = Arrays.asList("EMAIL", "FIRST_NAME", "LAST_NAME", "AGE", "FULL NAME", "ADDRESS");

        CSVRequest request = new CSVRequest();
        request.setFilePath(filePath);
        request.setDelimiter(delimiter);

        when(errorMessages.getIndexOutOfRangeExceptionMessageError()).thenReturn("Index out of range.");

        List<CSVMappedColumnsDTO> mappedColumns = Arrays.asList(
                CSVMappedColumnsDTO.builder().from("EMAIL").label("EMAIL").index(7).build(),
                CSVMappedColumnsDTO.builder().from("FIRST_NAME").label("FIRST_NAME").index(1).build(),
                CSVMappedColumnsDTO.builder().from("LAST_NAME").label("LAST_NAME").index(2).build()
        );

        request.setMappedColumns(mappedColumns);
        assertThrows(com.trevally_challenge.infrastructure.exceptions.IndexOutOfRangeException.class, () -> csvFileService.processSource(request));
    }

    @Test
    void processSource_EmptyCSVFile_ThrowsEmptyContactsException() {

        String filePath = "src/test/resources/files/emptyContacts.csv";
        String delimiter = ";";
        CSVRequest request = new CSVRequest();
        request.setFilePath(filePath);
        request.setDelimiter(delimiter);

        assertThrows(EmptyContactsException.class, () -> csvFileService.processSource(request));
    }

}
