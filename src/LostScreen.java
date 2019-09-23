//import java.awt.EventQueue;
//import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class LostScreen extends JPanel {

	private static final long serialVersionUID = 9222746381719025010L;

	public LostScreen() {
      
        
        JButton quitButton = new JButton("You Lost");
        add(quitButton);
        quitButton.addActionListener(new WinButton());
    }

   

   
}