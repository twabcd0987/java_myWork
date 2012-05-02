package com.tw.burnbrain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection getInstance() {
		// TODO Auto-generated method stub
	 try {
		 Class.forName("com.mysql.jdbc.Driver");
		 String url="jdbc:mysql://localhost:3306/burnbrain";
		 String user="root";
		 String pwd="abcd0987";
		 conn=DriverManager.getConnection(url,user,pwd);
		System.out.println("suscceed");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		return conn;
	}
	 
}
	public static void main(String[] args) throws SQLException{
		//String dbName="arith";
		String sql="select exp from ? where no=3";
		Connection connn=DBConnection.getInstance();
		PreparedStatement ps=connn.prepareStatement(sql);
		ps.setString(1, "arith");
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			System.out.println(rs.getString(1));
		}
	}
	private static Connection conn;
}
