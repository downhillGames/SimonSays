import java.awt.EventQueue;
import javax.xml.bind.DatatypeConverter;
import org.json.simple.*;
import javax.swing.*;
//all these are for encryption
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


public class Main {
	//initialize variables
  static GameScreen game_screen = new GameScreen();
  static GameSave gameSave = new GameSave();
  static Global global = new Global();
  static Menu menu = new MainMenu();
  static Map map = new Map(); 
  static Game game = new Game(map);
  static PauseScreen pause = new PauseScreen();
  static SimGame simGame;
  static JButton outArry[] = new JButton[9];
  
  
  
  	/*Returns a new encoded DES key for encryption*/
  	public static String getEncodedKey ()
  {
    String encodedKey = new String ();
    try
    {
      KeyGenerator keyGen = KeyGenerator.getInstance ("DES");
      SecretKey key = keyGen.generateKey ();

      encodedKey = Base64.getEncoder().encodeToString (key.getEncoded ());

    }
    catch (NoSuchAlgorithmException e)
    {
      e.printStackTrace ();
    }
    return encodedKey;
  }

  	/*Returns a new string encrypted with DES algorithm from input string to encrypt and encoded key*/
  	public static String encryptString (String stringToEncrypt, String encodedKey)
  {
    byte[]encryptedStringInBytes = null;
    String encryptedString = new String ();
    try
    {
      Cipher cipher = Cipher.getInstance ("DES/ECB/PKCS5Padding");
     

      byte[]decodedKey = Base64.getDecoder ().decode (encodedKey);
      SecretKey key = new SecretKeySpec (decodedKey, 0, decodedKey.length, "DES");
      byte[]stringInBytes = stringToEncrypt.getBytes ();
      cipher.init (Cipher.ENCRYPT_MODE, key);
      encryptedStringInBytes = cipher.doFinal (stringInBytes);

      encryptedString = DatatypeConverter.printBase64Binary (encryptedStringInBytes);

    }
    catch (NoSuchAlgorithmException | BadPaddingException |
	   InvalidKeyException | IllegalBlockSizeException |
	   NoSuchPaddingException e)
    {
      e.printStackTrace ();
    }

    return encryptedString;
  }

  	/*Returns a new string decrypted with DES algorithm from input string to decrypt and encoded key*/
  	public static String decryptString (String encryptedString, String encodedKey)
  {
    String decrypted = null;
    byte[]decryptedStringInBytes = null;
    try
    {

      decryptedStringInBytes = DatatypeConverter.parseBase64Binary (encryptedString);
      byte[]decodedKey = Base64.getDecoder ().decode (encodedKey);
      SecretKey originalKey = new SecretKeySpec (decodedKey, 0, decodedKey.length, "DES");

      Cipher cipher = Cipher.getInstance ("DES/ECB/PKCS5Padding");
      cipher.init (Cipher.DECRYPT_MODE, originalKey);
      byte[]decryptedString = cipher.doFinal (decryptedStringInBytes);
      decrypted = new String (decryptedString);


    }
    catch (NoSuchAlgorithmException | BadPaddingException |
	   InvalidKeyException | IllegalBlockSizeException |
	   NoSuchPaddingException e)
    {
      e.printStackTrace ();
    }

    return decrypted;
  }

  

	
  	
  	
  	public static boolean isSameString(String str1,String str2)
  	{
  		boolean isSameString = true;
  		
  		if (str1.compareTo(str2) != 0 )
  		{
  			isSameString = false ;
  		}
  		if (str1.length() == str2.length())
  		{
  			for (int i = 0; i < str1.length() - 1; i++  )
  	  		{
  	  			if (str1.charAt(i) != str2.charAt(i))
  	  			{
  	  				isSameString = false ;
  	  			
  	  			}
  	  		}
  		}
  		else
  		{
  			isSameString = false ;
  		}
  		
  		
  		return isSameString;
  	}
  	
  	
 
	
	/*Creates a new JSON Array of the interactions array encrypted for saving purposes*/
	@SuppressWarnings("unchecked")
	public static JSONArray encryptArray(JSONArray inArray, String key){
		JSONArray outArray = new JSONArray();
		for (int i = 0; i < inArray.size() ; i ++)
		{
			outArray.add(encryptString(String.valueOf(inArray.get(i)), key)) ;
			
		}
		return outArray;
	}

	/*Returns a new JSON Array of the given encrypted JSON Array Interactions Array*/
	@SuppressWarnings("unchecked")
	public static void decryptInteractionArray(JSONArray inArray , String key){
		returnGlobal().getInteractionArray().clear();
		
		for (int i = 0; i < inArray.size() ; i ++)
		{
			String temp =  decryptString((String )inArray.get(i), key) ;
			returnGlobal().getInteractionArray().add(Double.valueOf(temp)); 
		}
	 System.out.println(returnGlobal().getInteractionArray());
	}
	
	@SuppressWarnings("unchecked")
	/*Stores a decrypted version of input array in scores array*/
	public static void decryptScoresArray(JSONArray inArray , String key){
		returnGlobal().getScoresArray().clear();
		
		for (int i = 0; i < inArray.size() ; i ++)
		{
			String temp =  decryptString((String )inArray.get(i), key) ;
			returnGlobal().getScoresArray().add(Integer.valueOf(temp)); 
		}
	 System.out.println(returnGlobal().getScoresArray());
	}
	
	
	
	
	
	/*Starts the simulation game from sim menu*/
    public static void StartSim(){
    	
    	EventQueue.invokeLater(() -> {
    		  game_screen.setVisible(false);
              game_screen.remove(menu);
              game_screen.add(simGame);
              game_screen.setVisible(true);
    	});
    	
    	// invoke game's main loop after map is created
    	EventQueue.invokeLater(() -> {
    		simGame.displayGUI();
  	});
    	
    
    }
  
	/*Starts the main game from either new save or load save menus*/
    public static void StartGame(){
    	
    	EventQueue.invokeLater(() -> {
    		  game_screen.setVisible(false);
              game_screen.remove(menu);
              game_screen.add(game);
              game_screen.setVisible(true);
    	});
    	
    	// invoke game's main loop after map is created
    	EventQueue.invokeLater(() -> {
    		 game.displayGUI();
  	});
    	
    
    }

 
    
    
    public static int returnAge(String bdate)
    {
    	int age = 0;
    	
    	String yearString = bdate.substring(6);
    	int yearInt = Integer.valueOf(yearString);
    	age = 2019 - yearInt; 
    	
    	return age;
    }
    
    /*Returns to the main menu (reinvokes main), resets variables*/
    public static void returnToMenu()
    {
    	 returnGlobal().setNewGame(true);
    	 returnGlobal().setHealth(1);
    	 returnGlobal().setLevel(0);
    	 returnGlobal().setTimes_won(0);
    	 returnGlobal().setSpeed(1000);
    	 returnGlobal().getInteractionArray().clear();
    	 game_screen.setVisible(false);
         game_screen.remove(menu);
         
         if (Main.returnGlobal().getMode() == 4)
         {
        	 Main.returnGlobal().setMode(1);
         }
         game_screen.remove(game);
        readyGameNoStart();
         menu = new MainMenu();
         game_screen.add(menu);
         game_screen.setVisible(true);
         //main(null);
         nullSim();
    }
    
    /*Readies the sim for a new round, but does not start it*/
    public static void readySimNoStart()
    {
        simGame.setButton_pressed_index(0);
        simGame.setComputer_pressed_index(0);
        
        game_screen.setVisible(false);
        game_screen.remove(menu);
        
        Main.returnGlobal().setFirst_hit(true);
    }
    
    /*Readies the game for a new round, but does not start it*/
    public static void readyGameNoStart()
    {
        map = new Map();
        game = new Game(map);
        game.setButton_pressed_index(0);
        game.setComputer_pressed_index(0);
        
        game_screen.setVisible(false);
        game_screen.remove(menu);
        Main.returnGlobal().setFirst_hit(true);
    }
    
    /*Readies the game for a new round, and invokes startGame() */
    public static void readySim()
    {
      readySimNoStart();
      StartSim();
    }
    
    /*Readies the game for a new round, and invokes startGame() */
    public static void readyGame()
    {
      readyGameNoStart();
      
      StartGame();
    }

    /*Returns the main game instance*/
    public static Game returnGame(){
        return game;
    }
    
    /*Returns the main game instance*/
    public static SimGame returnSim(){
        return simGame;
    }

    /*Returns the game screen instance (the window itself)*/
    public static GameScreen returnFrame(){
        return game_screen;
    }
    
    /*Returns the global instance*/
    public static Global returnGlobal(){
        return global;
    }
    
    /*Returns the global instance*/
    public static GameSave returnGameSave(){
        return gameSave;
    }
    
    public static void newSim(){
        simGame = new SimGame();
    }
    
    public static void nullSim(){
        simGame = null;
    }

    /*Goes to the new player screen (removing any other screen & adding new save to JFrame)*/
    public static void PlayNewSaveMenu()
    {
    EventQueue.invokeLater(() -> {
	      game_screen.setVisible(false); 
	      game_screen.remove(menu);
	      menu = new NewSaveScreen();
	      game_screen.add(menu);
	      game_screen.setVisible(true);
    	});
    }
   
    /*Goes to the load player screen (removing any other screen & adding load save to JFrame)*/
    public static void PlayLoadSaveMenu()
    {
    EventQueue.invokeLater(() -> {
	      game_screen.setVisible(false); 
	      game_screen.remove(menu);
	      menu = new LoadSaveScreen();
	      game_screen.add(menu);
	      game_screen.setVisible(true);
    	});
    }
    
    /*Goes to the pause screen (removing game & adding pause screen to JFrame)*/
    public static void PlayPause()
    {
      game_screen.setVisible(false); 
      game_screen.remove(game);
      if (simGame != null)
      {
    	  game_screen.remove(simGame);  
      }
      game_screen.add(pause);
      game_screen.setVisible(true);
    }

    public static void PlayHighScoreMenu()
    {
    	game_screen.setVisible(false); 
    	game_screen.remove(menu);
	    menu = new HighScoreMenu();
	    game_screen.add(menu);
	    game_screen.setVisible(true);
    }
    
    public static void PlaySimulationMenu()
    {
    	game_screen.setVisible(false); 
    	game_screen.remove(menu);
	    menu = new simulationMenu();
	    game_screen.add(menu);
	    game_screen.setVisible(true);
    }
    
    
    /*Goes to the lose continue screen (removing game & adding lose continue screen to JFrame)*/
    public static void PlayLoseContinue()
    {
    	game_screen.setVisible(false); 
    	game_screen.remove(menu);
    	game_screen.remove(game);
    	if (simGame != null)
    	{
    		game_screen.remove(simGame);
    	}
	    menu = new LostScreenContinue();
	    game_screen.add(menu);
	    game_screen.setVisible(true);
    }
	
    /*Goes to the lose screen (removing game & adding lose screen to JFrame)*/
    @SuppressWarnings("unchecked")
	public static void PlayLose()
    {
     game_screen.setVisible(false); 
      game_screen.remove(menu);
      game_screen.remove(game);
      if (simGame != null)
  		{
  		game_screen.remove(simGame);
  		}
	  menu = new LostScreen();
	  game_screen.add(menu);
	  game_screen.setVisible(true);
      returnGlobal().setTotal_gametime(returnGlobal().getTotal_gametime() + returnGlobal().getGametime());
      //(returnGlobal().getLevel())
      returnGlobal().getScoresArray().add(returnGlobal().getLevel());
      
      if (returnGlobal().getMode() != 4)
      {
    	  if (returnGlobal().isNewGame())
          {
        	  returnGlobal().setHigh_level(returnGlobal().getLevel());
        	  if (returnGlobal().getHigh_level() < 5 && returnAge(returnGlobal().getBirthdate()) > 13)
        	  {
        		  returnGlobal().setDiagnosis("Possibly AD");
        	  }
        	  else if (returnGlobal().getHigh_level() >= 5 && returnAge(returnGlobal().getBirthdate()) > 13) 
        	  {
        		  returnGlobal().setDiagnosis("Likely not AD"); 
        	  }
        	  else if (returnAge(returnGlobal().getBirthdate()) <= 13)
        	  {
        		  returnGlobal().setDiagnosis("Too young to diagnose"); 
        	  }
        	  GameSave.appendToSaves();
        	  
          }
          else
          {
        	if (returnGlobal().getLevel() >  returnGlobal().getHigh_level())
        	{
        		returnGlobal().setHigh_level(returnGlobal().getLevel());
        	}
        	if (returnGlobal().getHigh_level() < 5 && returnAge(returnGlobal().getBirthdate()) > 13)
      	  	{
      		  	returnGlobal().setDiagnosis("Possibly AD");
      	  	}
      	  	else if (returnGlobal().getHigh_level() >= 5 && returnAge(returnGlobal().getBirthdate()) > 13) 
      	  	{
      	  		returnGlobal().setDiagnosis("Likely not AD"); 
      	  	}
      	  	else if (returnAge(returnGlobal().getBirthdate()) <= 13)
      	  	{
      	  		returnGlobal().setDiagnosis("Too young to diagnose"); 
      	  	}
        	 GameSave.replaceSave(returnGlobal().getLoadGamePosition());
          }
      }
     
    }

    /*Goes to the win screen (removing game & adding win screen to JFrame)*/
    public static void PlayWin(){
    	game_screen.setVisible(false); 
    	game_screen.remove(menu);
    	game_screen.remove(game);
    	
    	if (simGame != null)
    	{
    		game_screen.remove(simGame);
    	}
    	
	    menu = new WinScreen();
	    game_screen.add(menu);
	    game_screen.setVisible(true);
        
    }

    /*Goes to the help menu (removing main menu & adding help menu to JFrame)*/
	public static void PlayHelpMenu() {
		game_screen.setVisible(false); 
    	game_screen.remove(menu);
	    menu = new HelpMenu();
	    game_screen.add(menu);
	    game_screen.setVisible(true);
		
	}
	
	


	/*Main method of program*/
	public static void main(String[] args) {

	       EventQueue.invokeLater(() -> {
	            game_screen.add(menu);
	            GameSave.checkForSaveFile();

	            game_screen.setVisible(true);
	        });
	    }

	

}