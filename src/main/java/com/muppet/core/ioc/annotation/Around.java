package com.muppet.core.ioc.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Around {

	public String value() default "";

	public Class annotation() default Annotation.class;
}
