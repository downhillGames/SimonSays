
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class GameScreen extends JFrame{

	/*Game Screen Constructor*/
    public GameScreen() {

        initUI();
    }

    /*Initializes game screen, size and settings*/
    private void initUI() {

        setTitle("Game");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
       
        
        addWindowStateListener(new WindowStateListener() {
            
			@Override
			 /*Adds a listener for if the game is minimized*/
			public void windowStateChanged(WindowEvent e) {
				if(e.getNewState() ==1){//this means minimized
	                System.out.println("min");
	                if (Main.returnGame().getParent() != null)
	                {
	                	System.out.println(Main.returnGame().getParent());
		                Main.PlayPause();
	                }
	             }else if(e.getNewState()==0){//this means maximized/normal state
	            	 System.out.println("norm");
	            	
	             }
			} });
        
        
    }
    
    
}