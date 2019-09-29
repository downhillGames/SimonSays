//import java.util.concurrent.TimeUnit;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
//import java.io.*;


public class Main {
  //private static JFrame menu;
  //static WinScreen win;
  static GameScreen game_screen = new GameScreen();
  static MainMenu menu = new MainMenu();
  static Map map = new Map();
  static Global global = new Global();
  static SimpleEx game = new SimpleEx(map);
  static WinScreen win = new WinScreen();
  static PauseScreen pause = new PauseScreen();
  static NewSaveScreen newSave = new NewSaveScreen();
  static LostScreen lost = new LostScreen();
  static JButton outArry[] = new JButton[9];
  static int lives = 17;
  //static Object saves_obj;
  static JSONArray jsonArray;
  
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
  
  public static int loadGame(String name)
  {
	  int foundUser = -1;
	 
	  for (int i = 0; i < jsonArray.size(); i++)
	  {
		  if (((HashMap) jsonArray.get(i)).containsValue("Ty"))
		  {
			  foundUser = i;
			  returnGlobal().loadGamePosition = i;
		  }
	  }
	  
	  return foundUser;
  }

  @SuppressWarnings("unchecked")
public static void  appendToSaves() {
	  
	  JSONObject newplayer = new JSONObject();
	  newplayer.put( "user_name", returnGlobal().name );
	  newplayer.put( "birthdate", returnGlobal().birthdate );
	  newplayer.put( "city", returnGlobal().city );
	  newplayer.put( "state", returnGlobal().state );
	  newplayer.put( "zip_code", returnGlobal().zip_code );
	  newplayer.put( "country", returnGlobal().country);
	  newplayer.put( "highlevel", returnGlobal().high_level );
	  newplayer.put( "diagnosis", returnGlobal().diagnosis );
	  newplayer.put( "total_gametime", returnGlobal().total_gametime );
	  jsonArray.add(newplayer);
	  
	  //newplayer.replace("birthdate", returnGlobal().birthdate , "I THINK IT WORKED");
	  
	  System.out.println(loadGame("Ty"));
	  

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
            game_screen.setVisible(true);
        });
    }

 
  
    public static void StartGame(){
    	
    	EventQueue.invokeLater(() -> {
    		  game_screen.setVisible(false);
              game_screen.remove(newSave);
      
              game_screen.add(game);
              game_screen.setVisible(true);

              game.displayGUI();
              //game.displayGUI();
              
    		
    	});
    	
    	EventQueue.invokeLater(() -> {
    		//game.delay(outArry);
  		
  	});
    	
    
    }

    public static void returnToMenu()
    {
    	 game_screen.setVisible(false);
         game_screen.remove(lost);
         game_screen.remove(game);
         readyGameNoStart();
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
      for (int i=0; i< 50; i++)
      {
        game.buttns_pressd[i] = 0;
        game.computer_pressed[i] = 0;
      }
      game_screen.remove(win);
      game_screen.remove(lost);
      game_screen.remove(pause);
    }
    
    public static void readyGame()
    {
      map = new Map();
      game = new SimpleEx(map);
      game.button_pressed_index = 0;
      game.computer_pressed_index = 0;
      for (int i=0; i< 50; i++)
      {
        game.buttns_pressd[i] = 0;
        game.computer_pressed[i] = 0;
      }
      game_screen.setVisible(false);
      game_screen.remove(win);
      game_screen.remove(lost);
      game_screen.remove(pause);
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
	      game_screen.add(newSave);
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
	public static void PlayLose()
    {
      System.out.println("Player lost");
      game_screen.setVisible(false); 
      game_screen.remove(game);
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
    	((HashMap) jsonArray.get(0)).replace("total_gametime", (returnGlobal().total_gametime - returnGlobal().gametime) , returnGlobal().total_gametime);
		 
      }
    }

    public static void PlayWin(){
      System.out.println("Player won");
      game_screen.setVisible(false); 
      game_screen.remove(game);
      game_screen.add(win);
      game_screen.setVisible(true);
        
    }

   

}