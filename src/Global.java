import org.json.simple.JSONArray;


public class Global {

	// Global variables for the current player 
	private int simIndex;
	private boolean firstSim = true;
 
	//obfuscated - password
	private String m2qkz77qp7;
	
	//obfuscated - Total_gametime
	private double tzsmrnsoy7 = 0;
	
	//obfuscated -  birthdate
	private String acfiqoa2lu;
	
	//obfuscated - address
	private String rq91hbhzaj;
	
	//obfuscated - city
	private String xcnwhuqdbc;
	
	//obfuscated - state
	private String dbvzwgsddi;
	
	//obfuscated - zip code
	private String g3eqbkq1m6;
	
	//obfuscated - game mode
	private int vvwmbwd6p2 = 1;
	
	//obfuscated - country
	private String tfdsbsv9qo;
	
	//obfuscated - diagnosis
	private String tmjztkxe5m;
	
	//obfuscated - name
	private String rjc8qhtv1w; 
	
	//obfuscated - interactionArray 
	private JSONArray o6vja8lio1 = new JSONArray(); 
	
	//obfuscated - forwardArray 
	private JSONArray kUnu83XHme = new JSONArray(); 
	
	//obfuscated - scores array
	private JSONArray gjw2201t44 = new JSONArray(); 
	
	//obfuscated - high_level
	private double pplk7r7pbp = 0;
	
	//variables that do not need to be obfuscated
	private double gametime = 0;
	private int loadGamePosition = -1;
	private boolean newGame = true;
	private double roundtime = 0;
	private int level = Main.getStartingLevel();
	
	private boolean reverse_game = false;
	private boolean computer_playing = false;
	private boolean first_hit = true;
	private int times_won = 0;
	private int speed = 1000;
	private int health = 1;
    
    
    /*Getter*/
	public JSONArray getInteractionArray() {
		return o6vja8lio1;
	}

	/*Setter*/
	public void setInteractionArray(JSONArray interactionArray) {
		this.o6vja8lio1 = interactionArray;
	}

	/*Getter*/
	public double getTotal_gametime() {
		return tzsmrnsoy7;
	}

	/*Setter*/
	public void setTotal_gametime(double total_gametime) {
		this.tzsmrnsoy7 = total_gametime;
	}

	/*Getter*/
	public String getBirthdate() {
		return acfiqoa2lu;
	}

	/*Setter*/
	public void setBirthdate(String birthdate) {
		this.acfiqoa2lu = birthdate;
	}

	/*Getter*/
	public String getAddress() {
		return rq91hbhzaj;
	}

	/*Setter*/
	public void setAddress(String address) {
		this.rq91hbhzaj = address;
	}

	/*Getter*/
	public String getCity() {
		return xcnwhuqdbc;
	}

	/*Setter*/
	public void setCity(String city) {
		this.xcnwhuqdbc = city;
	}

	/*Getter*/
	public String getState() {
		return dbvzwgsddi;
	}

	/*Setter*/
	public void setState(String state) {
		this.dbvzwgsddi = state;
	}

	/*Getter*/
	public String getZip_code() {
		return g3eqbkq1m6;
	}

	/*Setter*/
	public void setZip_code(String zip_code) {
		this.g3eqbkq1m6 = zip_code;
	}

	/*Getter*/
	public int getMode() {
		return vvwmbwd6p2;
	}

	/*Setter*/
	public void setMode(int mode) {
		this.vvwmbwd6p2 = mode;
	}

	/*Getter*/
	public String getCountry() {
		return tfdsbsv9qo;
	}

	/*Setter*/
	public void setCountry(String country) {
		this.tfdsbsv9qo = country;
	}

	/*Getter*/
	public String getDiagnosis() {
		return tmjztkxe5m;
	}

	/*Setter*/
	public void setDiagnosis(String diagnosis) {
		this.tmjztkxe5m = diagnosis;
	}

	/*Getter*/
	public String getName() {
		return rjc8qhtv1w;
	}

	/*Setter*/
	public void setName(String name) {
		this.rjc8qhtv1w = name;
	}

	/*Getter*/
	public int getLoadGamePosition() {
		return loadGamePosition;
	}

	/*Setter*/
	public void setLoadGamePosition(int loadGamePosition) {
		this.loadGamePosition = loadGamePosition;
	}

	/*Getter*/
	public boolean isNewGame() {
		return newGame;
	}

	/*Setter*/
	public void setNewGame(boolean newGame) {
		this.newGame = newGame;
	}

	/*Getter*/
	public double getGametime() {
		return gametime;
	}

	/*Setter*/
	public void setGametime(double gametime) {
		this.gametime = gametime;
	}

	/*Getter*/
	public double getRoundtime() {
		return roundtime;
	}

	/*Setter*/
	public void setRoundtime(double roundtime) {
		this.roundtime = roundtime;
	}

	/*Getter*/
	public int getLevel() {
		return level;
	}

	/*Setter*/
	public void setLevel(int level) {
		this.level = level;
	}

	/*Getter*/
	public double getHigh_level() {
		return pplk7r7pbp;
	}

	/*Setter*/
	public void setHigh_level(double high_level) {
		this.pplk7r7pbp = high_level;
	}

	/*Getter*/
	public boolean isReverse_game() {
		return reverse_game;
	}

	/*Setter*/
	public void setReverse_game(boolean reverse_game) {
		this.reverse_game = reverse_game;
	}

	/*Getter*/
	public boolean isComputer_playing() {
		return computer_playing;
	}

	/*Setter*/
	public void setComputer_playing(boolean computer_playing) {
		this.computer_playing = computer_playing;
	}

	/*Getter*/
	public boolean isFirst_hit() {
		return first_hit;
	}

	/*Setter*/
	public void setFirst_hit(boolean first_hit) {
		this.first_hit = first_hit;
	}

	/*Getter*/
	public int getTimes_won() {
		return times_won;
	}

	/*Setter*/
	public void setTimes_won(int times_won) {
		this.times_won = times_won;
	}

	/*Getter*/
	public int getSpeed() {
		return speed;
	}

	/*Setter*/
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/*Getter*/
	public int getHealth() {
		return health;
	}

	/*Setter*/
	public void setHealth(int health) {
		this.health = health;
	}

	/*Getter*/
	public JSONArray getScoresArray() {
		return gjw2201t44;
	}

	/*Setter*/
	public void setScoresArray(JSONArray scoresArray) {
		this.gjw2201t44 = scoresArray;
	}

	/*Getter*/
	public int getSimIndex() {
		return simIndex;
	}

	/*Setter*/
	public void setSimIndex(int simIndex) {
		this.simIndex = simIndex;
	}

	/*Getter*/
	public boolean isFirstSim() {
		return firstSim;
	}

	/*Setter*/
	public void setFirstSim(boolean firstSim) {
		this.firstSim = firstSim;
	}


	/*Getter*/
	public String getPassword() {
		return m2qkz77qp7;
	}

	/*Setter*/
	public void setPassword(String password) {
		this.m2qkz77qp7 = password;
	}

	/*Getter*/
	public JSONArray getForwardArray() {
		return kUnu83XHme;
	}

	/*Setter*/
	public void setForwardArray(JSONArray kUnu83XHme) {
		this.kUnu83XHme = kUnu83XHme;
	}


	


	 
}