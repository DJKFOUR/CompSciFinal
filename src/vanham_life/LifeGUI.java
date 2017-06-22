package vanham_life;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Kyle
 */
public class LifeGUI {

    private final ImageIcon imageIcon = new ImageIcon("logo.png");
    private final JPanel contentPane = new JPanel();
    private final LifeGrid gameGrid = new LifeGrid();
    private final Logo logo = new Logo();
    private final LifeControl gameControl = new LifeControl(gameGrid);
    private final JPanel right = new JPanel();
    private final JFrame frame = new JFrame("LIFE");

    /**
     * Creates initializes a LifeGUI object
     * 
     * Pre: none
     * Post: a LifeGUI is created
     */
    public LifeGUI() {
        frame.setIconImage(imageIcon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setContentPane(contentPane);
        
        contentPane.setBackground(new Color(50, 50, 50));
        contentPane.add(gameGrid);
        
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setOpaque(false);
        right.add(logo);
        right.add(gameControl);
        contentPane.add(right);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    /**
     * Creates and shows the GUI
     * 
     * Pre: none
     * Post: a life GUI is displayed
     */
    private static void runGUI() {
        JFrame.setDefaultLookAndFeelDecorated(false);
        LifeGUI life = new LifeGUI();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                runGUI();
            }
        });
    }
}
