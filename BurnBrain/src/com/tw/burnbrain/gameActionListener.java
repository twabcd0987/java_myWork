package com.tw.burnbrain;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class gameActionListener implements ActionListener {
	private String gameType;
	public gameActionListener(String gameType){
		this.gameType=gameType;
	}
	@Override
	public void actionPerformed(final ActionEvent e) {
		// TODO Auto-generated method stub
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				if(true){
				//System.out.println(e.getSource());
				gameUI=new GameUI(gameType);
				gameUI.setVisible(true);
				gameUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			}
		});
	}
	public static void closeUI(){
		gameUI.dispose();
	}
	
	public static GameUI gameUI;
}
