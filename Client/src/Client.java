/**
 *  Volunteer Management System
 */

/**
 * @author Timothy
 *
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Font.*;
import javax.swing.text.*;
import java.net.*;
import java.awt.Cursor.*;
import java.awt.image.*; 

public class Client implements ActionListener, MouseListener, MouseMotionListener{
	//Start Window
	JFrame startWindow;
	startPanel startPanel;
	JTextField ip;
	JTextField password;
	JButton connect;
	JLabel title;
	JLabel ipLabel;
	JLabel passwordLabel;
	JLabel version;
	
	//Main Window
	JFrame mainWindow;
	
	/*
	try{
		URI uri = new URI("http://timothylock.co.nr/"); 
	}catch(Exception e){
	}
	Desktop dt = Desktop.getDesktop();*/
	
	public void actionPerformed(ActionEvent evt){
	}
	public void mouseDragged(MouseEvent evt){

	}
	public void mouseMoved(MouseEvent evt){
	}
	public void mousePressed(MouseEvent evt){
	}
	public void mouseExited(MouseEvent evt){
	}
	public void mouseEntered(MouseEvent evt){
	}
	public void mouseClicked(MouseEvent evt){
	}	
	public void mouseReleased(MouseEvent evt){
	}
	
	public Client(){
		//Start Window Setup
		startWindow = new JFrame ("Volunteer Management System - Connect To Server");
		startWindow.setSize(700,500);
		startPanel = new startPanel();
		startPanel.setLayout(null);
		
		
		//Start Panel Setup
		title = new JLabel("Connect To Server");
		title.setLocation(190,140);
		title.setSize(600,100);
		title.setFont(new Font("Calibri", Font.PLAIN, 40));
		
		ipLabel = new JLabel("Server IP:");
		ipLabel.setLocation(190,200);
		ipLabel.setSize(600,100);
		ipLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		startPanel.add(ipLabel);
		
		ip = new JTextField ();
		ip.setSize(200,30);
		ip.setLocation(290,235);
		ip.setFont(new Font("Calibri", Font.PLAIN, 20));
		startPanel.add(ip);
		
		passwordLabel = new JLabel("Password:");
		passwordLabel.setLocation(190,250);
		passwordLabel.setSize(600,100);
		passwordLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		startPanel.add(passwordLabel);
		
		password = new JTextField ();
		password.setSize(200,30);
		password.setLocation(290,285);
		password.setFont(new Font("Calibri", Font.PLAIN, 20));
		startPanel.add(password);
		
		connect = new JButton("Connect");
		connect.setSize(300,30);
		connect.setLocation(190, 330);
		connect.setFont(new Font("Calibri", Font.BOLD, 20));
		startPanel.add(connect);
		
		version = new JLabel("Version 1.3B_MCF_VERSION             								                                                                                                                     By Timothy Lock");
		version.setSize(700,20);
		version.setLocation(10,440);
		version.setFont(new Font("Calibri", Font.BOLD, 15));
		startPanel.add(version);
		
		
		startPanel.add(title);
		startWindow.setContentPane(startPanel);
		startWindow.setVisible(true);
	}

	public static void main(String[] args){
		Client theClient = new Client();
	}
}

