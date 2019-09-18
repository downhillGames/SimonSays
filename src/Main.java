import java.util.concurrent.TimeUnit;
import java.awt.EventQueue;
import javax.swing.*;
import java.io.*;


public class Main {
  //private static JFrame menu;
  //static WinScreen win;
  static GameScreen game_screen = new GameScreen();
  static MainMenu menu = new MainMenu();
  static Map map = new Map();
  static Global global = new Global();
  static SimpleEx game = new SimpleEx(map);
  static WinScreen win = new WinScreen();
  static LostScreen lost = new LostScreen();
  static JButton outArry[] = new JButton[9];
  static int lives = 17;
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
            game_screen.setVisible(true);
            
            
        });
    }

  
    public static void StartGame(){
    	
    	EventQueue.invokeLater(() -> {
    		  game_screen.setVisible(false);
              game_screen.remove(menu);
      
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
      for (int i=0; i< 50; i++)
      {
        game.buttns_pressd[i] = 0;
      }
      
      EventQueue.invokeLater(() -> {
      game_screen.setVisible(false);
      game_screen.remove(win);
      game_screen.add(game);
      game_screen.setVisible(true);
      game.displayGUI();
      });
    }

    public static SimpleEx returnGame(){
        return game;
    }

    public static Global returnGlobal(){
        return global;
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