package com.muppet.data.core;

import com.muppet.data.transaction.Transaction;

public class ThreadLocalTransaction {

	private static ThreadLocal<Transaction> local = new ThreadLocal<>();
	
	public static void set(Transaction transaction){
		local.set(transaction);
	}
	
	public static Transaction get(){
		return local.get();
	}
	
	public static void remove(){
		local.remove();
	}
	
	
	
}
