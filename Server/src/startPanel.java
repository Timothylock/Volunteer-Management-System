import java.awt.*; 
import javax.swing.*; 




public class startPanel extends JPanel{ 
	ImageIcon background = new ImageIcon(getClass().getResource("startheader.png"));


  //Methods 
  //overides JPanel 
  public void paintComponent(Graphics g){ 
    Graphics2D g2d = (Graphics2D)g; 
    g2d.setFont(new Font("Calibri", Font.PLAIN, 15));
    background.paintIcon(this, g2d, 0, 0);
  }
  
  //Constructors 
  public startPanel(){ 
    super();  
  }   
}