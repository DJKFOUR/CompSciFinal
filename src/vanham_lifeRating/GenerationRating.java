package vanham_lifeRating;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import vanham_life.Life;

public class GenerationRating implements ActionListener {

    private final ImageIcon imageIcon = new ImageIcon("logo.png");
    private final Life game = new Life(20, false);
    private final JFrame frame = new JFrame("Life File Generation Rating");
    private final JPanel contentPane = new JPanel();
    private final JPanel controls = new JPanel();
    private final JButton load, rate;
    private final JTextArea list;
    private final JScrollPane scroll;
    private static final Color GRAY = new Color(100, 100, 100);
    private static final Color TEAL = new Color(0, 250, 200);
    private static int generationNum = 0;
    private final ArrayList gens = new <Generation>ArrayList();
    
    /**
     * Constructor - Creates and initializes a GenerationRating Object
     * 
     * Pre: none
     * post: A GenerationRating object has been created
     */
    public GenerationRating() {
        frame.setIconImage(imageIcon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setContentPane(contentPane);

        contentPane.setBackground(new Color(50, 50, 50));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        
        controls.setOpaque(false);
        controls.setLayout(new GridLayout(1, 2, 10, 10));
        controls.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), new CompoundBorder(new LineBorder(TEAL, 3, true), BorderFactory.createEmptyBorder(5, 5, 5, 5))));

        list = new JTextArea(10, 30);
        list.setText("To begin, please load a file.");
        list.setFont(new Font("Century Gothic", Font.ITALIC, 12));
        list.setBackground(Color.BLACK);
        list.setForeground(TEAL);
        list.setLineWrap(true);
        list.setEditable(false);
        list.setVisible(true);
        scroll = new JScrollPane(list);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(scroll);

        load = new JButton("Load a file");
        load.setFont(new Font("Century Gothic", Font.BOLD, 12));
        load.setBackground(GRAY);
        load.setForeground(TEAL);
        load.setBorderPainted(false);
        load.setFocusPainted(false);
        load.addActionListener(this);
        load.setActionCommand("load");
        controls.add(load);

        rate = new JButton("Rate Generations");
        rate.setFont(new Font("Century Gothic", Font.BOLD, 12));
        rate.setBackground(GRAY);
        rate.setForeground(TEAL);
        rate.setBorderPainted(false);
        rate.setFocusPainted(false);
        rate.addActionListener(this);
        rate.setActionCommand("rate");
        rate.setVisible(false);
        controls.add(rate);

        contentPane.add(controls);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    /**
         * Handle button click event
         * 
         * Pre: none
         * Post: action performed
         * 
         * @param event = button event
         */
    @Override
    public void actionPerformed(ActionEvent event) {
        String whichBtn = event.getActionCommand();
        switch (whichBtn) {
            case "load":
                game.load();
                list.setText("Please click \"Rate Generations\"");
                generationNum = 1;
                rate.setVisible(true);
                frame.pack();
                break;
            case "rate":
                String listText = "";
                while (generationNum < 1000 && game.countPopulation()!=0) {
                    int population = game.countPopulation();
                    gens.add(new Generation(generationNum, population));
                    game.takeStep();
                    generationNum++;
                }
                Sorts.mergesort(gens, 0, gens.size() - 1);
                for (int i = gens.size() - 1; i >= 0; i--) { //Read list backwards(High to low)
                    listText += gens.get(i).toString();
                    if (i > 0) {
                        listText += "\n"; //Add data to a String
                    }
                }
                list.setFont(new Font("Century Gothic", Font.PLAIN, 12));
                list.setText(listText); //Display data
                list.grabFocus();
                list.setCaretPosition(0);
                frame.pack();
                System.out.println("Data sorted");
                break;
        }

    }
    
    /**
     * Creates and shows the GUI
     * 
     * Pre: none
     * Post: a GenerationRating GUI is displayed
     */
    private static void runGUI() {
        JFrame.setDefaultLookAndFeelDecorated(false);
        GenerationRating ranking = new GenerationRating();
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
