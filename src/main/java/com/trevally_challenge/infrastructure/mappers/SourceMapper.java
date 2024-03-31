package com.trevally_challenge.infrastructure.mappers;

import com.trevally_challenge.infrastructure.dto.SourceDTO;
import com.trevally_challenge.infrastructure.entities.Source;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface SourceMapper {

    @Mapping(target = "id", source = "source.id")
    @Mapping(target = "importDate", source = "source.importDate")
    @Mapping(target = "success", source = "source.success")
    @Mapping(target = "filePath", source = "source.filePath")
    SourceDTO sourceToSourceDTO(Source source);
}
