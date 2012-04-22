package com.tw.burnbrain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
	 try {
		 Class.forName("com.mysql.jdbc.Driver");
		 String url="jdbc:mysql://localhost:3306/test";
		 String user="root";
		 String pwd="abcd0987";
		 conn=DriverManager.getConnection(url,user,pwd);
		System.out.println("suscceed");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
}
	public static void main(String[] args){
		new Thread(new DBConnection()).start();
	}
	private Connection conn;
}
