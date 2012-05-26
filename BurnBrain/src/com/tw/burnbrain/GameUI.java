package com.tw.burnbrain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
		this.setSize(800,500);
		this.setLocation(300, 100);
		title=new JPanel();
		timeLabel=new JLabel("60");
		timeLabel.setFont(new Font("宋体 ",   Font.PLAIN,   30));
		backPanel.setLayout(new BorderLayout());
		questionIllustrate=new JPanel();
		questionIllustrate.setLayout(new BorderLayout());
		
		answerToChoose=new JPanel();
		answerToChoose.setBackground(Color.YELLOW);
		title.add(timeLabel);
		backPanel.add(title,BorderLayout.NORTH);
		backPanel.add(questionIllustrate,BorderLayout.CENTER);
		backPanel.add(answerToChoose,BorderLayout.SOUTH);
		titleLabel=new JLabel(); 
		questionIllustrate.add(titleLabel,BorderLayout.NORTH);
		questionIllustrate.setBackground(Color.WHITE);
	
		final MyTimerTask mtk=new MyTimerTask();
		new Thread(new showQuestion(gameType)).start();
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				GameUI.this.dispose();
				gameActionListener.removeAll();
				gameActionListener.closeUI();
				Main.gameResult=0;
				mtk.stop();
				//System.exit(0);
			}
			});
		
	}
	public void addTitle(String title){
		
		titleLabel=new JLabel(title); 
		questionIllustrate.add(titleLabel,BorderLayout.NORTH);
		this.titleLabel.setFont(new Font("宋体 ",   Font.PLAIN,   40));
		this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);	
	}
	public void addLabel(JLabel label){
		if(label!=null){
		questionIllustrate.add(label,BorderLayout.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体 ",   Font.PLAIN,   40));
		}
	}
	public  void addResultLabel(JLabel label){
		if(label!=null){
			
			questionIllustrate.add(label,BorderLayout.EAST);
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体 ",   Font.PLAIN,   40));
			JLabel westLabel=new JLabel();
			westLabel.setIcon(new ImageIcon("images\\white.jpg"));
			questionIllustrate.add(westLabel,BorderLayout.WEST);
			}
	}
	public void addButtons(MyButton[] buttons){
		for(int i=0;i<buttons.length;i++){
			//buttons[i].setSize(30,10);
			buttons[i].addActionListener(new QuestionResult(gameType));
			buttons[i].setFont(new Font("宋体 ",   Font.PLAIN,   40));
			answerToChoose.add(buttons[i]);
			buttons[i].setBorderPainted(true); 
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
	private static JLabel  answerlabel;
	private String gameType;
	private static JPanel backPanel;
	private JLabel titleLabel;
	private static JLabel timeLabel;
	private JPanel title;
	private static JPanel questionIllustrate;
	private static JPanel answerToChoose;
}
