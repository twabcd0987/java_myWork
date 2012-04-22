package com.tw.burnbrain;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main {

	/**@date 2012-4-21
	 * 这个是BurnBrain的测试函数，burnbrain是为一个简单的游戏，
	 * 从前几天玩过的一款手机游戏改写而来。
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ui=new myUI();
				ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ui.setVisible(true);
			}
		});
		//new Thread(new MusicPlayer()).start();
		//new Thread(new DBConnection()).start();
		
}
	public static JFrame ui; 
}
