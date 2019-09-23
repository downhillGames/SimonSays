//import java.awt.EventQueue;
//import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class MainMenu extends JPanel {

	private static final long serialVersionUID = -7892106385327845406L;
	static int lives = 6;
    public MainMenu() {
      
        initUI();
    }

    private void initUI() {
      
        JButton playButton = new JButton("Play Game");
        add(playButton);
        JButton quitButton = new JButton("Quit");
        add(quitButton);
        playButton.addActionListener(new button());
        quitButton.addActionListener(new button2());
    }
    

   
}