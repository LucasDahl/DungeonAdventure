import javax.swing.*;

/**
 * Create the frame
 */
public class GameWindow {


    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Dungeon Adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);


        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);


    }
}
