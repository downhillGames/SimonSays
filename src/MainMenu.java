//import java.awt.EventQueue;
//import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class MainMenu extends JPanel  implements ActionListener {

	private static final long serialVersionUID = -7892106385327845406L;
	JTextArea text;
	static int lives = 6;
    public MainMenu() {
      
        initUI();
    }

    private void initUI() {
      
    	createTextArea("Welcome to the Corsi Test");
        JButton playButton = new JButton("New Game");
        add(playButton);
        JButton loadButton = new JButton("Load Game");
        add(loadButton);
        JButton toggleButton = new JButton("Toggle Classic/Pause Mode");
        add(toggleButton);
        
        JButton quitButton = new JButton("Quit");
        add(quitButton);
        
        
        playButton.addActionListener(new button());
        loadButton.addActionListener(new loadGameBtn());
        quitButton.addActionListener(new button2());
        toggleButton.addActionListener(this);
        
        text = new JTextArea(1, 5);
        text.setEditable(false);
        text.setText("");
        text.setBackground(Main.returnFrame().getBackground()); 
	 	add(text);
        
    }
   
    public void changeMode(JTextArea text)
    {
    	if (Main.returnGlobal().classic_mode == true)
    	{
    		text.setText("Pause Mode");
    		Main.returnGlobal().classic_mode = false;
    	}
    	else if (Main.returnGlobal().classic_mode == false)
    	{
    		text.setText("Classic Mode");
    		Main.returnGlobal().classic_mode = true;
    	}
	 	revalidate();
    }

    public JTextArea createTextArea(String text)
	   {
		   	//JTextArea textArea = new JTextArea(1, text.length());
		 	Border border = BorderFactory.createMatteBorder(0, 600, 20, 600, Main.returnFrame().getBackground());
		 	
			JTextArea textArea = new JTextArea(1, 5);
		   	textArea.setEditable(false);
		   	textArea.append(text);
		   	textArea.setBackground(Main.returnFrame().getBackground()); 
		   	textArea.setBorder(border);
		   	add(textArea);
		   	return textArea;
	   }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	changeMode(text);
    }
	 
}