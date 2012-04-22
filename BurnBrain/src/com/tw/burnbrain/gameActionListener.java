package com.tw.burnbrain;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class gameActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				gameUI=new GameUI();
				gameUI.setVisible(true);
			}
		});
	}
	public static void closeUI(){
		gameUI.dispose();
	}
	private static JFrame gameUI;
}
