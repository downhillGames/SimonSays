import javax.swing.JTextField;

//import java.awt.EventQueue;
//import javax.swing.JFrame;

public class Global {

	public double total_gametime = 0;
	public String birthdate;
	public String address;
	public String city;
	public String state;
	public String zip_code;
	public  boolean classic_mode = true;
	public String country;
	public String diagnosis;
	public String name; 
	public int loadGamePosition = -1;
	public boolean newGame = true;
    public double gametime = 0;
    public double roundtime = 0;
    public int level = 2;
    //public int high_level = 2;
    public double high_level = 2;
    public boolean reverse_game = false;
    public boolean computer_playing = false;
    public boolean first_hit = true;
    public int times_won = 0;
    public int speed = 000;
    public int health = 1;
    public void Init() {

        //initUI();
    }
}