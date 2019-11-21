import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.json.simple.JSONArray;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class HighScoreMenu extends Menu implements ActionListener {
	
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
    	Border border4 = BorderFactory.createMatteBorder(0, 600, 5, 600, Main.returnFrame().getBackground());
    	

        
    	double[] highScores =  Main.returnGameSave().getHighScores().getValue();
    	String[] highScoreNames =  Main.returnGameSave().getHighScores().getKey();
    	
    	System.out.println(Main.returnGlobal().getInteractionArray());
    	
    	if (highScoreNames.length != 0)
    	{
    		for (int i = 0; i < highScoreNames.length ; i++)
        	{
    			String forwardString = new String();
    			if ( (double) Main.returnGlobal().getInteractionArray().get(i) == 1.0 )
    			{
    				forwardString = "Forward";
    			}
    			else if ((double) Main.returnGlobal().getInteractionArray().get(i) == 2.0)
    			{
    			   forwardString = "Reversed";
    			}
    				
        		createTextArea(border4 , "Username: " + highScoreNames[i] + "..................Score: " + ((int) highScores[i]) + "   ---   "+ forwardString );
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
    @SuppressWarnings({ "rawtypes", "static-access" })
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (nameField.getText().equals("") )
		{
			print("You did not enter a name!");
		}
		
		else if ( Main.returnGameSave().lookUpUser(nameField.getText()) == -1)
		{
			print("That save file does not exist!");
		}
		else
		{   
			Border border = BorderFactory.createMatteBorder(0, 0, 0, 0, Main.returnFrame().getBackground());
			Border border2 = BorderFactory.createMatteBorder(0, 500, 0, 500, Main.returnFrame().getBackground());
			int i = Main.returnGameSave().lookUpUser(nameField.getText());
			String key = (String) ((HashMap) Main.returnGameSave().getSavesArray().get(i)).get("zxbvwoved7");
			String name = Main.decryptString((String ) ((HashMap) Main.returnGameSave().getSavesArray().get(i)).get("rjc8qhtv1w"), key);
			String highlevel = Main.decryptString((String) ((HashMap) Main.returnGameSave().getSavesArray().get(i)).get("pplk7r7pbp") , key);
			double highlevel_double = Double.valueOf(highlevel);
			int highlevel_int = ((int) highlevel_double);
			print("Username: " + name + " High score: " +  highlevel_int);
			
			JSONArray levelArr = decryptArray((JSONArray ) ((HashMap) Main.returnGameSave().getSavesArray().get(i)).get("gjw2201t44") , key);
			JSONArray forwardArr = decryptArray((JSONArray ) ((HashMap) Main.returnGameSave().getSavesArray().get(i)).get("kUnu83XHme") , key);
			 
		
			 
			createTextArea(border2 , name + " has attempted the game " + levelArr.size() + " time(s)");

			for (int j = 0; j < levelArr.size() ; j++)
			{
				String forwardString;
				if ((double) forwardArr.get(j+1) == 1.0) {
					forwardString = "Forward";
				}
				else
				{
					forwardString = "Reversed";
				}
				createTextArea(border , "Attempt " + (j+1) + " - Score: " + levelArr.get(j) + " It was: " + forwardString );
			}
		}
		
        //textField.selectAll();
		
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
	 
	 
	 /**/
		@SuppressWarnings("unchecked")
		public static JSONArray decryptArray(JSONArray inArray , String key){
			
			JSONArray outArray = new JSONArray();
			for (int i = 0; i < inArray.size() ; i ++)
			{
				String temp =  Main.decryptString((String )inArray.get(i), key) ;
				outArray.add(Double.valueOf(temp)); 
			}
			return outArray;
		}
	
}