import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class levelSave {
	
	private static JSONArray levelSaveArr;
	
	/*Checks to see if level save file exists, creates one if it doesn't*/
	public void checkForSaveFile(){
	  File levelSave = new File("level.json");
	  
	  if ( !levelSave.exists())
	  { 
		  try {
			 levelSave.createNewFile();
			 levelSaveArr = new JSONArray();
			 newLevelSave();
			 //System.out.println( "Default level: " + Main.getStartingLevel());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  else
	  { 
		  if (levelSave.length() != 0)
		  {
			 Main.setStartingLevel(loadLevelObj() );
			 //System.out.println( "Default level: " + Main.getStartingLevel());
		  }
		  else
		  {
			  levelSaveArr = new JSONArray();
			  newLevelSave();
			  //System.out.println( "Default level: " + Main.getStartingLevel());
		  }
	  }
  }
	
	/*Loads default starting level from file*/
  	@SuppressWarnings("rawtypes")
	public static int loadLevelObj() {
	  JSONParser jsonParser = new JSONParser();
		int defaultLevel = 0;
	  try {
		Object level_obj = jsonParser.parse(new FileReader("level.json"));
		levelSaveArr = (JSONArray)level_obj;
		 String key = (String) ((HashMap) levelSaveArr.get(0)).get("khsv1c7ijy");
		String defaultLevelString =  Main.decryptString((String) ((HashMap) levelSaveArr.get(0)).get("kuinx1ro90"), key);
		defaultLevel = Integer.valueOf(defaultLevelString);
	} catch (IOException | ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return defaultLevel;
  }

  	/*Appends a new JSON Object to the JSON Array and writes to the level file*/
	@SuppressWarnings("unchecked")
	public static void  newLevelSave() {
		  
		  JSONObject newDefaultLevel = new JSONObject();
		  
		  String key = Main.getEncodedKey();  
		  newDefaultLevel.put( "khsv1c7ijy", key );
		  newDefaultLevel.put( "kuinx1ro90", Main.encryptString(Integer.toString(Main.getStartingLevel()), key));
		  levelSaveArr.add(newDefaultLevel);

		FileWriter save_file;
		try {
			save_file = new FileWriter("level.json");
			save_file.write(levelSaveArr.toJSONString());
			save_file.flush();
			save_file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  
		  
	  }
  	
  	/*Replaces updated default level with a new one and writes to the file*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	  
	public static void  replaceSave(int newLevel) {
		  String key = (String) ((HashMap) levelSaveArr.get(0)).get("khsv1c7ijy"); 
		  String encryptedLevel = Main.encryptString(Integer.toString(newLevel) , key);
		  ((HashMap) levelSaveArr.get(0)).replace("kuinx1ro90",  ((HashMap) levelSaveArr.get(0)).get("kuinx1ro90") , encryptedLevel);
		  
	 
		  FileWriter level_file;
		try {
			level_file = new FileWriter("level.json");
			level_file.write(levelSaveArr.toJSONString());
			level_file.flush();
			level_file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  
		  
	  }

}
