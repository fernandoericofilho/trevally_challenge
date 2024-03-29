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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

@Service
public class CSVFileService {

    @Autowired
    private ErrorMessages errorMessages;

    @Autowired
    private SourceRepository sourceRepository;

    public MappedColumnsResponse extractHeaders(CSVRequest request) {
        try {
            File file = new File(request.getFilePath());
            validateFile(file);
            return processHeaders(file, request.getDelimiter());
        } catch (IOException e) {
            throw new ProcessingFileEsception(errorMessages.getProcessingFileExceptionMessageError());
        }
    }

    private void validateFile(File file) {
        if (!file.exists()) {
            throw new FileExistException(errorMessages.getFileExistExceptionMessageError() + file.getName());
        }
        if (!file.getName().toLowerCase().endsWith(ValidFileFormat.VALID_CSV.getExtension())) {
            throw new FileExtensionException(errorMessages.getFileExtensionExceptionMessageError());
        }
    }

    private MappedColumnsResponse processHeaders(File file, String delimiter) throws IOException {
        List<CSVMappedColumnsDTO> mappedColumns = getCsvMappedColumnsDTOS(file, delimiter);
        return new MappedColumnsResponse(mappedColumns);
    }

    private List<CSVMappedColumnsDTO> getCsvMappedColumnsDTOS(File file, String delimiter) throws IOException {
        try (Stream<String> lines = Files.lines(file.toPath())) {
            Optional<String> headerLine = lines.findFirst();
            validateHeaderLine(headerLine.orElse(null), delimiter);
            return headerLine.map(line -> mapColumns(line.split(delimiter))).orElse(Collections.emptyList());
        }
    }

    private void validateHeaderLine(String line, String delimiter) {
        if (line == null || line.isEmpty()) {
            throw new MissingHeaderException(errorMessages.getMissingHeaderExceptionMessageError());
        }
        if (!line.contains(delimiter)) {
            throw new InvalidDelimiterException(errorMessages.getInvalidDelimiterExceptionMessageError());
        }
    }

    public Source processSource(CSVRequest request) {
        File file = new File(request.getFilePath());
        validateFile(file);

        List<CSVMappedColumnsDTO> mappedColumns = request.getMappedColumns();
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String headerLine = br.readLine();
            validateHeaderLine(headerLine, request.getDelimiter());

            int headersLength = headerLine.split(request.getDelimiter()).length;
            validateReMappedColumns(mappedColumns, headersLength);

            br.lines()
                    .map(line -> line.split(request.getDelimiter()))
                    .forEach(data -> {
                        if (data[0].isEmpty()) {
                            throw new EmptyContactsException(errorMessages.getEmptyContactExceptionMessageError());
                        }

                        List<ContactAttribute> attributes = mapContactAttributes(data, mappedColumns);
                        mapContact(contacts, data[0], attributes);
                    });

        } catch (IOException e) {
            throw new FileExistException(errorMessages.getProcessingFileExceptionMessageError());
        }

        Source source = mapContact(contacts, file.getName());
        return sourceRepository.save(source);
    }

    private Source mapContact(List<Contact> contacts, String fileName) {
        Source source = new Source();
        source.setImportDate(LocalDateTime.now());
        source.setSuccess(true);
        source.setFilePath(fileName);
        source.setContacts(contacts);
        return source;
    }

    private void mapContact(List<Contact> contacts, String email, List<ContactAttribute> attributes) {
        Contact contact = new Contact();
        contact.setId(UUID.randomUUID());
        contact.setEmail(email);
        contact.setAttributes(attributes);
        contacts.add(contact);
    }

    private List<ContactAttribute> mapContactAttributes(String[] data, List<CSVMappedColumnsDTO> mappedColumns) {
        List<ContactAttribute> attributes = new ArrayList<>();
        for (CSVMappedColumnsDTO column : mappedColumns) {
            String value = getValueByIndex(data, column.getIndex());
            String attributeName = column.getLabel().isEmpty() ? column.getFrom() : column.getLabel();
            ContactAttribute attribute = createContactAttribute(attributeName, value);
            attributes.add(attribute);
        }
        return attributes;
    }

    private ContactAttribute createContactAttribute(String attributeName, String attributeValue) {
        ContactAttribute attribute = new ContactAttribute();
        attribute.setId(UUID.randomUUID());
        attribute.setAttributeName(attributeName);
        attribute.setAttributeValue(attributeValue);
        return attribute;
    }

    private String getValueByIndex(String[] data, int index) {
        if (index >= 0 && index < data.length) {
            return data[index];
        }
        throw new IndexOutOfRangeException(errorMessages.getIndexOutOfRangeExceptionMessageError());
    }

    private void validateReMappedColumns(List<CSVMappedColumnsDTO> mappedColumns, int numHeaders) {
        mappedColumns.forEach(column -> {
            if (column.getIndex() >= numHeaders) {
                throw new IndexOutOfRangeException(errorMessages.getIndexOutOfRangeExceptionMessageError());
            }
        });
    }

    private List<CSVMappedColumnsDTO> mapColumns(String[] headers) {
        List<CSVMappedColumnsDTO> mappedColumns = new ArrayList<>();
        for (int i = 0; i < headers.length; i++) {
            CSVMappedColumnsDTO mappedColumn = CSVMappedColumnsDTO.builder()
                    .from(headers[i])
                    .label(headers[i])
                    .index(i).build();
            mappedColumns.add(mappedColumn);
        }
        return mappedColumns;
    }
}
