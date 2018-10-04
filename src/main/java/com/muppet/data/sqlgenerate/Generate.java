package com.muppet.data.sqlgenerate;

import com.muppet.data.resource.ResourceInfo;


public interface Generate {

	public String generate(ResourceInfo resourceInfo) throws SqlGenerateException;
}
