package tw.StudentManager;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InquireScoreUI extends JFrame {
	public InquireScoreUI(String username) {
		this.setVisible(true);
		this.setSize(600, 400);
		this.setLocation(400, 150);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		column = new Vector<String>();
		column.add("课程名");
		column.add("分数");
		column.add("选修学期");
		label = new JLabel("你的分数是 ：");
		this.add(label, BorderLayout.NORTH);
		//定义表格内容不可修改；
		model=new DefaultTableModel(){
			public   boolean   isCellEditable(int   row,int   column){ 
		        return   false; 
		    } 

		};
		model.setColumnIdentifiers(column);
		table = new JTable(model);
		table.setAutoCreateRowSorter(true);
		table.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		try{
		ps=conn.prepareStatement(sql_inquire);
		ps.setString(1, username);
		rs=ps.executeQuery();
		while(rs.next()){
			Vector values = new Vector();
			values.add(rs.getObject(1));
			values.add(rs.getObject(2));
			values.add(rs.getObject(3));
			model.addRow(values);
		}
		
		}catch(SQLException e1){
			e1.printStackTrace();
		}
		jsp=new JScrollPane(table);
		this.add(jsp,BorderLayout.CENTER);
	}

	// 根据sno检索学生学习成绩
	private String sql_inquire = "select cname,grade,term " + " from sc,class,tc "
									+ " where tc.cno=class.cno " + " and sc.tcno=tc.tcno "
									+ " and sc.sno=?";
	private Connection conn = DBConnection.getInstance();
	private PreparedStatement ps;
	private ResultSet rs;
	private String username;
	private JLabel label;
	private JTable table;
	private DefaultTableModel model;
	private Vector<String> column;
	private JScrollPane jsp;
	public static void main(String [] args){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				JFrame f = new InquireScoreUI("1002");
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
			}
		});
		
	}
}
