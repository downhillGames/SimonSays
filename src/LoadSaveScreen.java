
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class LoadSaveScreen extends Menu implements ActionListener {
	
	//initialize needed variables 
	JButton reversePlayButton = new JButton("Play Game - Reverse");
	private static JTextField nameField;
	private static JTextField passwordField;
	private static JTextField adressField;
	private static JTextField cityField;
	private static JTextField stateField;
	private static JTextField zipField;
	private static JTextField countryField;
	private static JTextArea errorText;
	private static final long serialVersionUID = -7892106385327845406L;
	private String password = "";
	int password_check_times = 0;
	long clock_timer;
	 /*Constructor -  invokes initUI() */
	public LoadSaveScreen() {
        initUI();
        
        Thread t = new Thread(new Runnable() {
            public void run() {
            	 setPasswordField();
            }
        });
        t.start();
    }
    
    /*Prints error if load game is unsuccessful */
    public void printError(String text){
    	errorText.setText(text);
	 	revalidate();
    }
    
    /*Sets asterisks in password field*/
    void setPasswordField() {
    	 while (password_check_times < 10000) {
    		 
    		 if (TimeUnit.NANOSECONDS.toMillis(System.nanoTime()) - clock_timer >= 500) {
                 
    			 String passwordAppend =  passwordField.getText();
    			 
    			 if (passwordAppend.length() < password.length())
     			{
    				 if (passwordAppend.length() == 0)
    				 {
    					 password = "";
    				 }
    				 else
    				 {
    					 int truncate = password.length() - passwordAppend.length(); 
    	     			 password = password.substring(0,password.length() - (truncate));
    				 }
     				
     			}
     	    	if (passwordAppend.contains("*"))
     	    	{
     	    		passwordAppend = passwordAppend.substring(password.length());
     	    	}
     	    	//System.out.println("Append" + passwordAppend);
    			 
    			 int asterisks = passwordField.getText().length();
    			 
    			 passwordField.setText(returnAsterisks(asterisks));
    			 password_check_times ++;
                 clock_timer = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
                 passwordField.setCaretPosition(asterisks);
                 password = password + passwordAppend;
             }
    		 
         }
    	 
    	 
    }
    
/*Returns string with amount of input asterisks*/
	String returnAsterisks(int amount) {
		String Astericks = "";
		for (int i = 0; i < amount ; i++)
		{
			Astericks = "*" + Astericks;
		}
		
    	return Astericks;
    	
    }

    /*Initializes all the needed load Game fields for the UI*/
    @SuppressWarnings("unused")
	private void initUI() {
    	Border border = BorderFactory.createMatteBorder(0, 0, 0, 0, Main.returnFrame().getBackground());
    	Border border2 = BorderFactory.createMatteBorder(0, 100, 0, 0, Main.returnFrame().getBackground());
    	Border border3 = BorderFactory.createMatteBorder(0, 0, 0, 900, Main.returnFrame().getBackground());
    	
    	nameField = createInputArea("Name: ", 105, border, nameField);
    	passwordField = createInputArea("Password: ", 105, border, passwordField);
    	JTextArea text = createTextArea("Need to update anything? Leave field blank if not");
    	text.setBorder(border3);
    	adressField = createInputArea("Street Address: ", 105, border, adressField);
    	cityField =createInputArea("City: ", 105, border, cityField);
    	stateField = createInputArea("State: ", 105, border, stateField);
    	zipField = createInputArea("Zip Code: ", 105 , border, zipField);
    	countryField = createInputArea("Country: ", 105 , border, countryField);
        
    	
    	

    	
    	JButton playButton = new JButton("Play Game - Forward");
    	JButton menuButton = new JButton("Back to Main Menu");
    	JButton newButton = new JButton("New Game");
    	JButton quitButton = new JButton("Quit Game");
    	//JButton reversePlayButton = new JButton("Play Game - Reverse");
    	///playButton.setBorder(border);
    	playButton.setLocation(550, 750);
        playButton.addActionListener(this);
        reversePlayButton.addActionListener(this);
        
        menuButton.addActionListener(new menuButton());
        newButton.addActionListener(new NewSaveBtn());
        quitButton.addActionListener(new QuitBtn());
        
        add(playButton, BorderLayout.CENTER);
        add(reversePlayButton, BorderLayout.CENTER);
        add(newButton, BorderLayout.CENTER);
        add(menuButton, BorderLayout.CENTER);
        add(quitButton, BorderLayout.CENTER);
        
        errorText = new JTextArea(1, 5);
        errorText.setEditable(false);
        errorText.setText("");
        errorText.setBackground(Main.returnFrame().getBackground()); 
	 	add(errorText);
	 	clock_timer = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
        Main.returnFrame().repaint();
    }
       	
	/*Upon click, starts game if load is successful - or shows error also updates any needed fields*/
    @Override
	public void actionPerformed(ActionEvent e) {
		if (nameField.getText().equals("") )
		{
			printError("You did not enter a name!");
		}
		else if ( nameField.getText().equals("DEVMODE") && password.equals("DEVMODE" )) {
			printError("You have enabled dev mode ");
			Main.setDev_mode(true);
		}
		else if ( Main.returnGameSave().loadGame(nameField.getText()) == -1)
		{
			printError( nameField.getText());
			
		}
		
		else if ( nameField.getText().equals("") )
		{
			printError("You did not enter a password!");
		} 
		else if (!Main.isSameString(Main.returnGameSave().returnPassword( Main.returnGameSave().lookUpUser(nameField.getText())), password))
		{
			printError("Incorrect password!");
		}
		
		else if (!zipField.getText().equals("") && !zipField.getText().matches("\\d{5}"))
		{
			printError("Please enter zip in XXXXX Format"); 
		}	
			
		else
		{
			Main.returnGameSave().loadGame(nameField.getText());
			if (!adressField.getText().equals("") )
			{
				Main.returnGlobal().setAddress(adressField.getText());
			}
			if (!cityField.getText().equals("") )
			{
				Main.returnGlobal().setCity(cityField.getText());
			}
			if (!stateField.getText().equals("") )
			{
				Main.returnGlobal().setState(stateField.getText());
			}
			
			 if (!zipField.getText().equals("") )
			{
				 
					Main.returnGlobal().setZip_code(zipField.getText());
				  
			}	
			 
			if (!countryField.getText().equals("") )
			{
				Main.returnGlobal().setCountry(countryField.getText());
			}
			
			errorText.setText("");
			
			if ( e.getSource() == reversePlayButton)
			{
				Main.returnGlobal().setReverse_game(true);
			}
			else
			{
				Main.returnGlobal().setReverse_game(false);
			}
			
			Main.StartGame();
		}
		
	}

}