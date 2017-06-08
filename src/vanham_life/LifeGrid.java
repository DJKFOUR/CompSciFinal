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
    
    private static int gameSize = 20;
    private static final int RECT_WIDTH = 20;
    private static final int RECT_HEIGHT = RECT_WIDTH;
    private int gridSize;
    static final Color TEAL = new Color(0, 250, 200);
    private static Color colour = TEAL;
    private Life game;

    public LifeGrid() {
        game = new Life(gameSize);
        gridSize = game.getSize();
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
                save(new File("temp.LifeTemp"));
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if (game.getCell(row, col) == 1) {
                    g.setColor(colour);
                    g.fillRect(col * RECT_HEIGHT, row * RECT_WIDTH, RECT_WIDTH, RECT_HEIGHT);
                }
                g.setColor(Color.GRAY);
                g.drawRect(col * RECT_HEIGHT, row * RECT_WIDTH, RECT_WIDTH, RECT_HEIGHT);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(gridSize * RECT_WIDTH + 1, gridSize * RECT_HEIGHT + 1);
        //added ones increase grid size so you can see the cell outlines
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
        for (int row = 0; row < game.getSize(); row++) {
            for (int col = 0; col < game.getSize(); col++) {
                if (game.getCell(row, col) == 1) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void setColour(Color c) {
        colour = c;
        repaint();
    }

    public void save(File f) {
        try {
            File lifeFile = f;
            
            /* write objects */
            FileOutputStream out = new FileOutputStream(lifeFile);
            ObjectOutputStream writeLife = new ObjectOutputStream(out);

            writeLife.writeObject(game);

            writeLife.close();
            out.close();

            System.out.println("Data written to file.");

        } catch (FileNotFoundException exception) {
            System.out.println("File could not be found.");
            System.err.println("FileNotFoundException: "
                    + exception.getMessage());
        } catch (IOException exception) {
            System.out.println("Problem with input/output.");
            System.err.println("IOException: " + exception.getMessage());
        }
    }

    public void load(File f) {
        try {
            File lifeFile = f;
            
            /* read objects */
            FileInputStream in = new FileInputStream(lifeFile);
            ObjectInputStream readLife = new ObjectInputStream(in);

            game = (Life)readLife.readObject();

            readLife.close();
            in.close();

            System.out.println("Data read from file.");

        } catch (FileNotFoundException exception) {
            System.out.println("File could not be found.");
            System.err.println("FileNotFoundException: "
                    + exception.getMessage());
        } catch (IOException exception) {
            System.out.println("Problem with input/output.");
            System.err.println("IOException: " + exception.getMessage());
        } catch (ClassNotFoundException exception) {
            System.out.println("Class could not be used to cast object.");
            System.err.println("ClassNotFoundException: "
                    + exception.getMessage());
        }
        repaint();
    }
    
    public static void deleteTemp() {
        File temp = new File("temp.LifeTemp");
        
        if(temp.exists()) {
            temp.delete(); //USE DELETE ON EXIT????????????????????????????????????????????????????????
        }
    }
}
