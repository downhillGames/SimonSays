import java.awt.Color;

import javax.swing.*;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeUnit;


@SuppressWarnings("serial")
public class Game extends JPanel
{	 
	//import map from map class
	static Map map;   
	
	/*initialize variables*/
	private int buttns_pressd[] = new int [10];
	private int buttns_pressd_reversed[] = new int [10];
	private int computer_pressed[] = new int [10]; 
	private int button_pressed_index = 0;
	private int computer_pressed_index = 0;
	static JButton done_button;
	long time_begin = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
	long clock_timer = time_begin;
	static int map_from_obj[] = new int [27]; 
	
	/*Game class constructor*/
	public Game(Map mp) {
        map = mp;
        Init();
    }
	
	 /*Creates a new map and invokes needed startup functions*/
	public static void Init()
	    {
	       
	        Map newmap = map;
			newmap.createMap(); 
			map_from_obj = newmap.getMap_array();
			

			
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
    		  if (getButtns_pressd()[i] != getComputer_pressed()[i])
    	      {
    	        flag = true;
    	      }
    	}
    	
    }
    
    for (int i = 0; i < getButtns_pressd().length ; i++)
    {
		Main.returnGlobal().getInteractionArray().add(getComputer_pressed()[i]);
    }
    Main.returnGlobal().getInteractionArray().add(-1);
 
    if  (Main.returnGlobal().isReverse_game())
    {
    	for (int i = 0; i < getButtns_pressd().length ; i++)
        {
    		//Main.returnGlobal().getInteractionArray().add(getButtns_pressd_reversed()[i]);
    		Main.returnGlobal().getInteractionArray().add(getButtns_pressd()[i]);
        }
    	
     }
    else
    {
    	for (int i = 0; i < getButtns_pressd().length ; i++)
        {
    		Main.returnGlobal().getInteractionArray().add(getButtns_pressd()[i]);
        }
    }  
    
    
    
    
    Main.returnGlobal().getInteractionArray().add(-1);
   
    
    
    if (flag == false)
    {
    	Main.returnGlobal().getInteractionArray().add(-2);
        Main.PlayWin();
 
    }
    else{
    	 
    	
    	if (Main.returnGlobal().getHealth() == 1)
    	{
    		Main.returnGlobal().getInteractionArray().add(-2);
    		Main.returnGlobal().setHealth(Main.returnGlobal().getHealth() - 1);
    		Main.PlayLoseContinue();
    	}
    	else
    	{
    		Main.returnGlobal().getInteractionArray().add(-3);
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
	  		while (hits <  Main.returnGlobal().getLevel() + 1 ) {
			  if (TimeUnit.NANOSECONDS.toMillis(System.nanoTime()) - clock_timer  >= (Main.returnGlobal().getSpeed() + buffer  ))
			  {
				  Main.returnGlobal().setComputer_playing(true);
				  int randomBlock = ThreadLocalRandom.current().nextInt(1, 8 + 1);
				  //inArray[randomBlock].setBackground(Color.WHITE);
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
  	    repaint();
		  	hit++;
		  }
		  
		}
  }

  /*Creates JButton Map from Map array and invokes the computer player's turn*/
    @SuppressWarnings("unchecked")
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
        
        addMapToInteractionArray();
        
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

   
   
   
    
    /*Interaction array: Map, Level, Times won, Health, Speed, Mode, Game Reversed, New Game,
      Player button pressed array, computer button pressed array, Win/Lose Flag,
       then -2 signifies the next entry -1 signifies the next variable, -3 signifies end of game (game over)*/
    
    /*Adds the map, mode, and various other variables to the Interactions array*/
	 @SuppressWarnings("unchecked")
    public static void addMapToInteractionArray()
    {
    	
			//Main.returnGlobal().getInteractionArray().add(map_from_obj[i]);
		 for (int i = 0; i < map_from_obj.length -1 ; i++)
		 {
			 Main.returnGlobal().getInteractionArray().add(map_from_obj[i]);
		 }
		 
		 
		Main.returnGlobal().getInteractionArray().add(-1);
		Main.returnGlobal().getInteractionArray().add(Main.returnGlobal().getLevel());
		Main.returnGlobal().getInteractionArray().add(-1);
		Main.returnGlobal().getInteractionArray().add(Main.returnGlobal().getTimes_won());
		Main.returnGlobal().getInteractionArray().add(-1);
		Main.returnGlobal().getInteractionArray().add(Main.returnGlobal().getHealth());
		Main.returnGlobal().getInteractionArray().add(-1);
		Main.returnGlobal().getInteractionArray().add(Main.returnGlobal().getSpeed());
		Main.returnGlobal().getInteractionArray().add(-1);
		Main.returnGlobal().getInteractionArray().add(Main.returnGlobal().getMode());
		Main.returnGlobal().getInteractionArray().add(-1);
		
		if (Main.returnGlobal().isReverse_game() == false )
		{
			Main.returnGlobal().getInteractionArray().add(1);	
		}
		else
		{
			Main.returnGlobal().getInteractionArray().add(2);	
		}
		Main.returnGlobal().getInteractionArray().add(-1);
		if (Main.returnGlobal().isNewGame() == true )
		{
			Main.returnGlobal().getInteractionArray().add(1);	
		}
		else
		{
			Main.returnGlobal().getInteractionArray().add(2);	
		}
		Main.returnGlobal().getInteractionArray().add(-1);	
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