
import java.awt.*; 
import javax.swing.*; 

public class mainPanel extends JPanel{ 
	ImageIcon background = new ImageIcon(getClass().getResource("mainheader.png"));
	ImageIcon success = new ImageIcon(getClass().getResource("success.png"));
	ImageIcon fail = new ImageIcon(getClass().getResource("fail.png"));
	
	int intSuccess = 0;
	int intFail = 0;
	
	Color c = new Color(238,238,238);


  
  //Methods 
  //overides JPanel 
  public void paintComponent(Graphics g){ 
    Graphics2D g2d = (Graphics2D)g; 
    g2d.setFont(new Font("Calibri", Font.PLAIN, 15));
    g2d.setColor(c);
    g2d.fillRect(0,0,1280,800);
    background.paintIcon(this, g2d, 0, 0);
    if (intSuccess == 1){
    	success.paintIcon(this, g2d, 500, 273);
    }
    if (intFail == 1){
    	fail.paintIcon(this, g2d, 500,  273);
    }
  }
  
  //Constructors 
  public mainPanel(){ 
    super();  
  }   
}