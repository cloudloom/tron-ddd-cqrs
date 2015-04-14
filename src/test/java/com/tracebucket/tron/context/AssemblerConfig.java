package com.tracebucket.tron.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sadath on 09-Apr-15.
 */
@Configuration
@ComponentScan(basePackages = {"com.tracebucket.tron.rest.assembler", "com.tracebucket.tron.util"})
public class AssemblerConfig {
}