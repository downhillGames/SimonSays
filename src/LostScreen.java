//import java.awt.EventQueue;
//import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class LostScreen extends JPanel {

	private static final long serialVersionUID = 9222746381719025010L;

	@SuppressWarnings("unchecked")
	 /*Shows losing screen (no lives left)*/
	public LostScreen() {
		createTextArea("You Lost");
		createTextArea("Score: " + (Main.returnGlobal().getTimes_won() )) ;
    	createTextArea("Play Time: " + convertTime(Main.returnGlobal().getGametime()) );
    	createTextArea("Round Time: " + convertTime(Main.returnGlobal().getRoundtime()) );
    	
    	Main.returnGlobal().getInteractionArray().add(Main.returnGlobal().getRoundtime());
		Main.returnGlobal().getInteractionArray().add(-1);
		Main.returnGlobal().getInteractionArray().add(Main.returnGlobal().getGametime());
		Main.returnGlobal().getInteractionArray().add(-1);
		
        JButton menuButton = new JButton("Main Menu");
        add(menuButton);
        menuButton.addActionListener(new menuButton());
        JButton quitButton = new JButton("Quit");
        add(quitButton);
        quitButton.addActionListener(new QuitBtn());
    }

	/*Creates JTextArea from input text*/
	 public JTextArea createTextArea(String text)
	   {
		 	Border border = BorderFactory.createMatteBorder(0, 600, 20, 600, Main.returnFrame().getBackground());
		 	
			JTextArea textArea = new JTextArea(1, 5);
		   	textArea.setEditable(false);
		   	textArea.append(text);
		   	textArea.setBackground(Main.returnFrame().getBackground()); 
		   	textArea.setBorder(border);
		   	add(textArea);
		   	return textArea;
	   }
   
	 /*Converts a double time in seconds to a cleaner MM:SS format*/
	 public static String convertTime(double time_in)
     {
  	 	int minutes = 0;
      	while (time_in > 60)
      	{
      		minutes += 1;
      		time_in -= 60;
      	}
      	String second_formatted = new DecimalFormat("##").format(time_in);
  	   String outString = minutes + ":" + second_formatted;
  	   return outString;
     }
}