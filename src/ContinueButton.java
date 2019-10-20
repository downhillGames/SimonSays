//import java.awt.*;
import java.awt.event.*;

public class ContinueButton implements ActionListener {
        
		/*Button that continues the game to the next round*/
        public void actionPerformed(ActionEvent e) {
        	Main.readyGame();
        }
}