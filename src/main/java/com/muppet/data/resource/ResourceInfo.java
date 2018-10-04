package com.muppet.data.resource;

import com.muppet.data.core.IllealInvokeException;

public abstract class ResourceInfo {

	public abstract String getSql();
	public ColumnInfo[] getColumns() {
		throw new IllealInvokeException("未实现获取列数组逻辑");
	}

	public abstract String getName();
	
}
