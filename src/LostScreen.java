import javax.swing.JButton;

public class LostScreen extends Menu {

	private static final long serialVersionUID = 9222746381719025010L;

	/*Shows losing screen (no lives left)*/
	@SuppressWarnings("unchecked")
	public LostScreen() {
		createTextAreaLineBreak("You Lost");
		createTextAreaLineBreak("Corsi score: " + (Main.returnGlobal().getLevel() - 1)) ;
		displayStats();
    	
		if (Main.returnGlobal().isReverse_game() == false )
		{	
			Main.returnGlobal().getForwardArray().add(1);	
		}
		else
		{
			Main.returnGlobal().getForwardArray().add(2);
		}
		
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