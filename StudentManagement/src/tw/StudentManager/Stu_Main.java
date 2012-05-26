package tw.StudentManager;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Stu_Main extends Frame{
	public Stu_Main(String username,int type){
		this.setTitle("学生个人管理主界面");
		picLabel=new JLabel();
		infoLabel=new JLabel();
		creditLabel=new JLabel();
		button =new JButton("选课");
		this.add(picLabel,new GBC(0,0).setAnchor(GBC.CENTER).setWeight(0,0));
		this.add(infoLabel,new GBC(2,0).setAnchor(GBC.CENTER).setWeight(0,0));
		this.add(creditLabel,new GBC(0,1).setAnchor(GBC.CENTER).setWeight(0,0));
		this.add(button);
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("这是选课模块");
			}
		});
	}
	public void setPicLabel(){
		
	}
	public static int PERMISSION_TYPE=0;
	private JButton button;
	private JLabel creditLabel;
	private JLabel picLabel;
	private JLabel infoLabel;
}
