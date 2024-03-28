package com.trevally_challenge.services;

import com.trevally_challenge.config.ErrorMessages;
import com.trevally_challenge.domain.dto.CSVMappedColumnsDTO;
import com.trevally_challenge.domain.entities.Contact;
import com.trevally_challenge.domain.entities.ContactAttribute;
import com.trevally_challenge.domain.entities.Source;
import com.trevally_challenge.domain.request.CSVRequest;
import com.trevally_challenge.domain.response.MappedColumnsResponse;
import com.trevally_challenge.enums.ValidFileFormat;
import com.trevally_challenge.exceptions.EmptyFileException;
import com.trevally_challenge.exceptions.FileProcessingException;
import com.trevally_challenge.exceptions.InvalidDelimiterException;
import com.trevally_challenge.exceptions.MissingHeaderException;
import com.trevally_challenge.repositories.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
            return processHeaders(file, request.getDelimiter());
        } catch (IOException e) {
            throw new FileProcessingException(errorMessages.getProcessingFileError() + e.getMessage());
        }
    }

    public void processSource(CSVRequest request) {
        File file = new File(request.getFilePath());
        String delimiter = request.getDelimiter();
        List<CSVMappedColumnsDTO> mappedColumns = request.getMappedColumns();

        List<Contact> contacts = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String headerLine = br.readLine();
            if (headerLine != null) {

                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(delimiter);

                    Contact contact = new Contact();
                    contact.setEmail(data[0]); // Assuming email is always the first column

                    List<ContactAttribute> attributes = new ArrayList<>();
                    for (CSVMappedColumnsDTO column : mappedColumns) {
                        ContactAttribute attribute = new ContactAttribute();
                        attribute.setAttributeName(column.getLabel().isEmpty() ? column.getFrom() : column.getLabel());
                        attribute.setAttributeValue(getValueByIndex(data, column.getIndex()));
                        attributes.add(attribute);
                    }

                    contact.setAttributes(attributes);
                    contacts.add(contact);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!contacts.isEmpty()) {
            Source source = new Source();
            source.setImportDate(LocalDateTime.now());
            source.setSuccess(true);
            source.setFilePath(file.getName());
            source.setContacts(contacts);

            sourceRepository.save(source);
        }
    }

    private String getValueByIndex(String[] data, int index) {
        if (index >= 0 && index < data.length) {
            return data[index];
        }
        return null;
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
