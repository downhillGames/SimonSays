 
import javax.swing.JButton;

@SuppressWarnings("serial")
public class PauseScreen extends Menu {
    public PauseScreen() {
      
        /*Creates simple pause menu with un-pause button*/
        JButton Button = new JButton("Paused");
        add(Button);
        Button.addActionListener(new PauseButton());
    }

}