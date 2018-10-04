package com.muppet.data.filters;

import com.muppet.data.transaction.Transaction;

import java.util.Map;

public class SqlRequest {

	private Transaction transaction;
	
	private Map<String,Object> parameter;

	

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Map<String, Object> getParameter() {
		return parameter;
	}

	public void setParameter(Map<String, Object> parameter) {
		this.parameter = parameter;
	}


	
	
}
