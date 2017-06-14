package vanham_lifeRating;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.*;
import vanham_life.LifeGrid;

public class GenerationRating implements ActionListener {

    private LifeGrid grid = new LifeGrid();
    private JFileChooser fileChooser;
    private FileFilter filter = new FileNameExtensionFilter("Life file", new String[]{"life"});
    private static JFrame frame;
    private static JPanel contentPane;
    private static JButton load, rate;
    private static JTextField list;
    private static final Color GRAY = new Color(100, 100, 100);
    private static final Color TEAL = new Color(0, 250, 200);
    private int generationNum = 0;
    private ArrayList gens = new <Generation>ArrayList();

    public GenerationRating() {
        
        frame = new JFrame("Life File Generation Rating");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(50, 50, 50));
        contentPane.setLayout(new GridLayout(0,1,10,10));
        
        load = new JButton("Load a file");
        load.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        load.setBackground(GRAY);
        load.setForeground(TEAL);
        load.setBorderPainted(false);
        load.setFocusPainted(false);
        load.addActionListener(this);
        load.setActionCommand("load");
        contentPane.add(load);

        rate = new JButton("Rate Generations");
        rate.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        rate.setBackground(GRAY);
        rate.setForeground(TEAL);
        rate.setBorderPainted(false);
        rate.setFocusPainted(false);
        rate.addActionListener(this);
        rate.setActionCommand("rate");
        rate.setVisible(false);
        contentPane.add(rate);

        list = new JTextField("Load a file and then select \"Rate Generations\" ");
        contentPane.add(list);

        frame.setResizable(false);
        frame.getSize(null);
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String whichBtn = event.getActionCommand();
        int returnValue;
        switch (whichBtn) {
            case "load":
                fileChooser = new JFileChooser();
                fileChooser.setFileFilter(filter);
                fileChooser.addChoosableFileFilter(filter);
                fileChooser.setAcceptAllFileFilterUsed(false);
                returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    grid.load(selectedFile);
                }
                generationNum = 0;
                rate.setVisible(true);
                frame.pack();
                break;
            case "rate":
                //STILL NEED TO DETERMINE WHETER A SOLID STATE WILL EXIST IN BOTH APPLICAIONS
                String listText = "";
                while (generationNum <= 999 && !grid.isEmpty()) {
                    int population = grid.countPopulation();
                    gens.add(new Generation(generationNum, population));
                    grid.step();
                    generationNum++;
                }
                Sorts.mergesort(gens, 0, gens.size() - 1);
                for (int i = gens.size()-1; i >= 0; i--) { //Read list backwards(High to low)
                    listText += gens.get(i).toString();
                    listText += "\n";
                }
                list.setText(listText);
                frame.pack();
                break;
        }

    }

    /**
     * Create and show the GUI.
     */
    private static void runGUI() {
        JFrame.setDefaultLookAndFeelDecorated(false);
        GenerationRating ranking = new GenerationRating();
    }

    public static void main(String[] args) {

        /* Methods that create and show a GUI should be
         run from an event-dispatching thread */
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                runGUI();
            }
        });
    }
}
