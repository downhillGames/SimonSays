import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GameSave {

	private static JSONArray savesArray;
	 
	 
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
	 
	 	/*Checks to see if saves file exists, creates one if it doesn't*/
		public void checkForSaveFile()
	  {
		  File saves = new File("saves.json");
		  
		  if ( !saves.exists())
		  {
			  System.out.println("save doesnt exist");
			  try {
				saves.createNewFile();
				setSavesArray(new JSONArray());
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
				  setSavesArray(new JSONArray());
			
			  }
		  }
	  }
	
	
		public static void resetSaves()
		{
			savesArray.clear();
			FileWriter save_file;
			try {
				save_file = new FileWriter("saves.json");
				save_file.write(getSavesArray().toJSONString());
				save_file.flush();
				save_file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		/*Creates JSON Array that stores all saves from file*/
	  	public static void createSavesObj() {
		  JSONParser jsonParser = new JSONParser();
			
		  try {
			Object saves_obj = jsonParser.parse(new FileReader("saves.json"));
			setSavesArray((JSONArray)saves_obj);
			System.out.println(getSavesArray());
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  	
	  	/*Returns the top 5 (if 5 exist or else just top scores in order) scores and their user names in two separate arrays (double array scores, string array user names)*/
	  	@SuppressWarnings("rawtypes")
		public AbstractMap.SimpleEntry<String[], double[]> getHighScores() 
	  	{ 
		  String[] top_player_names;
		  double[] top_player_scores;
		  int[] top_player_index;
		  if (getSavesArray().size() >= 5)
		  {
			  top_player_names = new String[5] ;
			  top_player_scores = new double[5];
			  top_player_index = new int[5];
		  }
		  else
		  {
			 top_player_names = new String[getSavesArray().size()] ;
			 top_player_scores = new double[getSavesArray().size()];
			 top_player_index = new int[getSavesArray().size()];
			   
		  }
		  
		  int score_index = 0;
		  int array_index = 0;
		  while (score_index <  top_player_scores.length)
		  {
			  String key =  (String) ((HashMap) getSavesArray().get(array_index)).get("zxbvwoved7");
			  String high_level_string = new String();
			  high_level_string = Main.decryptString((String ) ((HashMap) getSavesArray().get(array_index)).get("pplk7r7pbp"), key);
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
				  top_player_names[score_index] = Main.decryptString((String ) ((HashMap) getSavesArray().get(array_index)).get("rjc8qhtv1w"), key);
			  }
			  if (array_index == getSavesArray().size() - 1)
			  {
			      score_index++;
				  array_index = 0;
			  }
			  else
			  {
			      array_index++; 
			  }
		  }
		  
		  return new AbstractMap.SimpleEntry<String[], double[]>(top_player_names, top_player_scores); 

	}
	 

		@SuppressWarnings("rawtypes")
		public String returnPassword(int array_index)
		{
			String key =  (String) ((HashMap) getSavesArray().get(array_index)).get("zxbvwoved7");
			String password = Main.decryptString((String ) ((HashMap) getSavesArray().get(array_index)).get("m2qkz77qp7"), key); 
			return password;
		}
		
		/*Returns the index in the JSON Array if input user name exists, returns -1 if user is not found*/
		 @SuppressWarnings({ "rawtypes" })
		public int lookUpUser(String name)
		  {
			  int foundUser = -1;
			 
			  for (int i = 0; i < getSavesArray().size(); i++)
			  {
				 String key = (String) ((HashMap) getSavesArray().get(i)).get("zxbvwoved7");
				  
				  System.out.println(Main.decryptString((String) ((HashMap) getSavesArray().get(i)).get("rjc8qhtv1w"), key)); 
				  System.out.println((String) ((HashMap) getSavesArray().get(i)).get("rjc8qhtv1w")); 
				  System.out.println(Main.encryptString(name , key)); 
				  //if (((HashMap) savesArray.get(i)).containsValue(encryptString(name , key)))
				  
				  if (Main.isSameString(Main.decryptString((String) ((HashMap) getSavesArray().get(i)).get("rjc8qhtv1w"), key) , name))	 
				  {
					  System.out.println(Main.decryptString((String) ((HashMap) getSavesArray().get(i)).get("rjc8qhtv1w"), key) + " NAME" ); 
					  System.out.println(name + " NAME2" ); 
					  foundUser = i;
				  }
			  }
			  
			  return foundUser;
		  }
	  
		 /*Loads variables into global if user is found from input user name , returns the index in the JSON Array if input user name exists, returns -1 if user is not found*/
	  @SuppressWarnings({ "rawtypes" })
	  	public int loadGame(String name)
	  {
		  int foundUser = -1;
		 
		  for (int i = 0; i < getSavesArray().size(); i++)
		  {
			  String key = (String) ((HashMap) getSavesArray().get(i)).get("zxbvwoved7");
			   
			  
			  //if (((HashMap) savesArray.get(i)).containsValue(encryptString(name , key)))
			  if (Main.isSameString(Main.decryptString((String) ((HashMap) getSavesArray().get(i)).get("rjc8qhtv1w"), key) , name))	  
			  {
				  String high_level_string = new String();
				  String gametime_string = new String();	  
				  
				  foundUser = i;
				  Main.returnGlobal().setLoadGamePosition(i);
				  Main.returnGlobal().setNewGame(false);
				  //String key = new String();
				  //key = (String) ((HashMap) savesArray.get(i)).get("key");
				  Main.returnGlobal().setName(Main.decryptString((String ) ((HashMap) getSavesArray().get(i)).get("rjc8qhtv1w"), key));
				  System.out.println(Main.returnGlobal().getName());
				  Main.returnGlobal().setBirthdate(Main.decryptString((String ) ((HashMap) getSavesArray().get(i)).get("acfiqoa2lu"), key));
				  Main.returnGlobal().setPassword(Main.decryptString((String ) ((HashMap) getSavesArray().get(i)).get("m2qkz77qp7"), key));
				  Main.returnGlobal().setAddress(Main.decryptString((String ) ((HashMap) getSavesArray().get(i)).get("rq91hbhzaj"), key));
				  Main.returnGlobal().setCity(Main.decryptString((String) ((HashMap) getSavesArray().get(i)).get("xcnwhuqdbc"), key));
				  Main.returnGlobal().setState(Main.decryptString((String) ((HashMap) getSavesArray().get(i)).get("dbvzwgsddi"), key));
				  Main.returnGlobal().setZip_code(Main.decryptString((String) ((HashMap) getSavesArray().get(i)).get("g3eqbkq1m6"), key));
				  Main.returnGlobal().setCountry(Main.decryptString((String) ((HashMap) getSavesArray().get(i)).get("tfdsbsv9qo"), key));
				  Main.returnGlobal().setDiagnosis(Main.decryptString((String) ((HashMap) getSavesArray().get(i)).get("tmjztkxe5m"), key));
				  high_level_string = Main.decryptString((String) ((HashMap) getSavesArray().get(i)).get("pplk7r7pbp") , key);
				  gametime_string = Main.decryptString((String) ((HashMap) getSavesArray().get(i)).get("tzsmrnsoy7"), key);
				  Main.decryptInteractionArray((JSONArray) ((HashMap) getSavesArray().get(i)).get("o6vja8lio1") , key);
				  Main.decryptScoresArray((JSONArray) ((HashMap) getSavesArray().get(i)).get("gjw2201t44") , key);
				 decryptArray((JSONArray) ((HashMap) getSavesArray().get(i)).get("kUnu83XHme") , key);
				 Main.returnGlobal().setTotal_gametime(Double.valueOf(gametime_string));
 
				 Main.returnGlobal().setHigh_level(Double.valueOf(high_level_string));
			  }
		  }
		  
		  return foundUser;
	  }

	  	/*Replaces updated variables from a loaded game in JSON Array and the writes to the save file*/
	  @SuppressWarnings({ "unchecked", "rawtypes" })
	  
	  	public static void  replaceSave(int index) {
		  String key = (String) ((HashMap) getSavesArray().get(index)).get("zxbvwoved7");
		  ((HashMap) getSavesArray().get(index)).replace("zxbvwoved7",  ((HashMap) getSavesArray().get(index)).get("zxbvwoved7") , key);
		  ((HashMap) getSavesArray().get(index)).replace("rq91hbhzaj",  ((HashMap) getSavesArray().get(index)).get("rq91hbhzaj") , Main.encryptString(Main.returnGlobal().getAddress(), key));
		  ((HashMap) getSavesArray().get(index)).replace("xcnwhuqdbc", ((HashMap) getSavesArray().get(index)).get("xcnwhuqdbc") , Main.encryptString(Main.returnGlobal().getCity(), key));
		  ((HashMap) getSavesArray().get(index)).replace("dbvzwgsddi", ((HashMap) getSavesArray().get(index)).get("dbvzwgsddi")  , Main.encryptString(Main.returnGlobal().getState(), key));
		  ((HashMap) getSavesArray().get(index)).replace("g3eqbkq1m6", ((HashMap) getSavesArray().get(index)).get("g3eqbkq1m6"), Main.encryptString(Main.returnGlobal().getZip_code(), key));
		  ((HashMap) getSavesArray().get(index)).replace("tfdsbsv9qo", ((HashMap) getSavesArray().get(index)).get("tfdsbsv9qo"), Main.encryptString(Main.returnGlobal().getCountry(), key));
		  ((HashMap) getSavesArray().get(index)).replace("pplk7r7pbp", ((HashMap) getSavesArray().get(index)).get("pplk7r7pbp") , Main.encryptString(String.valueOf(Main.returnGlobal().getHigh_level()), key));
		  ((HashMap) getSavesArray().get(index)).replace("tmjztkxe5m", ((HashMap) getSavesArray().get(index)).get("tmjztkxe5m"), Main.encryptString(Main.returnGlobal().getDiagnosis(), key));
		  ((HashMap) getSavesArray().get(index)).replace("tzsmrnsoy7", ((HashMap) getSavesArray().get(index)).get("tzsmrnsoy7") , Main.encryptString(String.valueOf(Main.returnGlobal().getTotal_gametime()) , key));
		  
		  
		  //gjw2201t44
		  JSONArray encryptedInteractions = Main.encryptArray( Main.returnGlobal().getInteractionArray() ,key);
		  ((HashMap) getSavesArray().get(index)).replace("o6vja8lio1", ((HashMap) getSavesArray().get(index)).get("o6vja8lio1") , encryptedInteractions);
		
		  JSONArray encryptedScores = Main.encryptArray( Main.returnGlobal().getScoresArray()  ,key);
		  ((HashMap) getSavesArray().get(index)).replace("gjw2201t44", ((HashMap) getSavesArray().get(index)).get("gjw2201t44") , encryptedScores);
		  
		  JSONArray encryptedForward = Main.encryptArray( Main.returnGlobal().getScoresArray()  ,key);
		  ((HashMap) getSavesArray().get(index)).replace("kUnu83XHme", ((HashMap) getSavesArray().get(index)).get("kUnu83XHme") , encryptedForward);
		  
		  FileWriter save_file;
		try {
			save_file = new FileWriter("saves.json");
			save_file.write(getSavesArray().toJSONString());
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
		  
		  String zxbvwoved7 = Main.getEncodedKey();//obfuscated -  key 
		  String high_score_string = String.valueOf(Main.returnGlobal().getHigh_level());
		  String gametime_string = String.valueOf(Main.returnGlobal().getTotal_gametime());
		  
		  newplayer.put( "zxbvwoved7", zxbvwoved7 );
		  newplayer.put( "rjc8qhtv1w", Main.encryptString(Main.returnGlobal().getName(), zxbvwoved7));
		  newplayer.put( "m2qkz77qp7", Main.encryptString(Main.returnGlobal().getPassword(), zxbvwoved7));
		  newplayer.put( "acfiqoa2lu", Main.encryptString(Main.returnGlobal().getBirthdate(), zxbvwoved7 ));
		  newplayer.put( "rq91hbhzaj", Main.encryptString(Main.returnGlobal().getAddress(), zxbvwoved7));
		  newplayer.put( "xcnwhuqdbc", Main.encryptString(Main.returnGlobal().getCity(), zxbvwoved7 ));
		  newplayer.put( "dbvzwgsddi", Main.encryptString(Main.returnGlobal().getState(), zxbvwoved7 ));
		  newplayer.put( "g3eqbkq1m6", Main.encryptString(Main.returnGlobal().getZip_code(), zxbvwoved7));
		  newplayer.put( "tfdsbsv9qo", Main.encryptString(Main.returnGlobal().getCountry(), zxbvwoved7 ));
		  newplayer.put( "pplk7r7pbp", Main.encryptString(high_score_string , zxbvwoved7 ));
		  newplayer.put( "tmjztkxe5m", Main.encryptString(Main.returnGlobal().getDiagnosis(), zxbvwoved7 ));
		  newplayer.put( "tzsmrnsoy7", Main.encryptString(gametime_string, zxbvwoved7));
		  
		  JSONArray encryptedInteractions = Main.encryptArray( Main.returnGlobal().getInteractionArray() ,zxbvwoved7);
		  newplayer.put( "o6vja8lio1", encryptedInteractions);
		  
		  JSONArray encryptedScores = Main.encryptArray( Main.returnGlobal().getScoresArray() ,zxbvwoved7);
		  newplayer.put( "gjw2201t44", encryptedScores);
		  
		  JSONArray encryptedForward = Main.encryptArray( Main.returnGlobal().getScoresArray() ,zxbvwoved7);
		  newplayer.put( "kUnu83XHme", encryptedForward);
		  
		  getSavesArray().add(newplayer);

		FileWriter save_file;
		try {
			save_file = new FileWriter("saves.json");
			save_file.write(getSavesArray().toJSONString());
			save_file.flush();
			save_file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  
		  
	  }

	public static JSONArray getSavesArray() {
		return savesArray;
	}

	public static void setSavesArray(JSONArray savesArray) {
		GameSave.savesArray = savesArray;
	}
}
