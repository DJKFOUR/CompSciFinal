package vanham_life;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Kyle
 */
public class LifeGUI {

    private static ImageIcon imageIcon = new ImageIcon("logo.png");
    private static JPanel contentPane = new JPanel();
    private static LifeGrid gameGrid = new LifeGrid();
    private static Logo logo = new Logo();
    private static LifeControl gameControl = new LifeControl(gameGrid);
    private static JPanel right = new JPanel();
    private static JFrame frame = new JFrame("LIFE");

    /**
     * Creates and shows the GUI
     * 
     * Pre: none
     * Post: a life GUI is displayed
     */
    private static void createAndShowGui() {
        frame.setIconImage(imageIcon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        contentPane.setBackground(new Color(50, 50, 50));
        contentPane.add(gameGrid);
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setOpaque(false);
        right.add(logo);
        right.add(gameControl);
        contentPane.add(right);
        frame.setResizable(false);
        frame.add(contentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGui();
            }
        });
    }
}
