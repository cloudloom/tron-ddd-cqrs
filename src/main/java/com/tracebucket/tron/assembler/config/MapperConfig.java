package com.tracebucket.tron.assembler.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sadath on 15-Apr-15.
 */
@Configuration
public class MapperConfig {

    @Bean
    public Mapper mapper() {
        return new DozerBeanMapper();
    }

    @Bean
    public ObjectMapper objectMapper()
    {
        return new ObjectMapper();
    }
}
