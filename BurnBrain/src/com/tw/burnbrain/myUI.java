package com.tw.burnbrain;

import java.awt.GridLayout;
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
		this.setSize(400,400);
		this.setLocation(455, 159);
		
		JPanel centerPanel=new JPanel();
		JButton gameB=new JButton("game");
		centerPanel.add(gameB);
		gameB.addActionListener(new gameActionListener());
		JButton illustrate=new JButton("illustrate");
		centerPanel.add(illustrate);
		JButton close=new JButton("close");
		close.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		centerPanel.add(close);
		this.add(centerPanel);
		
	}
}