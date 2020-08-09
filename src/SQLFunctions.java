import java.sql.*; 

public class SQLFunctions {

		private String user = "downhillgames_test";
		private String pass = "thisisapassword";
		private String dbClass = "com.mysql.jdbc.Driver";
		private String dbDriver = "jdbc:mysql://downhillgames.com:3306/downhillgames_CorsiHighScores";
		private Connection con = null;

		
		public void connectToDatabase(){
			System.out.println("Attempting to connect.");
		              
		    // Establishing Connection 
				try {
					Class.forName(dbClass).newInstance();
					//"jdbc:mysql://hostname:port/databaseName", "username", "password"
					con = DriverManager.getConnection(dbDriver, user, pass); 
			        if (con != null) {         
			        	System.out.println("Connected");
			        }
			        else {            
			           System.out.println("Not Connected"); 
			           con.close();
			        }
				}
				
				catch (SQLException | InstantiationException  | IllegalAccessException  | ClassNotFoundException e) {
					/// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("An error Occured"); 
				}
				
				
		}
		
		public void insertIntoDatabase(int id, String name, String scoreEncrypt, int score, String encryptKey) {
			
		
			   String insert = "insert into Players values('" +id+ "', '" +name+ "', '" +scoreEncrypt+ "', '" +score+ "', '" +encryptKey+ "')"; 
			   
			   try {
				   Statement sqlStatement = con.createStatement(); 
				   int pass = sqlStatement.executeUpdate(insert);
				   
		            if (pass > 0)             
		                System.out.println("Successfully Inserted");             
		            else            
		                System.out.println("Insert Failed"); 
				   
			   } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			   }
		}
		
		public void updateHighScoreint(int id, String name, String scoreEncrypt, int score, String encryptKey){
			
			try {
            // Updating database 
			Statement sqlstatement = con.createStatement();
            String update1 = "UPDATE Players set Score = '" + score + "' WHERE Name = '" +name+ "' AND EncryptKey = '" + encryptKey + "'"; 
            String update2 = "UPDATE Players set ScoreEncrypt = '" + scoreEncrypt + "' WHERE Name = '" +name+ "' AND EncryptKey = '" + encryptKey + "'"; 
            sqlstatement.executeUpdate(update1);
            sqlstatement.executeUpdate(update2);
			}
	        catch(Exception e) 
	        { 
	            //System.out.println(e); 
	            e.printStackTrace();
	        } 
			
		}
		
}
