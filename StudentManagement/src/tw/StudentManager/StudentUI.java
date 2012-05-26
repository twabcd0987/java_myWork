package tw.StudentManager;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StudentUI extends Frame{
	public StudentUI(final String username){
		this.setTitle("学生个人管理主界面");
		//picLabel=new JLabel("pic");
		nameLabel=new JLabel();
		noLabel=new JLabel();
		sexLabel=new JLabel();
		sdeptLabel=new JLabel();
		creditLabel=new JLabel("credit");
		chooseClass =new JButton("     "+"选课"+"    ");
		changePWD=new JButton("修改密码");
	//	this.add(picLabel,new GBC(0,0).setAnchor(GBC.CENTER).setWeight(0,0));
		JPanel left=new JPanel();
		setInfoLabel(username);
		left.add(nameLabel);
		//left.add(noLabel,new GBC(0,1).setAnchor(GBC.LINE_START));
		left.add(noLabel);
		left.add(sexLabel);
		left.add(sdeptLabel);
		JPanel right=new JPanel();
		
		//this.add(creditLabel,new GBC(1,0).setAnchor(GBC.LINE_END).setWeight(0,0));
		right.add(chooseClass);
		if(MainUI.xuanke_PROXY)
			chooseClass.setEnabled(true);
		else
			chooseClass.setEnabled(false);
		right.add(changePWD);
		inquire=new JButton("分数查询");
		right.add(inquire);
		inquire.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new InquireScoreUI(username);
			}
		});
		exit=new JButton("退出");
		right.add(exit);
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				StudentUI.this.dispose();
				MainUI.retry();
			}
		
		});
		
		
		this.add(left,new GBC(0,0));
		this.add(right,new GBC(0,1));
		chooseClass.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
						new chooseClassUI(username);
				}catch(Exception e1)
				{
					e1.printStackTrace();
					System.out.println("已经不知道到底是哪里的异常了");
				}
			}
		});
		changePWD.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				EventQueue.invokeLater(new Runnable(){
					public void run(){
						new InputPassword(StudentUI.this,username,"student");
					}
				});
				System.out.println("这是修改密码的模块");
			}
		});
	}
	//显示学分
	/*public void setCreditLabel(){
		
	}*/
	//显示学生信息
	public void setInfoLabel(String username){
		try {
			System.out.println(username);
			String sql_searchInfo="select sno,sname,sex,sdept from student where sno=?";
			ps=conn.prepareStatement(sql_searchInfo);
			ps.setString(1,username);
			rs=ps.executeQuery();
			StringBuilder sb=new StringBuilder();
			if(rs.next()){
				noLabel.setText("学号:"+rs.getString(1)+"\n");
				nameLabel.setText("姓名:"+rs.getString(2)+"\n");
				sexLabel.setText("性别:"+rs.getString(3)+"\n");
				sdeptLabel.setText("院系:"+rs.getString(4)+"\n");
//				infoLabel.setText(sb.toString());
				//System.out.println("aaaa"+'\n'+"222");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	//显示用户头像；
	/*public void setPicLabel(){
	
	}*/
	
	private Connection conn=DBConnection.getInstance();
	private PreparedStatement ps;
	private ResultSet rs;
	private JButton chooseClass;
	private JLabel creditLabel;
	private JLabel picLabel;
	private JLabel nameLabel;
	private JLabel noLabel;
	private JLabel sexLabel;
	private JLabel sdeptLabel;
	private JButton changePWD;
	private JButton exit;
	private JButton inquire;
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
			JFrame sui=new StudentUI("1001");
			sui.setDefaultCloseOperation(EXIT_ON_CLOSE);
			}
		});
	}
}
