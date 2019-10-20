//import java.awt.EventQueue;
//import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class LoadSaveScreen extends JPanel implements ActionListener {
	
	
	//initialize needed variables 
	JButton reversePlayButton = new JButton("Play Game - Reverse");
	private static JTextField nameField;
	private static JTextField adressField;
	private static JTextField cityField;
	private static JTextField stateField;
	private static JTextField zipField;
	private static JTextField countryField;
	private static JTextArea errorText;
	private static final long serialVersionUID = -7892106385327845406L;
    
	 /*Constructor -  invokes initUI() */
	public LoadSaveScreen() {
      
        initUI();
    }
    
    /*Prints error if load game is unsuccessful */
    public void printError(String text)
    {
    	
    	errorText.setText(text);
	 	revalidate();
    }

    /*Initializes all the needed load Game fields for the UI*/
    @SuppressWarnings("unused")
	private void initUI() {
    	Border border = BorderFactory.createMatteBorder(0, 0, 0, 0, Main.returnFrame().getBackground());
    	Border border2 = BorderFactory.createMatteBorder(0, 100, 0, 0, Main.returnFrame().getBackground());
    	Border border3 = BorderFactory.createMatteBorder(0, 0, 0, 900, Main.returnFrame().getBackground());
    	
    	nameField = createInputArea("Name: ", 110, border, nameField);
    	JTextArea text = createTextArea("Need to update anything? Leave field blank if not");
    	text.setBorder(border3);
    	adressField = createInputArea("Street Address: ", 105, border, adressField);
    	cityField =createInputArea("City: ", 110, border, cityField);
    	stateField = createInputArea("State: ", 110, border, stateField);
    	zipField = createInputArea("Zip Code: ", 110 , border, zipField);
    	countryField = createInputArea("Country: ", 110 , border, countryField);
        
    	

    	
    	JButton playButton = new JButton("Play Game - Forward");
    	JButton newButton = new JButton("New Game");
    	JButton quitButton = new JButton("Quit Game");
    	//JButton reversePlayButton = new JButton("Play Game - Reverse");
    	///playButton.setBorder(border);
    	playButton.setLocation(550, 750);
        playButton.addActionListener(this);
        reversePlayButton.addActionListener(this);
        
        newButton.addActionListener(new NewSaveBtn());
        quitButton.addActionListener(new QuitBtn());
        
        add(playButton, BorderLayout.CENTER);
        add(reversePlayButton, BorderLayout.CENTER);
        add(newButton, BorderLayout.CENTER);
        add(quitButton, BorderLayout.CENTER);
        
        errorText = new JTextArea(1, 5);
        errorText.setEditable(false);
        errorText.setText("");
        errorText.setBackground(Main.returnFrame().getBackground()); 
	 	add(errorText);
        
        Main.returnFrame().repaint();
    }
       
	
	/*Upon click, starts game if load is successful - or shows error also updates any needed fields*/
    @Override
	public void actionPerformed(ActionEvent e) {
		if (nameField.getText().equals("") )
		{
			printError("You did not enter a name!");
		}
		
		else if ( Main.loadGame(nameField.getText()) == -1)
		{
			printError("That save file does not exist!");
		}
		else
		{
			
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

	/*Creates JTextField with input text, length, border*/
	public JTextField createInputArea (String text, int length, Border border, JTextField field ) {
		createTextArea(text);
    	field = new JTextField(length);
    	field.setBorder(border);
    	
    	add(field);
    	return field;
	}
	
	/*Creates JTextArea with input text*/
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