import org.json.simple.JSONArray;


public class Global {

	// Global variables for the current player 
	
	private int simIndex;
	
	private boolean firstSim = true;
 
	private boolean dev_mode = false;
	
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
	
	//obfuscated - scores array
	private JSONArray gjw2201t44 = new JSONArray(); 
	
	//obfuscated - high_level
	private double pplk7r7pbp = 0;
	
	//variables that do not need to be obfuscated
	private double gametime = 0;
	private int loadGamePosition = -1;
	private boolean newGame = true;
	private double roundtime = 0;
	private int level = 2;
	
	private boolean reverse_game = false;
	private boolean computer_playing = false;
	private boolean first_hit = true;
	private int times_won = 0;
	private int speed = 1000;
	private int health = 1;
    
    
    /*Getters and Setters*/
	public JSONArray getInteractionArray() {
		return o6vja8lio1;
	}


	public void setInteractionArray(JSONArray interactionArray) {
		this.o6vja8lio1 = interactionArray;
	}

	/*Total_gametime getTotal_gametime()*/
	public double getTotal_gametime() {
		return tzsmrnsoy7;
	}


	public void setTotal_gametime(double total_gametime) {
		this.tzsmrnsoy7 = total_gametime;
	}


	public String getBirthdate() {
		return acfiqoa2lu;
	}


	public void setBirthdate(String birthdate) {
		this.acfiqoa2lu = birthdate;
	}


	public String getAddress() {
		return rq91hbhzaj;
	}


	public void setAddress(String address) {
		this.rq91hbhzaj = address;
	}


	public String getCity() {
		return xcnwhuqdbc;
	}


	public void setCity(String city) {
		this.xcnwhuqdbc = city;
	}


	public String getState() {
		return dbvzwgsddi;
	}


	public void setState(String state) {
		this.dbvzwgsddi = state;
	}


	public String getZip_code() {
		return g3eqbkq1m6;
	}


	public void setZip_code(String zip_code) {
		this.g3eqbkq1m6 = zip_code;
	}


	public int getMode() {
		return vvwmbwd6p2;
	}


	public void setMode(int mode) {
		this.vvwmbwd6p2 = mode;
	}


	public String getCountry() {
		return tfdsbsv9qo;
	}


	public void setCountry(String country) {
		this.tfdsbsv9qo = country;
	}


	public String getDiagnosis() {
		return tmjztkxe5m;
	}


	public void setDiagnosis(String diagnosis) {
		this.tmjztkxe5m = diagnosis;
	}


	public String getName() {
		return rjc8qhtv1w;
	}


	public void setName(String name) {
		this.rjc8qhtv1w = name;
	}


	public int getLoadGamePosition() {
		return loadGamePosition;
	}


	public void setLoadGamePosition(int loadGamePosition) {
		this.loadGamePosition = loadGamePosition;
	}


	public boolean isNewGame() {
		return newGame;
	}


	public void setNewGame(boolean newGame) {
		this.newGame = newGame;
	}


	public double getGametime() {
		return gametime;
	}


	public void setGametime(double gametime) {
		this.gametime = gametime;
	}


	public double getRoundtime() {
		return roundtime;
	}


	public void setRoundtime(double roundtime) {
		this.roundtime = roundtime;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public double getHigh_level() {
		return pplk7r7pbp;
	}


	public void setHigh_level(double high_level) {
		this.pplk7r7pbp = high_level;
	}


	public boolean isReverse_game() {
		return reverse_game;
	}


	public void setReverse_game(boolean reverse_game) {
		this.reverse_game = reverse_game;
	}


	public boolean isComputer_playing() {
		return computer_playing;
	}


	public void setComputer_playing(boolean computer_playing) {
		this.computer_playing = computer_playing;
	}


	public boolean isFirst_hit() {
		return first_hit;
	}


	public void setFirst_hit(boolean first_hit) {
		this.first_hit = first_hit;
	}


	public int getTimes_won() {
		return times_won;
	}


	public void setTimes_won(int times_won) {
		this.times_won = times_won;
	}


	public int getSpeed() {
		return speed;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}


	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}


	public JSONArray getScoresArray() {
		return gjw2201t44;
	}


	public void setScoresArray(JSONArray scoresArray) {
		this.gjw2201t44 = scoresArray;
	}


	public int getSimIndex() {
		return simIndex;
	}


	public void setSimIndex(int simIndex) {
		this.simIndex = simIndex;
	}


	public boolean isFirstSim() {
		return firstSim;
	}


	public void setFirstSim(boolean firstSim) {
		this.firstSim = firstSim;
	}


	public boolean isDev_mode() {
		return dev_mode;
	}


	public void setDev_mode(boolean dev_mode) {
		this.dev_mode = dev_mode;
	}


	 
}