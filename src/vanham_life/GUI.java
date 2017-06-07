package vanham_life;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Kyle
 */
public class GUI {

    private static ImageIcon imageIcon = new ImageIcon("logo.png");
    private static JPanel contentPane = new JPanel();
    private static LifeGrid gameGrid = new LifeGrid();
    private static Logo logo = new Logo();
    private static LifeControl gameControl = new LifeControl(gameGrid);
    private static JLabel genCount = new JLabel(" ");
    private static JPanel right = new JPanel();
    private static JFrame frame = new JFrame("LIFE");
    private static final Color TEAL = new Color(0, 250, 200);

    private static void createAndShowGui() {

        frame.setIconImage(imageIcon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        contentPane.setBackground(new Color(50, 50, 50));
        contentPane.add(gameGrid);
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.add(logo);
        right.add(gameControl);
        genCount.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        genCount.setForeground(TEAL);
        right.add(genCount);
        right.setOpaque(false);
        contentPane.add(right);
        frame.setResizable(false);
        frame.add(contentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void pack() {
        frame.pack();
    }

    public static void changeGenCounter(String text) {
        genCount.setText(text);
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
