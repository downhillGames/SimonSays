//import java.awt.*;
import java.awt.event.*;

public class WinButton implements ActionListener {
        
		/*Advances level depending on game mode upon button press*/
        public void actionPerformed(ActionEvent e) {
        		
        	
        	
        	if (Main.returnGlobal().getMode() == 4)
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