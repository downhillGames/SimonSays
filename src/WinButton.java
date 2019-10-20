//import java.awt.*;
import java.awt.event.*;

public class WinButton implements ActionListener {
        
		/*Advances level depending on game mode upon button press*/
        public void actionPerformed(ActionEvent e) {
        		
        	if (Main.returnGlobal().getMode() == 3)
        	{
        		//if level is even
        		if (Main.returnGlobal().getTimes_won() % 2 == 0)
        		{
        			if 	(Main.returnGlobal().getSpeed() > 100 )
        			{
        				Main.returnGlobal().setSpeed(Main.returnGlobal().getSpeed() - 100);
        			}
        		}
        		else
        		{
        			Main.returnGlobal().setLevel(Main.returnGlobal().getLevel() + 1);	
        		}
        	}
        	else
        	{
        		Main.returnGlobal().setLevel(Main.returnGlobal().getLevel() + 1);	
        	}
        	Main.readyGame();
        }
}