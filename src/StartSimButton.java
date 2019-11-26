import java.awt.event.*;

public class StartSimButton implements ActionListener {
        
		/*Readies the game for the sim mode and goes it*/
        public void actionPerformed(ActionEvent e) {
        	Main.readySim();
        }
}