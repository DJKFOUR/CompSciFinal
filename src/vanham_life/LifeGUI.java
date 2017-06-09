package vanham_life;

import java.awt.*;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

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
    private static JLabel genCount = new JLabel(" ");
    private static JPanel right = new JPanel();
    private static JPanel buttonsAndStats = new JPanel();
    private static JFrame frame = new JFrame("LIFE");
    private static final Color TEAL = new Color(0, 250, 200);

    private static void createAndShowGui() {

        frame.setIconImage(imageIcon.getImage());
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent winEvt) {
                LifeGrid.deleteTemp();
                System.exit(0);
            }
        });
        frame.setResizable(false);
        contentPane.setBackground(new Color(50, 50, 50));
        contentPane.add(gameGrid);
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        buttonsAndStats.setOpaque(false);
        buttonsAndStats.setLayout(new BoxLayout(buttonsAndStats, BoxLayout.Y_AXIS));
        buttonsAndStats.setBorder(new TitledBorder(new LineBorder(TEAL, 3, true), "MENU", TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION, new Font("Century Gothic", Font.PLAIN, 12), TEAL));
        right.setOpaque(false);
        buttonsAndStats.add(gameControl);
        genCount.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        genCount.setForeground(TEAL);
        buttonsAndStats.add(genCount);
        right.add(logo);
        right.add(buttonsAndStats);
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
