package com.tracebucket.tron.endpoint;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * Created by ffl on 15-04-2015.
 */
@Target(
		{ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface OverrideRequestMapping {
}
