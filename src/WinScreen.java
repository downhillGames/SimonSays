import javax.swing.JButton;

@SuppressWarnings("serial")
public class WinScreen extends Menu {

	/*Constructor - Initializes & adds UI elements*/
    @SuppressWarnings("unchecked")
	public WinScreen() {
    	
    	createTextAreaLineBreak("You Won!");
    	
    	createTextAreaLineBreak("Corsi score: " + (Main.returnGlobal().getLevel() )) ;
    	System.out.println("CORSI SCORE: " + Main.returnGlobal().getLevel());
    	Main.returnGlobal().setTimes_won(Main.returnGlobal().getTimes_won() + 1);
    	displayStats();

    	if (Main.returnGlobal().getMode() == 3 || Main.returnGlobal().getMode() == 5)
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
    	
    	if (Main.returnGlobal().getMode() == 1 ||  Main.returnGlobal().getMode() == 2 || Main.returnGlobal().getMode() == 3)
    	{
    		Main.returnGlobal().getInteractionArray().add(Main.returnGlobal().getRoundtime());
    		Main.returnGlobal().getInteractionArray().add(-1);
    		Main.returnGlobal().getInteractionArray().add(Main.returnGlobal().getGametime());
    		Main.returnGlobal().getInteractionArray().add(-1);
    	}
    	
    	
    	if (Main.returnGlobal().getMode() == 1 || Main.returnGlobal().getMode() == 4 || Main.returnGlobal().getMode() == 5)
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