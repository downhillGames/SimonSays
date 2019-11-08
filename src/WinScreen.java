
import javax.swing.JButton;

@SuppressWarnings("serial")
public class WinScreen extends Menu {

	 /*Constructor - Initializes & adds UI elements*/
    @SuppressWarnings("unchecked")
	public WinScreen() {
    	
    	createTextAreaLineBreak("You Won!");
    	Main.returnGlobal().setTimes_won(Main.returnGlobal().getTimes_won() + 1);
    	Main.returnGlobal().setLevel(Main.returnGlobal().getLevel() + 1);
    	System.out.println("CORSI SCORE: " + Main.returnGlobal().getLevel());
    	createTextAreaLineBreak("Score: " + (Main.returnGlobal().getTimes_won())) ;
    	createTextAreaLineBreak("Play Time: " + convertTime(Main.returnGlobal().getGametime()) );
    	createTextAreaLineBreak("Round Time: " + convertTime(Main.returnGlobal().getRoundtime()) );
    	
    	
    	if (Main.returnGlobal().getMode() == 1 ||  Main.returnGlobal().getMode() == 2 || Main.returnGlobal().getMode() == 3)
    	{
    		Main.returnGlobal().getInteractionArray().add(Main.returnGlobal().getRoundtime());
    		Main.returnGlobal().getInteractionArray().add(-1);
    		Main.returnGlobal().getInteractionArray().add(Main.returnGlobal().getGametime());
    		Main.returnGlobal().getInteractionArray().add(-1);
    	}
    	
    	
    	if (Main.returnGlobal().getMode() == 1 || Main.returnGlobal().getMode() == 4)
    	{
    		timerPlay();
    	}
    	else if (Main.returnGlobal().getMode() == 2 || Main.returnGlobal().getMode() == 3)
    	{
    		
            JButton continueButton = new JButton("Continue");
            add(continueButton);
            continueButton.addActionListener(new WinButton());
    	}
    	
    }

  
    
}