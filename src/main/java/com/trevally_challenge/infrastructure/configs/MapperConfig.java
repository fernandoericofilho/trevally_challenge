package com.trevally_challenge.infrastructure.configs;

import com.trevally_challenge.infrastructure.mappers.SourceMapper;
import com.trevally_challenge.infrastructure.mappers.impl.SourceMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public SourceMapper sourceMapper() { return new SourceMapperImpl(); }
}
