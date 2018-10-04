package com.muppet.data.annotations;

import com.muppet.data.sql.SqlType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Type {
	public SqlType type() ;
	public int length() default -1;	
	
}

