package tw.StudentManager;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AdminUI extends JFrame{
	public AdminUI(String username){
		this.setVisible(true);
		this.setSize(600, 400);
		this.setLocation(400,150);
		this.setResizable(false);
		JPanel panel=new JPanel();
		this.setLayout(new BorderLayout());
		this.add(panel,BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3,3));
		openXuanke=new JButton("开始选课");
		openXuanke.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MainUI.xuanke_PROXY=true;
				try{
				ps=conn.prepareStatement(sql_updateStatus);
				ps.setInt(1, 1);
				ps.setString(2, "xuanke_PROXY");
				System.out.println(sql_updateStatus);
				}catch(SQLException e1){
					e1.printStackTrace();
				}
				AdminUI.this.repaint();
			}
		});
		panel.add(openXuanke);
		closeXuanke=new JButton("结束选课");
		closeXuanke.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MainUI.xuanke_PROXY=false;
				try{
					ps=conn.prepareStatement(sql_updateStatus);
					ps.setObject(1, 0);
					ps.setObject(2, "xuanke_PROXY");
					}catch(SQLException e1){
						e1.printStackTrace();
					}
				AdminUI.this.repaint();
			}
		});
		panel.add(closeXuanke);
		openDengfen=new JButton("开始登分");
		openDengfen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MainUI.dengfen_PROXY=true;
				try{
					ps=conn.prepareStatement(sql_updateStatus);
					ps.setObject(1, 1);
					ps.setObject(2, "dengfen_PROXY");
					
					}catch(SQLException e1){
						e1.printStackTrace();
					}
				AdminUI.this.repaint();
			}
		});
		panel.add(openDengfen);
		closeDengfen=new JButton("结束登分");
		closeDengfen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MainUI.dengfen_PROXY=false;
				try{
					ps=conn.prepareStatement(sql_updateStatus);
					ps.setObject(1, 0);
					ps.setObject(2, "dengfen_PROXY");
					}catch(SQLException e1){
						e1.printStackTrace();
					}
				AdminUI.this.repaint();
			}
		});
		exit=new JButton("退出");
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AdminUI.this.dispose();
				MainUI.retry();
			}
		});
		panel.add(closeDengfen); 
		panel.add(exit);
		southPanel=new JPanel();
		//设置学期的地方哦
		JPanel termPanel=new JPanel();
		changeTerm=new JButton("设置学期");
		termBox=new JComboBox();
		termBox.addItem("1");
		termBox.addItem("2");
		termBox.addItem("3");
		termPanel.add(termBox);
		termPanel.add(changeTerm);
		panel.add(termPanel);
		changeTerm.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			String term=	(String) termBox.getSelectedItem();
			MainUI.CURRENT_TERM=Integer.parseInt(term);
			try{
			ps=conn.prepareStatement(sql_updateStatus);
			ps.setObject(1, MainUI.CURRENT_TERM);
			ps.setObject(2, "CURRENT_TERM");
			if(!ps.execute())
				System.out.println("选课权限设置不成功");
			}catch(SQLException e1){
				e1.printStackTrace();
			}
			System.out.println("当前学期为："+MainUI.CURRENT_TERM);
			}
		
		});
		this.add(southPanel,BorderLayout.SOUTH);
		xuankeStatus =new JLabel();
		dengfenStatus=new JLabel();
		StringBuilder sb=new StringBuilder();
		sb.append("选课状态：  ");
		if(MainUI.xuanke_PROXY)
			sb.append("开启");
		else 
			sb.append("关闭");
		xuankeStatus.setText(sb.toString());
		sb=new StringBuilder();
		sb.append("登分状态：  ");
		if(MainUI.dengfen_PROXY)
			sb.append("开启");
		else 
			sb.append("关闭");
		dengfenStatus.setText(sb.toString());
		southPanel.add(xuankeStatus);
		southPanel.add(dengfenStatus);
	}
	public void repaint(){
		StringBuilder sb=new StringBuilder();
		sb.append("选课状态：  ");
		if(MainUI.xuanke_PROXY)
			sb.append("开启");
		else 
			sb.append("关闭");
		xuankeStatus.setText(sb.toString());
		sb=new StringBuilder();
		sb.append("登分状态：  ");
		if(MainUI.dengfen_PROXY)
			sb.append("开启");
		else 
			sb.append("关闭");
		dengfenStatus.setText(sb.toString());
		southPanel.add(xuankeStatus);
		southPanel.add(dengfenStatus);
	}
	private String sql_updateStatus="update statusTable set value=?"+" where status=? ";
	private Connection conn=DBConnection.getInstance();
	private PreparedStatement ps;
	private ResultSet rs;
	private JButton exit;
	private JPanel southPanel;
	private JLabel xuankeStatus;
	private JLabel dengfenStatus;
	private JButton openXuanke;
	private JButton closeXuanke;
	private JButton openDengfen;
	private JButton closeDengfen;
	private JButton changeTerm;
	private JComboBox termBox;
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				new AdminUI("5001");
			}
		});
	}
}
