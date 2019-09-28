//import java.util.concurrent.TimeUnit;
//import java.awt.EventQueue;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class GameScreen extends JFrame{

    public GameScreen() {

        initUI();
    }

    private void initUI() {

        setTitle("Game");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        /*
        addWindowStateListener(new WindowStateListener() {
            if(this.getState()==1){//this means minimized
                System.out.println("min");
             }else if(this.getState()==0){//this means maximized/normal state
            	 System.out.println("norm");
             };
        
        */
        
    }
    
    /*
    public void frame_windowStateChanged(WindowEvent e){
    	   // minimized
    	   if ((e.getNewState() & ICONIFIED) == ICONIFIED){
    	      System.out.print("minimized");
    	   }
    	   // maximized
    	   else if ((e. ) == MAXIMIZED_BOTH){
    		   System.out.print("maximized");
    	   }
    	}
*/
    
}