package com.muppet.data.transaction;

import java.sql.Connection;

public interface TransactionFactory {

  /*void setProperties(Properties props);*/

  Transaction newTransaction(Connection conn, boolean autoCommit);

}
