package com.tracebucket.tron.autoconfig;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by sadath on 15-Jun-2015.
 */
public class NonExistingExceptionAdviceBeans implements Condition {
    private static final String EXCEPTION_ADVICE_CONFIGURATION_BEAN_NAME = "exceptionAdviceConfiguration";

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return !context.getBeanFactory().containsBean(EXCEPTION_ADVICE_CONFIGURATION_BEAN_NAME);
    }
}