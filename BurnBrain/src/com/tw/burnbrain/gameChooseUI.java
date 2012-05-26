package com.tw.burnbrain;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class gameChooseUI extends JFrame{
	public  gameChooseUI(){
		setSize(400,400);
		setTitle("游戏选择界面");
		setVisible(true);
		setLocation(455, 159);
		/* backPanel=new JPanel(){
			public void paintComponent(Graphics g){
				ImageIcon icon=new ImageIcon("images\\1.jpg");
				Image img=icon.getImage();
				g.drawImage(img,0,0,icon.getIconWidth(),icon.getIconHeight(),icon.getImageObserver());
			}
		};*/
		backPanel=new JPanel();
		Font font=new Font("宋体 ",   Font.PLAIN,   40);
		backPanel.setLayout(new GridLayout(3,1));
		JButton arith=new JButton("加减乘除");
		addButton(arith);
		arith.addActionListener(new gameActionListener("arith"));
		arith.setFont(font);
		JButton ssc=new JButton("快速猜拳");
		addButton(ssc);
		ssc.addActionListener(new gameActionListener("ssc"));
		ssc.setFont(font);
		JButton color=new JButton("颜色判断");
		addButton(color);
		color.addActionListener(new gameActionListener("color"));
		color.setFont(font);
		add(backPanel);
	}
	public void addButton(JButton button){
		backPanel.add(button);
		button.setContentAreaFilled(false);
		button.setBorder(null);
	}
	private JPanel backPanel;
}