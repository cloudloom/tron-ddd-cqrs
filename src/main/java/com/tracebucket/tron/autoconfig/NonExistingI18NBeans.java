package com.tracebucket.tron.autoconfig;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by sadath on 07-Jul-2015.
 */
public class NonExistingI18NBeans implements Condition {
    private static final String I_18_N_CONFIGURATION_BEAN_NAME = "i18nConfiguration";

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return !context.getBeanFactory().containsBean(I_18_N_CONFIGURATION_BEAN_NAME);
    }
}