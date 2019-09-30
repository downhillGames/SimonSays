import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.*;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeUnit;


@SuppressWarnings("serial")
public class SimpleEx extends JPanel
{	 
  static Map map;            
	public SimpleEx(Map mp) {
        map = mp;
        Init();
    }
	
	public int buttns_pressd[] = new int [10];
	public int buttns_pressd_reversed[] = new int [10];
	public int computer_pressed[] = new int [10]; 
	public int button_pressed_index = 0;
	public int computer_pressed_index = 0;
	static JButton done_button;
	long time_begin = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
	long clock_timer = time_begin;
	
	static int map_from_obj[] = new int [27]; 

	public void reverseArray()
	  {
		int temp_index = 0;
		for (int i = 0 ;  i < button_pressed_index ; i++)
		{
			buttns_pressd_reversed[i] = buttns_pressd[(button_pressed_index -  1) - i];
		}
		
		for (int i = 0 ;  i < 10 ; i++)
		{
			System.out.println(buttns_pressd_reversed[i]);
		}
		
		
	  }	
		
	
  public void checkArray()
  {
    
    boolean flag = false;
    for (int i = 0; i < 10 ; i++)
    {
    	if (Main.returnGlobal().reverse_game)
    	{
    		 if (buttns_pressd_reversed[i] != computer_pressed[i])
    	      {
    	        flag = true;
    	      }
    	}
    	else
    	{
    		  if (buttns_pressd[i] != computer_pressed[i])
    	      {
    	        flag = true;
    	      }
    	}
    	
    }
    if (flag == false)
    {
        Main.PlayWin();
    }
    else{
        Main.PlayLose();
    }
  }

  
  public static void clickRandomBlock(JButton[] inArray)
  {
	  int randomBlock = ThreadLocalRandom.current().nextInt(1, 8 + 1);
	  inArray[randomBlock].setEnabled(true);
	  inArray[randomBlock].doClick();
  }
  
  public void hit(JButton[] inArray)
	{
	  		int buffer = 0;
		  	if  (Main.returnGlobal().first_hit)
		  	{
		  		buffer = 500;
		  	}
		  	else
		  	{
		  		buffer = 0;
		  	}
	  		int hits = 0;
	  		while (hits <  Main.returnGlobal().level) {
			  if (TimeUnit.NANOSECONDS.toMillis(System.nanoTime()) - clock_timer >= (Main.returnGlobal().speed + buffer))
			  {
				  Main.returnGlobal().computer_playing = true;
				  int randomBlock = ThreadLocalRandom.current().nextInt(1, 8 + 1);
				  //inArray[randomBlock].setBackground(Color.WHITE);
				  inArray[randomBlock].setEnabled(true);
				  java.awt.Toolkit.getDefaultToolkit().beep();
				  inArray[randomBlock].doClick(1000);
				  inArray[randomBlock].setEnabled(false);
				  clock_timer = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
				  Main.returnGlobal().computer_playing = false;
				  Main.returnGlobal().first_hit = false;
				  hits++;
			  }
	  		}
		  
		  }
  
  
		  

	
	public void activateAllButtons(JButton[] inArray)
	{
		for (int i = 1; i < inArray.length ; i++)
		{
			inArray[i].setEnabled(true);
		}
	}

  public void createDoneButton() { 
	  int hit = 0;
		while (hit <  1) {
		  if (TimeUnit.NANOSECONDS.toMillis(System.nanoTime()) - clock_timer >= 1000)
		  {
  		done_button = new JButton("Submit");
  	    done_button.setSize(100, 30);
  	    done_button.setLocation(550, 625);
  	    done_button.addActionListener(new submitbutton());
  	    add(done_button);
  	    repaint();
		  	hit++;
		  }
		  
		}
  }

    public void displayGUI()
    {

    	JButton outArray[] = new JButton[10];
        

   
        
        for (int i = 1; i < 10; i++)
        {
        	   JButton button = new JButton("");
               button.setSize(50, 50);
               button.setLocation(map_from_obj[(i *3) - 1], map_from_obj[(i * 3) - 2]);
               button.setBackground(Color.BLUE);
               outArray[i] = button;
               
               button.addActionListener(new gameButton(map_from_obj[(i *3) - 3]));
               button.setEnabled(false);
               add(button);
               
               
        }
        
        clock_timer = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
        Thread t= new Thread (new Runnable() {
        	public void run() {
        		try 
        		{	
        		hit(outArray);
  
        	 }
        	finally
        	{
        		System.out.println("hit");
        		createDoneButton();
        		activateAllButtons(outArray);
        	}
        		
        	}
        });
       t.start();
      
     
    }

    public static void Init()
    {
       
        Map newmap = map;
		newmap.createMap(); 
		map_from_obj = newmap.getMap_array();

    }
    
    // getters and setters
	public int[] getButtns_pressd() {
		return buttns_pressd;
	}
	public void setButtns_pressd(int buttns_pressd[]) {
		this.buttns_pressd = buttns_pressd;
	}
	public int getButton_pressed_index() {
		return button_pressed_index;
	}
	public void setButton_pressed_index(int button_pressed_index) {
		this.button_pressed_index = button_pressed_index;
	}
}