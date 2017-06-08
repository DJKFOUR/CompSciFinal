package vanham_lifeRating;

import javax.swing.*;
import java.awt.event.*;

public class GenerationRating implements ActionListener {
    private static JFrame frame;
    private static JPanel contentPane;
    private static JLabel list;
    private static JButton load,rate;

    public GenerationRating() {
        
        frame = new JFrame("Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        contentPane = new JPanel();
        
        load = new JButton("Load a file");
        load.addActionListener(this);
        load.setActionCommand("load");
        contentPane.add(load);
        
        rate = new JButton("Rate Generations");
        rate.addActionListener(this);
        rate.setActionCommand("rate");
        contentPane.add(rate);
        
        list = new JLabel("Click the button plz");
        contentPane.add(list);
        
        
        
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
        public void actionPerformed(ActionEvent event) {
            String whichBtn = event.getActionCommand();
            
            switch (whichBtn) {
                case "load":
                    
                    break;
                case "rate":
                    //run the file and store each generation in an arraylist
                    //until all cells are dead or 999+ is reached
                    
                    //STILL NEED TO DETERMINE WHETER A SOLID STATE WILL EXIST IN BOTH APPLICAIONS
                    
                    break;
        }
        frame.pack(); //resize frame to fit elements as they grow in size
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

