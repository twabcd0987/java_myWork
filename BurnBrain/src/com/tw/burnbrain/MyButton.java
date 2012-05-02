package com.tw.burnbrain;

import javax.swing.JButton;

public class MyButton extends JButton {
	public MyButton(String buttonName,boolean isRight) {
		super(buttonName);
		this.isRight = isRight;
	}
	public MyButton(){
		super();
	}
	public boolean isRightAnswer(){
		return isRight;
	}
	public void  setIsRight(boolean isRight){
		this.isRight=isRight;
	}
	
	private String buttonName;
	private boolean isRight;
}
