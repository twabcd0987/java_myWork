package tw.StudentManager;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class InputPassword extends JFrame{
	public InputPassword(JFrame f,final String username,String userType){
		this.setTitle("输入密码");
		this.username=username;
		this.userType=userType;
		String userNo;
		System.out.println("userType:"+userType);
		if(userType.equals("admin"))
			userNo="ano";
		else if(userType.equals("teacher"))
			userNo="tno";
		else
				userNo="sno";
		final String sql_update_password=" update  "+userType+
				"  set password  = ?"
				+ "  where "+userNo+" = ? ";
		System.out.println("userNo:"+userNo);
		this.setSize(500,300);
		GridBagLayout  gbc=new GridBagLayout();
		this.setLayout(gbc);
		this.setLocation(500, 260);
		this.setVisible(true);
		JLabel password=new JLabel("输入密码");
		this.add(password,new GBC(0,0));
		final JPasswordField txt_pwd=new JPasswordField();
		txt_pwd.setColumns(10);
		this.add(txt_pwd,new GBC(1,0));
		JLabel password1=new JLabel("确认密码");
		this.add(password1,new GBC(0,1));
		final JPasswordField txt_pwd1=new JPasswordField();
		this.add(txt_pwd1,new GBC(1,1));
		txt_pwd1.setColumns(10);
		JButton ok=new JButton("确认");
		this.add(ok,new GBC(0,2));
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String pwd=txt_pwd.getText();
				String pwd1=txt_pwd1.getText();
				System.out.println("pwd===="+pwd.toString());
				System.out.println("pwd===="+pwd1.toString());
				if(pwd.equals(pwd1)){
					//写入数据库
					JOptionPane jop=new JOptionPane();
					jop.showMessageDialog(null, "输入成功");
					
				}
				else{
					JOptionPane jop=new JOptionPane();
					jop.showMessageDialog(null, "密码不一致");
				}
				conn=DBConnection.getInstance();
				System.out.println("ps:"+sql_update_password);
				try{
				ps=conn.prepareStatement(sql_update_password);
				String pwd_md5=new String(MD5.getMD5(txt_pwd.getText().getBytes()));
				ps.setString(1, pwd_md5);
				ps.setString(2, username);
				ps.execute();
				}catch(Exception e1){
					e1.printStackTrace();
				}
				
				InputPassword.this.dispose();
			}
		});
		JButton cancel=new JButton("退出");
		this.add(cancel,new GBC(1,2));
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				InputPassword.this.dispose();
			}
		});
	}
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private String username;
	private String userType;
	public static void main(String [] agrs){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				InputPassword f=new InputPassword(null,"1001","student");
			}
		});
	}
}
