package tw.StudentManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
//cp.add(new JLabel("�Ѿ���½��"),new GBC(1,0).setAnchor(GBC.CENTER).setWeight(0,0));

public class Frame extends JFrame{
	public Frame(){
		this.setVisible(true);
		this.setSize(600, 400);
		this.setLocation(400,150);
		this.setResizable(false);
		GridBagLayout  gbc=new GridBagLayout();
		this.setLayout(gbc);
	}
}

