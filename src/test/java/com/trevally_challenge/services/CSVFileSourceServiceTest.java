package com.trevally_challenge.services;

import com.trevally_challenge.config.ErrorMessages;
import com.trevally_challenge.domain.dto.CSVMappedColumnsDTO;
import com.trevally_challenge.exceptions.EmptyFileException;
import com.trevally_challenge.exceptions.FileProcessingException;
import com.trevally_challenge.exceptions.InvalidDelimiterException;
import com.trevally_challenge.exceptions.MissingHeaderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class CSVFileSourceServiceTest {

    public static final String TEST_CSV = "test.csv";
    public static final String TEXT_CSV = "text/csv";
    public static final String INVALID_FORMAT = "test.html";
    private static final String HEADER_LINE = "Header1,Header2,Header3";
    private static final String EMPTY_CONTENT = "";
    private static final String MISSING_HEADER_CONTENT = "\n";
    private static final String INVALID_DELIMITER_CONTENT = "Header1,Header2,Header3";

    @Mock
    private ErrorMessages errorMessages;

    @InjectMocks
    private CSVFileSourceService csvFileSourceService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetCSVHeaders_ValidFile() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(HEADER_LINE.getBytes());
        MultipartFile file = new MockMultipartFile(TEST_CSV, TEST_CSV, TEXT_CSV, inputStream);

        when(errorMessages.getEmptyFileError()).thenReturn("Empty file error");
        when(errorMessages.getMissingHeaderError()).thenReturn("Missing header error");
        when(errorMessages.getInvalidDelimiterError()).thenReturn("Invalid delimiter error");

        List<CSVMappedColumnsDTO> columns = csvFileSourceService.getCSVHeaders(file, ",");
        assertEquals(3, columns.size());
        assertEquals("Header1", columns.get(0).getFrom());
        assertEquals("Header2", columns.get(1).getFrom());
        assertEquals("Header3", columns.get(2).getFrom());
    }

    @Test
    void testGetCSVHeaders_EmptyFile() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(EMPTY_CONTENT.getBytes());
        MultipartFile file = new MockMultipartFile(TEST_CSV, TEST_CSV, TEXT_CSV, inputStream);

        when(errorMessages.getEmptyFileError()).thenReturn("Empty file error");

        assertThrows(EmptyFileException.class, () -> csvFileSourceService.getCSVHeaders(file, ","));
    }

    @Test
    void testGetCSVHeaders_MissingHeader() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(MISSING_HEADER_CONTENT.getBytes());
        MultipartFile file = new MockMultipartFile(TEST_CSV, TEST_CSV, TEXT_CSV, inputStream);

        when(errorMessages.getMissingHeaderError()).thenReturn("Missing header error");

        assertThrows(MissingHeaderException.class, () -> csvFileSourceService.getCSVHeaders(file, ","));
    }

    @Test
    void testGetCSVHeaders_InvalidDelimiter() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(INVALID_DELIMITER_CONTENT.getBytes());
        MultipartFile file = new MockMultipartFile(TEST_CSV, TEST_CSV, TEXT_CSV, inputStream);

        when(errorMessages.getInvalidDelimiterError()).thenReturn("Invalid delimiter error");

        assertThrows(InvalidDelimiterException.class, () -> csvFileSourceService.getCSVHeaders(file, ";"));
    }

    @Test
    void testGetCSVHeaders_InvalidFileExtension() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(HEADER_LINE.getBytes());
        MultipartFile file = new MockMultipartFile(INVALID_FORMAT, INVALID_FORMAT, TEXT_CSV, inputStream);

        when(errorMessages.getProcessingFileError()).thenReturn("Invalid file format. Only CSV files are supported.");

        assertThrows(FileProcessingException.class, () -> csvFileSourceService.getCSVHeaders(file, ","));
    }
}
