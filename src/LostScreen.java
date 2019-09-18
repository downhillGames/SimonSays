import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class LostScreen extends JPanel {

   
    public LostScreen() {
      
        
        JButton quitButton = new JButton("You Lost");
        add(quitButton);
        quitButton.addActionListener(new WinButton());
    }

   

   
}