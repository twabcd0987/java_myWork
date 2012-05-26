package com.tw.burnbrain;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class QuestionResult implements ActionListener{
	public QuestionResult(String gameType){
		this.dbName=gameType;
	}
	public void actionPerformed(ActionEvent e){
			MyButton bt=(MyButton) e.getSource();
			//如果答对了
			
			if(bt.isRightAnswer())
			{
				Main.gameResult=Main.gameResult+1;
				label.setIcon(new ImageIcon("images\\right.jpg"));
			}//如果答错了
			else {
				label.setIcon(new ImageIcon("images\\wrong.jpg"));
			}
			EventQueue.invokeLater(new Runnable(){
				public void run(){
					gameActionListener.gameUI.addResultLabel(label);
				}
			});
			GameUI.clear();
			new Thread(new showQuestion(dbName)).start();
	}
	private JLabel label=new JLabel();
	private String gameType;
	private String dbName;
}
