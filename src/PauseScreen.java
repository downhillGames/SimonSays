import javax.swing.JPanel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class PauseScreen extends JPanel {

   
    public PauseScreen() {
      
        
        JButton Button = new JButton("Paused");
        add(Button);
        Button.addActionListener(new PauseButton());
    }

   

   
}