package tw.StudentManager;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;


public class loginActionListener implements ActionListener{
	public loginActionListener(JTextField name_txt,JPasswordField password_txt,JComboBox userTypeBox){
		this.name_txt=name_txt;
		this.password_txt=password_txt;
		this.userTypeBox=userTypeBox;
	}
	public void actionPerformed(ActionEvent e){
		final String username=name_txt.getText();
		String password=password_txt.getText();
		this.userType=(String) userTypeBox.getSelectedItem();
		System.out.println(userType.toString()+"我就是我");
		System.out.println(username+"我就是我");
		System.out.println(password+"我就是我");
		//验证用户输入的密码，超过三次登录错误就退出程序
		Connection conn=DBConnection.getInstance();
		String userNo;
		if(userType.equals("admin"))
			userNo="ano";
		else if(userType.equals("teacher"))
			userNo="tno";
		else
				userNo="sno";
		String sql="select password from " +userType+ " where " +userNo+" =? ";
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			System.out.println("username : "+username);
			rs=ps.executeQuery();
			if(i<3){
			if(rs.next()){
				System.out.println("i'm here");
			String tmpPassword= rs.getString(1);
			System.out.println("tmppassword  "+tmpPassword);
			 try {
				password_md5=new String(MD5.getMD5(password.getBytes()));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("加密类型错误");
			}
			 if(tmpPassword.equals("")){
				 JOptionPane jop =new JOptionPane();
					jop.showMessageDialog(null, "该用户还没有密码");
			 }
			 else if(!password_md5.equals( tmpPassword)){
				JOptionPane jop =new JOptionPane();
				jop.showMessageDialog(null, "密码错误");
			}
			else {
				EventQueue.invokeLater(new Runnable() {
					JFrame f;
					public void run() {
							if(userType.equals("student"))
								f=new StudentUI(username);//学生
							else if(userType.equals("teacher"))
								f=new TeacherUI(username);//老师
							else{
							System.out.println("andmin");	
								f=new AdminUI(username);//管理员
							}
						f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					}
				});
			}
			}
			else
				System.out.println("else error");
			}
			else{
				JOptionPane jop =new JOptionPane();
				jop.showMessageDialog(null, "登录错误3次，退出");
				System.exit(0);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("数据库操作失败");
		}
		
		}
	private JComboBox userTypeBox;
	private int type;
	private static int i=0;
	private String password_md5;
	private JTextField name_txt;
	private JPasswordField password_txt;
	private String userType;
}
