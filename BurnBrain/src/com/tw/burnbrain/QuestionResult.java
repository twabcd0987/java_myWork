package com.tw.burnbrain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionResult implements ActionListener{
	public QuestionResult(String gameType){
		this.gameType=gameType;
		if(gameType.equals("arith"))
			dbName="arith";
	}
	public void actionPerformed(ActionEvent e){
			MyButton bt=(MyButton) e.getSource();
			if(bt.isRightAnswer())
				Main.gameResult=Main.gameResult+1;
			GameUI.clear();
			new Thread(new showQuestion(dbName)).start();
	}
	private String gameType;
	private String dbName;
}
