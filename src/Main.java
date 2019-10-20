import java.awt.EventQueue;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import javax.xml.bind.DatatypeConverter;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
import java.util.AbstractMap;


public class Main {
	//initialize variables
  static GameScreen game_screen = new GameScreen();
  static MainMenu menu = new MainMenu();
  static Map map = new Map();
  static Global global = new Global();
  static Game game = new Game(map);
  static WinScreen win;
  static PauseScreen pause = new PauseScreen();
  static NewSaveScreen newSave = new NewSaveScreen();
  static LoadSaveScreen loadSave = new LoadSaveScreen();
  static HighScoreMenu highScoreMenu;
  static HelpMenu helpMenu = new HelpMenu();
  static LostScreen lost = new LostScreen();
  static LostScreenContinue lostContinue;
  static JButton outArry[] = new JButton[9];
  static int lives = 17;
  static JSONArray jsonArray;
  
  	/*Returns a new encoded DES key for encryption*/
  	public static String getEncodedKey ()
  {
    String encodedKey = new String ();
    try
    {
      KeyGenerator keyGen = KeyGenerator.getInstance ("DES");
      SecretKey key = keyGen.generateKey ();

      encodedKey = Base64.getEncoder ().encodeToString (key.getEncoded ());

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

  	/*Creates JSON Array that stores all saves from file*/
  	public static void createSavesObj() {
	  JSONParser jsonParser = new JSONParser();
		
	  try {
		Object saves_obj = jsonParser.parse(new FileReader("saves.json"));
		jsonArray = (JSONArray)saves_obj;
		System.out.println(jsonArray);
	} catch (IOException | ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
  }

	/*Returns the top 5 (if 5 exist or else just top scores in order) scores and their user names in two separate arrays (double array scores, string array user names)*/
  	@SuppressWarnings("rawtypes")
	public static AbstractMap.SimpleEntry<String[], double[]> getHighScores() 
{ 
	  String[] top_player_names;
	  double[] top_player_scores;
	  int[] top_player_index;
	  if (jsonArray.size() >= 5)
	  {
		  top_player_names = new String[5] ;
		  top_player_scores = new double[5];
		  top_player_index = new int[5];
	  }
	  else
	  {
		 top_player_names = new String[jsonArray.size()] ;
		 top_player_scores = new double[jsonArray.size()];
		 top_player_index = new int[jsonArray.size()];
		   
	  }
	  
	  
	  top_player_names = new String[jsonArray.size()] ;
	  top_player_scores = new double[jsonArray.size()];
	  top_player_index = new int[jsonArray.size()];
	  

	  
	  int score_index = 0;
	  int array_index = 0;
	  while (score_index <  top_player_scores.length)
	  {
		  String key =  (String) ((HashMap) jsonArray.get(array_index)).get("key");
		  String high_level_string = new String();
		  high_level_string = decryptString((String ) ((HashMap) jsonArray.get(array_index)).get("highlevel"), key);
		  double high_level = Double.valueOf(high_level_string);
		  
		  
		 for (int i = 0; i < score_index; i++)
		 {
			 if (array_index == top_player_index[i])
			 {
				 high_level = 0;
			 }
		 }
		  
		  
		  if (high_level >  top_player_scores[score_index])
		  {
			  top_player_scores[score_index] = high_level;
			  top_player_index[score_index] = array_index;
			  top_player_names[score_index] = decryptString((String ) ((HashMap) jsonArray.get(array_index)).get("user_name"), key);
		  }
		  
		  
		  //printDoubleArray(top_player_scores);
		 // printIntegerArray(top_player_index);
		  
		  if (array_index == jsonArray.size() - 1)
		  {
		      score_index++;
			  array_index = 0;
			  //high_level  = 0;
		  }
		  else
		  {
			  //printDoubleArray(top_player_scores);
			  //printStringArray(top_player_names);
		      array_index++; 
		  }
	  }
	  
	  return new AbstractMap.SimpleEntry<String[], double[]>(top_player_names, top_player_scores); 

}
 
	/*Returns the index in the JSON Array if input user name exists, returns -1 if user is not found*/
	 @SuppressWarnings({ "rawtypes" })
	public static int lookUpUser(String name)
	  {
		  int foundUser = -1;
		 
		  for (int i = 0; i < jsonArray.size(); i++)
		  {
			  String key = (String) ((HashMap) jsonArray.get(i)).get("key");
			  
			  
			  if (((HashMap) jsonArray.get(i)).containsValue(encryptString(name , key)))
			  {
				  foundUser = i;
			  }
		  }
		  
		  return foundUser;
	  }
  
	 /*Loads variables into global if user is found from input user name , returns the index in the JSON Array if input user name exists, returns -1 if user is not found*/
  @SuppressWarnings({ "rawtypes" })
  	public static int loadGame(String name)
  {
	  int foundUser = -1;
	 
	  for (int i = 0; i < jsonArray.size(); i++)
	  {
		  String key = (String) ((HashMap) jsonArray.get(i)).get("key");
		  
		  
		  if (((HashMap) jsonArray.get(i)).containsValue(encryptString(name , key)))
		  {
			  String high_level_string = new String();
			  String gametime_string = new String();	  
			  
			  foundUser = i;
			  returnGlobal().setLoadGamePosition(i);
			  returnGlobal().setNewGame(false);
			  //String key = new String();
			  //key = (String) ((HashMap) jsonArray.get(i)).get("key");
			  returnGlobal().setName(decryptString((String ) ((HashMap) jsonArray.get(i)).get("user_name"), key));
			  System.out.println(returnGlobal().getName());
			  returnGlobal().setBirthdate(decryptString((String ) ((HashMap) jsonArray.get(i)).get("birthdate"), key));
			  returnGlobal().setAddress(decryptString((String ) ((HashMap) jsonArray.get(i)).get("address"), key));
			  returnGlobal().setCity(decryptString((String) ((HashMap) jsonArray.get(i)).get("city"), key));
			  returnGlobal().setState(decryptString((String) ((HashMap) jsonArray.get(i)).get("state"), key));
			  returnGlobal().setZip_code(decryptString((String) ((HashMap) jsonArray.get(i)).get("zip_code"), key));
			  returnGlobal().setCountry(decryptString((String) ((HashMap) jsonArray.get(i)).get("country"), key));
			  returnGlobal().setDiagnosis(decryptString((String) ((HashMap) jsonArray.get(i)).get("diagnosis"), key));
			  //returnGlobal().high_level = (int) ((HashMap) jsonArray.get(i)).get("highlevel");
			  high_level_string = decryptString((String) ((HashMap) jsonArray.get(i)).get("highlevel") , key);
			  gametime_string = decryptString((String) ((HashMap) jsonArray.get(i)).get("total_gametime"), key);
			  decryptInteractionArray((JSONArray) ((HashMap) jsonArray.get(i)).get("interaction_array") , key);
			 returnGlobal().setTotal_gametime(Double.valueOf(gametime_string));
			 returnGlobal().setHigh_level(Double.valueOf(high_level_string));
		  }
	  }
	  
	  return foundUser;
  }

  	/*Replaces updated variables from a loaded game in JSON Array and the writes to the save file*/
  @SuppressWarnings({ "unchecked", "rawtypes" })
  
  	public static void  replaceSave(int index) {
	  String key = getEncodedKey();
	  ((HashMap) jsonArray.get(index)).replace("rq91hbhzaj",  ((HashMap) jsonArray.get(index)).get("address") , encryptString(returnGlobal().getAddress(), key));
	  ((HashMap) jsonArray.get(index)).replace("xcnwhuqdbc", ((HashMap) jsonArray.get(index)).get("city") , encryptString(returnGlobal().getCity(), key));
	  ((HashMap) jsonArray.get(index)).replace("dbvzwgsddi", ((HashMap) jsonArray.get(index)).get("state")  , encryptString(returnGlobal().getState(), key));
	  ((HashMap) jsonArray.get(index)).replace("g3eqbkq1m6", ((HashMap) jsonArray.get(index)).get("zip_code"), encryptString(returnGlobal().getZip_code(), key));
	  ((HashMap) jsonArray.get(index)).replace("tfdsbsv9qo", ((HashMap) jsonArray.get(index)).get("country"), encryptString(returnGlobal().getCountry(), key));
	  ((HashMap) jsonArray.get(index)).replace("pplk7r7pbp", ((HashMap) jsonArray.get(index)).get("high_level") , encryptString(String.valueOf(returnGlobal().getHigh_level()), key));
	  ((HashMap) jsonArray.get(index)).replace("tmjztkxe5m", ((HashMap) jsonArray.get(index)).get("diagnosis"), encryptString(returnGlobal().getDiagnosis(), key));
	  ((HashMap) jsonArray.get(index)).replace("tzsmrnsoy7", ((HashMap) jsonArray.get(index)).get("total_gametime") , encryptString(String.valueOf(returnGlobal().getTotal_gametime()) , key));
	  
	  JSONArray encryptedInteractions = encryptInteractionArray( key);
	  ((HashMap) jsonArray.get(index)).replace("o6vja8lio1", ((HashMap) jsonArray.get(index)).get("interaction_array") , encryptedInteractions);
	FileWriter save_file;
	try {
		save_file = new FileWriter("saves.json");
		save_file.write(jsonArray.toJSONString());
		save_file.flush();
		save_file.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
	  
  }
  
  	/*Appends a new JSON Object to the JSON Array and writes to the save file (for new saves)*/
@SuppressWarnings("unchecked")
	public static void  appendToSaves() {
	  
	  JSONObject newplayer = new JSONObject();
	  
	  String zxbvwoved7 = getEncodedKey();//obfuscated -  key 
	  String high_score_string = String.valueOf(returnGlobal().getHigh_level());
	  String gametime_string = String.valueOf(returnGlobal().getTotal_gametime());
	  
	  newplayer.put( "zxbvwoved7", zxbvwoved7 );
	  newplayer.put( "rjc8qhtv1w", encryptString(returnGlobal().getName(), zxbvwoved7));
	  newplayer.put( "acfiqoa2lu", encryptString(returnGlobal().getBirthdate(), zxbvwoved7 ));
	  newplayer.put( "rq91hbhzaj", encryptString(returnGlobal().getAddress(), zxbvwoved7));
	  newplayer.put( "xcnwhuqdbc", encryptString(returnGlobal().getCity(), zxbvwoved7 ));
	  newplayer.put( "dbvzwgsddi", encryptString(returnGlobal().getState(), zxbvwoved7 ));
	  newplayer.put( "g3eqbkq1m6", encryptString(returnGlobal().getZip_code(), zxbvwoved7));
	  newplayer.put( "tfdsbsv9qo", encryptString(returnGlobal().getCountry(), zxbvwoved7 ));
	  newplayer.put( "pplk7r7pbp", encryptString(high_score_string , zxbvwoved7 ));
	  newplayer.put( "tmjztkxe5m", encryptString(returnGlobal().getDiagnosis(), zxbvwoved7 ));
	  newplayer.put( "tzsmrnsoy7", encryptString(gametime_string, zxbvwoved7));
	  JSONArray encryptedInteractions = encryptInteractionArray( zxbvwoved7);
	  newplayer.put( "o6vja8lio1", encryptedInteractions);
	  jsonArray.add(newplayer);

	FileWriter save_file;
	try {
		save_file = new FileWriter("saves.json");
		save_file.write(jsonArray.toJSONString());
		save_file.flush();
		save_file.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
	  
  }
	
	/*Creates a new JSON Array of the interactions array encrypted for saving purposes*/
	@SuppressWarnings("unchecked")
	public static JSONArray encryptInteractionArray(String key){
		JSONArray outArray = new JSONArray();
		for (int i = 0; i < returnGlobal().getInteractionArray().size() ; i ++)
		{
			outArray.add(encryptString(String.valueOf(returnGlobal().getInteractionArray().get(i)), key)) ;
			
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
	
	/*Checks to see if saves file exists, creates one if it doesn't*/
	public static void checkForSaveFile()
  {
	  File saves = new File("saves.json");
	  
	  if ( !saves.exists())
	  {
		  System.out.println("save doesnt exist");
		  try {
			saves.createNewFile();
			jsonArray = new JSONArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  else
	  {
		  System.out.println("save exists!!");
		  if (saves.length() != 0)
		  {
			  createSavesObj();
		  }
		  else
		  {
			  jsonArray = new JSONArray();
		
		  }
	  }
  }
  
	/*Starts the main game from either new save or load save menus*/
    public static void StartGame(){
    	
    	EventQueue.invokeLater(() -> {
    		  game_screen.setVisible(false);
              game_screen.remove(newSave);
              game_screen.remove(loadSave);
              game_screen.add(game);
              game_screen.setVisible(true);
    	});
    	
    	// invoke game's main loop after map is created
    	EventQueue.invokeLater(() -> {
    		 game.displayGUI();
  	});
    	
    
    }

    /*Returns to the main menu (reinvokes main), resets variables*/
    public static void returnToMenu()
    {
    	 returnGlobal().setNewGame(true);
    	 returnGlobal().setHealth(1);
    	 returnGlobal().setLevel(1);
    	 returnGlobal().setSpeed(1000);
    	 returnGlobal().getInteractionArray().clear();
    	 game_screen.setVisible(false);
         game_screen.remove(lost);
         game_screen.remove(highScoreMenu);
         game_screen.remove(game);
         game_screen.remove(helpMenu);
         readyGameNoStart();
         menu = new MainMenu();
         main(null);
    }
    
    /*Readies the game for a new round, but does not start it*/
    public static void readyGameNoStart()
    {
        map = new Map();
        game = new Game(map);
        game.setButton_pressed_index(0);
        game.setComputer_pressed_index(0);
        
        
        if (returnGlobal().getLevel() > 9)
        {
        	for (int i=0; i< 50; i++)
            {
              game.getButtns_pressd()[i] = 0;
              game.getComputer_pressed()[i] = 0;
              game.getButtns_pressd_reversed()[i] = 0;
            }
        }
        else
        {
        	for (int i=0; i< 10; i++)
            {
              game.getButtns_pressd()[i] = 0;
              game.getComputer_pressed()[i] = 0;
              game.getButtns_pressd_reversed()[i] = 0;
            }	
        }
        
        game_screen.setVisible(false);
        game_screen.remove(lost);
        
        if (win != null)
        {
        	game_screen.remove(win);
        }
        
        if (lostContinue != null)
        {
        	game_screen.remove(lostContinue);
        }
        
        game_screen.remove(pause);
        Main.returnGlobal().setFirst_hit(true);
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

    /*Returns the game screen instance (the window itself)*/
    public static GameScreen returnFrame(){
        return game_screen;
    }
    
    /*Returns the global instance*/
    public static Global returnGlobal(){
        return global;
    }

    /*Goes to the new player screen (removing any other screen & adding new save to JFrame)*/
    public static void PlayNewSaveMenu()
    {
    EventQueue.invokeLater(() -> {
	      game_screen.setVisible(false); 
	      game_screen.remove(menu);
	      game_screen.remove(loadSave);
	      game_screen.add(newSave);
	      game_screen.setVisible(true);
    	});
    }
   
    /*Goes to the load player screen (removing any other screen & adding load save to JFrame)*/
    public static void PlayLoadSaveMenu()
    {
    EventQueue.invokeLater(() -> {
	      game_screen.setVisible(false); 
	      game_screen.remove(menu);
	      game_screen.remove(newSave);
	      game_screen.add(loadSave);
	      game_screen.setVisible(true);
    	});
    }
    
    /*Goes to the pause screen (removing game & adding pause screen to JFrame)*/
    public static void PlayPause()
    {
      game_screen.setVisible(false); 
      game_screen.remove(game);
      game_screen.add(pause);
      game_screen.setVisible(true);
    }

    public static void PlayHighScoreMenu()
    {
    	game_screen.setVisible(false); 
	      game_screen.remove(menu);
	      game_screen.add(highScoreMenu);
	      game_screen.setVisible(true);
    }
    
    /*Goes to the lose continue screen (removing game & adding lose continue screen to JFrame)*/
    public static void PlayLoseContinue()
    {
    	game_screen.setVisible(false); 
        game_screen.remove(game);
        lostContinue = new LostScreenContinue();
        game_screen.add(lostContinue);
        game_screen.setVisible(true);
    }
	
    /*Goes to the lose screen (removing game & adding lose screen to JFrame)*/
    public static void PlayLose()
    {
      System.out.println("Player lost");
      game_screen.setVisible(false); 
      game_screen.remove(game);
      lost = new LostScreen();
      game_screen.add(lost);
      game_screen.setVisible(true);
      returnGlobal().setTotal_gametime(returnGlobal().getTotal_gametime() + returnGlobal().getGametime());
      if (returnGlobal().isNewGame())
      {
    	  returnGlobal().setHigh_level(returnGlobal().getLevel());
    	  if (returnGlobal().getHigh_level() < 5)
    	  {
    		  returnGlobal().setDiagnosis("Likely AD");
    	  }
    	  else
    	  {
    		  returnGlobal().setDiagnosis("Likely not AD"); 
    	  }
    	  appendToSaves();
    	  
      }
      else
      {
    	if (returnGlobal().getLevel() >  returnGlobal().getHigh_level())
    	{
    		returnGlobal().setHigh_level(returnGlobal().getLevel());
    	}
    	 if (returnGlobal().getHigh_level() < 5)
    	 {
    		  returnGlobal().setDiagnosis("Likely AD");
    	}
    	 else
    	  {
    		  returnGlobal().setDiagnosis("Likely not AD"); 
    	  }
    	 replaceSave(returnGlobal().getLoadGamePosition());
      }
    }

    /*Goes to the win screen (removing game & adding win screen to JFrame)*/
    public static void PlayWin(){
      System.out.println("Player won");
      game_screen.setVisible(false); 
      game_screen.remove(game);
      win = new WinScreen();
      game_screen.add(win);
      game_screen.setVisible(true);
        
    }

    /*Goes to the help menu (removing main menu & adding help menu to JFrame)*/
	public static void PlayHelpMenu() {
		 game_screen.setVisible(false); 
	      game_screen.remove(menu);
	      game_screen.add(helpMenu);
	      game_screen.setVisible(true);
		
	}

	/*Main method of program*/
	public static void main(String[] args) {

	       EventQueue.invokeLater(() -> {
	            game_screen.add(menu);
	            checkForSaveFile();
	           // System.out.print(getHighScores().getValue() +  " "  + getHighScores().getValue() );
	            highScoreMenu = new HighScoreMenu();
	           // System.out.println(getHighScores().getKey() + " " + getHighScores().getValue() );
	            game_screen.setVisible(true);
	        });
	    }

}