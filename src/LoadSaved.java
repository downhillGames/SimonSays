//import java.awt.EventQueue;
//import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class LoadSaved extends JPanel {

    public LoadSaved() {
      
        initUI();
    }

    private void initUI() {
        JTextField field = new JTextField(15);
        JButton playButton = new JButton("Load Saved");
        add(playButton);
        add(field);
        playButton.addActionListener(new button());
        //quitButton.addActionListener(new button2());
    }
    

   
}