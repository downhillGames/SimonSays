import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatsButton implements ActionListener {
        
		/*Goes to the High score menu upon button press*/
        public void actionPerformed(ActionEvent e) {
        	Main.PlayStatsMenu();
        }
}