import java.awt.Color;

import javax.swing.*;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeUnit;


@SuppressWarnings("serial")
public class SimGame extends JPanel
{	 
	
	/*initialize variables*/
	private int buttns_pressd[] = new int [10];
	private int buttns_pressd_reversed[] = new int [10];
	private int computer_pressed[] = new int [10]; 
	private int button_pressed_index = 0;
	private int computer_pressed_index = 0;
	static JButton done_button;
	private int times_hit = 0;
	long time_begin ;
	long clock_timer = time_begin;
	static int map_from_obj[] = new int [27]; 
	int timesPCHit = 0;
	
	/*Game class constructor*/
	public SimGame() {
        Init();
    }
	
	 /*Creates a new map and invokes needed startup functions*/
	public static void Init()
	    {
	       
	       
			map_from_obj = getInteractionMap();
			//addMapToInteractionArray(map_from_obj);

			
	    }

	/*reads in needed info from interaction array including map and if game is reversed*/ 
	public static int[] getInteractionMap()
	{
		int[] map = new int[27];
		
		for (int i = 0; i < map.length - 1; i++)
		{
			int gameIndex;
			if (Main.returnGlobal().getSimIndex() == -1)
			// if (Main.returnGlobal().isFirstSim())
			{
				 gameIndex = Main.returnGlobal().getSimIndex() + 1 + i;
				// Main.returnGlobal().setFirstSim(false);
			}
			else
			{
				gameIndex = Main.returnGlobal().getSimIndex() + 5 + i;
			} 
			//gameIndex = Main.returnGlobal().getSimIndex() + 1 + i;
			map[i] =  (int) Math.round( (double) Main.returnGlobal().getInteractionArray().get(gameIndex));
		}
		
		 
		
		if (Main.returnGlobal().getSimIndex() == -1)
		// if (Main.returnGlobal().isFirstSim())
			{
			 System.out.println(Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex() + 38) + " REVERSE");
			 System.out.println(Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex() + 36) + " MODE");
			 if ((int) Math.round( (double)  Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex() + 36))  == 3)
				{
					Main.returnGlobal().setMode(5);
				}
			 
			 
			 if ((int) Math.round( (double)  Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex() + 38))  == 2)
				{
					Main.returnGlobal().setReverse_game(true);
				}
				else if((int) Math.round( (double)  Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex() + 38 ))  == 1)
				{
					Main.returnGlobal().setReverse_game(false);
				}
			 Main.returnGlobal().setFirstSim(false);
			 Main.returnGlobal().setSimIndex(Main.returnGlobal().getSimIndex() + 42);
			}
		
		 else
			{
			 System.out.println(Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex() + 42) + " REVERSE");
			 System.out.println(Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex() + 40) + " MODE");
			 
			 if ((int) Math.round( (double)  Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex() + 40))  == 3)
				{
					Main.returnGlobal().setMode(5);
				}
			 if ((int) Math.round( (double)  Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex() + 42))  == 2)
				{
					Main.returnGlobal().setReverse_game(true);
				}
				else if((int) Math.round( (double)  Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex() + 42 ))  == 1)
				{
					Main.returnGlobal().setReverse_game(false);
				}
			 Main.returnGlobal().setSimIndex(Main.returnGlobal().getSimIndex() + 46);
			}
		
		 
		
		 System.out.println(Main.returnGlobal().getSimIndex());
		return map;
	}
	
	/*reverses the players input for backward mode*/
	public void reverseArray()
	  {
		for (int i = 0 ;  i < getButton_pressed_index() ; i++)
		{
			getButtns_pressd_reversed()[i] = getButtns_pressd()[(getButton_pressed_index() -  1) - i];
		}
		
		for (int i = 0 ;  i < 10 ; i++)
		{
			System.out.println(getButtns_pressd_reversed()[i]);
		}
		
		
	  }	
		
	
	/*checks computer array (forward) versus player array (forward or reversed) , plays win or lose screen*/
	public void checkArray()
  {
    
    boolean flag = false;
    for (int i = 0; i < getButtns_pressd().length ; i++)
    {
    	if (Main.returnGlobal().isReverse_game())
    	{
    		 if (getButtns_pressd_reversed()[i] != getComputer_pressed()[i])
    	      {
    	        flag = true;
    	      }
    	}
    	else
    	{
    		System.out.println(getButtns_pressd()[i]);
    		System.out.println(getComputer_pressed()[i]);
    		  if (getButtns_pressd()[i] != getComputer_pressed()[i])
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
 
    	
    	if (Main.returnGlobal().getHealth() == 1)
    	{
    	Main.returnGlobal().setHealth(Main.returnGlobal().getHealth() - 1);
    		Main.PlayLoseContinue();
    	}
    	else
    	{
    		Main.PlayLose();
    		
    	}
       
    }
  }


	/*computer clicks random block*/
	public static void clickRandomBlock(JButton[] inArray)
  {
	  int randomBlock = ThreadLocalRandom.current().nextInt(1, 8 + 1);
	  inArray[randomBlock].setEnabled(true);
	  inArray[randomBlock].doClick();
  }
  
  	/*Simulates players button presses of that round (computer player's "turn") */
  	public void simHit(JButton[] inArray)
 	{
 	  		@SuppressWarnings("unused")
 			int buffer = 0;
 		  	if  (Main.returnGlobal().isFirst_hit())
 		  	{
 		  		buffer = 3500;
 		  	}
 		  	else
 		  	{
 		  		buffer = 0;
 		  	}
 	  		
 	  		
 	  		while ((int) Math.round( (double)  Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex())) != 0 &&
 	  				(int) Math.round( (double)  Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex())) != -1) {
 			  if (TimeUnit.NANOSECONDS.toMillis(System.nanoTime()) - clock_timer  >= (Main.returnGlobal().getSpeed() + buffer  ))
 			  {
 				  int randomBlock = (int) Math.round( (double)  Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex()));
 				  //inArray[randomBlock].setBackground(Color.WHITE);
 				  Main.returnGlobal().setSimIndex(Main.returnGlobal().getSimIndex() + 1);
 				  inArray[randomBlock].setEnabled(true);
 				  java.awt.Toolkit.getDefaultToolkit().beep();
 				  inArray[randomBlock].doClick(Main.returnGlobal().getSpeed());
 				  inArray[randomBlock].setEnabled(false);
 				  clock_timer = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
 				  Main.returnGlobal().setFirst_hit(false);
 				  buffer = 0;
 				  setTimes_hit(getTimes_hit() + 1);
 				 // hits++;
 			  }
 	  		}
 	  		int hits = 0;
 	  	 buffer = 1000;
 	  		while (hits < 1) {
 	  			if (TimeUnit.NANOSECONDS.toMillis(System.nanoTime()) - clock_timer  >= (Main.returnGlobal().getSpeed() + buffer  ))
 	 			  {
 	  				while( (int) Math.round( (double)  Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex())) == 0)
 	  				{
 	  					Main.returnGlobal().setSimIndex(Main.returnGlobal().getSimIndex() + 1);
 	  				}
 	  				
 	  				
 	  				done_button.setEnabled(true);
 	 				java.awt.Toolkit.getDefaultToolkit().beep();
 	 				done_button.doClick();
 	 				///done_button.setEnabled(false);
 	 				 hits++;
 	 			  }
 	  		}
 		  
 		  }
  
  	/*Clicks the amount of blocks needed in each round (computer player's "turn")*/
  	public void hit(JButton[] inArray)
	{
	  		@SuppressWarnings("unused")
			int buffer = 0;
		  	if  (Main.returnGlobal().isFirst_hit())
		  	{
		  		buffer = 500;
		  	}
		  	else
		  	{
		  		buffer = 0;
		  	}
	  		
	  		while ((int) Math.round( (double)  Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex())) != 0 &&
 	  				(int) Math.round( (double)  Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex())) != -1) {
	 
	  			
			  if (TimeUnit.NANOSECONDS.toMillis(System.nanoTime()) - clock_timer  >= (Main.returnGlobal().getSpeed() + buffer  ))
			  {
				  Main.returnGlobal().setComputer_playing(true);
				  System.out.println(Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex()) + " WHERE WE ARE IN INTERACTIONS ARRAY - IY");
				  int randomBlock = (int) Math.round( (double)  Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex()));
				  
				  Main.returnGlobal().setSimIndex(Main.returnGlobal().getSimIndex() + 1);
				  inArray[randomBlock].setEnabled(true);
				  java.awt.Toolkit.getDefaultToolkit().beep();
				  inArray[randomBlock].doClick(Main.returnGlobal().getSpeed());
				  inArray[randomBlock].setEnabled(false);
				  clock_timer = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
				  Main.returnGlobal().setComputer_playing(false);
				  Main.returnGlobal().setFirst_hit(false);
				  //hits++;
				  timesPCHit++;
			  }
	  		}
		  
		  }
  

  	/*Activates all game buttons*/
	public void activateAllButtons(JButton[] inArray)
	{
		for (int i = 1; i < inArray.length ; i++)
		{
			inArray[i].setEnabled(true);
		}
	}

	/*Creates the "Done" button after computer player's turn*/
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
  	    	//revalidate();
  	    	repaint();
		  	hit++;
		  	
		  }
		  
		}
  }

	/*Creates JButton Map from Map array and invokes the computer player's turn*/
    public void displayGUI()
    {
    	 JButton[] outArray = new JButton[10];
    	 Main.returnGlobal().setRoundtime(0);
    	 if ( Main.returnGlobal().getLevel() > 9)
    	 {
    		setButtns_pressd(new int [50]);
    		setButtns_pressd_reversed(new int [50]);
    		setComputer_pressed(new int [50]); 
    	 }
   
        
        for (int i = 1; i < 10; i++)
        {
        	   JButton button = new JButton("");
               button.setSize(50, 50);
               button.setLocation(map_from_obj[(i *3) - 2], map_from_obj[(i * 3) - 1]);
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
        	    time_begin = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());	
        		hit(outArray);
        		Main.returnGlobal().setFirst_hit(true);
        	 }
        	finally
        	{
        		createDoneButton();
        		
        	 
        		while( (int) Math.round( (double)  Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex())) == 0)
  				{
  					Main.returnGlobal().setSimIndex(Main.returnGlobal().getSimIndex() + 1);
  				}
    			Main.returnGlobal().setSimIndex(Main.returnGlobal().getSimIndex() + 1);
        	
        		System.out.println( Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex()));
        		
        		simHit(outArray);
        		 
        		Main.returnGlobal().setSimIndex(Main.returnGlobal().getSimIndex() + 1);
	  			 
	  				
	        		
	        		System.out.println(Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex() - 2) + " - 2 index");
	  				System.out.println(Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex() - 1) + " - 1 index");
	  				System.out.println(Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex()) + " WHERE WE ARE IN INTERACTIONS ARRAY");
	  				System.out.println(Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex() + 1) + " + 1 index");
	  				System.out.println(Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex() + 2 ) + " + 2 index");
        	}
        		
        	}
        });
       t.start();
      
      
     
    }
  
	/*Getter*/ 
	public int[] getButtns_pressd() {
		return buttns_pressd;
	}
	
	/*Setter*/ 
	public void setButtns_pressd(int buttns_pressd[]) {
		this.buttns_pressd = buttns_pressd;
	}

	/*Getter*/ 
	public int getComputer_pressed_index() {
		return computer_pressed_index;
	}

	/*Setter*/ 
	public void setComputer_pressed_index(int computer_pressed_index) {
		this.computer_pressed_index = computer_pressed_index;
	}

	/*Getter*/ 
	public int[] getComputer_pressed() {
		return computer_pressed;
	}

	/*Setter*/ 
	public void setComputer_pressed(int computer_pressed[]) {
		this.computer_pressed = computer_pressed;
	}

	/*Getter*/ 
	public int getButton_pressed_index() {
		return button_pressed_index;
	}

	/*Setter*/ 
	public void setButton_pressed_index(int button_pressed_index) {
		this.button_pressed_index = button_pressed_index;
	}

	/*Getter*/ 
	public int[] getButtns_pressd_reversed() {
		return buttns_pressd_reversed;
	}

	/*Setter*/ 
	public void setButtns_pressd_reversed(int buttns_pressd_reversed[]) {
		this.buttns_pressd_reversed = buttns_pressd_reversed;
	}

	/*Getter*/ 
	public int getTimes_hit() {
		return times_hit;
	}

	/*Setter*/ 
	public void setTimes_hit(int times_hit) {
		this.times_hit = times_hit;
	}
    
	
}