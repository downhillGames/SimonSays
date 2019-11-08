import javax.swing.JButton;

public class LostScreen extends Menu {

	private static final long serialVersionUID = 9222746381719025010L;

	@SuppressWarnings("unchecked")
	 /*Shows losing screen (no lives left)*/
	public LostScreen() {
		createTextAreaLineBreak("You Lost");
		createTextAreaLineBreak("Score: " + (Main.returnGlobal().getTimes_won() )) ;
    	createTextAreaLineBreak("Play Time: " + convertTime(Main.returnGlobal().getGametime()) );
    	createTextAreaLineBreak("Round Time: " + convertTime(Main.returnGlobal().getRoundtime()) );
    	
    	
		
		if (Main.returnGlobal().getMode() == 1 ||  Main.returnGlobal().getMode() == 2 || Main.returnGlobal().getMode() == 3)
		{
			Main.returnGlobal().getInteractionArray().add(Main.returnGlobal().getRoundtime());
			Main.returnGlobal().getInteractionArray().add(-1);
			Main.returnGlobal().getInteractionArray().add(Main.returnGlobal().getGametime());
			Main.returnGlobal().getInteractionArray().add(-1);
		}
		
        JButton menuButton = new JButton("Main Menu");
        add(menuButton);
        menuButton.addActionListener(new menuButton());
        JButton quitButton = new JButton("Quit");
        add(quitButton);
        quitButton.addActionListener(new QuitBtn());
    }

}