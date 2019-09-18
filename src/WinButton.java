//import java.awt.*;
import java.awt.event.*;

public class WinButton implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
              //System.out.println("menue");
              Main.returnGlobal().level +=1;
              Main.readyGame();
        }
}