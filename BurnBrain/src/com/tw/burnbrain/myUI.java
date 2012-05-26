package com.tw.burnbrain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


class myUI extends JFrame{
	/**
	 * 主界面，主要提供三个选项按钮，一个是进入游戏，一个是说明，还有一个是退出
	 * 
	 */
	public myUI(){
		
		super();
		setTitle("主界面");
		this.setBackground(Color.WHITE);
		this.setSize(400,400);
		this.setLocation(455, 159);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		//设置背景图层，用来显示背景图片
		JPanel backPanel=new JPanel();
		this.add(backPanel,BorderLayout.CENTER);
		JButton gameB=new JButton("开始游戏");
		gameB.setFont(new   Font( "宋体 ",   Font.PLAIN,   40));
		backPanel.add(gameB);
		//设置按钮透明，只显示按钮上的文字
		gameB.setContentAreaFilled(false);
		gameB.setBorder(null);
		//this.add(gameB);
		gameB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			//	System.out.println("ahh");
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						gamechooseui=new gameChooseUI();
						Main.ui.setVisible(false);
						gamechooseui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					}
				});
			}
		});
		JButton illustrate=new JButton("游戏说明");
		illustrate.setFont(new   Font( "宋体 ",   Font.PLAIN,   40));
		backPanel.add(illustrate);
		illustrate.setContentAreaFilled(false);
		illustrate.setBorder(null);
		JButton close=new JButton("退出游戏");
		GridLayout gl=new GridLayout(3,1);
		backPanel.setLayout(gl);
		close.setFont(new   Font( "宋体 ",   Font.PLAIN,   40));
		close.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		close.setContentAreaFilled(false);
		close.setBorder(null);
		backPanel.add(close);
	}
	public static JFrame gamechooseui;
}