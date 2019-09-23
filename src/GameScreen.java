//import java.util.concurrent.TimeUnit;
//import java.awt.EventQueue;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameScreen extends JFrame {

    public GameScreen() {

        initUI();
    }

    private void initUI() {

        setTitle("Game");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}