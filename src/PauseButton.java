import java.awt.event.*;

public class PauseButton implements ActionListener {
        
		/*Resumes game after being paused upon button press*/
        public void actionPerformed(ActionEvent e) {
        	
	        if (Main.returnGlobal().getMode() == 1 || Main.returnGlobal().getMode() == 2 || Main.returnGlobal().getMode() == 3 )
	        {
	        	 Main.readyGame();
	        }
	        else
	        {
	        	Main.readySim();
	        }
       
        }
}