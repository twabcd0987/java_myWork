package com.tw.burnbrain;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class showQuestion implements Runnable{
	public showQuestion (String gameType){
		this.gameType=gameType;
	}
	public void run(){
		
		//此处根据gameType判断到底是用哪个数据库》》》？？？？？？
		dbName=gameType;
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			GameUI gameUI=gameActionListener.gameUI;
			gameUI.addTitle(QuestionCreator.getTitle(dbName));
			gameUI.addLabel(QuestionCreator.getQuestion(dbName));
			gameUI.addButtons(QuestionCreator.getButtons(dbName));
		
		}
	});
	}
	//private static QuestionCreator qc=new QuestionCreator(); 
	private String gameType;
	private String dbName;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
}
