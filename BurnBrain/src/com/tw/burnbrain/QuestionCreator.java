package com.tw.burnbrain;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class QuestionCreator {
	public static JLabel getQuestion(String dbName){
		try{
		boolean isExist=getInfo(dbName);
		while(!isExist)
			isExist=getInfo(dbName);
		question.setText(rs.getString(1));
		System.out.println("question="+rs.getString(1));
		}catch(Exception e){
			e.printStackTrace();
		}
		return question;
	}
	public static String getTitle(String dbName){
		try {
			System.out.println(dbName);
			ps=conn.prepareStatement(sql_title+dbName);
		rs=ps.executeQuery();
		if(rs.next())
			title=rs.getString(1);
		System.out.println(title);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return title;
	}
	public static MyButton[] getButtons(String dbName){
		try{
			
			boolean isRight=false;
			int tmp=(int) (Math.random()*4);
			ps=conn.prepareStatement(sql_ans+dbName+sql_where+no);
			//ps.setString(1,no+"");
			rs=ps.executeQuery();
			if(rs.next()){
				//为了保证正确答案的按钮不总是在同一位置，利用随机变量tmp改变按钮的起始位置。
			for(int i=0;i<4;i++){
					String answer=rs.getString(i+1);
					System.out.println("answer = "+i+answer);
					if(i==0) isRight=true;
					else isRight=false;
					if(i+tmp>3) {buttons[i+tmp-4]=new MyButton(answer+"",isRight);
					buttons[i+tmp-4].setFont(new Font("Consolas", Font.PLAIN, 12));}
					else {buttons[i+tmp]=new MyButton(answer+"",isRight);
						buttons[i+tmp].setFont(new Font("Consolas", Font.PLAIN, 12));}	
			}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
			return buttons;
		
	}
	private static boolean getInfo(String dbName) throws Exception{
		getNo(dbName); 
		System.out.println("no=="+no);
		ps=conn.prepareStatement(sql_exp+dbName+sql_where+no);
		System.out.println(sql_exp+dbName+sql_where+no);
		//ps.setString(1,no+"");
		rs=ps.executeQuery();
		return rs.next()?true:false;
	}
	public static void  getNo(String dbName) throws SQLException{
		ps=conn.prepareStatement(sql_no+dbName);
		rs=ps.executeQuery();
		if(rs.next())
			no=rs.getInt(1);
		no=(int) ((int) no*Math.random());
		System.out.println("no="+no);
		//return no;
	}
	//private static String db_Name="";
	private static String sql_no="select count(no) from ";
	private static String sql_exp="select exp from " ;
	private static String sql_where=" where no=";
	//private static String sql_rightAnswer="selcet answer from arith where no=?";
	private static String sql_ans="select answer,answer1,answer2,answer3 from " ;
	private static String sql_title="select title from ";
	private static int no;
	private static  Connection conn=DBConnection.getInstance();
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static String title;
	private static JLabel question=new JLabel();
	private static MyButton[] buttons=new MyButton[4];
	public static void main(String[] ags){
		String dbName="arith";
		dbName="ssc";
		try {
			QuestionCreator.getTitle(dbName);
			QuestionCreator.getQuestion(dbName);
			QuestionCreator.getButtons(dbName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
