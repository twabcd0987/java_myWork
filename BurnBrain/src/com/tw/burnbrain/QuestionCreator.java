package com.tw.burnbrain;

import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class QuestionCreator {
	//题面
	public static JLabel getQuestion(String dbName){
		question=new JLabel();
		/*if(!question.getText().equals(""))
			question.setText("");*/
		try{
		boolean isExist=getInfo(dbName);
		while(!isExist)
			isExist=getInfo(dbName);
		if(dbName.equals("arith"))
		question.setText(rs.getString(1));
		else {
			String path="images\\"+dbName+"\\"+rs.getString(1);
		//	System.out.println(path);
			 /* ImageIcon ico=new ImageIcon(path);
			  Image temp=ico.getImage().getScaledInstance(question.getWidth(),question.getHeight(),ico.getImage().SCALE_DEFAULT);
			  ico=new ImageIcon(temp);*/
				question.setIcon(new ImageIcon(path));
			  //question.setIcon(ico);
			
		}
		//System.out.println("question="+rs.getString(1));
		}catch(Exception e){
			e.printStackTrace();
		}
		return question;
	}
	//题目
	public static String getTitle(String dbName){
		String title=null;
		System.out.println("heello title");
		try {
			//System.out.println(dbName);
			ps=conn.prepareStatement(sql_title+dbName+sql_where+no);
		rs=ps.executeQuery();
		System.out.println("getTitle no===="+no);
		if(rs.next())
			title=rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("title="+title);
		return title;
	}
	public static MyButton[] getButtons(String dbName){
		
		try{
			int n=4;
			if("ssc".equals(dbName))
				n=3;
			boolean isRight=false;
			buttons=new MyButton[n];
			int tmp=(int) (Math.random()*n);
			if("ssc".equals(dbName)){
				ps=conn.prepareStatement("select answer,answer1,answer2 from ssc where no="+no);
			}
			else
				ps=conn.prepareStatement(sql_ans+dbName+sql_where+no);
			//ps.setString(1,no+"");
			rs=ps.executeQuery();
			if(rs.next()){
				//为了保证正确答案的按钮不总是在同一位置，利用随机变量tmp改变按钮的起始位置。
			for(int i=0;i<n;i++){
					String answer=rs.getString(i+1);
					System.out.println("answer = "+i+answer);
					if(i==0) isRight=true;
					else isRight=false;
					if(i+tmp>n-1) {
						if(dbName.equals("arith")||dbName.equals("color")){
							buttons[i+tmp-n]=new MyButton(answer+"",isRight);
						buttons[i+tmp-n].setFont(new Font("Consolas", Font.PLAIN, 12));
						}
					else{
						buttons[i+tmp-n]=new MyButton("",isRight);
						String path="images\\"+dbName+"\\"+answer;
						  /*ImageIcon ico=new ImageIcon(path);
						  Image temp=ico.getImage().getScaledInstance(question.getWidth(),question.getHeight(),ico.getImage().SCALE_DEFAULT);
						  ico=new ImageIcon(temp);*/
						  buttons[i+tmp-n].setIcon(new ImageIcon(path));
					}
					}
					else {
						if(dbName.equals("arith")||dbName.equals("color")){
							buttons[i+tmp]=new MyButton(answer+"",isRight);
							buttons[i+tmp].setFont(new Font("Consolas", Font.PLAIN, 12));
						}
						else{
							buttons[i+tmp]=new MyButton("",isRight);
							String path="images\\"+dbName+"\\"+answer;
							 /* ImageIcon ico=new ImageIcon(path);
							  Image temp=ico.getImage().getScaledInstance(question.getWidth(),question.getHeight(),ico.getImage().SCALE_DEFAULT);
							  ico=new ImageIcon(temp);*/
							  buttons[i+tmp].setIcon(new ImageIcon(path));
						}
						}	
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
	public static void    getNo(String dbName) throws SQLException{
		ps=conn.prepareStatement(sql_no+dbName);
		rs=ps.executeQuery();
		no=0;
		while(rs.next()){
			no=no+1;
		}
		System.out.println("no=========="+no);
		no=(int) ((int) no*Math.random());
		System.out.println("no="+no);
		//return no;
	}
	//private static String db_Name="";
	private static String sql_no="select no from ";
	private static String sql_exp="select exp from " ;
	private static String sql_where=" where no=";
	//private static String sql_rightAnswer="selcet answer from arith where no=?";
	private static String sql_ans="select answer,answer1,answer2,answer3 from " ;
	private static String sql_title="select title from ";
	private static int no;
	private static  Connection conn=DBConnection.getInstance();
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static JLabel question=new JLabel();
	private static MyButton[] buttons=null;
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
