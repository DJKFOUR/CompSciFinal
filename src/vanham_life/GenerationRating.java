package vanham_life;

import javax.swing.*;
import java.awt.event.*;

public class GenerationRating implements ActionListener {
    private static JFrame frame;
    private static JPanel contentPane;
    private static JLabel label;
    private static JButton button;

    public GenerationRating() {
        
        frame = new JFrame("Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        contentPane = new JPanel();
        
        label = new JLabel("Click the button plz");
        contentPane.add(label);
        
        button = new JButton("Rate Generations");
        button.addActionListener(this);
        button.setActionCommand("rate");
        contentPane.add(button);
        
        frame.setContentPane(contentPane);
        
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Handle button click action event
     * pre: Action event is Clicked
     * post: Label displays different message depending on if the button was
     * clicked.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String whichBtn = e.getActionCommand();
        
        if (whichBtn.equals("rate")) {
            label.setText("Print list of generations in order");
        }
        frame.pack(); //resize frame to fit button as it grows in size
    }

    /**
     * Create and show the GUI.
     */
    private static void runGUI() {
        JFrame.setDefaultLookAndFeelDecorated(false);
        GenerationRating greeting = new GenerationRating();
    }

    public static void main(String[] args) {
        
        /* Methods that create and show a GUI should be
         run from an event-dispatching thread */
        javax.swing.SwingUtilities.invokeLater(new Runnable() {public void run() {
                runGUI();
            }
        });
    }
}

