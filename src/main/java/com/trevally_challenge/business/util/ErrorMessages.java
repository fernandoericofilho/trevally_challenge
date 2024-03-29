package com.trevally_challenge.business.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "error")
public class ErrorMessages {
    private String invalidDelimiterError;
    private String emptyFileError;
    private String missingHeaderError;
    private String processingFileError;
    private String invalidFileFormatError;
    private String emptyMappedColumnsError;
    private String indexOutOfRange;
}
