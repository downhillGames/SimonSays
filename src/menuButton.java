//import java.awt.*;
import java.awt.event.*;

public class menuButton implements ActionListener {
	
		/*Returns to the main menu of the game (resets everything)*/
        public void actionPerformed(ActionEvent e) {
        	Main.returnToMenu();
        }
}