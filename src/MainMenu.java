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
    
	 /*Constructor -  invokes initUI() */
	public MainMenu() {
      
        initUI();
    }

    /*Initializes UI components of main menu*/
    private void initUI() {
      
    	createTextArea("Welcome to the Corsi Test");
        JButton playButton = new JButton("New Game");
        add(playButton);
        JButton loadButton = new JButton("Load Game");
        add(loadButton);
        JButton toggleButton = new JButton("Toggle Mode");
        add(toggleButton);
        
        JButton helpButton = new JButton("Help Menu");
        add(helpButton);
        
        JButton simButton = new JButton("Simulation");
        add(simButton);
        
        
        JButton highScoreButton = new JButton("High Scores");
        add(highScoreButton);
        
        JButton quitButton = new JButton("Quit");
        add(quitButton);
        
        
        playButton.addActionListener(new NewSaveBtn());
        simButton.addActionListener(new SimulationButton());
        loadButton.addActionListener(new loadGameBtn());
        quitButton.addActionListener(new QuitBtn());
        helpButton.addActionListener(new helpButton());
        highScoreButton.addActionListener(new HighScoreBtn());
        toggleButton.addActionListener(this);
        
        text = new JTextArea(1, 5);
        text.setEditable(false);
        text.setText("");
        text.setBackground(Main.returnFrame().getBackground()); 
	 	add(text);
        
    }
   
    /*Sets the various game modes*/
    public void changeMode(JTextArea text)
    {
    	if (Main.returnGlobal().getMode() == 1)
    	{
    		text.setText("Pause Mode");
    		Main.returnGlobal().setMode(2);
    	}
    	else if (Main.returnGlobal().getMode() == 2)
    	{
    		text.setText("Modern Mode");
    		Main.returnGlobal().setMode(3);
    	}
    	else if (Main.returnGlobal().getMode() == 3)
    	{
    		text.setText("Classic Mode");
    		Main.returnGlobal().setMode(1);
    	}
	 	revalidate();
    }

    /*Creates JText Area from input text*/
    public JTextArea createTextArea(String text)
	   {
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
    /*On button press, invoke change modes*/
    public void actionPerformed(ActionEvent e) {
    	changeMode(text);
    }
	 
}