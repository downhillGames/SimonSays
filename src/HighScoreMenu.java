import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class HighScoreMenu extends JPanel implements ActionListener {
	
	// initialize variables
	private static JTextField nameField;
	private static JTextArea errorText;
	
	/*High Scores menu constructor, invokes initUI() */
    public HighScoreMenu() {
      
        initUI();
    }
    
    /*Prints High score if name is looked up*/
    public void print(String text)
    {
    	
    	errorText.setText(text);
	 	revalidate();
    }

    @SuppressWarnings("unused")
    /*Prints top 5 (or less if less than 5 saves) high scores in the save file*/
	private void initUI() {
    	
    	//various border to use
    	Border border = BorderFactory.createMatteBorder(0, 0, 0, 0, Main.returnFrame().getBackground());
    	Border border2 = BorderFactory.createMatteBorder(0, 100, 0, 0, Main.returnFrame().getBackground());
    	Border border3 = BorderFactory.createMatteBorder(0, 0, 0, 900, Main.returnFrame().getBackground());
    	Border border4 = BorderFactory.createMatteBorder(0, 600, 20, 600, Main.returnFrame().getBackground());
    	

        
    	double[] highScores =  Main.getHighScores().getValue();
    	String[] highScoreNames =  Main.getHighScores().getKey();
    	
    	
    	
    	if (highScoreNames.length != 0)
    	{
    		for (int i = 0; i < highScoreNames.length ; i++)
        	{
        		createTextArea(border4 , "Username: " + highScoreNames[i] + " Score: " + ((int) highScores[i]));
        	}
    		nameField = createInputArea("Look up high score, enter name: ", 90, border, nameField);
    	}
    	else
    	{
    		createTextArea(border4 , "No High Scores Exist on file!");
    	}
    	
    	
    	JButton menuButton = new JButton("Back to Main Menu");
    	JButton findPlayerButton = new JButton("Press to look up high score");
    	menuButton.addActionListener(new menuButton());
    	findPlayerButton.addActionListener(this);
    	 add(findPlayerButton, BorderLayout.CENTER);
    	add(menuButton, BorderLayout.CENTER);
        errorText = createTextArea(border4 , "");
        errorText.setEditable(false);
        errorText.setText("");
        errorText.setBackground(Main.returnFrame().getBackground()); 
	 	add(errorText);
        
        Main.returnFrame().repaint();
    }
       
	
	/*Prints High score if name is looked up*/
    @SuppressWarnings("rawtypes")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (nameField.getText().equals("") )
		{
			print("You did not enter a name!");
		}
		
		else if ( Main.lookUpUser(nameField.getText()) == -1)
		{
			print("That save file does not exist!");
		}
		else
		{   
			int i = Main.lookUpUser(nameField.getText());
			String key = (String) ((HashMap) Main.jsonArray.get(i)).get("key");
			String name = Main.decryptString((String ) ((HashMap) Main.jsonArray.get(i)).get("user_name"), key);
			String highlevel = Main.decryptString((String) ((HashMap) Main.jsonArray.get(i)).get("highlevel") , key);
			double highlevel_double = Double.valueOf(highlevel);
			int highlevel_int = ((int) highlevel_double);
			print("Username: " + name + " Score: " +  highlevel_int);
			
		}
		
        //textField.selectAll();
		
	}

    /*Creates a JTextField area with input size, text, border*/
	public JTextField createInputArea (String text, int length, Border border, JTextField field ) {
		createTextArea(border, text);
    	field = new JTextField(length);
    	field.setBorder(border);
    	
    	add(field);
    	return field;
	}
	
	/*Creates JText Area with input border and text*/
	 public JTextArea createTextArea(Border border, String text)
	   {
		   	//JTextArea textArea = new JTextArea(1, text.length());
		 	
		 	
			JTextArea textArea = new JTextArea(1, 5);
		   	textArea.setEditable(false);
		   	textArea.append(text);
		   	textArea.setBackground(Main.returnFrame().getBackground()); 
		   	textArea.setBorder(border);
		   	add(textArea);
		   	return textArea;
	   }
	
}