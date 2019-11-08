import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JPanel {

	
	/*Creates JTextField with input text, length, border*/
	public JTextField createInputArea (String text, int length, Border border, JTextField field ) {
		createTextArea(text);
    	field = new JTextField(length);
    	field.setBorder(border);
    	
    	add(field);
    	return field;
	}
	
	/*Creates JTextArea from input text with a line break*/
	 public JTextArea createTextAreaLineBreak(String text)
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
	 
	 
	 /*Creates JTextArea from input text*/
	 public JTextArea createTextArea(String text)
	   {
		 	
			JTextArea textArea = new JTextArea(1, 5);
		   	textArea.setEditable(false);
		   	textArea.append(text);
		   	textArea.setBackground(Main.returnFrame().getBackground()); 
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
	
	 
	 
	  /* Goes to game in three seconds (classic mode)*/
	    public void  timerPlay()
	    {
	    	Thread t= new Thread (new Runnable() {
	        	public void run() {
	        		
	        		try {
						Thread.sleep(3000);
			        		
			        	if (Main.returnGlobal().getMode() == 4)
						{
							Main.newSim();
							Main.readySim();
						}
						else
						{
							
							Main.readyGame();
						}
			        	
			        	
			        	
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		
	        	}
	        });
	       t.start();
	    }
}
