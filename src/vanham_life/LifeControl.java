package vanham_life;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author Kyle
 */
public class LifeControl extends JPanel {

    private final LifeGrid grid;
    private final JPanel buttons = new JPanel();
    private final JPanel counter = new JPanel();
    private final JButton save, load, step, clear, run, recall;
    private final JButton red, orange, yellow, green, blue, purple, teal;
    private final JLabel generationCounter;
    private int generationNum = 0;
    private final Timer timer = new Timer(250, new TimerListener());
    private static final Color GRAY = new Color(100, 100, 100);
    private static final Color TEAL = new Color(0, 250, 200);
    private static final Color PURPLE = new Color(182, 66, 244);
    private static Color GUI_COLOUR = TEAL;
    
    /**
     * Constructor - Creates and initializes the control panel
     * 
     * Pre: a LifeGrid object to control
     * Post: a LifeControl object has been created
     * 
     * @param grid = LifeGrid to control
     */
    public LifeControl(final LifeGrid grid) {
        this.grid = grid;
        
        setOpaque(false);
        setBorder(new CompoundBorder(new TitledBorder(new LineBorder(TEAL, 3, true), "MENU", TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION, new Font("Century Gothic", Font.PLAIN, 12), TEAL), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        buttons.setLayout(new GridLayout(0, 2, 10, 10));
        buttons.setOpaque(false);

        save = new JButton("Save");
        save.setFont(new Font("Century Gothic", Font.BOLD, 14));
        save.setBackground(GRAY);
        save.setForeground(GUI_COLOUR);
        save.setBorderPainted(false);
        save.setFocusPainted(false);
        save.addActionListener(new BtnListener());
        save.setActionCommand("save");
        buttons.add(save);

        load = new JButton("Load");
        load.setFont(new Font("Century Gothic", Font.BOLD, 14));
        load.setBackground(GRAY);
        load.setForeground(GUI_COLOUR);
        load.setBorderPainted(false);
        load.setFocusPainted(false);
        load.addActionListener(new BtnListener());
        load.setActionCommand("load");
        buttons.add(load);

        step = new JButton("Take Step");
        step.setFont(new Font("Century Gothic", Font.BOLD, 14));
        step.setBackground(GRAY);
        step.setForeground(GUI_COLOUR);
        step.setBorderPainted(false);
        step.setFocusPainted(false);
        step.addActionListener(new BtnListener());
        step.setActionCommand("step");
        buttons.add(step);

        clear = new JButton("Clear Grid");
        clear.setFont(new Font("Century Gothic", Font.BOLD, 14));
        clear.setBackground(GRAY);
        clear.setForeground(GUI_COLOUR);
        clear.setBorderPainted(false);
        clear.setFocusPainted(false);
        clear.addActionListener(new BtnListener());
        clear.setActionCommand("clear");
        buttons.add(clear);

        run = new JButton("Run");
        run.setFont(new Font("Century Gothic", Font.BOLD, 14));
        run.setBackground(GRAY);
        run.setForeground(GUI_COLOUR);
        run.setBorderPainted(false);
        run.setFocusPainted(false);
        run.addActionListener(new BtnListener());
        run.setActionCommand("run");
        buttons.add(run);

        recall = new JButton("Recall");
        recall.setFont(new Font("Century Gothic", Font.BOLD, 14));
        recall.setBackground(GRAY);
        recall.setForeground(GUI_COLOUR);
        recall.setBorderPainted(false);
        recall.setFocusPainted(false);
        recall.addActionListener(new BtnListener());
        recall.setActionCommand("recall");
        recall.setVisible(false); //Not shown by default
        buttons.add(recall);

        red = new JButton("");
        red.setBackground(Color.RED);
        red.setBorderPainted(false);
        red.setFocusPainted(false);
        red.addActionListener(new BtnListener());
        red.setActionCommand("red");
        buttons.add(red);

        orange = new JButton("");
        orange.setBackground(Color.ORANGE);
        orange.setBorderPainted(false);
        orange.setFocusPainted(false);
        orange.addActionListener(new BtnListener());
        orange.setActionCommand("orange");
        buttons.add(orange);

        yellow = new JButton("");
        yellow.setBackground(Color.YELLOW);
        yellow.setBorderPainted(false);
        yellow.setFocusPainted(false);
        yellow.addActionListener(new BtnListener());
        yellow.setActionCommand("yellow");
        buttons.add(yellow);

        green = new JButton("");
        green.setBackground(Color.GREEN);
        green.setBorderPainted(false);
        green.setFocusPainted(false);
        green.addActionListener(new BtnListener());
        green.setActionCommand("green");
        buttons.add(green);

        teal = new JButton("");
        teal.setBackground(TEAL);
        teal.setBorderPainted(false);
        teal.setFocusPainted(false);
        teal.addActionListener(new BtnListener());
        teal.setActionCommand("teal");
        buttons.add(teal);

        blue = new JButton("");
        blue.setBackground(Color.BLUE);
        blue.setBorderPainted(false);
        blue.setFocusPainted(false);
        blue.addActionListener(new BtnListener());
        blue.setActionCommand("blue");
        buttons.add(blue);

        purple = new JButton("");
        purple.setBackground(PURPLE);
        purple.setBorderPainted(false);
        purple.setFocusPainted(false);
        purple.addActionListener(new BtnListener());
        purple.setActionCommand("purple");
        buttons.add(purple);
        
        counter.setOpaque(false);
        
        generationCounter = new JLabel("Generation: " + generationNum);
        generationCounter.setFont(new Font("Century Gothic", Font.BOLD, 14));
        generationCounter.setForeground(TEAL);
        counter.add(generationCounter);
        
        add(buttons);
        add(generationCounter);
    }

    class BtnListener implements ActionListener {
        
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
                case "save":
                    grid.save();
                    break;
                case "load":
                    grid.load();
                    generationNum = 0;
                    generationCounter.setText("Generation: " + generationNum);
                    break;
                case "step":
                    grid.step();
                    generationNum++;
                    generationCounter.setText("Generation: " + generationNum);
                    System.out.println("Single Step Taken");
                    break;
                case "clear":
                    grid.clear();
                    generationNum = 0;
                    generationCounter.setText("Generation: " + generationNum);
                    System.out.println("Grid Cleared");
                    break;
                case "run":
                    timer.start();
                    recall.setVisible(false);
                    run.setText("STOP");
                    run.setBackground(Color.RED);
                    run.setForeground(Color.BLACK);
                    run.setActionCommand("stop"); //Set button to stop timer
                                                  //instead of start
                    System.out.println("Timer Running: " + timer.isRunning());
                    break;
                case "stop":
                    recall.show();
                    timer.stop();
                    run.setText("Run");
                    run.setBackground(GRAY);
                    run.setForeground(GUI_COLOUR);
                    run.setActionCommand("run"); //Set button to start timer
                                                 //instead of stop
                    System.out.println("Timer Running: " + timer.isRunning());
                    break;
                case "recall":
                    grid.loadTemp();
                    generationNum = 0;
                    generationCounter.setText("Generation: " + generationNum);
                    break;
                case "red":
                    changeGUIColor(Color.RED);
                    break;
                case "orange":
                    changeGUIColor(Color.ORANGE);
                    break;
                case "yellow":
                    changeGUIColor(Color.YELLOW);
                    break;
                case "green":
                    changeGUIColor(Color.GREEN);
                    break;
                case "teal":
                    changeGUIColor(TEAL);
                    break;
                case "blue":
                    changeGUIColor(Color.BLUE);
                    break;
                case "purple":
                    changeGUIColor(PURPLE);
                    break;
            }
        }
        
        /**
         * Sets the color of the GUI(All buttons and colored features)
         * 
         * Pre: a Color object
         * Post: GUI color has been updated
         * 
         * @param c = Color Object to use as the GUI Color
         */
        private void changeGUIColor(Color c) {
            GUI_COLOUR = c;
            grid.setColour(GUI_COLOUR);
            generationCounter.setForeground(GUI_COLOUR);
            setBorder(new CompoundBorder(new TitledBorder(new LineBorder(GUI_COLOUR, 3, true), "MENU", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Century Gothic", Font.BOLD, 14), GUI_COLOUR), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            save.setForeground(GUI_COLOUR);
            load.setForeground(GUI_COLOUR);
            step.setForeground(GUI_COLOUR);
            clear.setForeground(GUI_COLOUR);
            run.setForeground(GUI_COLOUR);
            recall.setForeground(GUI_COLOUR);
        }
    }

    class TimerListener implements ActionListener {
        
        /**
         * Handle timer event
         * 
         * Pre: none
         * Post: action performed
         * 
         * @param event = timer event
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            if (!grid.isEmpty()) { //If the grid has something alive still
                if (generationNum > 999) { //Prevent from running indefinitely
                    timer.stop();
                    recall.setVisible(true);
                    run.setText("Run");
                    run.setBackground(GRAY);
                    run.setForeground(GUI_COLOUR);
                    run.setActionCommand("run");
                    System.out.println("Timer Running: " + timer.isRunning());
                    generationNum = 999;
                    generationCounter.setText("Generation: 999+");
                } else {
                    grid.step();
                    generationNum++;
                    generationCounter.setText("Generation: " + generationNum);
                }
            } else {
                timer.stop();
                recall.setVisible(true);
                run.setText("Run");
                run.setBackground(GRAY);
                run.setForeground(GUI_COLOUR);
                run.setActionCommand("run");
                System.out.println("Timer Running: " + timer.isRunning());
            }
        }
    }
}
