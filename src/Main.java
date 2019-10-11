//import java.util.concurrent.TimeUnit;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;
import java.util.AbstractMap;
//import java.io.*;


public class Main {
  //private static JFrame menu;
  //static WinScreen win;
  static GameScreen game_screen = new GameScreen();
  static MainMenu menu = new MainMenu();
  static Map map = new Map();
  static Global global = new Global();
  static SimpleEx game = new SimpleEx(map);
  //static WinScreen win = new WinScreen();
  static WinScreen win;
  static PauseScreen pause = new PauseScreen();
  static NewSaveScreen newSave = new NewSaveScreen();
  static LoadSaveScreen loadSave = new LoadSaveScreen();
  static LostScreen lost = new LostScreen();
  //static LostScreenContinue lostContinue = new LostScreenContinue();
  static LostScreenContinue lostContinue;
  static JButton outArry[] = new JButton[9];
  static int lives = 17;
  //static Object saves_obj;
  static JSONArray jsonArray;
  
  
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

  public static String encryptString (String stringToEncrypt,
				      String encodedKey)
  {
    byte[]encryptedStringInBytes = null;
    String encryptedString = new String ();
    try
    {


      Cipher cipher = Cipher.getInstance ("DES/ECB/PKCS5Padding");
      //out.write(pvt.getEncoded());


      byte[]decodedKey = Base64.getDecoder ().decode (encodedKey);
      SecretKey key =
	new SecretKeySpec (decodedKey, 0, decodedKey.length, "DES");
      byte[]stringInBytes = stringToEncrypt.getBytes ();
      cipher.init (Cipher.ENCRYPT_MODE, key);
      encryptedStringInBytes = cipher.doFinal (stringInBytes);

      encryptedString =
	DatatypeConverter.printBase64Binary (encryptedStringInBytes);

    }
    catch (NoSuchAlgorithmException | BadPaddingException |
	   InvalidKeyException | IllegalBlockSizeException |
	   NoSuchPaddingException e)
    {
      e.printStackTrace ();
    }

    return encryptedString;
  }

  public static String decryptString (String encryptedString,
				      String encodedKey)
  {
    String decrypted = null;
    byte[]decryptedStringInBytes = null;
    try
    {

      decryptedStringInBytes =
	DatatypeConverter.parseBase64Binary (encryptedString);
      byte[]decodedKey = Base64.getDecoder ().decode (encodedKey);
      SecretKey originalKey =
	new SecretKeySpec (decodedKey, 0, decodedKey.length, "DES");

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
  
  
  public static AbstractMap.SimpleEntry<double[], String[]> getHighScores() 
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
	  
	  
	  int score_index = 0;
	  int array_index = 0;
	  while (score_index <  top_player_scores.length - 1)
	  {
		  String key =  (String) ((HashMap) jsonArray.get(array_index)).get("key");
		  String high_level_string = new String();
		  high_level_string =decryptString((String ) ((HashMap) jsonArray.get(array_index)).get("high_level"), key);
		  double high_level = Double.valueOf(high_level_string);
		  if (high_level >  top_player_scores[score_index])
		  {
			  top_player_scores[score_index] = high_level;
			  top_player_names[score_index] = decryptString((String ) ((HashMap) jsonArray.get(array_index)).get("user_name"), key);;
		  }
		  
	  }
	  
	  return new AbstractMap.SimpleEntry<double[], String[]>(top_player_scores, top_player_names); 
      
      
  } 

  
  @SuppressWarnings({ "unchecked", "rawtypes" })
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
			  returnGlobal().loadGamePosition = i;
			  returnGlobal().newGame = false;
			  //String key = new String();
			  //key = (String) ((HashMap) jsonArray.get(i)).get("key");
			  returnGlobal().name = decryptString((String ) ((HashMap) jsonArray.get(i)).get("user_name"), key);
			  System.out.println(returnGlobal().name);
			  returnGlobal().birthdate = decryptString((String ) ((HashMap) jsonArray.get(i)).get("birthdate"), key);
			  returnGlobal().address = decryptString((String ) ((HashMap) jsonArray.get(i)).get("address"), key);
			  returnGlobal().city = decryptString((String) ((HashMap) jsonArray.get(i)).get("city"), key);
			  returnGlobal().state = decryptString((String) ((HashMap) jsonArray.get(i)).get("state"), key);
			  returnGlobal().zip_code = decryptString((String) ((HashMap) jsonArray.get(i)).get("zip_code"), key);
			  returnGlobal().country = decryptString((String) ((HashMap) jsonArray.get(i)).get("country"), key);
			  returnGlobal().diagnosis = decryptString((String) ((HashMap) jsonArray.get(i)).get("diagnosis"), key);
			  //returnGlobal().high_level = (int) ((HashMap) jsonArray.get(i)).get("highlevel");
			  high_level_string = decryptString((String) ((HashMap) jsonArray.get(i)).get("highlevel") , key);
			  gametime_string = decryptString((String) ((HashMap) jsonArray.get(i)).get("total_gametime"), key);
		  
			 returnGlobal().total_gametime =  Double.valueOf(gametime_string);
			 returnGlobal().high_level = Double.valueOf(high_level_string);
		  }
	  }
	  
	  return foundUser;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  
public static void  replaceSave(int index) {
	  
	  //SONObject newplayer = new JSONObject();
	  String key = getEncodedKey();
	  ((HashMap) jsonArray.get(index)).replace("address",  ((HashMap) jsonArray.get(index)).get("address") , encryptString(returnGlobal().address, key));
	  ((HashMap) jsonArray.get(index)).replace("city", ((HashMap) jsonArray.get(index)).get("city") , encryptString(returnGlobal().city, key));
	  ((HashMap) jsonArray.get(index)).replace("state", ((HashMap) jsonArray.get(index)).get("state")  , encryptString(returnGlobal().state, key));
	  ((HashMap) jsonArray.get(index)).replace("zip_code", ((HashMap) jsonArray.get(index)).get("zip_code"), encryptString(returnGlobal().zip_code, key));
	  ((HashMap) jsonArray.get(index)).replace("country", ((HashMap) jsonArray.get(index)).get("country"), encryptString(returnGlobal().country, key));
	  ((HashMap) jsonArray.get(index)).replace("highlevel", ((HashMap) jsonArray.get(index)).get("high_level") , encryptString(String.valueOf(returnGlobal().high_level), key));
	  ((HashMap) jsonArray.get(index)).replace("diagnosis", ((HashMap) jsonArray.get(index)).get("diagnosis"), encryptString(returnGlobal().diagnosis, key));
	  ((HashMap) jsonArray.get(index)).replace("total_gametime", ((HashMap) jsonArray.get(index)).get("total_gametime") , encryptString(String.valueOf(returnGlobal().total_gametime) , key));

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
  
  
@SuppressWarnings("unchecked")
public static void  appendToSaves() {
	  
	  JSONObject newplayer = new JSONObject();
	  String key = getEncodedKey();
	  String high_score_string = String.valueOf(returnGlobal().high_level);
	  String gametime_string = String.valueOf(returnGlobal().total_gametime);
	  
	  newplayer.put( "key", key );
	  newplayer.put( "user_name", encryptString(returnGlobal().name, key));
	  newplayer.put( "birthdate", encryptString(returnGlobal().birthdate, key ));
	  newplayer.put( "address", encryptString(returnGlobal().address, key ));
	  newplayer.put( "city", encryptString(returnGlobal().city, key ));
	  newplayer.put( "state", encryptString(returnGlobal().state, key ));
	  newplayer.put( "zip_code", encryptString(returnGlobal().zip_code, key));
	  newplayer.put( "country", encryptString(returnGlobal().country, key ));
	  newplayer.put( "highlevel", encryptString(high_score_string , key ));
	  newplayer.put( "diagnosis", encryptString(returnGlobal().diagnosis, key ));
	  newplayer.put( "total_gametime", encryptString(gametime_string, key));
	  jsonArray.add(newplayer);
	 // newplayer.get("user_name");
	  //newplayer.replace("birthdate", returnGlobal().birthdate , "I THINK IT WORKED");
	  

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
  
  public static void main(String[] args) {

       EventQueue.invokeLater(() -> {
            game_screen.add(menu);
            checkForSaveFile();
            System.out.println(getHighScores().getKey() + " " + getHighScores().getValue() );
            game_screen.setVisible(true);
        });
    }

 
  
    public static void StartGame(){
    	
    	EventQueue.invokeLater(() -> {
    		  game_screen.setVisible(false);
              game_screen.remove(newSave);
              game_screen.remove(loadSave);
              game_screen.add(game);
              game_screen.setVisible(true);

             
              //game.displayGUI();
              
    		
    	});
    	
    	EventQueue.invokeLater(() -> {
    		//game.delay(outArry);
    		 game.displayGUI();
  	});
    	
    
    }

    public static void returnToMenu()
    {
    	 returnGlobal().newGame = true;
    	 returnGlobal().health = 1;
    	 game_screen.setVisible(false);
         game_screen.remove(lost);
         game_screen.remove(game);
         readyGameNoStart();
         menu = new MainMenu();
         //game_screen.revalidate();
         main(null);
         //game_screen.add(menu);
         //checkForSaveFile();
         //game_screen.setVisible(true);
    }
    
    
    public static void readyGameNoStart()
    {
        map = new Map();
        game = new SimpleEx(map);
        game.button_pressed_index = 0;
        game.computer_pressed_index = 0;
        for (int i=0; i< 10; i++)
        {
          game.buttns_pressd[i] = 0;
          game.computer_pressed[i] = 0;
          game.buttns_pressd_reversed[i] = 0;
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
        Main.returnGlobal().first_hit = true;
    }
    
    public static void readyGame()
    {
      readyGameNoStart();
      StartGame();
    }

    public static SimpleEx returnGame(){
        return game;
    }

    public static GameScreen returnFrame(){
        return game_screen;
    }
    
    public static Global returnGlobal(){
        return global;
    }

    public static NewSaveScreen returnNewSaveScreen(){
        return newSave;
    }
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
    
    public static void PlayPause()
    {
      game_screen.setVisible(false); 
      game_screen.remove(game);
      game_screen.add(pause);
      game_screen.setVisible(true);
    }

    
    @SuppressWarnings("unchecked")
    public static void PlayLoseContinue()
    {
    	game_screen.setVisible(false); 
        game_screen.remove(game);
        lostContinue = new LostScreenContinue();
        game_screen.add(lostContinue);
        game_screen.setVisible(true);
    }
	public static void PlayLose()
    {
      System.out.println("Player lost");
      game_screen.setVisible(false); 
      game_screen.remove(game);
      lost = new LostScreen();
      game_screen.add(lost);
      game_screen.setVisible(true);
      returnGlobal().total_gametime +=  returnGlobal().gametime;
      if (returnGlobal().newGame)
      {
    	  returnGlobal().high_level = returnGlobal().level;
    	  if (returnGlobal().high_level < 5)
    	  {
    		  returnGlobal().diagnosis = "Likely AD";
    	  }
    	  else
    	  {
    		  returnGlobal().diagnosis = "Likely not AD"; 
    	  }
    	  appendToSaves();
    	  
      }
      else
      {
    	if (returnGlobal().level >  returnGlobal().high_level)
    	{
    		returnGlobal().high_level = returnGlobal().level;
    	}
    	 if (returnGlobal().high_level < 5)
    	 {
    		  returnGlobal().diagnosis = "Likely AD";
    	}
    	 else
    	  {
    		  returnGlobal().diagnosis = "Likely not AD"; 
    	  }
    	 replaceSave(returnGlobal().loadGamePosition);
      }
    }

    public static void PlayWin(){
      System.out.println("Player won");
      game_screen.setVisible(false); 
      game_screen.remove(game);
      win = new WinScreen();
      game_screen.add(win);
      game_screen.setVisible(true);
        
    }

   

}