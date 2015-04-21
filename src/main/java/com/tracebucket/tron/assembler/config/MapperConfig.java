package com.tracebucket.tron.assembler.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sadath on 15-Apr-15.
 */
@Configuration
public class MapperConfig {

    @Bean
    public Mapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList(new String[] {"dozermapping.xml"}));
        return mapper;
    }
}
