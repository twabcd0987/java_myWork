package com.tw.burnbrain;

import javax.swing.JButton;

public class MyButton extends JButton {
	public MyButton(String buttonName,boolean isRight) {
		super(buttonName);
		this.isRight = isRight;
	}
	public boolean isRightAnswer(){
		return isRight;
	}
	private String buttonName;
	private boolean isRight;
}
