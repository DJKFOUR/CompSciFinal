package vanham_life;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Kyle
 */
public class GUI {

    private static void createAndShowGui() {
        ImageIcon imageIcon = new ImageIcon("logo.png");
        JPanel contentPane = new JPanel();
        LifeGrid gameGrid = new LifeGrid();
        Logo logo = new Logo();
        LifeControl gameControl = new LifeControl(gameGrid);
        JPanel right = new JPanel();

        JFrame frame = new JFrame("LIFE");
        frame.setIconImage(imageIcon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        contentPane.setBackground(new Color(50, 50, 50));
        contentPane.add(gameGrid);
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.add(logo);
        right.add(gameControl);
        right.setOpaque(false);
        contentPane.add(right);
        
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
