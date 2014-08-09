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
import java.io.*;

public class Server implements ActionListener, MouseListener, MouseMotionListener{
	//Version
	String strVersion = "0.41alpha";
	
	//Networking Components
	JTextField ssmfield;
	SuperSocketMaster ssm;
	Thread ssmthread;
	String strLine;
	String strTemp[];
	String strfile;
	
	//Start Window
	JFrame startWindow;
	startPanel startPanel;
	JTextField file;
	JTextField password;
	JButton startServer;
	JLabel title;
	JLabel fileLabel;
	JLabel passwordLabel;
	JLabel version;
	JLabel credit;
	JButton browse;
	JFileChooser chooser = new JFileChooser();
	
	//Main Window Varibles
	String strPassword;
	String csvlocation;
	
	
	/*
	try{
		URI uri = new URI("http://timothylock.co.nr/"); 
	}catch(Exception e){
	}
	Desktop dt = Desktop.getDesktop();*/
	
	public void actionPerformed(ActionEvent evt){
		if (evt.getSource() == startServer){
			
		}else if(evt.getSource() == ssmfield){  //Network Messages
			strLine = ssmfield.getText();
			if (strLine != null){ //Split message
		        strTemp = strLine.split(",");
			}else{
				strLine = "IGNORE,FILLER,LOL";
				strTemp = strLine.split(",");
			}
		}
		if (evt.getSource() == browse){
			browseCSV();
		}else if (evt.getSource() == startServer){
			strPassword = password.getText();
			try{
				ssm = new SuperSocketMaster(ssmfield,9001);
				ssmthread = new Thread(ssm);
				ssmthread.start();
				startWindow.setVisible(false);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Could not start server. Most likely cause is that port 9001 is in use. Please make sure no other instance of the server is running!", "Cannot Start", JOptionPane.INFORMATION_MESSAGE);
				System.exit(-1);
			}
		}
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
	
	public Server(){
		//Networking
	    ssmfield = new JTextField("Created By Timothy Lock");
	    ssmfield.addActionListener(this);
	    ssmfield.setSize(600,25);
	    ssmfield.setVisible (false);
	    ssmfield.setEditable (false);
		
		//Start Window Setup
		startWindow = new JFrame ("Volunteer Management System - Server Mode");
		startWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startWindow.setSize(700,500);
		startPanel = new startPanel();
		startPanel.setLayout(null);
		
		
		//Start Panel Setup
		title = new JLabel("Server Mode");
		title.setLocation(235,140);
		title.setSize(600,100);
		title.setFont(new Font("Calibri", Font.PLAIN, 40));
		
		fileLabel = new JLabel("Select CSV File (must follow template):");
		fileLabel.setLocation(20,200);
		fileLabel.setSize(600,100);
		fileLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		startPanel.add(fileLabel);
		
		file = new JTextField ();
		file.setSize(200,30);
		file.setLocation(350,235);
		file.setFont(new Font("Calibri", Font.PLAIN, 20));
		startPanel.add(file);
		
		passwordLabel = new JLabel("Set a Password to startServer:");
		passwordLabel.setLocation(20,250);
		passwordLabel.setSize(600,100);
		passwordLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		startPanel.add(passwordLabel);
		
		password = new JTextField ();
		password.setSize(307,30);
		password.setLocation(350,285);
		password.setFont(new Font("Calibri", Font.PLAIN, 20));
		startPanel.add(password);
		
		startServer = new JButton("Start Server");
		startServer.addActionListener(this);
		startServer.setSize(300,30);
		startServer.setLocation(190, 330);
		startServer.setFont(new Font("Calibri", Font.BOLD, 20));
		startPanel.add(startServer);
		
		version = new JLabel("Version " + strVersion);
		version.setSize(700,20);
		version.setLocation(10,440);
		version.setFont(new Font("Calibri", Font.BOLD, 15));
		startPanel.add(version);
		
		credit = new JLabel("By Timothy Lock");
		credit.setSize(700,20);
		credit.setLocation(575,440);
		credit.setFont(new Font("Calibri", Font.BOLD, 15));
		startPanel.add(credit);
		
		browse = new JButton("Browse");
		browse.addActionListener(this);
		browse.setSize(105,29);
		browse.setLocation(550, 235);
		browse.setFont(new Font("Calibri", Font.BOLD, 20));
		startPanel.add(browse);
		
		startPanel.add(title);
		startWindow.setContentPane(startPanel);
		startWindow.setVisible(true);
	}

	public static void main(String[] args){
		Server theServer = new Server();
	}
	
	public static void pause(int intMS){
		try{
			Thread.sleep(intMS);
		}catch(InterruptedException e){
		} 
	}
	
	public void browseCSV() {
	    chooser.setCurrentDirectory(new File("/home/me/Documents"));
	    int retrival = chooser.showSaveDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION) {
	      try {
	        csvlocation = ("" + chooser.getSelectedFile());
	        file.setText(csvlocation);
	      } catch (Exception ex) {
		    JOptionPane.showMessageDialog(null, "The file could not be selected. Is it read-only? Try putting it in an unprotected directory such as Desktop", "Unknown Error", JOptionPane.INFORMATION_MESSAGE);
	        file.setText("Please Select A File");
	      }
	    }
	  }
}

