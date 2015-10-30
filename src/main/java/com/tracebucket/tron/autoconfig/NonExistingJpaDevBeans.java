package com.tracebucket.tron.autoconfig;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by ffl on 28-05-2015.
 */
public class NonExistingJpaDevBeans implements Condition {
	private static final String JPA_DEV_CONFIGURATION_BEAN_NAME = "jpaDevConfiguration";

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return !context.getBeanFactory().containsBean(JPA_DEV_CONFIGURATION_BEAN_NAME);
	}
}