package com.tw.burnbrain;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MyTimerTask {
	//游戏时间，60秒
	private int sec=60;
	public MyTimerTask(){
		this.timeLabel=GameUI.getTimeLabel();
		final Timer timer=new Timer();
		timer.schedule(new TimerTask(){
			public void run(){
				sec=sec-1;
				timeLabel.setText("还剩： "+sec+"");
				System.out.println(sec+"");
				if(sec<1) {
					timer.cancel();
					gameActionListener.closeUI();
					showResult();
					}
			}
		}, 0,1000);
	}
	private void showResult(){
		JOptionPane jop=new JOptionPane();
		
		jop.showConfirmDialog(null, "your score: "+Main.gameResult);
	}
	private  JLabel timeLabel;
}
