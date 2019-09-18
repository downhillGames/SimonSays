import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class WinScreen extends JPanel {

   
    public WinScreen() {
      
        
        JButton quitButton = new JButton("You won");
        add(quitButton);
        quitButton.addActionListener(new WinButton());
    }

   

   
}