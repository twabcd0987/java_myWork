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
		timer=new Timer();
		tt=new TimerTask(){
			public void run(){
				sec=sec-1;
				timeLabel.setText("还剩： "+sec+"");
				System.out.println(sec+"");
				if(sec<1) {
					timer.cancel();
					gameActionListener.removeAll();
					gameActionListener.closeUI();
					showResult();
					}
			}
		};
		timer.schedule(tt, 0,1000);
	}
	
	private void showResult(){
		JOptionPane jop=new JOptionPane();
		jop.showMessageDialog(null, "你的得分是"+Main.gameResult);
//		jop.showConfirmDialog(null, "your score: "+Main.gameResult);
		Main.gameResult=0;
	}
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("diaoyong wo stop");
		timer.cancel();
		
	}
	private  JLabel timeLabel;
	private final Timer timer;
	private TimerTask tt;
	
}
