package com.tracebucket.tron.ddd.annotation;

import java.lang.annotation.*;

/**
 * @author ffl
 * @since 09-02-2015
 * @version 0.1
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface PersistChanges {
    public String repository() default "";
}
