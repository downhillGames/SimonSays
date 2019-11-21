//import java.awt.*;
import java.awt.event.*;

public class ContinueButton implements ActionListener {
        
	
		
		/*Button that continues the game to the next round*/
        public void actionPerformed(ActionEvent e) {
        	
        	
        	if (Main.returnGlobal().getMode() == 4 || Main.returnGlobal().getMode() == 5)
    		{
        		Main.newSim();
        		Main.readySim();
    		}
        	else
        	{
        		Main.readyGame();
        	}
        }
}