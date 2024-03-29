package com.trevally_challenge.business.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "error")
public class ErrorMessages {

    private String processingFileExceptionMessageError;
    private String fileExistExceptionMessageError;
    private String fileExtensionExceptionMessageError;
    private String missingHeaderExceptionMessageError;
    private String invalidDelimiterExceptionMessageError;
    private String indexOutOfRangeExceptionMessageError;
    private String emptyContactExceptionMessageError;

}
