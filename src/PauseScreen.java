import javax.swing.JPanel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class PauseScreen extends JPanel {
    public PauseScreen() {
      
        /*Creates simple pause menu with un-pause button*/
        JButton Button = new JButton("Paused");
        add(Button);
        Button.addActionListener(new PauseButton());
    }

}