 import javax.swing.JTextArea; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import javax.swing.JButton;

public class MainMenu extends Menu  implements ActionListener {

	private static final long serialVersionUID = -7892106385327845406L;
	JTextArea text;
	JButton resetButton;
	
	 /*Constructor -  invokes initUI() */
	public MainMenu() {
      
        initUI();
    }

    /*Initializes UI components of main menu*/
    private void initUI() {
      
    	createTextAreaLineBreak("Welcome to the Corsi Test");
        JButton playButton = new JButton("New Game");
        add(playButton);
        JButton loadButton = new JButton("Load Game");
        add(loadButton);
        JButton toggleButton = new JButton("Toggle Mode");
        add(toggleButton);
        
        JButton helpButton = new JButton("Help Menu");
        add(helpButton);
        
        
        
        if (Main.isDev_mode())
    	{
        	JButton simButton = new JButton("Simulation");
            add(simButton);
    		simButton.addActionListener(new SimulationButton());
    		
    		JButton statsButton = new JButton("Stats");
            add(statsButton);
            statsButton.addActionListener(new StatsButton());
            
            resetButton = new JButton("Reset");
            add(resetButton);
            resetButton.addActionListener(this);
    		
    	}
        
        
        JButton highScoreButton = new JButton("High Scores");
        add(highScoreButton);
        
        JButton quitButton = new JButton("Quit");
        add(quitButton);
        
        
        playButton.addActionListener(new NewSaveBtn());
        
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

    /*Performs action if mode button or reset button is pressed*/
    @SuppressWarnings("static-access")
	@Override
    /*On button press, invoke change modes*/
    public void actionPerformed(ActionEvent e) {
    	
    	if ( e.getSource() == resetButton)
		{
			Main.returnGameSave().resetSaves();
			text.setText("All saves have been reset");
		}
    	else
    	{
    		changeMode(text);
    	}
    	
    }
	 
}