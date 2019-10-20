import java.awt.event.*;

public class gameButton implements ActionListener {
	private int index;
	
	/*Game Button Constructor, requires an index (imported from map)*/
	 public gameButton(int indx) {
	        index = indx;
	    }

	
	/*Appends Player's button choice to Player Button Pressed Array (Forward or Reversed) when pressed*/
	 @Override
	 public void actionPerformed(ActionEvent e) {
		Main.returnGame();
	// TODO Auto-generated method stub

	if (Main.returnGlobal().isComputer_playing() == true)
	{
		int cur_index = Main.returnGame().getComputer_pressed_index();
		Main.returnGame().getComputer_pressed()[cur_index] = index;
		Main.returnGame().setComputer_pressed_index(Main.returnGame().getComputer_pressed_index() + 1);
		System.out.println(Main.returnGame().getComputer_pressed()[cur_index]);
	}
	else
	{
		int cur_index = Main.returnGame().getButton_pressed_index();
		Main.returnGame().getButtns_pressd()[cur_index] = index;
		Main.returnGame().setButton_pressed_index(Main.returnGame().getButton_pressed_index() + 1);
		System.out.println(Main.returnGame().getButtns_pressd()[cur_index]);
	}
    
        
	}
	
}