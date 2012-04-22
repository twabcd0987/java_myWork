package com.tw.burnbrain;

import java.util.Timer;
import java.util.TimerTask;

public class Test__TimerTask {
	private int sec=60;
	public Test__TimerTask(){
		final Timer timer=new Timer();
		timer.schedule(new TimerTask(){
			public void run(){
				System.out.println(sec--);
				if(sec<0) timer.cancel();
			}
		}, 0,1000);
	}
	public static void main(String[] args){
		new Test__TimerTask();
	}
}


