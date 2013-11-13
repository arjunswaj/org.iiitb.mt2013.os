package org.iiitb.model.bean.segmentation;

import java.sql.Connection;
import java.sql.DriverManager;


public class MySqlConnection {
static Connection conn;
	
	public static Connection getConnection()
	{
		try
		{
			Class.forName(RuntimeSettings.driver);
			conn = DriverManager.getConnection(RuntimeSettings.url + RuntimeSettings.dbName, RuntimeSettings.dbUser, RuntimeSettings.dbPwd);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return conn;
	}
}