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
	long time_begin = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
	long clock_timer = time_begin;
	static int map_from_obj[] = new int [27]; 
	
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

	public static int[] getInteractionMap()
	{
		int[] map = new int[27];
		
		for (int i = 0; i < map.length - 1; i++)
		{
			int gameIndex;
			/*if (Main.returnGlobal().isFirstSim())
			{
				 gameIndex = Main.returnGlobal().getSimIndex() + 5 + i;
				 Main.returnGlobal().setFirstSim(false);
			}
			else
			{
				gameIndex = Main.returnGlobal().getSimIndex() + 1 + i;
			}*/
			gameIndex = Main.returnGlobal().getSimIndex() + 5 + i;
			map[i] =  (int) Math.round( (double) Main.returnGlobal().getInteractionArray().get(gameIndex));
		}
		
		 
		if ((int) Math.round( (double)  Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex() + 42))  == 2)
		{
			Main.returnGlobal().setReverse_game(true);
		}
		else if((int) Math.round( (double)  Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex() + 42 ))  == 1)
		{
			Main.returnGlobal().setReverse_game(false);
		}
		
		System.out.println(Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex() + 42) + " REVERSE");
		 Main.returnGlobal().setSimIndex(Main.returnGlobal().getSimIndex() + 46);
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
@SuppressWarnings("unchecked")
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
  
  /*Clicks the amount of blocks needed in each round (computer player's "turn")*/
  
  
  public void simHit(JButton[] inArray)
 	{
 	  		@SuppressWarnings("unused")
 			int buffer = 0;
 		  	if  (Main.returnGlobal().isFirst_hit())
 		  	{
 		  		buffer = 2000;
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
 				  times_hit++;
 				 // hits++;
 			  }
 	  		}
 	  		int hits = 0;
 	  	 buffer = 500;
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
	  		int hits = 0;
	  		while (hits <  Main.returnGlobal().getTimes_won() + 1 ) {
	  			
			  if (TimeUnit.NANOSECONDS.toMillis(System.nanoTime()) - clock_timer  >= (Main.returnGlobal().getSpeed() + buffer  ))
			  {
				  Main.returnGlobal().setComputer_playing(true);
				  int randomBlock = (int) Math.round( (double)  Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex()));
				  //inArray[randomBlock].setBackground(Color.WHITE);
				  Main.returnGlobal().setSimIndex(Main.returnGlobal().getSimIndex() + 1);
				  inArray[randomBlock].setEnabled(true);
				  java.awt.Toolkit.getDefaultToolkit().beep();
				  inArray[randomBlock].doClick(Main.returnGlobal().getSpeed());
				  inArray[randomBlock].setEnabled(false);
				  clock_timer = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
				  Main.returnGlobal().setComputer_playing(false);
				  Main.returnGlobal().setFirst_hit(false);
				  hits++;
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
        		hit(outArray);
        		Main.returnGlobal().setFirst_hit(true);
        	 }
        	finally
        	{
        		createDoneButton();
        		int counter = 10 -  Main.returnGlobal().getTimes_won();
        		Main.returnGlobal().setSimIndex(Main.returnGlobal().getSimIndex() + counter);
        		System.out.println( Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex()));
        		
        		simHit(outArray);
        		 
        		Main.returnGlobal().setSimIndex(Main.returnGlobal().getSimIndex() + 1);
	  			 
	  				int counter2 = 10 -  times_hit;
	        		//Main.returnGlobal().setSimIndex(Main.returnGlobal().getSimIndex() + times_hit);
	        		
	        		System.out.println(Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex() - 2) + " - 2 index");
	  				System.out.println(Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex() - 1) + " - 1 index");
	  				System.out.println(Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex()) + " WHERE WE ARE IN INTERACTIONS ARRAY");
	  				System.out.println(Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex() + 1) + " + 1 index");
	  				System.out.println(Main.returnGlobal().getInteractionArray().get(Main.returnGlobal().getSimIndex() + 2 ) + " + 2 index");
        		//activateAllButtons(outArray);
        	}
        		
        	}
        });
       t.start();
      
      
     
    }

   
   
   
   
	
	 
	 /*Getters and Setters*/ 
	 public int[] getButtns_pressd() {
		return buttns_pressd;
	}
	

	public void setButtns_pressd(int buttns_pressd[]) {
		this.buttns_pressd = buttns_pressd;
	}

	public int getComputer_pressed_index() {
		return computer_pressed_index;
	}

	public void setComputer_pressed_index(int computer_pressed_index) {
		this.computer_pressed_index = computer_pressed_index;
	}

	public int[] getComputer_pressed() {
		return computer_pressed;
	}

	public void setComputer_pressed(int computer_pressed[]) {
		this.computer_pressed = computer_pressed;
	}

	public int getButton_pressed_index() {
		return button_pressed_index;
	}

	public void setButton_pressed_index(int button_pressed_index) {
		this.button_pressed_index = button_pressed_index;
	}

	public int[] getButtns_pressd_reversed() {
		return buttns_pressd_reversed;
	}

	public void setButtns_pressd_reversed(int buttns_pressd_reversed[]) {
		this.buttns_pressd_reversed = buttns_pressd_reversed;
	}
    
	
}