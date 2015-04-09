package com.tracebucket.tron.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.spring.context.config.EnableReactor;

/**
 * @author ffl
 * @since 08-04-2015
 * @version 0.1
 */

@Configuration
@EnableReactor
@EnableSpringConfigured
@ComponentScan(basePackages = {"com.tracebucket.tron.cqrs.support", "com.tracebucket.tron.event", "com.tracebucket.tron.ddd.support", "com.tracebucket.tron.ddd.domain"})
public class TronConfig {

    @Bean
    public Reactor commandBus(Environment env) {
        // implicit Environment is injected into bean def method
        return Reactors.reactor().env(env).get();
    }

    @Bean
    public Reactor eventBus(Environment env) {
        // implicit Environment is injected into bean def method
        return Reactors.reactor().env(env).get();
    }
}
