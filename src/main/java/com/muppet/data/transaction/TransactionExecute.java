package com.muppet.data.transaction;

import com.muppet.data.core.Session;

public interface TransactionExecute {

	
	public Object execute(Session session, Transaction transaction);
}
