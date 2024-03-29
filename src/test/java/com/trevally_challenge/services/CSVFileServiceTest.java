package com.trevally_challenge.services;

import com.trevally_challenge.api.request.CSVRequest;
import com.trevally_challenge.business.CSVFileService;
import com.trevally_challenge.business.util.ErrorMessages;
import com.trevally_challenge.infrastructure.dto.CSVMappedColumnsDTO;
import com.trevally_challenge.infrastructure.entities.Source;
import com.trevally_challenge.infrastructure.exceptions.EmptyFileException;
import com.trevally_challenge.infrastructure.exceptions.FileProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CSVFileServiceTest {

    @Mock
    private ErrorMessages errorMessages;

    @InjectMocks
    private CSVFileService csvFileService;

    private CSVRequest mockCSVRequest;

    @Before
    public void setUp() {
        mockCSVRequest = new CSVRequest();
        mockCSVRequest.setFilePath("mockFilePath");
        mockCSVRequest.setDelimiter(",");
        mockCSVRequest.setMappedColumns(Collections.emptyList());
    }

    @Test(expected = FileProcessingException.class)
    public void processCSVFile_NotFound_ExceptionThrown() throws IOException {
        when(errorMessages.getProcessingFileError()).thenReturn("File processing error: ");
        when(errorMessages.getEmptyFileError()).thenReturn("Empty file error");
        when(errorMessages.getInvalidFileFormatError()).thenReturn("Invalid file format error");

        csvFileService.extractHeaders(mockCSVRequest);
    }

    @Test(expected = EmptyFileException.class)
    public void processCSVFile_EmptyFile_ExceptionThrown() throws IOException {
        File emptyFile = mock(File.class);
        when(emptyFile.exists()).thenReturn(true);
        when(emptyFile.length()).thenReturn(0L);

        csvFileService.processSource(mockCSVRequest);
    }

    @Test(expected = FileProcessingException.class)
    public void persistCSV_NotFound_ExceptionThrown() throws IOException {
        when(errorMessages.getProcessingFileError()).thenReturn("File processing error: ");
        when(errorMessages.getEmptyFileError()).thenReturn("Empty file error");
        when(errorMessages.getInvalidFileFormatError()).thenReturn("Invalid file format error");

        csvFileService.persistCSV(mockCSVRequest);
    }

    @Test(expected = FileProcessingException.class)
    public void processSource_EmptyFile_ExceptionThrown() throws IOException {
        File emptyFile = mock(File.class);
        when(emptyFile.exists()).thenReturn(true);
        when(emptyFile.length()).thenReturn(0L);

        csvFileService.processSource(mockCSVRequest);
    }

    @Test(expected = FileProcessingException.class)
    public void processCSVFile_FileProcessingExceptionThrown() throws IOException {
        CSVRequest mockCSVRequest = mock(CSVRequest.class);
        when(mockCSVRequest.getFilePath()).thenReturn("invalid/path");

        CSVFileService csvFileService = new CSVFileService();
        csvFileService.processSource(mockCSVRequest);
    }

    @Test(expected = EmptyFileException.class)
    public void processCSVFile_EmptyFileExceptionThrown() throws IOException {
        CSVRequest mockCSVRequest = mock(CSVRequest.class);
        File emptyFile = mock(File.class);
        when(emptyFile.exists()).thenReturn(true);
        when(emptyFile.length()).thenReturn(0L);
        when(mockCSVRequest.getFilePath()).thenReturn("valid/path");

        CSVFileService csvFileService = new CSVFileService();
        csvFileService.processSource(mockCSVRequest);
    }

    @Test(expected = FileProcessingException.class)
    public void processCSVFile_IOExceptionThrown() throws IOException {
        CSVRequest mockCSVRequest = mock(CSVRequest.class);
        File emptyFile = mock(File.class);
        when(emptyFile.exists()).thenReturn(true);
        when(emptyFile.length()).thenThrow(IOException.class);
        when(mockCSVRequest.getFilePath()).thenReturn("valid/path");

        CSVFileService csvFileService = new CSVFileService();
        csvFileService.processSource(mockCSVRequest);
    }

    @Test
    public void processSource_SuccessfullyProcessed() throws IOException {
        CSVRequest mockCSVRequest = mock(CSVRequest.class);

        File mockFile = mock(File.class);
        when(mockFile.getName()).thenReturn("mockFileName");
        when(mockCSVRequest.getFilePath()).thenReturn("valid/path");
        when(mockCSVRequest.getDelimiter()).thenReturn(",");

        List<CSVMappedColumnsDTO> mappedColumns = new ArrayList<>();
        CSVMappedColumnsDTO mappedColumn = new CSVMappedColumnsDTO();
        mappedColumn.setIndex(0);
        mappedColumn.setFrom("From");
        mappedColumn.setLabel("Label");
        mappedColumns.add(mappedColumn);
        when(mockCSVRequest.getMappedColumns()).thenReturn(mappedColumns);

        Source result = csvFileService.processSource(mockCSVRequest);
        assertEquals("mockFileName", result.getFilePath());
        assertEquals(1, result.getContacts().size());
        assertEquals("From", result.getContacts().get(0).getAttributes().get(0).getAttributeName());
    }

    @Test(expected = FileProcessingException.class)
    public void processSource_FileProcessingExceptionThrown() throws IOException {
        CSVRequest mockCSVRequest = mock(CSVRequest.class);
        File mockFile = mock(File.class);
        when(mockFile.getName()).thenReturn("mockFileName");
        when(mockCSVRequest.getFilePath()).thenReturn("valid/path");
        when(mockCSVRequest.getDelimiter()).thenReturn(",");
        List<CSVMappedColumnsDTO> mappedColumns = new ArrayList<>();
        CSVMappedColumnsDTO mappedColumn = new CSVMappedColumnsDTO();
        mappedColumn.setIndex(0);
        mappedColumn.setFrom("From");
        mappedColumn.setLabel("Label");
        mappedColumns.add(mappedColumn);
        when(mockCSVRequest.getMappedColumns()).thenReturn(mappedColumns);

        csvFileService.processSource(mockCSVRequest);
    }
}
