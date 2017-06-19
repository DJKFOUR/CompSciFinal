package vanham_life;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import javax.swing.*;

/**
 *
 * @author Kyle
 */
public class LifeGrid extends JPanel {
    
    private static File temp;
    private static final int GAME_SIZE = 20;
    private static final int RECT_WIDTH = 20;
    private static final int RECT_HEIGHT = RECT_WIDTH;
    static final Color TEAL = new Color(0, 250, 200);
    private static Color colour = TEAL;
    private Life game;

    public LifeGrid() {
        try {
            temp = File.createTempFile("life", ".tmp");
            System.out.println("Temp file : " + temp.getAbsolutePath());
        } catch (IOException exception) {
            System.out.println("Problem with input/output.");
            System.err.println("IOException: " + exception.getMessage());
        }
        temp.deleteOnExit();
        game = new Life(GAME_SIZE);
        setBackground(Color.BLACK);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / RECT_WIDTH;
                int y = e.getY() / RECT_HEIGHT;
                if (game.getCell(y, x) == 1) {
                    game.setCell(y, x, 0);
                    System.out.println(x + "," + y + ": DEAD");
                } else {
                    game.setCell(y, x, 1);
                    System.out.println(x + "," + y + ": Alive");
                }
                repaint();
                saveTemp();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < GAME_SIZE; row++) {
            for (int col = 0; col < GAME_SIZE; col++) {
                if (game.getCell(row, col) == 1) {
                    g.setColor(colour);
                    g.fillRect(col * RECT_HEIGHT, row * RECT_WIDTH, RECT_WIDTH, RECT_HEIGHT);
                }
                g.setColor(Color.GRAY);
                g.drawRect(col * RECT_HEIGHT, row * RECT_WIDTH, RECT_WIDTH-1, RECT_HEIGHT-1);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(GAME_SIZE * RECT_WIDTH, GAME_SIZE * RECT_HEIGHT);
    }

    public void step() {
        game.takeStep();
        repaint();
    }

    public void clear() {
        game.killAllCells();
        repaint();
    }

    public boolean isEmpty() {
        return game.countPopulation() == 0;
    }
    
    public void setColour(Color c) {
        colour = c;
        repaint();
    }

    public void save() {
        game.save();
    }
    
    public void load() {
        game.load();
        repaint();
        saveTemp();
    }
    
    public void saveTemp() {
        try {
            
            /* write objects */
            FileOutputStream out = new FileOutputStream(temp);
            ObjectOutputStream writeLife = new ObjectOutputStream(out);

            writeLife.writeObject(game);

            writeLife.close();
            out.close();

            System.out.println("Temp file updated.");

        } catch (FileNotFoundException e) {
            System.out.println("File could not be found.");
            System.err.println("FileNotFoundException: "
                    + e.getMessage());
        } catch (IOException e) {
            System.out.println("Problem with input/output.");
            System.err.println("IOException: " + e.getMessage());
        }
    }
    
    public void loadTemp() {
        try {
            
            /* read objects */
            FileInputStream in = new FileInputStream(temp);
            ObjectInputStream readLife = new ObjectInputStream(in);

            game = (Life)readLife.readObject();

            readLife.close();
            in.close();

            System.out.println("Data read from file.");

        } catch (FileNotFoundException e) {
            System.out.println("File could not be found.");
            System.err.println("FileNotFoundException: "
                    + e.getMessage());
        } catch (IOException e) {
            System.out.println("Problem with input/output.");
            System.err.println("IOException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class could not be used to cast object.");
            System.err.println("ClassNotFoundException: "
                    + e.getMessage());
        }
        repaint();
    }
    
    public int countPopulation() {
        return game.countPopulation();
    }
}
