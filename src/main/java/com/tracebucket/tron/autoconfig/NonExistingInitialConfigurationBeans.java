package com.tracebucket.tron.autoconfig;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by sadath on 11-Jun-2015.
 */
public class NonExistingInitialConfigurationBeans implements Condition {
    private static final String INITIAL_CONFIGURATION_BEAN_NAME = "initialConfiguration";

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return !context.getBeanFactory().containsBean(INITIAL_CONFIGURATION_BEAN_NAME);
    }
}