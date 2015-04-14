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
 * Created by ffl on 14-04-2015.
 */
@Configuration
@EnableReactor
@EnableSpringConfigured
@ComponentScan(basePackages = {"com.tracebucket.tron.event", "com.tracebucket.tron.ddd.support", "com.tracebucket.tron.ddd.domain"})
public class DDDConfig {
	@Bean
	public Reactor eventBus(Environment env) {
		// implicit Environment is injected into bean def method
		return Reactors.reactor().env(env).get();
	}
}
