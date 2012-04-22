package com.tw.burnbrain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;

public class test {
	public static void main(String[] args){
		Random ran=new Random();
		int firstNum=ran.nextInt(20);
	int secondNum=ran.nextInt(firstNum);
		char op='+';
		int answer=5;
		System.out.println(firstNum);
		System.out.println(secondNum);
		System.out.println(answer+1+"");
		JButton b=new JButton("aaaa");
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				e.toString();
			}
		});
	}
}
