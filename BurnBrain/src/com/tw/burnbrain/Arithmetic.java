package com.tw.burnbrain;

import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
//连接数据库，从数据库中获取题目；
public class Arithmetic {
	//
	public JLabel getQuestionLabel() {
		
		return questionLabel;
	}

	// 此处有一个排序的问题，
	public JButton[] getAnswerLabel(){
		int tmp=ran.nextInt()/4;
		for(int i=0;i<4;i++){
			if(i==0) isRight=true;
			else isRight=false;
			if(i+tmp>3) buttons[i+tmp-4]=new MyButton(answer+i+"",isRight);
			else buttons[i+tmp]=new MyButton(answer+i+"",isRight);
		}
		return buttons;
	}

	public int getAnswer() {
		return answer;
	}
	private boolean isRight;
	private JButton[] buttons;
	private Random ran;
	private int firstNum;
	private int secondNum;
	private int answer;
	private static char[] ops = { '*', '+', '-' };
	private JLabel questionLabel;
	private JLabel answerLabel;
}
