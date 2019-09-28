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

public class NewSaveScreen extends JPanel implements ActionListener {
	
	
	
	
	public static JTextField nameField;
	public static JTextField birthdateField;
	
	public static JTextField adressField;
	public static JTextField cityField;
	public static JTextField stateField;
	
	public static JTextField zipField;
	public static JTextField countryField;
	
	public static JTextArea errorText;
	
	
	private static final long serialVersionUID = -7892106385327845406L;
	static int lives = 6;
    public NewSaveScreen() {
      
        initUI();
    }
    
    public void printError(String text)
    {
    	
    	errorText.setText(text);
	 	revalidate();
    }

    private void initUI() {
    	Border gf = BorderFactory.createMatteBorder(0, 0, 0, 0, Main.returnFrame().getBackground());
    	Border border = BorderFactory.createMatteBorder(0, 0, 10, 0, Main.returnFrame().getBackground());
    	
    	nameField = createInputArea("Name: ", 110, gf, nameField);
    	birthdateField = createInputArea("Birth Date: ", 110, gf, birthdateField);
    	adressField = createInputArea("Street Address: ", 105, gf, adressField);
    	cityField =createInputArea("City: ", 110, gf, cityField);
    	stateField = createInputArea("State: ", 110, gf, stateField);
    	zipField = createInputArea("Zip Code: ", 110 , gf, zipField);
    	countryField = createInputArea("Country: ", 110 , gf, countryField);
        
    	

    	
    	JButton playButton = new JButton("Play Game");
    	///playButton.setBorder(border);
    	playButton.setLocation(550, 750);
        playButton.addActionListener(this);
        add(playButton, BorderLayout.CENTER);
       
        errorText = new JTextArea(1, 5);
        errorText.setEditable(false);
        errorText.setText("");
        errorText.setBackground(Main.returnFrame().getBackground()); 
	 	add(errorText);
        
        Main.returnFrame().repaint();
    }
       
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (nameField.getText().equals("") )
		{
			printError("You did not enter a name!");
		}
		else
		{
			errorText.setText("");
			Main.returnGlobal().name = nameField.getText();
			//System.out.print(player_name);
			Main.StartGame();
		}
		
        //textField.selectAll();
		
	}

	public JTextField createInputArea (String text, int length, Border border, JTextField field ) {
		createTextArea(text);
    	field = new JTextField(length);
    	field.setBorder(border);
    	
    	add(field);
    	return field;
	}
	
   public JTextArea createTextArea(String text)
   {
	   	//JTextArea textArea = new JTextArea(1, text.length());
		JTextArea textArea = new JTextArea(1, 5);
	   	textArea.setEditable(false);
	   	textArea.append(text);
	   	textArea.setBackground(Main.returnFrame().getBackground()); 
	 	add(textArea);
	   	return textArea;
   }
	
}