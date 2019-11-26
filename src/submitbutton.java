import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class submitbutton implements ActionListener {
        
	/*Gets round time and adds it to the total game time*/
	public void actionPerformed(ActionEvent e) {
        	
        	
        	
        	double round_length = (TimeUnit.NANOSECONDS.toMillis(System.nanoTime())  - Main.returnGame().time_begin) / 1000.0;
       
        	if (Main.returnGlobal().getMode() == 4 ||  Main.returnGlobal().getMode() == 5)
        	{
        		round_length = (TimeUnit.NANOSECONDS.toMillis(System.nanoTime())  - Main.returnSim().time_begin) / 1000.0;
        	}
        	
        	Main.returnGlobal().setGametime(Main.returnGlobal().getGametime() + round_length);	
        	Main.returnGlobal().setRoundtime(round_length);	
        	System.out.println("Round Time Formatted: " + convertTime(round_length));
        	System.out.println("this game Time Formatted: " + convertTime(Main.returnGlobal().getGametime()));
        	System.out.println("Total Time Formatted: " + convertTime(Main.returnGlobal().getTotal_gametime() + Main.returnGlobal().getGametime()));
        	
        	if (Main.returnGlobal().getMode() != 4 && Main.returnGlobal().getMode() != 5)
        	{
        		if (Main.global.isReverse_game())
            	{
            		Main.returnGame().reverseArray();
            		Main.returnGame().checkArray();
            	}
            	else
            	{
            		Main.returnGame().checkArray();
            	}
        	}
        	else
        	{
        		if (Main.global.isReverse_game())
            	{
            		Main.returnSim().reverseArray();
            		Main.returnSim().checkArray();
            	}
            	else
            	{
            		Main.returnSim().checkArray();
            	}
        	}
        	
        		
        	
        	
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