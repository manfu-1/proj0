import javax.swing.JFrame;
import java.awt.Color;

public class GameFrame extends JFrame{
    public GameFrame(){
        setTitle("2048");
        setSize(370,420);
        getContentPane().setBackground(new Color(66,136,83));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
