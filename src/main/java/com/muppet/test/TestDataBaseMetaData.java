package com.muppet.test;

import java.sql.SQLException;

public class TestDataBaseMetaData {

	
	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException{
		/*Connection connection = new DataSourceUtil().getConnection();
		DatabaseMetaData metaData = connection.getMetaData();
		ResultSet rs = metaData.getTypeInfo();
		while (rs.next()) {
			System.out.print(rs.getString("TYPE_NAME")+"  ");
			System.out.print(rs.getShort("DATA_TYPE")+"  ");
			System.out.println(rs.getString("PRECISION"));
		}
		CloseUtil.close(connection);
		CloseUtil.close(rs);*/
	}
}
