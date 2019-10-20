import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class NewSaveScreen extends JPanel implements ActionListener {
	//Initialize variables
	JButton reversePlayButton = new JButton("Play Game - Reverse");
	private static JTextField nameField;
	private static JTextField birthdateField;
	private static JTextField adressField;
	private static JTextField cityField;
	private static JTextField stateField;
	private static JTextField zipField;
	private static JTextField countryField;
	private static JTextArea errorText;
	private static final long serialVersionUID = -7892106385327845406L;
    
	/*Constructor, invokes initUI*/
	public NewSaveScreen() {
      
        initUI();
    }
    
	/*Prints error if any fields are not set when button pressed*/
    public void printError(String text)
    {
    	
    	errorText.setText(text);
	 	revalidate();
    }

    /*Initializes and adds UI needed for all fields that are asked in new save*/
    private void initUI() {
    	Border border = BorderFactory.createMatteBorder(0, 0, 0, 0, Main.returnFrame().getBackground());
    	//Border border = BorderFactory.createMatteBorder(0, 0, 10, 0, Main.returnFrame().getBackground());
    	
    	nameField = createInputArea("Name: ", 110, border, nameField);
    	birthdateField = createInputArea("Birth Date: ", 110, border, birthdateField);
    	adressField = createInputArea("Street Address: ", 105, border, adressField);
    	cityField =createInputArea("City: ", 110, border, cityField);
    	stateField = createInputArea("State: ", 110, border, stateField);
    	zipField = createInputArea("Zip Code: ", 110 , border, zipField);
    	countryField = createInputArea("Country: ", 110 , border, countryField);
        
    	

    	
    	JButton playButton = new JButton("Play Game - Forward");
    	JButton loadButton = new JButton("Load Game");
    	JButton quitButton = new JButton("Quit Game");
    	//JButton reversePlayButton = new JButton("Play Game - Reverse");
    	///playButton.setBorder(border);
    	playButton.setLocation(550, 750);
        playButton.addActionListener(this);
        reversePlayButton.addActionListener(this);
        loadButton.addActionListener(new loadGameBtn());
        quitButton.addActionListener(new QuitBtn());
        add(playButton, BorderLayout.CENTER);
        add(reversePlayButton, BorderLayout.CENTER);
        add(loadButton, BorderLayout.CENTER);
        add(quitButton, BorderLayout.CENTER);
        
        errorText = new JTextArea(1, 5);
        errorText.setEditable(false);
        errorText.setText("");
        errorText.setBackground(Main.returnFrame().getBackground()); 
	 	add(errorText);
        
        Main.returnFrame().repaint();
    }
       
    /*Starts game if all fields are set, prints error if not*/
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println(e.getSource());
		
		if (nameField.getText().equals("") )
		{
			printError("You did not enter a name!");
		}
		else if (Main.loadGame(nameField.getText()) != -1)
		{
			printError("Please enter a different name, this save file exists already, or load save");
		}
		else if (birthdateField.getText().equals("") )
		{
			printError("You did not enter a birth date!");
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
		else if (countryField.getText().equals("") )
		{
			printError("You did not enter a country!");
		}
		else
		{
			errorText.setText("");
			Main.returnGlobal().setName(nameField.getText());
			Main.returnGlobal().setBirthdate(birthdateField.getText());
			Main.returnGlobal().setAddress(adressField.getText());
			Main.returnGlobal().setCity(cityField.getText());
			Main.returnGlobal().setState(stateField.getText());
			Main.returnGlobal().setZip_code(zipField.getText());
			Main.returnGlobal().setCountry(countryField.getText());
			
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

	/*Creates JTextFile from input text, length, border*/
	public JTextField createInputArea (String text, int length, Border border, JTextField field ) {
		createTextArea(text);
    	field = new JTextField(length);
    	field.setBorder(border);
    	
    	add(field);
    	return field;
	}
	
	/*Creates JText area from input text*/
   public JTextArea createTextArea(String text)
   {
		JTextArea textArea = new JTextArea(1, 5);
	   	textArea.setEditable(false);
	   	textArea.append(text);
	   	textArea.setBackground(Main.returnFrame().getBackground()); 
	 	add(textArea);
	   	return textArea;
   }
	
}