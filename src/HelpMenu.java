
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class HelpMenu extends Menu {

	/*Creates all the text and buttons needed for the help menu*/
	public HelpMenu() {
    	
    	createTextArea("Instructions:");
    	createTextArea("9 blocks will appear in random places on screen");
    	createTextArea("Each round will present you with a pattern you must repeat either in order or in reversed order by clicking the buttons after the pattern has finished");
    	createTextArea("");
    	createTextArea("");
    	createTextArea("Game Modes:");
    	createTextArea("Classic Mode: 3 second transistion between rounds, amount of blocks to hit increases each round, this is the default mode");
    	createTextArea("Pause Mode: Pause menus between rounds to allow time for break,  amount of blocks to hit increases each round");
    	createTextArea("Modern Mode: Pause menus between rounds to allow time for break, rounds alternate in increasing amount of blocks & speed");
    	 JButton menuButton = new JButton("Back To Main Menu");
         add(menuButton);
         menuButton.addActionListener(new menuButton());
    }
    
	/*Creates a JText Area with input text*/
	public JTextArea createTextArea(String text){
		 	Border border = BorderFactory.createMatteBorder(0, 600, 5 , 600, Main.returnFrame().getBackground());
			JTextArea textArea = new JTextArea(1, 5);
		   	textArea.setEditable(false);
		   	textArea.append(text);
		   	textArea.setBackground(Main.returnFrame().getBackground()); 
		   	textArea.setBorder(border);
		   	add(textArea);
		   	return textArea;
	   }
	 
}