package com.tracebucket.tron.context;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ffl on 14-04-2015.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AssemblerResolutionConfig.class)
public @interface EnableAutoAssemblerResolution {
	String[] basePackages() default {};
}
