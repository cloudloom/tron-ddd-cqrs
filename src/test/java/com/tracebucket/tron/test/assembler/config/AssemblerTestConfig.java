package com.tracebucket.tron.test.assembler.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tracebucket.tron.context.EnableAutoAssemblerResolution;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by ffl on 14-04-2015.
 */
@Configuration
@EnableAutoAssemblerResolution(basePackages = "com.tracebucket.tron.test.assembler.assembler")
@ComponentScan(basePackages = {"com.tracebucket.tron.test.assembler"})
public class AssemblerTestConfig {

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
