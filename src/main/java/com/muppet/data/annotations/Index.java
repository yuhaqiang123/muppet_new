package com.muppet.data.annotations;
public @interface Index {

	public String name();
	public String[] field();
	public boolean unique();
}
