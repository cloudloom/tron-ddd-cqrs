package com.tracebucket.tron.context;

import com.tracebucket.tron.rest.validation.TronControllerAdvisor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by sadath on 15-Jun-2015.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(TronControllerAdvisor.class)
public @interface EnableControllerAdvisor {
}