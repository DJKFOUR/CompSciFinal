package vanham_lifeRating;

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
    private static JTextField list;
    private static JButton load, rate;
    private int generationNum = 0;
    private ArrayList gens = new <Generation>ArrayList();

    public GenerationRating() {
        
        frame = new JFrame("Life File Generation Rating");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        
        load = new JButton("Load a file");
        load.addActionListener(this);
        load.setActionCommand("load");
        contentPane.add(load);

        rate = new JButton("Rate Generations");
        rate.addActionListener(this);
        rate.setActionCommand("rate");
        rate.setVisible(false);
        contentPane.add(rate);

        list = new JTextField("Click the button plz");
        contentPane.add(list);

        frame.setResizable(false);
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Handle button click action event pre: Action event is Clicked post: Label
     * displays different message depending on if the button was clicked.
     */
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
                //run the file and store each generation in an arraylist
                //until all cells are dead or 999+ is reached

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
