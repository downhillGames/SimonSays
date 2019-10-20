import java.awt.event.*;

public class PauseButton implements ActionListener {
        
		/*Resumes game after being paused upon button press*/
        public void actionPerformed(ActionEvent e) {
        Main.readyGame();
        }
}