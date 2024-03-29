package com.trevally_challenge.business;

import com.trevally_challenge.api.request.CSVRequest;
import com.trevally_challenge.api.response.MappedColumnsResponse;
import com.trevally_challenge.business.util.ErrorMessages;
import com.trevally_challenge.infrastructure.dto.CSVMappedColumnsDTO;
import com.trevally_challenge.infrastructure.entities.Contact;
import com.trevally_challenge.infrastructure.entities.ContactAttribute;
import com.trevally_challenge.infrastructure.entities.Source;
import com.trevally_challenge.infrastructure.enums.ValidFileFormat;
import com.trevally_challenge.infrastructure.exceptions.*;
import com.trevally_challenge.infrastructure.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CSVFileService {

    @Autowired
    private ErrorMessages errorMessages;

    @Autowired
    private SourceRepository sourceRepository;

    public MappedColumnsResponse extractHeaders(CSVRequest request) {
        try {
            File file = new File(request.getFilePath());
            return processHeaders(file, request.getDelimiter());
        } catch (IOException e) {
            throw new FileProcessingException(errorMessages.getProcessingFileError() + e.getMessage());
        }
    }

    public MappedColumnsResponse persistCSV(CSVRequest request) {
        try {
            File file = new File(request.getFilePath());
            validateFile(file);
            return processHeaders(file, request.getDelimiter());
        } catch (IOException e) {
            throw new FileProcessingException(errorMessages.getProcessingFileError() + e.getMessage());
        }
    }

    public Source processSource(CSVRequest request) {
        File file = new File(request.getFilePath());
        String delimiter = request.getDelimiter();
        List<CSVMappedColumnsDTO> mappedColumns = request.getMappedColumns();

        List<Contact> contacts = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String headerLine = br.readLine();
            if (headerLine != null) {
                String[] headers = headerLine.split(delimiter);
                validateMappedColumns(mappedColumns, headers.length);
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(delimiter);

                    Contact contact = new Contact();
                    contact.setId(UUID.randomUUID());
                    contact.setEmail(data[0]);

                    List<ContactAttribute> attributes = new ArrayList<>();
                    for (CSVMappedColumnsDTO column : mappedColumns) {
                        String value = getValueByIndex(data, column.getIndex(), headers.length);
                        ContactAttribute attribute = new ContactAttribute();
                        attribute.setId(UUID.randomUUID());
                        attribute.setAttributeName(column.getLabel().isEmpty() ? column.getFrom() : column.getLabel());
                        attribute.setAttributeValue(value);
                        attributes.add(attribute);
                    }

                    contact.setAttributes(attributes);
                    contacts.add(contact);
                }
            }
        } catch (IOException e) {
            throw new FileProcessingException(errorMessages.getProcessingFileError() + e.getMessage());
        }

        if (contacts.isEmpty()) {
            throw new EmptyFileException(errorMessages.getEmptyFileError());
        }

        Source source = new Source();
        source.setImportDate(LocalDateTime.now());
        source.setSuccess(true);
        source.setFilePath(file.getName());
        source.setContacts(contacts);

        return sourceRepository.save(source);
    }

    private String getValueByIndex(String[] data, int index, int numColumns) {
        if (index >= 0 && index < numColumns) {
            return data[index];
        }
        throw new InvalidColumnRangeException(errorMessages.getIndexOutOfRange() + index);
    }

    private void validateMappedColumns(List<CSVMappedColumnsDTO> mappedColumns, int numHeaders) {
        mappedColumns.forEach(column -> {
            if (column.getIndex() >= numHeaders) {
                throw new InvalidColumnRangeException(errorMessages.getIndexOutOfRange() + column.getIndex());
            }
        });
    }

    private void validateFile(File file) {
        if (!file.exists()) {
            throw new FileProcessingException(errorMessages.getProcessingFileError());
        }
        if (file.length() == 0) {
            throw new EmptyFileException(errorMessages.getEmptyFileError());
        }
        if (!isValidFileExtension(file.getName())) {
            throw new FileProcessingException(errorMessages.getInvalidFileFormatError());
        }
    }

    private MappedColumnsResponse processHeaders(File file, String delimiter) throws IOException {
        List<CSVMappedColumnsDTO> mappedColumns = getCsvMappedColumnsDTOS(file, delimiter);
        return new MappedColumnsResponse(mappedColumns);
    }

    private List<CSVMappedColumnsDTO> getCsvMappedColumnsDTOS(File file, String delimiter) throws IOException {
        List<CSVMappedColumnsDTO> mappedColumns = new ArrayList<>();
        validateFileNotEmpty(file);
        validateFileExtension(file);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.toURI().toURL().openStream()))) {
            String line = br.readLine();
            checkHeaderExists(line);
            checkDelimiter(delimiter, line);
            mapColumns(line.split(delimiter), mappedColumns);
        }
        return mappedColumns;
    }

    private void validateFileNotEmpty(File file) {
        if (file.length() == 0) {
            throw new EmptyFileException(errorMessages.getEmptyFileError());
        }
    }

    private void validateFileExtension(File file) {
        String filename = file.getName();
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
