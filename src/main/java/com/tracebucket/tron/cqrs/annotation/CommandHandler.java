package com.tracebucket.tron.cqrs.annotation;


import java.lang.annotation.*;

/**
 * @author ffl
 * @since 08-04-2015
 * @version 0.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface CommandHandler {
    public String event() default "";
}
