//import java.util.concurrent.TimeUnit;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
	} catch (IOException | ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
  }
  
  
  @SuppressWarnings("unchecked")
public static void  appendToSaves(String username, int high_level, int total_time, String stuff) {
	  
	  JSONObject newplayer = new JSONObject();
	  newplayer.put( "user_name", username );
	  newplayer.put( "highlevel", high_level );
  }
	
  public static void checkForSaveFile()
  {
	  File saves = new File("saves.json");
	  
	  if ( !saves.exists())
	  {
		  System.out.println("save doesnt exist");
		  try {
			saves.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  else
	  {
		  System.out.println("save exists!!");
	  }
  }
  
  public static void main(String[] args) {

       EventQueue.invokeLater(() -> {
            //var game = new Game();
             
            //final var global = new Global();
            //menu = new MainMenu();
           // win = new WinScreen();
            //var menu = new MainMenu();  
            //menu.main();
            //menu.setVisible(true);
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
      game_screen.remove(game);
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
    
    public static void PlayLose()
    {
      System.out.println("Player lost");
      game_screen.setVisible(false); 
      game_screen.remove(game);
      game_screen.add(lost);
      game_screen.setVisible(true);
    }

    public static void PlayWin(){
      System.out.println("Player won");
      game_screen.setVisible(false); 
      game_screen.remove(game);
      game_screen.add(win);
      game_screen.setVisible(true);
        
    }

   

}