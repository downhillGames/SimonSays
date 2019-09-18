//import java.util.concurrent.TimeUnit;
//import java.awt.EventQueue;
import javax.swing.*;

public class GameScreen extends JFrame {

    public GameScreen() {

        initUI();
    }

    private void initUI() {

        setTitle("Game");
        setSize(650, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}