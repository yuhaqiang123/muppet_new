package com.muppet.data.datasource;

import com.muppet.data.util.log.Logger;

public class StandardDataSourceListener implements DataSourceListener{

	@Override
	public void event(DataSourceEvent event) {
		//Logger.println("------------------------------------------------");
		Logger.debugln(event);
		//ArrayUtil.println(Thread.currentThread().getStackTrace());
		//Logger.println("------------------------------------------------");
		//throw ExceptionUtil.getRuntimeException(event.getError());
	}
}
