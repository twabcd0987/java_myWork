package com.tw.burnbrain;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/*
 * 进行游戏的界面，分成上中下三部分，
 * 上部为题目要求，和计时器
 * 中部为题目描述，
 * 下部为题目可选的答案
 */
public class GameUI extends JFrame {
	public GameUI(String gameType){
		this.gameType=gameType;
		setTitle("游戏界面");
		backPanel=new JPanel(){
				public void paintComponent(Graphics g){
					ImageIcon icon=new ImageIcon("images\\2.jpg");
					Image img=icon.getImage();
					g.drawImage(img,0,0,icon.getIconWidth(),icon.getIconHeight(),icon.getImageObserver());
				}
			};
			this.add(backPanel);
		this.setSize(400,400);
		this.setLocation(455, 159);
		title=new JPanel();
		timeLabel=new JLabel("60");
		timeLabel.setFont(new Font("宋体 ",   Font.PLAIN,   40));
		backPanel.setLayout(new BorderLayout());
		questionIllustrate=new JPanel();
		questionIllustrate.setLayout(new BorderLayout());
		answerToChoose=new JPanel();
		title.add(timeLabel);
		backPanel.add(title,BorderLayout.NORTH);
		backPanel.add(questionIllustrate,BorderLayout.CENTER);
		backPanel.add(answerToChoose,BorderLayout.SOUTH);
		titleLabel=new JLabel(); 
		questionIllustrate.add(titleLabel,BorderLayout.NORTH);
		JLabel tm=new JLabel("     题目 :");
		tm.setHorizontalAlignment(SwingConstants.CENTER);
		questionIllustrate.add(tm,BorderLayout.WEST);
		tm.setFont(new Font("宋体 ",   Font.PLAIN,   40));
		new MyTimerTask();
		new Thread(new showQuestion(gameType)).start();
	}
	public void addTitle(String str_title){
		titleLabel.setText(str_title);
		titleLabel.setFont(new Font("宋体 ",   Font.PLAIN,   40));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}
	public void addLabel(JLabel label){
		if(label!=null){
		questionIllustrate.add(label,BorderLayout.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体 ",   Font.PLAIN,   40));
		}
	}
	public void addButtons(MyButton[] buttons){
		for(int i=0;i<4;i++){
			//buttons[i].setSize(30,10);
			buttons[i].addActionListener(new QuestionResult(gameType));
			
			answerToChoose.add(buttons[i]);
			buttons[i].setBorderPainted(false);
			buttons[i].setContentAreaFilled(false);
			}
	}
	public static void clear(){
		answerToChoose.removeAll();
		questionIllustrate.removeAll();
		backPanel.repaint();
	}
	public static JLabel getTimeLabel(){
		return timeLabel;
	}
	private String gameType;
	private static JPanel backPanel;
	private JLabel titleLabel;
	private static JLabel timeLabel;
	private JPanel title;
	private static JPanel questionIllustrate;
	private static JPanel answerToChoose;
}
