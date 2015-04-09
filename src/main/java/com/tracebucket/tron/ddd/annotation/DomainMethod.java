package com.tracebucket.tron.ddd.annotation;

import java.lang.annotation.*;

/**
 * @author ffl
 * @since 29-01-2015
 * @version 0.1
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface DomainMethod {
    public String event() default "";
}
