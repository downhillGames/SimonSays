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

	 static JSONArray savesArray;
	 
	 
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
				savesArray = new JSONArray();
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
				  savesArray = new JSONArray();
			
			  }
		  }
	  }
	
	
		/*Creates JSON Array that stores all saves from file*/
	  	public static void createSavesObj() {
		  JSONParser jsonParser = new JSONParser();
			
		  try {
			Object saves_obj = jsonParser.parse(new FileReader("saves.json"));
			savesArray = (JSONArray)saves_obj;
			System.out.println(savesArray);
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
		  if (savesArray.size() >= 5)
		  {
			  top_player_names = new String[5] ;
			  top_player_scores = new double[5];
			  top_player_index = new int[5];
		  }
		  else
		  {
			 top_player_names = new String[savesArray.size()] ;
			 top_player_scores = new double[savesArray.size()];
			 top_player_index = new int[savesArray.size()];
			   
		  }
		  
		  int score_index = 0;
		  int array_index = 0;
		  while (score_index <  top_player_scores.length)
		  {
			  String key =  (String) ((HashMap) savesArray.get(array_index)).get("zxbvwoved7");
			  String high_level_string = new String();
			  high_level_string = Main.decryptString((String ) ((HashMap) savesArray.get(array_index)).get("pplk7r7pbp"), key);
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
				  top_player_names[score_index] = Main.decryptString((String ) ((HashMap) savesArray.get(array_index)).get("rjc8qhtv1w"), key);
			  }
			  if (array_index == savesArray.size() - 1)
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
	 

		/*Returns the index in the JSON Array if input user name exists, returns -1 if user is not found*/
		 @SuppressWarnings({ "rawtypes" })
		public int lookUpUser(String name)
		  {
			  int foundUser = -1;
			 
			  for (int i = 0; i < savesArray.size(); i++)
			  {
				 String key = (String) ((HashMap) savesArray.get(i)).get("zxbvwoved7");
				  
				  System.out.println(Main.decryptString((String) ((HashMap) savesArray.get(i)).get("rjc8qhtv1w"), key)); 
				  System.out.println((String) ((HashMap) savesArray.get(i)).get("rjc8qhtv1w")); 
				  System.out.println(Main.encryptString(name , key)); 
				  //if (((HashMap) savesArray.get(i)).containsValue(encryptString(name , key)))
				  
				  if (Main.isSameString(Main.decryptString((String) ((HashMap) savesArray.get(i)).get("rjc8qhtv1w"), key) , name))	 
				  {
					  System.out.println(Main.decryptString((String) ((HashMap) savesArray.get(i)).get("rjc8qhtv1w"), key) + " NAME" ); 
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
		 
		  for (int i = 0; i < savesArray.size(); i++)
		  {
			  String key = (String) ((HashMap) savesArray.get(i)).get("zxbvwoved7");
			   
			  
			  //if (((HashMap) savesArray.get(i)).containsValue(encryptString(name , key)))
			  if (Main.isSameString(Main.decryptString((String) ((HashMap) savesArray.get(i)).get("rjc8qhtv1w"), key) , name))	  
			  {
				  String high_level_string = new String();
				  String gametime_string = new String();	  
				  
				  foundUser = i;
				  Main.returnGlobal().setLoadGamePosition(i);
				  Main.returnGlobal().setNewGame(false);
				  //String key = new String();
				  //key = (String) ((HashMap) savesArray.get(i)).get("key");
				  Main.returnGlobal().setName(Main.decryptString((String ) ((HashMap) savesArray.get(i)).get("rjc8qhtv1w"), key));
				  System.out.println(Main.returnGlobal().getName());
				  Main.returnGlobal().setBirthdate(Main.decryptString((String ) ((HashMap) savesArray.get(i)).get("acfiqoa2lu"), key));
				  Main.returnGlobal().setAddress(Main.decryptString((String ) ((HashMap) savesArray.get(i)).get("rq91hbhzaj"), key));
				  Main.returnGlobal().setCity(Main.decryptString((String) ((HashMap) savesArray.get(i)).get("xcnwhuqdbc"), key));
				  Main.returnGlobal().setState(Main.decryptString((String) ((HashMap) savesArray.get(i)).get("dbvzwgsddi"), key));
				  Main.returnGlobal().setZip_code(Main.decryptString((String) ((HashMap) savesArray.get(i)).get("g3eqbkq1m6"), key));
				  Main.returnGlobal().setCountry(Main.decryptString((String) ((HashMap) savesArray.get(i)).get("tfdsbsv9qo"), key));
				  Main.returnGlobal().setDiagnosis(Main.decryptString((String) ((HashMap) savesArray.get(i)).get("tmjztkxe5m"), key));
				  high_level_string = Main.decryptString((String) ((HashMap) savesArray.get(i)).get("pplk7r7pbp") , key);
				  gametime_string = Main.decryptString((String) ((HashMap) savesArray.get(i)).get("tzsmrnsoy7"), key);
				  Main.decryptInteractionArray((JSONArray) ((HashMap) savesArray.get(i)).get("o6vja8lio1") , key);
				  Main.decryptScoresArray((JSONArray) ((HashMap) savesArray.get(i)).get("gjw2201t44") , key);
				 Main.returnGlobal().setTotal_gametime(Double.valueOf(gametime_string));
 
				 Main.returnGlobal().setHigh_level(Double.valueOf(high_level_string));
			  }
		  }
		  
		  return foundUser;
	  }

	  	/*Replaces updated variables from a loaded game in JSON Array and the writes to the save file*/
	  @SuppressWarnings({ "unchecked", "rawtypes" })
	  
	  	public static void  replaceSave(int index) {
		  String key = (String) ((HashMap) savesArray.get(index)).get("zxbvwoved7");
		  ((HashMap) savesArray.get(index)).replace("zxbvwoved7",  ((HashMap) savesArray.get(index)).get("zxbvwoved7") , key);
		  ((HashMap) savesArray.get(index)).replace("rq91hbhzaj",  ((HashMap) savesArray.get(index)).get("rq91hbhzaj") , Main.encryptString(Main.returnGlobal().getAddress(), key));
		  ((HashMap) savesArray.get(index)).replace("xcnwhuqdbc", ((HashMap) savesArray.get(index)).get("xcnwhuqdbc") , Main.encryptString(Main.returnGlobal().getCity(), key));
		  ((HashMap) savesArray.get(index)).replace("dbvzwgsddi", ((HashMap) savesArray.get(index)).get("dbvzwgsddi")  , Main.encryptString(Main.returnGlobal().getState(), key));
		  ((HashMap) savesArray.get(index)).replace("g3eqbkq1m6", ((HashMap) savesArray.get(index)).get("g3eqbkq1m6"), Main.encryptString(Main.returnGlobal().getZip_code(), key));
		  ((HashMap) savesArray.get(index)).replace("tfdsbsv9qo", ((HashMap) savesArray.get(index)).get("tfdsbsv9qo"), Main.encryptString(Main.returnGlobal().getCountry(), key));
		  ((HashMap) savesArray.get(index)).replace("pplk7r7pbp", ((HashMap) savesArray.get(index)).get("pplk7r7pbp") , Main.encryptString(String.valueOf(Main.returnGlobal().getHigh_level()), key));
		  ((HashMap) savesArray.get(index)).replace("tmjztkxe5m", ((HashMap) savesArray.get(index)).get("tmjztkxe5m"), Main.encryptString(Main.returnGlobal().getDiagnosis(), key));
		  ((HashMap) savesArray.get(index)).replace("tzsmrnsoy7", ((HashMap) savesArray.get(index)).get("tzsmrnsoy7") , Main.encryptString(String.valueOf(Main.returnGlobal().getTotal_gametime()) , key));
		  
		  
		  //gjw2201t44
		  JSONArray encryptedInteractions = Main.encryptArray( Main.returnGlobal().getInteractionArray() ,key);
		  ((HashMap) savesArray.get(index)).replace("o6vja8lio1", ((HashMap) savesArray.get(index)).get("o6vja8lio1") , encryptedInteractions);
		
		  JSONArray encryptedScores = Main.encryptArray( Main.returnGlobal().getScoresArray()  ,key);
		  ((HashMap) savesArray.get(index)).replace("gjw2201t44", ((HashMap) savesArray.get(index)).get("gjw2201t44") , encryptedScores);
		  
		  FileWriter save_file;
		try {
			save_file = new FileWriter("saves.json");
			save_file.write(savesArray.toJSONString());
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
		  
		  savesArray.add(newplayer);

		FileWriter save_file;
		try {
			save_file = new FileWriter("saves.json");
			save_file.write(savesArray.toJSONString());
			save_file.flush();
			save_file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  
		  
	  }
}
