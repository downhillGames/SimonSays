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

	public LostScreen() {
		createTextArea("You Lost");
		createTextArea("Score: " + (Main.returnGlobal().level - 1)) ;
    	createTextArea("Play Time: " + convertTime(Main.returnGlobal().gametime) );
    	createTextArea("Round Time: " + convertTime(Main.returnGlobal().roundtime) );
        JButton menuButton = new JButton("Main Menu");
        add(menuButton);
        menuButton.addActionListener(new LostSaveButton());
        JButton quitButton = new JButton("Quit");
        add(quitButton);
        quitButton.addActionListener(new button2());
    }

   
	 public JTextArea createTextArea(String text)
	   {
		   	//JTextArea textArea = new JTextArea(1, text.length());
		 	Border border = BorderFactory.createMatteBorder(0, 600, 20, 600, Main.returnFrame().getBackground());
		 	
			JTextArea textArea = new JTextArea(1, 5);
		   	textArea.setEditable(false);
		   	textArea.append(text);
		   	textArea.setBackground(Main.returnFrame().getBackground()); 
		   	textArea.setBorder(border);
		   	add(textArea);
		   	return textArea;
	   }
   
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