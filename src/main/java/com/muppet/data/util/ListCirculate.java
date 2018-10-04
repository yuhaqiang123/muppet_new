package com.muppet.data.util;

import java.util.List;

public interface ListCirculate<T> {
	
	public void execute(List<T> list,T t,int index,Object... args);
}
