package com.tw.burnbrain;

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
	public GameUI(){
		this.setSize(400,400);
		this.setLocation(455, 159);
		title=new JPanel();
		timeLabel=new JLabel("60");
		
		questionIllustrate=new JPanel();
		answerToChoose=new JPanel();
		answerToChoose.add(timeLabel);
		System.out.println("time start to work");
		new MyTimerTask();
		//new Thread(new MakeQuestion()).start();
		JLabel l=new JLabel();
		l.setText(".....");
		answerToChoose.add(l);
		this.add(title);
		this.add(questionIllustrate);
		this.add(answerToChoose);
	}
	//自定义题目的要求
	/*public void addQuestion(JLabel question){
		if(question==null)
			question=new JLabel("结果是多少");
		title.add(question);
	}
	//自定义题目的描述
	public void addQuestionIllustrate(JLabel queIllustrate){
		if(queIllustrate==null)
			queIllustrate=new JLabel("8-4");
			questionIllustrate.add(queIllustrate);
	}
	//自定义题目的可选答案
	public void addAnswerToChoose(JButton[] answerButtons){
		for(int i=0;i<answerButtons.length;i++){
			if(answerButtons[i]==null)
				answerButtons[i]=new JButton();
			answerToChoose.add(answerButtons[i]);
		}
	}*/
	public static JLabel getTimeLabel(){
		return timeLabel;
	}
	
	private static JLabel timeLabel;
	private JPanel title;
	private JPanel questionIllustrate;
	private JPanel answerToChoose;
}
