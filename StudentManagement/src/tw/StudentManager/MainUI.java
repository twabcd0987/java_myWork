package tw.StudentManager;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainUI extends Frame{
	public MainUI(String str){
		this.setTitle(str);
		JLabel userType=new JLabel("用户类型");
		this.add(userType,new GBC(0,0).setAnchor(GBC.LINE_START));
		//此处还要加一个下拉框，选择用户类型；
		JComboBox userTypeCombo=new JComboBox();
		 userTypeCombo.addItem("admin");  
		 userTypeCombo.addItem("teacher"); 
		 userTypeCombo.addItem("student");
		 
		 this.add(userTypeCombo,new GBC(1,0).setAnchor(GBC.LINE_START));
		JLabel userName=new JLabel("用户名");
		this.add(userName,new GBC(0,1).setAnchor(GBC.LINE_START));
		name_txt=new JTextField();
		name_txt.setColumns(10);
		this.add(name_txt,new GBC(1,1).setAnchor(GBC.LINE_START));
		JLabel password=new JLabel("密码");
		this.add(password,new GBC(0,2).setAnchor(GBC.LINE_START).setFill(MAXIMIZED_BOTH));
		password_txt=new JPasswordField();
		password_txt.setColumns(10);
		this.add(password_txt,new GBC(1,2).setAnchor(GBC.LINE_START));
		JButton login=new JButton("登录");
		System.out.println(name_txt.getText());
		 login.addActionListener(new loginActionListener(name_txt,password_txt,userTypeCombo));
		this.add(login,new GBC(0,3).setAnchor(GBC.LINE_START));
		JButton retry=new JButton("重置");
		this.add(retry,new GBC(1,3).setAnchor(GBC.LINE_START));
		retry.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				name_txt.setText("");
				password_txt.setText("");
			}
		});
		try{
			conn=DBConnection.getInstance();
			ps=conn.prepareStatement(sql_getStatus);
			ps.setString(1,"CURRENT_TERM");
			rs=ps.executeQuery();
			if(rs.next())
				CURRENT_TERM=rs.getInt(1);
			ps.setString(1, "xuanke_PROXY");
			rs=ps.executeQuery();
			if(rs.next())
				xuanke_PROXY=rs.getInt(1)==0?false:true;
			ps.setString(1, "dengfen_PROXY");
			rs=ps.executeQuery();
			if(rs.next())
				dengfen_PROXY=rs.getInt(1)==0?false:true;
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public static void retry(){
		name_txt.setText("");
		password_txt.setText("");
	}
	private String sql_getStatus="select value from statusTable where status=?";
	private static JTextField name_txt;
	private static JPasswordField password_txt;
	public static boolean xuanke_PROXY;
	public  static boolean dengfen_PROXY;
	public static int CURRENT_TERM;
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame f = new MainUI("主界面");
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}

}
