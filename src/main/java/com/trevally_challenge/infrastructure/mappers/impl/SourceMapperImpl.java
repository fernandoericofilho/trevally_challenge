package com.trevally_challenge.infrastructure.mappers.impl;

import com.trevally_challenge.infrastructure.dto.ContactAttributeDTO;
import com.trevally_challenge.infrastructure.dto.ContactDTO;
import com.trevally_challenge.infrastructure.dto.SourceDTO;
import com.trevally_challenge.infrastructure.entities.Contact;
import com.trevally_challenge.infrastructure.entities.ContactAttribute;
import com.trevally_challenge.infrastructure.entities.Source;
import com.trevally_challenge.infrastructure.mappers.SourceMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SourceMapperImpl implements SourceMapper {

    @Override
    public SourceDTO sourceToSourceDTO(Source source) {
        SourceDTO sourceDTO = new SourceDTO();
        sourceDTO.setId(source.getId());
        sourceDTO.setImportDate(source.getImportDate());
        sourceDTO.setSuccess(source.isSuccess());
        sourceDTO.setFilePath(source.getFilePath());
        sourceDTO.setContacts(source.getContacts().stream()
                .map(this::contactToDTO)
                .collect(Collectors.toList()));
        return sourceDTO;
    }

    private ContactDTO contactToDTO(Contact contact) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(contact.getId());
        contactDTO.setEmail(contact.getEmail());
        List<ContactAttributeDTO> attributeDTOs = contact.getAttributes().stream()
                .map(this::attributeToDTO)
                .collect(Collectors.toList());
        contactDTO.setAttributes(attributeDTOs);
        return contactDTO;
    }

    private ContactAttributeDTO attributeToDTO(ContactAttribute attribute) {
        ContactAttributeDTO attributeDTO = new ContactAttributeDTO();
        attributeDTO.setId(attribute.getId());
        attributeDTO.setAttributeName(attribute.getAttributeName());
        attributeDTO.setAttributeValue(attribute.getAttributeValue());
        return attributeDTO;
    }
}
