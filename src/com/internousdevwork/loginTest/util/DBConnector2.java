package com.internousdevwork.loginTest.util;
/**
 *
 */


import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBConnector2{

	private static String driverName = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/"; //struts2 DB名
	private static String user = "root"; //DBのユーザ名
	private static String pass = "mysql";  //DBに接続するときのパスワード


    public static Connection getConnection(String database){
		StringBuffer sBuffer = new StringBuffer("");
		sBuffer.append(url);
		sBuffer.append(database);
		url = sBuffer.toString();

		Connection con = null;
		try{
			Class.forName(driverName);
			con = (Connection) DriverManager.getConnection(url,user,pass);
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	url = "jdbc:mysql://localhost/";
	return con;
	}
}