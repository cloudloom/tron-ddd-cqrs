package com.tracebucket.tron.assembler.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sadath on 15-Apr-15.
 */
@Configuration
public class MapperConfig {

    @Bean
    public Mapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        List<String> dozerMappingFiles = new ArrayList<String>();
        dozerMappingFiles.add("dozermapping.xml");
        mapper.setMappingFiles(dozerMappingFiles);
        return mapper;
    }
}
