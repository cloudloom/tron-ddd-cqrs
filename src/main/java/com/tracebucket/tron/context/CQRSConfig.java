package com.tracebucket.tron.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import reactor.Environment;
import reactor.bus.EventBus;


/**
 * @author ffl
 * @since 08-04-2015
 * @version 0.1
 */

@Configuration
@EnableEventBus
@EnableSpringConfigured
@ComponentScan(basePackages = {"com.tracebucket.tron.cqrs.support"})
public class CQRSConfig {

    @Bean
    public EventBus commandBus(Environment env) {
        // implicit Environment is injected into bean def method
        return EventBus.create(env, Environment.THREAD_POOL);
    }


}
