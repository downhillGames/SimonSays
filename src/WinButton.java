//import java.awt.*;
import java.awt.event.*;

public class WinButton implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
              //System.out.println("menue");
        	Main.returnGlobal().times_won  +=1;
        	if (Main.returnGlobal().times_won % 2 == 0)
        	{
        		if (Main.returnGlobal().speed > 100)
        		{
        			Main.returnGlobal().speed -=100;
        		}
        			
        	}
        	else
        	{
        		Main.returnGlobal().level +=1;	
        	}
              
              Main.readyGame();
        }
}