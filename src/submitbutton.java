//import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class submitbutton implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
              //System.out.println("menue");
        	double round_length = (TimeUnit.NANOSECONDS.toMillis(System.nanoTime())  - SimpleEx.time_begin) / 1000.0;
        	
        	double round_time = round_length;
        	
        	//calculates round time
        	int temp = 0;
        	while (round_time > 60) {
        		round_time -= 60;
        		temp +=1;
        	}
        	round_time = Double.valueOf(new String(new DecimalFormat("#").format(temp) + "."+ new DecimalFormat("##").format(round_time )));
        	
        	//calculates play time
        	Main.returnGlobal().gametime_seconds += (round_length);
        	Main.returnGlobal().gametime_minutes = (Main.returnGlobal().gametime_seconds / 60);
        
        	String min = new DecimalFormat("#").format(Main.returnGlobal().gametime_minutes);
        	
        	int minute = Integer.valueOf(min);
        	double round_seconds = Main.returnGlobal().gametime_seconds - (minute * 60);
        	if (round_seconds < 0)
        	{
        		round_seconds = 60 + round_seconds;
        		minute -=1;
        	}
        	String round_sec_string = new DecimalFormat("##").format(round_seconds);
        	double full_time = Double.valueOf(new String(minute + "."+ round_sec_string));
        	Main.returnGlobal().gametime_minutes_and_secs = full_time;
        	System.out.println("Round Time: " + round_time);
        	System.out.println("Total Time: " +Main.returnGlobal().gametime_minutes_and_secs);
        	System.out.println("Total Time Formatted: " + new DecimalFormat("#").format(Main.returnGlobal().gametime_minutes_and_secs) 
        			+ ":" + new DecimalFormat(".##").format(Main.returnGlobal().gametime_minutes_and_secs));
        	Main.returnGame().checkArray();
        }
}