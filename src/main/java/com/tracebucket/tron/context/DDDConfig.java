package com.tracebucket.tron.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import reactor.Environment;
import reactor.bus.EventBus;

/**
 * Created by ffl on 14-04-2015.
 */
@Configuration
@EnableEventBus
@EnableSpringConfigured
@ComponentScan(basePackages = {"com.tracebucket.tron.event", "com.tracebucket.tron.ddd.support", "com.tracebucket.tron.ddd.domain"})
public class DDDConfig {
	@Bean
	public EventBus domainEventBus(Environment env) {
		// implicit Environment is injected into bean def method
		return EventBus.create(env, Environment.THREAD_POOL);
	}
}
