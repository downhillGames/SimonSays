//import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class submitbutton implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
              //System.out.println("menue");
        	double round_length = (TimeUnit.NANOSECONDS.toMillis(System.nanoTime())  - Main.returnGame().time_begin) / 1000.0;
        	Main.returnGlobal().gametime += round_length;	
        	System.out.println("Round Time Formatted: " + convertTime(round_length));
        	System.out.println("Total Time Formatted: " + convertTime(Main.returnGlobal().gametime));
        	Main.returnGame().checkArray();
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