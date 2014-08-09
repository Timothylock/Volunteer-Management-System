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
	//Version
	String strVersion = "0.41alpha";
	
	//Networking Components
	JTextField ssmfield;
	SuperSocketMaster ssm;
	Thread ssmthread;
	String strLine;
	String strTemp[];
	String strIP;
	int intAccept = 0;
	int intDebugMode = 0;
	
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
	JLabel credit;
	
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
	JButton manualSignIn;
	JButton volunteerInquiry;
	JLabel successLabel;
	JLabel failureLabel;
	Timer displayIcon;
	
	//Vol Inq Window
	JFrame inqWindow;
	inqPanel inqPanel;
	JLabel inqVolNameLabel;
	JLabel inqVolIDLabel;
	JLabel inqVolEmailLabel;
	JLabel inqVolSignInLabel;
	JLabel inqVolSignOutLabel;
	JLabel inqVolAccumulatedHoursLabel;
	JTextField inqVolName;
	JTextField inqVolID;
	JTextField inqVolEmail;
	JTextField inqVolSignIn;
	JTextField inqVolSignOut;
	JTextField inqVolAccumulatedHours;
	JLabel inqTitle;
	JButton inqSave;
	JButton inqCancel;
	
	//About Window
	JFrame aboutWindow;
	aboutPanel aboutPanel;
	JTextArea license;
	JScrollPane licenseScroll;
	int moveChat = 0;
	JLabel aboutTitle;
	JLabel aboutVersion;
	JLabel aboutDescription;
	JLabel aboutAuthor;
	JLabel aboutDonate;
	
	//Menu
	JMenuBar menuBar;
	JMenu helpMenu;
	JMenu modeMenu;
	JMenu volunteerMenu;
	JMenuItem addHoursMenuItem;
	JMenuItem inquiryMenuItem;
	JMenuItem signinMenuItem;
	JMenuItem signoutMenuItem;
	JMenuItem aboutMenuItem;
	
	//Main Window Varibles
	String strPassword;
	int intMode;
	String strTempString;
	String strEmail;
	
	
	/*
	try{
		URI uri = new URI("http://timothylock.co.nr/"); 
	}catch(Exception e){
	}
	Desktop dt = Desktop.getDesktop();*/
	
	public void actionPerformed(ActionEvent evt){
		if (evt.getSource() == connect){
			if (intDebugMode != 1){
				try{
					strIP = InetAddress.getLocalHost().getHostAddress();
				}catch(Exception e){
				}	
				
				try{
			        ssm = new SuperSocketMaster(ssmfield, ip.getText() ,9001);
			        ssmthread = new Thread(ssm);
			        ssmthread.start();
			        //Login to server
			        pause(2000);
			        strPassword = password.getText();
			        ssm.sendText(strIP + ",AUTHENTICATE," + strPassword); 
			        startWindow.setVisible(false);
			      }catch(Exception e){
			        JOptionPane.showMessageDialog(null, "Could not reach server. Please check your connection", "No Connection", JOptionPane.INFORMATION_MESSAGE);
			        System.exit(-1);
			      }
				pause (500);
				
			}
		    
		    if (intAccept==0){
		    	JOptionPane.showMessageDialog(null, "The server refused your connection. Reason: Bad password", "Bad Password", JOptionPane.INFORMATION_MESSAGE);				
		    	System.exit(0);
		    }
			Object mode = JOptionPane.showInputDialog(startPanel,
		            "Are you going to be signing in or out volunteers? This can be changed later",
		            "Select Mode", JOptionPane.QUESTION_MESSAGE,
		            null, new String[] { "Signing In", "Signing Out"},"Signing In");
			try{
				if (mode.equals("Signing In")){
					this.intMode = 1;
			    	signin.setText("Sign In");
			    	manualSignIn.setText("Manual Sign In");
				}else if (mode.equals("Signing Out")){
					this.intMode = 2;
			    	signin.setText("Sign Out");
			    	manualSignIn.setText("Manual Sign Out");
				}else{
					JOptionPane.showMessageDialog(startWindow, "An option was not selected. Defaulting to signing in");
					this.intMode = 1;
				}
			}catch(NullPointerException e){
				JOptionPane.showMessageDialog(startWindow, "An option was not selected. Defaulting to signing in");
				this.intMode = 1;
			}
			mainWindow.setVisible(true);
		}else if(evt.getSource() == displayIcon){
			mainPanel.intSuccess = 0;
			mainPanel.intFail = 0;
			successLabel.setVisible(false);
			failureLabel.setVisible(false);
			mainPanel.repaint();
			displayIcon.stop();
		}else if(evt.getSource() == ssmfield){  //Network Messages
			strLine = ssmfield.getText();
			if (strLine != null){ //Split message
		        strTemp = strLine.split(",");
			}else{
				strLine = "IGNORE,FILLER,LOL";
				strTemp = strLine.split(",");
			}
			if (strTemp[0].equals(strIP)){ //Make sure that its for this client
				if (strTemp[1].equals("ACCEPT")){
					intAccept = 1;
				}else if (strTemp[1].equals("REJECT")){
					intAccept = 0;
				}else if(strTemp[1].equals("SUCCESS")){
					nameTextField.setText(strTemp[2]);
					signinTextField.setText(strTemp[3]);
					signoutTextField.setText(strTemp[4]);
					mainPanel.intSuccess = 1;
					mainPanel.repaint();
					successLabel.setVisible(true);
					displayIcon.start();
				}else if(strTemp[1].equals("FAIL")){
					nameTextField.setText(strTemp[2]);
					signinTextField.setText("");
					signoutTextField.setText("");
					mainPanel.intFail = 1;
					failureLabel.setVisible(true);
					mainPanel.repaint();
					displayIcon.start();
				}else if(strTemp[1].equals("EXIST")){
					//WINDOW
				}else if(strTemp[1].equals("NOEXIST")){
			    	JOptionPane.showMessageDialog(null, "The volunteer email/barcode does not exist. Please check your spelling", "Does Not Exist", JOptionPane.INFORMATION_MESSAGE);
				}else if(strTemp[1].equals("VOLDATA")){
					inqVolID.setText(strTemp[2]);
					inqVolName.setText(strTemp[3]);
					inqVolEmail.setText(strTemp[4]);
					inqVolSignIn.setText(strTemp[5]);
					inqVolSignOut.setText (strTemp[6]);
					inqWindow.setVisible(true);
				}
					
			}
		}
		if(evt.getSource() == inquiryMenuItem){
			strTempString = JOptionPane.showInputDialog ( "Enter the email OR the barcode of the volunteer" );
			if(intDebugMode != 1){
				ssm.sendText(strIP + "," + strPassword + ",INQUIRY," + strTempString);
			}
			volunteerIDField.requestFocusInWindow();
		}else if(evt.getSource() == manualSignIn){
			strEmail = JOptionPane.showInputDialog ( "Enter the email of the volunteer" );
			if (intMode == 1){
				ssm.sendText(strIP + "," + strPassword + ",MANUALSIGNIN," + strEmail);
			}else if(intMode == 2){
				ssm.sendText(strIP + "," + strPassword + ",MANUALSIGNOUT," + strEmail);
			}
			volunteerIDField.requestFocusInWindow();
		}else if(evt.getSource () == signin){
			if (intMode == 1){
				ssm.sendText(strIP + "," + strPassword + ",SIGNIN," + volunteerIDField.getText());
				volunteerIDField.setText("");
			}else if(intMode == 2){
				ssm.sendText(strIP + "," + strPassword + ",SIGNOUT," + volunteerIDField.getText());
			}
		}else if(evt.getSource() == signinMenuItem){
			intMode = 1;
	    	signin.setText("Sign In");
	    	manualSignIn.setText("Manual Sign In");
	    	JOptionPane.showMessageDialog(null, "Mode switched to SIGN IN. You can now sign in volunteers", "Mode Switched", JOptionPane.INFORMATION_MESSAGE);
		}else if(evt.getSource() == signoutMenuItem){
			intMode = 2;
	    	signin.setText("Sign Out");
	    	manualSignIn.setText("Manual Sign Out");
	    	JOptionPane.showMessageDialog(null, "Mode switched to SIGN OUT. You can now sign out volunteers", "Mode Switched", JOptionPane.INFORMATION_MESSAGE);
		}else if(evt.getSource() == inqSave){
			ssm.sendText(strIP + "," + strPassword + ",UPDATE," + inqVolID.getText() + "," + inqVolName.getText() + "," +  inqVolEmail.getText() + "," + inqVolSignIn.getText() + "," +  inqVolSignOut.getText() + "," + inqVolAccumulatedHours.getText());
			inqWindow.setVisible(false);
			JOptionPane.showMessageDialog(null, "Volunteer Data Updated!", "Data Saved", JOptionPane.INFORMATION_MESSAGE);
		}else if(evt.getSource() == inqCancel){
			inqWindow.setVisible(false);
		}else if(evt.getSource() == aboutMenuItem){
			aboutWindow.setVisible(true);
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
		//Networking
	    ssmfield = new JTextField("Created By Timothy Lock");
	    ssmfield.addActionListener(this);
	    ssmfield.setSize(600,25);
	    ssmfield.setVisible (false);
	    ssmfield.setEditable (false);
		
		//Start Window Setup
		startWindow = new JFrame ("Volunteer Management System - Connect To Server");
		startWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		startPanel.add(title);
		startWindow.setContentPane(startPanel);
		startWindow.setVisible(true);
		
		//Main Window Setup
		mainWindow = new JFrame ("Volunteer Management System - Connect To Server");
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(1000,630);
		
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
		nameTextField.setEditable(false);
		nameTextField.setSize(325,40);
		nameTextField.setLocation(150,280);
		nameTextField.setFont(new Font("Calibri", Font.PLAIN, 30));
		mainPanel.add(nameTextField);
		
		signinTextField = new JTextField ();
		signinTextField.setEditable(false);
		signinTextField.setSize(250,40);
		signinTextField.setLocation(225,330);
		signinTextField.setFont(new Font("Calibri", Font.PLAIN, 30));
		mainPanel.add(signinTextField);
		
		signoutTextField = new JTextField ();
		signoutTextField.setEditable(false);
		signoutTextField.setSize(230,40);
		signoutTextField.setLocation(245,380);
		signoutTextField.setFont(new Font("Calibri", Font.PLAIN, 30));
		mainPanel.add(signoutTextField);
		
		totalHoursTextField = new JTextField ();
		totalHoursTextField.setEditable(false);
		totalHoursTextField.setSize(155,40);
		totalHoursTextField.setLocation(320,480);
		totalHoursTextField.setFont(new Font("Calibri", Font.PLAIN, 30));
		mainPanel.add(totalHoursTextField);
		
		manualSignIn = new JButton("Manual Sign In");
		manualSignIn.addActionListener(this);
		manualSignIn.setSize(210,60);
		manualSignIn.setLocation(725, 465);
		manualSignIn.setFont(new Font("Calibri", Font.PLAIN, 25));
		mainPanel.add(manualSignIn);
		
		successLabel = new JLabel("Signed In");
		successLabel.setForeground(Color.green);
		successLabel.setLocation(700,295);
		successLabel.setSize(600,100);
		successLabel.setFont(new Font("Calibri", Font.PLAIN, 50));
		successLabel.setVisible(false);
		mainPanel.add(successLabel);
		
		failureLabel = new JLabel("Fail. Check Name");
		failureLabel.setForeground(Color.red);
		failureLabel.setLocation(660,295);
		failureLabel.setSize(600,100);
		failureLabel.setFont(new Font("Calibri", Font.PLAIN, 40));
		failureLabel.setVisible(false);
		mainPanel.add(failureLabel);
		
		mainWindow.getRootPane().setDefaultButton(signin);
		volunteerIDField.requestFocusInWindow();
		
		displayIcon = new Timer(2000, this);
		
		//Inquiry Window
		inqWindow = new JFrame ("Edit Volunteer Data");
		inqWindow.setSize(500,400);
		
		
		inqPanel = new inqPanel();
		inqPanel.setLayout(null);
		inqWindow.setContentPane(inqPanel);
		
		inqVolIDLabel = new JLabel("Volunteer ID:");
		inqVolIDLabel.setLocation(20,50);
		inqVolIDLabel.setSize(600,50);
		inqVolIDLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		inqPanel.add(inqVolIDLabel);
		
		inqVolNameLabel = new JLabel("Volunteer Name:");
		inqVolNameLabel.setLocation(20,90);
		inqVolNameLabel.setSize(600,50);
		inqVolNameLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		inqPanel.add(inqVolNameLabel);
		
		inqVolEmailLabel = new JLabel("Volunteer Email:");
		inqVolEmailLabel.setLocation(20,130);
		inqVolEmailLabel.setSize(600,50);
		inqVolEmailLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		inqPanel.add(inqVolEmailLabel);
		
		inqVolSignInLabel = new JLabel("Sign In Time:");
		inqVolSignInLabel.setLocation(20,170);
		inqVolSignInLabel.setSize(600,50);
		inqVolSignInLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		inqPanel.add(inqVolSignInLabel);
		
		inqVolSignOutLabel = new JLabel("Sign Out Time:");
		inqVolSignOutLabel.setLocation(20,210);
		inqVolSignOutLabel.setSize(600,50);
		inqVolSignOutLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		inqPanel.add(inqVolSignOutLabel);
		
		inqVolAccumulatedHoursLabel = new JLabel("Accumulated Hours:");
		inqVolAccumulatedHoursLabel.setLocation(20,250);
		inqVolAccumulatedHoursLabel.setSize(600,50);
		inqVolAccumulatedHoursLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		inqPanel.add(inqVolAccumulatedHoursLabel);
		
		inqVolID = new JTextField ();
		inqVolID.setEditable(true);
		inqVolID.setSize(250,25);
		inqVolID.setLocation(205,60);
		inqVolID.setFont(new Font("Calibri", Font.PLAIN, 20));
		inqPanel.add(inqVolID);
		
		inqVolName = new JTextField ();
		inqVolName.setEditable(true);
		inqVolName.setSize(250,25);
		inqVolName.setLocation(205,100);
		inqVolName.setFont(new Font("Calibri", Font.PLAIN, 20));
		inqPanel.add(inqVolName);
		
		inqVolEmail = new JTextField ();
		inqVolEmail.setEditable(true);
		inqVolEmail.setSize(250,25);
		inqVolEmail.setLocation(205,140);
		inqVolEmail.setFont(new Font("Calibri", Font.PLAIN, 20));
		inqPanel.add(inqVolEmail);
		
		inqVolSignIn = new JTextField ();
		inqVolSignIn.setEditable(true);
		inqVolSignIn.setSize(250,25);
		inqVolSignIn.setLocation(205,180);
		inqVolSignIn.setFont(new Font("Calibri", Font.PLAIN, 20));
		inqPanel.add(inqVolSignIn);
		
		inqVolSignOut = new JTextField ();
		inqVolSignOut.setEditable(true);
		inqVolSignOut.setSize(250,25);
		inqVolSignOut.setLocation(205,220);
		inqVolSignOut.setFont(new Font("Calibri", Font.PLAIN, 20));
		inqPanel.add(inqVolSignOut);
		
		inqVolAccumulatedHours = new JTextField ();
		inqVolAccumulatedHours.setEditable(true);
		inqVolAccumulatedHours.setSize(250,25);
		inqVolAccumulatedHours.setLocation(205,260);
		inqVolAccumulatedHours.setFont(new Font("Calibri", Font.PLAIN, 20));
		inqPanel.add(inqVolAccumulatedHours);
		
		inqTitle = new JLabel("Edit Volunteer Data");
		inqTitle.setLocation(20,5);
		inqTitle.setSize(600,50);
		inqTitle.setFont(new Font("Calibri", Font.ITALIC, 35));
		inqPanel.add(inqTitle);
		
		inqSave = new JButton("Update Info");
		inqSave.addActionListener(this);
		inqSave.setSize(200,40);
		inqSave.setLocation(254, 300);
		inqSave.setFont(new Font("Calibri", Font.PLAIN, 20));
		inqPanel.add(inqSave);
		
		inqCancel = new JButton("Cancel");
		inqCancel.addActionListener(this);
		inqCancel.setSize(100,40);
		inqCancel.setLocation(20, 300);
		inqCancel.setFont(new Font("Calibri", Font.PLAIN, 20));
		inqPanel.add(inqCancel);
		
		//AboutWindow
		aboutWindow = new JFrame ("About");
		aboutWindow.setSize(400,500);
		
		aboutPanel = new aboutPanel();
		aboutPanel.setLayout(null);
		aboutWindow.setContentPane(aboutPanel);
		
		aboutTitle = new JLabel("Volunteer Management System");
		aboutTitle.setLocation(20,70);
		aboutTitle.setSize(600,50);
		aboutTitle.setFont(new Font("Calibri", Font.BOLD, 20));
		aboutPanel.add(aboutTitle);
		
		aboutVersion = new JLabel("Version: " + strVersion);
		aboutVersion.setLocation(20,100);
		aboutVersion.setSize(600,50);
		aboutVersion.setFont(new Font("Calibri", Font.PLAIN, 20));
		aboutPanel.add(aboutVersion);
		
		aboutAuthor = new JLabel("Written by Timothy Lock");
		aboutAuthor.setLocation(20,130);
		aboutAuthor.setSize(600,50);
		aboutAuthor.setFont(new Font("Calibri", Font.PLAIN, 20));
		aboutPanel.add(aboutAuthor);
		
		aboutDescription = new JLabel("An easy way to keep track of volunteer hours via barcode!");
		aboutDescription.setLocation(20,160);
		aboutDescription.setSize(600,50);
		aboutDescription.setFont(new Font("Calibri", Font.PLAIN, 15));
		aboutPanel.add(aboutDescription);
		
		aboutDonate = new JLabel("Over 100 hours spent. Please donate if you can! :)");
		aboutDonate.setLocation(20,210);
		aboutDonate.setSize(600,50);
		aboutDonate.setFont(new Font("Calibri", Font.BOLD, 17));
		aboutPanel.add(aboutDonate);
		
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
	    
	    volunteerMenu = new JMenu("Volunteer Inquiry");
	    menuBar.add(volunteerMenu);
	    
	    addHoursMenuItem = new JMenuItem("Add/Remove Hours");
	    addHoursMenuItem.addActionListener(this);
	    //volunteerMenu.add(addHoursMenuItem);     //This function got depreciated into "Edit Volunteer Data"
	    
	    inquiryMenuItem = new JMenuItem("View/Edit Volunteer Information");
	    inquiryMenuItem.addActionListener(this);
	    volunteerMenu.add(inquiryMenuItem);
	    
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
	
	public static void pause(int intMS){
		try{
			Thread.sleep(intMS);
		}catch(InterruptedException e){
		} 
	}
}

