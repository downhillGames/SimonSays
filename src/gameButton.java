//import java.awt.Color;
//import java.awt.Component;
import java.awt.event.*;

//import javax.swing.JFrame;
//import javax.swing.SwingUtilities;

public class gameButton implements ActionListener {
	private int index;
	
	 public gameButton(int indx) {
	        // Assignments should not re-declare the fields
	        index = indx;
	    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
    //var game = ;
    int cur_index = Main.returnGame().button_pressed_index;
    Main.returnGame().buttns_pressd[cur_index] = index;
    Main.returnGame().button_pressed_index+=1;
    System.out.println(Main.returnGame().buttns_pressd[cur_index]);
		//JFrame frame = (JFrame) SwingUtilities.getRoot(component);
        //frame.getContentPane().setBackground(Color.RED);
        
	}
	
}