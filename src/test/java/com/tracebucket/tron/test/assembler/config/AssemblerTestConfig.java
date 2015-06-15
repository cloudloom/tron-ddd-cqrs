package com.tracebucket.tron.test.assembler.config;

import com.tracebucket.tron.context.EnableAutoAssemblerResolution;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ffl on 14-04-2015.
 */
@Configuration
@EnableAutoAssemblerResolution(basePackages = "com.tracebucket.tron.test.assembler.assembler")
@ComponentScan(basePackages = {"com.tracebucket.tron.test.assembler"})
public class AssemblerTestConfig {

}