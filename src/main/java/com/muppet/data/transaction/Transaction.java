package com.muppet.data.transaction;

import java.sql.Connection;
import java.sql.SQLException;

public interface Transaction {

	  Connection getConnection();

	  void commit() throws SQLException;

	  void rollback() throws SQLException;

	  void close() throws SQLException;
	
}
