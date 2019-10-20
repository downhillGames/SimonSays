import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class LostScreenContinue  extends JPanel   {

	@SuppressWarnings("unchecked")
	/*Shows losing screen (1 life left)*/
	public LostScreenContinue() {
		createTextArea("You lost that round, but you still have one more chance!");
		createTextArea("Score: " + (Main.returnGlobal().getTimes_won() )) ;
    	createTextArea("Play Time: " + convertTime(Main.returnGlobal().getGametime()) );
    	createTextArea("Round Time: " + convertTime(Main.returnGlobal().getRoundtime()) );
    	
    	Main.returnGlobal().getInteractionArray().add(Main.returnGlobal().getRoundtime());
		Main.returnGlobal().getInteractionArray().add(-1);
		Main.returnGlobal().getInteractionArray().add(Main.returnGlobal().getGametime());
		Main.returnGlobal().getInteractionArray().add(-1);
    	
		//different game modes
		if (Main.returnGlobal().getMode() == 1)
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

	/* Goes to game in three seconds (classic mode)*/
	public void  timerPlay()
    {
		
    	Thread t= new Thread (new Runnable() {
        	public void run() {
        		
        		try {
					Thread.sleep(3000);
		        	Main.readyGame();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        	}
        });
       t.start();
    }
	
	/*creates JTextArea with input text*/
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
