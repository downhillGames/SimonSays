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
      
        JButton playButton = new JButton("New Game");
        add(playButton);
        JButton loadButton = new JButton("Load Game");
        add(loadButton);
        JButton quitButton = new JButton("Quit");
        add(quitButton);
        playButton.addActionListener(new button());
        loadButton.addActionListener(new loadGameBtn());
        quitButton.addActionListener(new button2());
    }
    

   
}