package vanham_life;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;

/**
 *
 * @author Kyle
 */
public class LifeControl extends JPanel {

    private LifeGrid grid;
    private JPanel buttons;
    private JPanel counter;
    private JButton save, load, step, clear, run, recall;
    private JButton red, orange, yellow, green, blue, purple, teal;
    private JLabel generationCounter;
    private JFileChooser fileChooser;
    private FileFilter filter = new FileNameExtensionFilter("Life file", new String[]{"life"});
    private int generationNum = 0;
    private Timer timer = new Timer(250, new TimerListener());
    private static final Color GRAY = new Color(100, 100, 100);
    private static final Color TEAL = new Color(0, 250, 200);
    private static final Color PURPLE = new Color(182, 66, 244);

    public LifeControl(final LifeGrid grid) {
        this.grid = grid;
        
        setOpaque(false);
        setBorder(new CompoundBorder(new TitledBorder(new LineBorder(TEAL, 3, true), "MENU", TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION, new Font("Century Gothic", Font.PLAIN, 12), TEAL), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        buttons = new JPanel();
        buttons.setLayout(new GridLayout(0, 2, 10, 10));
        buttons.setOpaque(false);
        
        counter = new JPanel();
        counter.setOpaque(false);

        save = new JButton("Save");
        save.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        save.setBackground(GRAY);
        save.setForeground(TEAL);
        save.setBorderPainted(false);
        save.setFocusPainted(false);
        save.addActionListener(new BtnListener());
        save.setActionCommand("save");
        buttons.add(save);

        load = new JButton("Load");
        load.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        load.setBackground(GRAY);
        load.setForeground(TEAL);
        load.setBorderPainted(false);
        load.setFocusPainted(false);
        load.addActionListener(new BtnListener());
        load.setActionCommand("load");
        buttons.add(load);

        step = new JButton("Take Step");
        step.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        step.setBackground(GRAY);
        step.setForeground(TEAL);
        step.setBorderPainted(false);
        step.setFocusPainted(false);
        step.addActionListener(new BtnListener());
        step.setActionCommand("step");
        buttons.add(step);

        clear = new JButton("Clear Grid");
        clear.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        clear.setBackground(GRAY);
        clear.setForeground(TEAL);
        clear.setBorderPainted(false);
        clear.setFocusPainted(false);
        clear.addActionListener(new BtnListener());
        clear.setActionCommand("clear");
        buttons.add(clear);

        run = new JButton("Run");
        run.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        run.setBackground(GRAY);
        run.setForeground(TEAL);
        run.setBorderPainted(false);
        run.setFocusPainted(false);
        run.addActionListener(new BtnListener());
        run.setActionCommand("run");
        buttons.add(run);

        recall = new JButton("Recall");
        recall.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        recall.setBackground(GRAY);
        recall.setForeground(TEAL);
        recall.setBorderPainted(false);
        recall.setFocusPainted(false);
        recall.addActionListener(new BtnListener());
        recall.setActionCommand("recall");
        recall.setVisible(false);
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
        
        generationCounter = new JLabel(" ");
        generationCounter.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        generationCounter.setForeground(TEAL);
        counter.add(generationCounter);
        
        add(buttons);
        add(generationCounter);
    }

    class BtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            String whichBtn = event.getActionCommand();
            int returnValue;
            switch (whichBtn) {
                case "save":
                    fileChooser = new JFileChooser();
                    fileChooser.setFileFilter(filter);
                    fileChooser.addChoosableFileFilter(filter);
                    fileChooser.setAcceptAllFileFilterUsed(false);
                    returnValue = fileChooser.showSaveDialog(null);
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        String fileName = selectedFile.getAbsolutePath();
                        if (!fileName.endsWith(".Life")) {
                            fileName = selectedFile.getAbsolutePath() + ".Life";
                        }
                        File saveFile = new File(fileName);
                        grid.save(saveFile);
                    }
                    break;
                case "load":
                    fileChooser = new JFileChooser();
                    fileChooser.setFileFilter(filter);
                    fileChooser.addChoosableFileFilter(filter);
                    fileChooser.setAcceptAllFileFilterUsed(false);
                    returnValue = fileChooser.showOpenDialog(null);
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        grid.load(selectedFile);
                        grid.saveTemp();
                    }
                    generationNum = 0;
                    generationCounter.setText(" ");
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
                    generationCounter.setText(" ");
                    System.out.println("Grid Cleared");
                    break;
                case "run":
                    timer.start();
                    recall.setVisible(false);
                    run.setText("STOP");
                    run.setFont(new Font("Century Gothic", Font.BOLD, 12));
                    run.setBackground(Color.RED);
                    run.setForeground(Color.BLACK);
                    run.setActionCommand("stop");
                    System.out.println("Timer Running: " + timer.isRunning());
                    break;
                case "stop":
                    recall.show();
                    timer.stop();
                    run.setText("Run");
                    run.setFont(new Font("Century Gothic", Font.PLAIN, 12));
                    run.setBackground(GRAY);
                    run.setForeground(TEAL);
                    run.setActionCommand("run");
                    System.out.println("Timer Running: " + timer.isRunning());
                    break;
                case "recall":
                    grid.loadTemp();
                    generationNum = 0;
                    generationCounter.setText(" ");
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
                    recall.setVisible(true);
                    run.setText("Run");
                    run.setFont(new Font("Century Gothic", Font.PLAIN, 12));
                    run.setBackground(GRAY);
                    run.setForeground(TEAL);
                    run.setActionCommand("run");
                    System.out.println("Timer Running: " + timer.isRunning());
                    generationNum = 999;
                    generationCounter.setText("Generation: 999+");
                } else {
                    grid.step();
                    generationNum++;
                    generationCounter.setText("Generation: " + generationNum);
                }
                LifeGUI.pack();
            } else {
                timer.stop();
                recall.setVisible(true);
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
