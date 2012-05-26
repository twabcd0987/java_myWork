package com.tw.burnbrain;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class showQuestion implements Runnable{
	public showQuestion (String gameType){
		this.dbName=gameType;
	}
	public void run(){
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			GameUI gameUI=gameActionListener.gameUI;
			gameUI.addLabel(QuestionCreator.getQuestion(dbName));
			gameUI.addTitle(QuestionCreator.getTitle(dbName));
			gameUI.addButtons(QuestionCreator.getButtons(dbName));
		}
	});
	}
	//private static QuestionCreator qc=new QuestionCreator(); 
	private String dbName;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
}
