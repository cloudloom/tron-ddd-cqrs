package com.tracebucket.tron.autoconfig;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by sadath on 16-Jun-2015.
 */
public class NonExistingResourceConfigurationBeans implements Condition {
    private static final String RESOURCE_CONFIGURATION_BEAN_NAME = "resourceConfiguration";

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return !context.getBeanFactory().containsBean(RESOURCE_CONFIGURATION_BEAN_NAME);
    }
}