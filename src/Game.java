import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class Game extends JPanel {

    public Game() {
      
        initUI();
    }

    private void initUI() {
      
        JButton quitButton = new JButton("Hello");
        add(quitButton);
        JButton quitButton2 = new JButton("Hi");
        add(quitButton2);
        quitButton.addActionListener(new button());
        quitButton2.addActionListener(new button2());
    }
    

   
}