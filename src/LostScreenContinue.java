import javax.swing.JButton;

@SuppressWarnings("serial")
public class LostScreenContinue extends Menu  {

	@SuppressWarnings("unchecked")
	/*Shows losing screen (1 life left)*/
	public LostScreenContinue() {
		createTextAreaLineBreak("You lost that round, but you still have one more chance!");
		displayStats();
    	
    	if (Main.returnGlobal().getMode() != 4)
    	{
    		Main.returnGlobal().getInteractionArray().add(Main.returnGlobal().getRoundtime());
    		Main.returnGlobal().getInteractionArray().add(-1);
    		Main.returnGlobal().getInteractionArray().add(Main.returnGlobal().getGametime());
    		Main.returnGlobal().getInteractionArray().add(-1);
    	}
    	
    	
    	
		//different game modes
		if (Main.returnGlobal().getMode() == 1 || Main.returnGlobal().getMode() ==  4)
    	{
    		timerPlay();
    	}
    	else if (Main.returnGlobal().getMode() == 2)
    	{
			
	        JButton continueButton = new JButton("Continue");
	        add(continueButton);
	        continueButton.addActionListener(new ContinueButton());
	        JButton quitButton = new JButton("Quit");
	        add(quitButton);
	        quitButton.addActionListener(new QuitBtn());
    	}
    	else if (Main.returnGlobal().getMode() == 3)
    	{
			
	        JButton continueButton = new JButton("Continue");
	        add(continueButton);
	        continueButton.addActionListener(new ContinueButton());
	        JButton quitButton = new JButton("Quit");
	        add(quitButton);
	        quitButton.addActionListener(new QuitBtn());
    	}
    }

	

}
