package com.tw.burnbrain;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class MyTimerTask {
	private int sec=60;
	public MyTimerTask(){
		this.timeLabel=GameUI.getTimeLabel();
		final Timer timer=new Timer();
		timer.schedule(new TimerTask(){
			public void run(){
				sec=sec-1;
				timeLabel.setText(sec+"");
				System.out.println(sec+"");
				if(sec<1) {
					timer.cancel();
					gameActionListener.closeUI();
					//showresult();
					}
			}
		}, 0,1000);
	}
	private  JLabel timeLabel;
}
