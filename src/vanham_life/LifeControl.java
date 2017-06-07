package vanham_life;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

/**
 *
 * @author Kyle
 */
public class LifeControl extends JPanel {

    private LifeGrid grid;
    private JButton save, load, step, clear, run;
    private JButton red, orange, yellow, green, blue, purple, teal;
    private JFileChooser fileChooser;
    private int generationNum = 0;
    private Timer timer = new Timer(250, new TimerListener());
    private static final Color GRAY = new Color(100, 100, 100);
    private static final Color TEAL = new Color(0, 250, 200);
    private static final Color PURPLE = new Color(182, 66, 244);

    public LifeControl(final LifeGrid grid) {
        this.grid = grid;

        setLayout(new GridLayout(0, 2, 10, 10));
        setOpaque(false);

        save = new JButton("Save");
        save.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        save.setBackground(GRAY);
        save.setForeground(TEAL);
        save.setBorder(null);
        save.setFocusPainted(false);
        save.addActionListener(new BtnListener());
        save.setActionCommand("save");
        add(save);

        load = new JButton("Load");
        load.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        load.setBackground(GRAY);
        load.setForeground(TEAL);
        load.setBorder(null);
        load.setFocusPainted(false);
        load.addActionListener(new BtnListener());
        load.setActionCommand("load");
        add(load);

        step = new JButton("Take Step");
        step.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        step.setBackground(GRAY);
        step.setForeground(TEAL);
        step.setBorder(null);
        step.setFocusPainted(false);
        step.addActionListener(new BtnListener());
        step.setActionCommand("step");
        add(step);

        clear = new JButton("Clear Grid");
        clear.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        clear.setBackground(GRAY);
        clear.setForeground(TEAL);
        clear.setBorderPainted(false);
        clear.setFocusPainted(false);
        clear.addActionListener(new BtnListener());
        clear.setActionCommand("clear");
        add(clear);

        run = new JButton("Run");
        run.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        run.setBackground(GRAY);
        run.setForeground(TEAL);
        run.setBorderPainted(false);
        run.setFocusPainted(false);
        run.addActionListener(new BtnListener());
        run.setActionCommand("run");
        add(run);

        red = new JButton("");
        red.setBackground(Color.RED);
        red.setBorderPainted(false);
        red.setFocusPainted(false);
        red.addActionListener(new BtnListener());
        red.setActionCommand("red");
        add(red);

        orange = new JButton("");
        orange.setBackground(Color.ORANGE);
        orange.setBorderPainted(false);
        orange.setFocusPainted(false);
        orange.addActionListener(new BtnListener());
        orange.setActionCommand("orange");
        add(orange);

        yellow = new JButton("");
        yellow.setBackground(Color.YELLOW);
        yellow.setBorderPainted(false);
        yellow.setFocusPainted(false);
        yellow.addActionListener(new BtnListener());
        yellow.setActionCommand("yellow");
        add(yellow);

        green = new JButton("");
        green.setBackground(Color.GREEN);
        green.setBorderPainted(false);
        green.setFocusPainted(false);
        green.addActionListener(new BtnListener());
        green.setActionCommand("green");
        add(green);

        teal = new JButton("");
        teal.setBackground(TEAL);
        teal.setBorderPainted(false);
        teal.setFocusPainted(false);
        teal.addActionListener(new BtnListener());
        teal.setActionCommand("teal");
        add(teal);

        blue = new JButton("");
        blue.setBackground(Color.BLUE);
        blue.setBorderPainted(false);
        blue.setFocusPainted(false);
        blue.addActionListener(new BtnListener());
        blue.setActionCommand("blue");
        add(blue);

        purple = new JButton("");
        purple.setBackground(PURPLE);
        purple.setBorderPainted(false);
        purple.setFocusPainted(false);
        purple.addActionListener(new BtnListener());
        purple.setActionCommand("purple");
        add(purple);
    }

    class BtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            String whichBtn = event.getActionCommand();
            int returnValue;
            switch (whichBtn) {
                case "save":
                    fileChooser = new JFileChooser();
                    returnValue = fileChooser.showOpenDialog(null);
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        grid.save(selectedFile);
                    }
                    break;
                case "load":
                    fileChooser = new JFileChooser();
                    returnValue = fileChooser.showOpenDialog(null);
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        grid.load(selectedFile);
                    }
                    generationNum = 0;
                    GUI.changeGenCounter("");
                    break;
                case "step":
                    grid.step();
                    generationNum++;
                    GUI.changeGenCounter("Generation: " + generationNum);
                    System.out.println("Single Step Taken");
                    break;
                case "clear":
                    grid.clear();
                    System.out.println("Grid Cleared");
                    generationNum = 0;
                    GUI.changeGenCounter("");
                    break;
                case "run":
                    timer.start();
                    run.setText("STOP");
                    run.setFont(new Font("Century Gothic", Font.BOLD, 12));
                    run.setBackground(Color.RED);
                    run.setForeground(Color.BLACK);
                    run.setActionCommand("stop");
                    System.out.println("Timer Running: " + timer.isRunning());
                    break;
                case "stop":
                    timer.stop();
                    run.setText("Run");
                    run.setFont(new Font("Century Gothic", Font.PLAIN, 12));
                    run.setBackground(GRAY);
                    run.setForeground(TEAL);
                    run.setActionCommand("run");
                    System.out.println("Timer Running: " + timer.isRunning());
                    break;
                case "red":
                    grid.setColour(Color.RED);
                    break;
                case "orange":
                    grid.setColour(Color.ORANGE);
                    break;
                case "yellow":
                    grid.setColour(Color.YELLOW);
                    break;
                case "green":
                    grid.setColour(Color.GREEN);
                    break;
                case "teal":
                    grid.setColour(TEAL);
                    break;
                case "blue":
                    grid.setColour(Color.BLUE);
                    break;
                case "purple":
                    grid.setColour(PURPLE);
                    break;
            }
        }
    }

    class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!grid.isEmpty()) {
                if (generationNum > 999) {
                    timer.stop();
                    run.setText("Run");
                    run.setFont(new Font("Century Gothic", Font.PLAIN, 12));
                    run.setBackground(GRAY);
                    run.setForeground(TEAL);
                    run.setActionCommand("run");
                    System.out.println("Timer Running: " + timer.isRunning());
                    generationNum = 999;
                    GUI.changeGenCounter("Generation: 999+");
                } else {
                    grid.step();
                    generationNum++;
                    GUI.changeGenCounter("Generation: " + generationNum);
                }
                GUI.pack();
            } else {
                timer.stop();
                run.setText("Run");
                run.setFont(new Font("Century Gothic", Font.PLAIN, 12));
                run.setBackground(GRAY);
                run.setForeground(TEAL);
                run.setActionCommand("run");
                System.out.println("Timer Running: " + timer.isRunning());
            }
        }
    }
}
