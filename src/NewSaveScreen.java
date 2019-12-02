import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class NewSaveScreen extends Menu implements ActionListener {
	
	//Initialize variables
	JButton reversePlayButton = new JButton("Play Game - Reverse");
	private static JTextField nameField;
	private static JTextField passwordField;
	private static JTextField birthdateField;
	private static JTextField adressField;
	private static JTextField cityField;
	private static JTextField stateField;
	private static JTextField zipField;
	private static JTextField countryField;
	private static JTextArea errorText;
	private String password = "";
	private static final long serialVersionUID = -7892106385327845406L;
	int password_check_times = 0;
	
	long clock_timer;
	/*Constructor, invokes initUI*/
	public NewSaveScreen() {
      
        initUI();
        Thread t = new Thread(new Runnable() {
            public void run() {
            	 setPasswordField();
            }
        });
        t.start();
        
    }
    
	/*Prints error if any fields are not set when button pressed*/
    public void printError(String text)
    {
    	
    	errorText.setText(text);
	 	revalidate();
    }

    
    /*Returns the numerical month the player inputs*/
    public static int returnMonth(String bdate)
    {
    	String monthString = bdate.substring(0, 2);
    	
    	int month = Integer.valueOf(monthString);
    	
    	return month;
    }
    
    /*Returns the day of the month that the player inputs*/
    public static int returnDay(String bdate)
    {
    	String dayString = bdate.substring(3, 5);
    	int day = Integer.valueOf(dayString);
    	System.out.println(day + " DAY");
    	return day;
    }
    
    /*Sets asterisks in password field*/
    void setPasswordField() {
    	 while (password_check_times < 1000) {
    		 
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
     	    	System.out.println("Append" + passwordAppend);
    			 
    			 int asterisks = passwordField.getText().length();
    			 
    			 passwordField.setText(returnAsterisks(asterisks));
    			 password_check_times ++;
                 clock_timer = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
                 passwordField.setCaretPosition(asterisks);
                 password = password + passwordAppend;
             }
    		 
         }
    	 
    	 
    }
    
	String returnAsterisks(int amount) {
		String Astericks = "";
		for (int i = 0; i < amount ; i++)
		{
			Astericks = "*" + Astericks;
		}
		
    	return Astericks;
    	
    }
    
    /*Initializes and adds UI needed for all fields that are asked in new save*/
    private void initUI() {
    	Border border = BorderFactory.createMatteBorder(0, 0, 0, 0, Main.returnFrame().getBackground());
    	//Border border = BorderFactory.createMatteBorder(0, 0, 10, 0, Main.returnFrame().getBackground());
    	
    	nameField = createInputArea("Name: ", 110, border, nameField);
    	passwordField = createInputArea("Password: ", 110, border, passwordField);
    	birthdateField = createInputArea("Birth Date: ", 110, border, birthdateField);
    	adressField = createInputArea("Street Address: ", 105, border, adressField);
    	cityField =createInputArea("City: ", 110, border, cityField);
    	stateField = createInputArea("State: ", 110, border, stateField);
    	zipField = createInputArea("Zip Code: ", 110 , border, zipField);
    	countryField = createInputArea("Country: ", 110 , border, countryField);
        
    	

    	
    	JButton playButton = new JButton("Play Game - Forward");
    	JButton loadButton = new JButton("Load Game");
    	JButton menuButton = new JButton("Back to Menu");
    	JButton quitButton = new JButton("Quit Game");
    	//JButton reversePlayButton = new JButton("Play Game - Reverse");
    	///playButton.setBorder(border);
    	playButton.setLocation(550, 750);
        playButton.addActionListener(this);
        reversePlayButton.addActionListener(this);
        loadButton.addActionListener(new loadGameBtn());
        menuButton.addActionListener(new menuButton());
        quitButton.addActionListener(new QuitBtn());
        add(playButton, BorderLayout.CENTER);
        add(reversePlayButton, BorderLayout.CENTER);
        add(loadButton, BorderLayout.CENTER);
        add(menuButton, BorderLayout.CENTER);
        add(quitButton, BorderLayout.CENTER);
        
        errorText = new JTextArea(1, 5);
        errorText.setEditable(false);
        errorText.setText("");
        errorText.setBackground(Main.returnFrame().getBackground()); 
	 	add(errorText);
        
        Main.returnFrame().repaint();
        clock_timer = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
    }
       
    /*Starts game if all fields are set, prints error if not*/
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println(e.getSource());
		
		if (nameField.getText().equals("") )
		{
			printError("You did not enter a name!");
		}
		else if (Main.returnGameSave().loadGame(nameField.getText()) != -1 || nameField.getText().equals("DEV_MODE"))
		{
			printError("Please enter a different name, this save file exists already, or load save");
		}
		else if (passwordField.getText().equals(""))
		{
			printError("You did not enter  password!");
		}
		else if (birthdateField.getText().equals("") )
		{
			printError("You did not enter a birth date!");
		}
		else if (!birthdateField.getText().matches("\\d{2}\\-\\d{2}\\-\\d{4}")  )
		{
			printError("Please enter birtdate in MM-DD-YYYY Format");
		}
		
		else if (Main.returnAge(birthdateField.getText()) < 5 ||  Main.returnAge(birthdateField.getText()) > 125 )
		{
			printError("Please enter a correct birth year ");
		}
		
		else if (returnMonth(birthdateField.getText()) < 0 ||  returnMonth(birthdateField.getText()) > 12 )
		{
			printError("Please enter a correct month");
		}
		
		else if (returnDay(birthdateField.getText()) < 0 ||  returnDay(birthdateField.getText()) > 31 )
		{
			printError("Please enter a correct date");
		}
		
		else if (adressField.getText().equals("") )
		{
			printError("You did not enter an address!");
		}
		else if (cityField.getText().equals("") )
		{
			printError("You did not enter a city!");
		}
		else if (stateField.getText().equals("") )
		{
			printError("You did not enter a state!");
		}
		else if (zipField.getText().equals("") )
		{
			printError("You did not enter a zip code!");
		}
		else if (!zipField.getText().matches("\\d{5}")  )
		{
			printError("Please enter zip in XXXXX Format");
		}
		else if (countryField.getText().equals("") )
		{
			printError("You did not enter a country!");
		}
		else
		{
			errorText.setText("");
			Main.returnGlobal().setName(nameField.getText());
			Main.returnGlobal().setBirthdate(birthdateField.getText());
			Main.returnGlobal().setPassword(password);
			Main.returnGlobal().setAddress(adressField.getText());
			Main.returnGlobal().setCity(cityField.getText());
			Main.returnGlobal().setState(stateField.getText());
			Main.returnGlobal().setZip_code(zipField.getText());
			Main.returnGlobal().setCountry(countryField.getText());
			System.out.println("Password" + password);
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