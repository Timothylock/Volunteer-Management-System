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
	mainPanel mainPanel;
	JTextField volunteerIDField;
	JLabel volunteerID;
	JButton signin;
	JLabel nameLabel;
	JLabel signinLabel;
	JLabel signoutLabel;
	JLabel totalHoursLabel;
	JTextField nameTextField;
	JTextField signinTextField;
	JTextField signoutTextField;
	JTextField totalHoursTextField;
	
	//Menu
	JMenuBar menuBar;
	JMenu helpMenu;
	JMenu modeMenu;
	JMenuItem signinMenuItem;
	JMenuItem signoutMenuItem;
	JMenuItem aboutMenuItem;
	
	//Main Window Varibles
	int intMode;
	
	
	/*
	try{
		URI uri = new URI("http://timothylock.co.nr/"); 
	}catch(Exception e){
	}
	Desktop dt = Desktop.getDesktop();*/
	
	public void actionPerformed(ActionEvent evt){
		if (evt.getSource() == connect){
			startWindow.setVisible(false);
			Object mode = JOptionPane.showInputDialog(startPanel,
		            "Are you going to be signing in or out volunteers? This can be changed later",
		            "Select Mode", JOptionPane.QUESTION_MESSAGE,
		            null, new String[] { "Signing In", "Signing Out"},"Signing In");
			try{
				if (mode.equals("Signing In")){
					this.intMode = 1;
				}else if (mode.equals("Signing Out")){
					this.intMode = 2;
				}else{
					JOptionPane.showMessageDialog(startWindow, "An option was not selected. Defaulting to signing in");
					this.intMode = 1;
				}
			}catch(NullPointerException e){
				JOptionPane.showMessageDialog(startWindow, "An option was not selected. Defaulting to signing in");
				this.intMode = 1;
			}
			System.out.println(intMode);
			mainWindow.setVisible(true);
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
		connect.addActionListener(this);
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
		
		//Main Window Setup
		mainWindow = new JFrame ("Volunteer Management System - Connect To Server");
		mainWindow.setSize(1000,600);
		
		mainPanel = new mainPanel();
		mainPanel.setLayout(null);
		
		volunteerIDField = new JTextField ();
		volunteerIDField.setSize(250,60);
		volunteerIDField.setLocation(475,135);
		volunteerIDField.setFont(new Font("Calibri", Font.PLAIN, 40));
		mainPanel.add(volunteerIDField);
		
		volunteerID = new JLabel("Scan/Enter Volunteer ID:");
		volunteerID.setLocation(50,120);
		volunteerID.setSize(600,100);
		volunteerID.setFont(new Font("Calibri", Font.PLAIN, 40));
		mainPanel.add(volunteerID);
		
		signin = new JButton("Sign In");
		signin.addActionListener(this);
		signin.setSize(175,60);
		signin.setLocation(760, 135);
		signin.setFont(new Font("Calibri", Font.PLAIN, 40));
		mainPanel.add(signin);
		
		nameLabel = new JLabel("Name:");
		nameLabel.setLocation(50,250);
		nameLabel.setSize(600,100);
		nameLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
		mainPanel.add(nameLabel);
		
		signinLabel = new JLabel("Sign In Time:");
		signinLabel.setLocation(50,300);
		signinLabel.setSize(600,100);
		signinLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
		mainPanel.add(signinLabel);
		
		signoutLabel = new JLabel("Sign Out Time:");
		signoutLabel.setLocation(50,350);
		signoutLabel.setSize(600,100);
		signoutLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
		mainPanel.add(signoutLabel);
		
		totalHoursLabel = new JLabel("Accumulated Hours:");
		totalHoursLabel.setLocation(50,450);
		totalHoursLabel.setSize(600,100);
		totalHoursLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
		mainPanel.add(totalHoursLabel);
		
		nameTextField = new JTextField ();
		nameTextField.setSize(325,40);
		nameTextField.setLocation(150,280);
		nameTextField.setFont(new Font("Calibri", Font.PLAIN, 30));
		mainPanel.add(nameTextField);
		
		signinTextField = new JTextField ();
		signinTextField.setSize(250,40);
		signinTextField.setLocation(225,330);
		signinTextField.setFont(new Font("Calibri", Font.PLAIN, 30));
		mainPanel.add(signinTextField);
		
		signoutTextField = new JTextField ();
		signoutTextField.setSize(230,40);
		signoutTextField.setLocation(245,380);
		signoutTextField.setFont(new Font("Calibri", Font.PLAIN, 30));
		mainPanel.add(signoutTextField);
		
		totalHoursTextField = new JTextField ();
		totalHoursTextField.setSize(155,40);
		totalHoursTextField.setLocation(320,480);
		totalHoursTextField.setFont(new Font("Calibri", Font.PLAIN, 30));
		mainPanel.add(totalHoursTextField);
		
	    //Menu
	    menuBar = new JMenuBar();
	    mainWindow.setJMenuBar(menuBar);
	    
	    modeMenu = new JMenu("Mode");
	    menuBar.add(modeMenu);
	    
	    signinMenuItem = new JMenuItem("Signing Volunteers In");
	    signinMenuItem.addActionListener(this);
	    modeMenu.add(signinMenuItem);
	    
	    signoutMenuItem = new JMenuItem("Signing Volunteers Out");
	    signoutMenuItem.addActionListener(this);
	    modeMenu.add(signoutMenuItem);
	    
	    helpMenu = new JMenu("Help");
	    menuBar.add(helpMenu);
	    
	    aboutMenuItem = new JMenuItem("About");
	    aboutMenuItem.addActionListener(this);
	    helpMenu.add(aboutMenuItem);
		
		mainWindow.setContentPane(mainPanel);
		
	}

	public static void main(String[] args){
		Client theClient = new Client();
	}
}

