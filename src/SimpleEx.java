import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.*;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeUnit;


public class SimpleEx extends JPanel
{	 
  static Map map;            
	public SimpleEx(Map mp) {
        map = mp;
        Init();
    }
	
	public int buttns_pressd[] = new int [50]; 
	public int button_pressed_index = 0;
	
	static int map_from_obj[] = new int [27]; 

  public void checkArray()
  {
    
    boolean flag = false;
    for (int i = 0; i < (button_pressed_index /2) -1 ; i++)
    {
      if (buttns_pressd[i] != buttns_pressd[i + Main.returnGlobal().level])
      {
        flag = true;
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

  
  public static void clickRandomBlack(JButton[] inArray)
  {
	  int randomBlock = ThreadLocalRandom.current().nextInt(1, 8 + 1);
	  inArray[randomBlock].setBackground(Color.WHITE);
	  try {
		TimeUnit.SECONDS.sleep(2);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  inArray[randomBlock].setBackground(Color.BLUE);
	  try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  //int randomBlock = ThreadLocalRandom.current().nextInt(1, 8 + 1);
	  //inArray[randomBlock].setEnabled(true);
	  //inArray[randomBlock].doClick(1000);
  }
  
  
	public void delay(JButton[] inArray)
	{
		  
		  System.out.println(inArray[0]);
		 
		  
		 
		
		for (int i = 0; i < Main.returnGlobal().level ; i ++){
			//clickRandomBlack(inArray);
			}
		  
		  
		  
		  
		  
		  for (int i = 1; i < 9; i++)
		  {
			  inArray[i].setEnabled(true); 
		  }
		  //inArray[0].setEnabled(true);
		  //inArray[0].doClick(1000);
		  //inArray[0].setEnabled(false); 
	}
   
	//JButton[]
    public void displayGUI()
    {
      //this.setOpaque(true);
      //this.setBackground(Color.WHITE);
      //this.setLayout(null);
    	JButton outArray[] = new JButton[10];
        

        //JPanel contentPane = new JPanel();
        JButton done_button = new JButton("");
        done_button .setSize(300, 30);
        done_button.setLocation(300, 550);
        
        for (int i = 1; i < 10; i++)
        {
        	   JButton button = new JButton("");
               button.setSize(50, 50);
               button.setLocation(map_from_obj[(i *3) - 1], map_from_obj[(i * 3) - 2]);
               button.setBackground(Color.BLUE);
               outArray[i] = button;
               
               button.addActionListener(new gameButton(map_from_obj[(i *3) - 3]));
               
               add(button);
               
               
        }
        
        done_button.addActionListener(new submitbutton());
        add(done_button);
        


        //button.setEnabled(false);
       // EventQueue.invokeLater(() -> {	
		delay(outArray);
        //});
        //outArray[0] = button;
        //outArray[1] = button2;
        //System.out.println(outArray[0]);
        
	//return outArray;
	
	

     
    }

    public static void Init()
    {
       
        Map newmap = map;
				newmap.createMap(); 
				map_from_obj = newmap.getMap_array();
        //outArry = displayGUI();
        //System.out.println(outArry[0]);
        //Systen(outArry[0]);
        //delay(outArry);
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