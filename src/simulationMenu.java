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
public class simulationMenu extends Menu implements ActionListener {
	
	// initialize variables
	private static JTextField nameField;
	private static JTextField gameField;
	private static JTextArea errorText;
	private static  JButton findPlayerButton;
	
	private JSONArray gameStartIndexes;
	private Integer amountOfGames;
	
    public simulationMenu() {
      
        initUI();
    }
    
    public void print(String text)
    {
    	
    	errorText.setText(text);
	 	revalidate();
    }

    @SuppressWarnings("unused")

	private void initUI() {
    	
    	//various border to use
    	Border border = BorderFactory.createMatteBorder(0, 0, 0, 0, Main.returnFrame().getBackground());
    	Border border2 = BorderFactory.createMatteBorder(0, 100, 0, 0, Main.returnFrame().getBackground());
    	Border border3 = BorderFactory.createMatteBorder(0, 0, 0, 900, Main.returnFrame().getBackground());
    	Border border4 = BorderFactory.createMatteBorder(0, 600, 5, 600, Main.returnFrame().getBackground());
    	

        

    	nameField = createInputArea("Look up name to sim: ", 90, border, nameField);
    	
    	
    	
    	
    	JButton menuButton = new JButton("Back to Main Menu");
    	findPlayerButton = new JButton("Press to start sim");
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
       
	
	/*looks up user for simulation game*/
    @SuppressWarnings({ "rawtypes", "static-access" })
	@Override
	public void actionPerformed(ActionEvent e) {
		
    	if ( e.getSource() == findPlayerButton)
		{
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
    			Main.returnGameSave().loadGame(nameField.getText());
    			String key = (String) ((HashMap) Main.returnGameSave().getSavesArray().get(i)).get("zxbvwoved7");
    			String name = Main.decryptString((String ) ((HashMap) Main.returnGameSave().getSavesArray().get(i)).get("rjc8qhtv1w"), key);
    			String highlevel = Main.decryptString((String) ((HashMap) Main.returnGameSave().getSavesArray().get(i)).get("pplk7r7pbp") , key);
    			double highlevel_double = Double.valueOf(highlevel);
    			int highlevel_int = ((int) highlevel_double);
    			print("Username: " + name + " High score: " +  highlevel_int);
    			
    			JSONArray levelArr = decryptArray((JSONArray ) ((HashMap) Main.returnGameSave().getSavesArray().get(i)).get("gjw2201t44") , key);
    			
    			createTextArea(border2 , name + " has attempted the game " + levelArr.size() + " time(s)");
    			
    			for (int j  = 1; j < Main.returnGlobal().getForwardArray().size() ; j++ )
    			{
    				//int backward2 = (int) Math.round( (double)  Main.returnGlobal().getInteractionArray().get((int) gameStartIndexes.get(j) + 42));
    				
    				if ((double) Main.returnGlobal().getForwardArray().get(j) == 1.0 )
        			{ 
    					createTextArea(border2 ,"Game # "  + (j) +" was played Forward    -- Score: " + levelArr.get(j-1));
        				//createTextAreaLineBreak("Game # "  + (j+2) +" was played Forward");
        			}
        			else if ((double) Main.returnGlobal().getForwardArray().get(j) == 2.0)
        			{
        				createTextArea(border2 ,"Game # " + (j) +" played in reverse      -- Score: " + levelArr.get(j-1));
        				//createTextAreaLineBreak("Game # " + (j+2) +" played in reverse");
        			}
    			}
    			
    			amountOfGames = levelArr.size();
    			gameField = createInputArea("Which game would you like to sim?: ", 90, border, gameField);
    			
    			JButton startButton = new JButton("Press to start sim");
    			add(startButton, BorderLayout.CENTER);
    	    	startButton.addActionListener(this);
    			
    		}
    		
		}
    	else
    	{
    		if (gameField.getText().equals("") || isNumeric(gameField.getText()) == false )
    		{
    			print("You did not enter a number!");
    		}
    		
    		else if ( Integer.valueOf(gameField.getText()) > amountOfGames || Integer.valueOf(gameField.getText()) < 0)
    		{
    			print("That amount of games does not exist");
    		}
    		else
    		{
    			gameStartIndexes = getStartingIndexes(Main.returnGlobal().getInteractionArray());
    			 
    			System.out.println(gameStartIndexes);
    			
    			if (Integer.valueOf(gameField.getText())  == 1 )
    			{
    				Main.returnGlobal().setSimIndex(-1);
    			}
    			else
    			{
    				int val = Integer.valueOf(gameField.getText()) - 2;
    				Main.returnGlobal().setSimIndex((int) gameStartIndexes.get(val));
    			}
    			
    			Main.returnGlobal().setFirstSim(true);
    			Main.returnGlobal().setMode(4);
    			Main.newSim();
    			Main.readySim();
    			
    		}
    	}
		
		
		

		
	}

    
    @SuppressWarnings("unchecked")
    /*returns a JSON Array with all of the indexes of '-3' in interactions array, signifying a new game*/
	public JSONArray getStartingIndexes(JSONArray InArray)
    {
		JSONArray startIndexes = new JSONArray();
    	
		for (int i = 0; i < InArray.size() -1 ; i++)
		{
			double val = (double) InArray.get(i);
			if (val == -3.0)
			{
				startIndexes.add(i);
			}
		}
		
    	return startIndexes;
    	
    }
    
    /*Creates a JTextField area with input size, text, border*/
	public JTextField createInputArea (String text, int length, Border border, JTextField field ) {
		createTextArea(border, text);
    	field = new JTextField(length);
    	field.setBorder(border);
    	
    	add(field);
    	return field;
	}
	
	/*returns if T if a string is a numeric F if not by returning F if exception is thrown / caught*/
	@SuppressWarnings("unused")
	public static boolean isNumeric(String strNum) {
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException | NullPointerException nfe) {
	        return false;
	    }
	    return true;
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
	 
	 
	 /*returns a decrypted JSON array from given encrypted JSON array*/
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