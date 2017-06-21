package vanham_life;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author Kyle
 */
public class LifeGrid extends JPanel {
    
    private static final int GAME_SIZE = 20;
    private static final int RECT_WIDTH = 20;
    private static final int RECT_HEIGHT = RECT_WIDTH;
    static final Color TEAL = new Color(0, 250, 200);
    private static Color colour = TEAL;
    private Life game;
    
    /**
     * Constructor - creates a LifeGrid object
     * 
     * Pre: none
     * Post: a LifeGrid object has been created
     * 
     */
    public LifeGrid() {
        game = new Life(GAME_SIZE, true);
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
    
    /**
     * Draws the grid and cells
     * 
     * Pre: none
     * Post: lines and cells are drawn
     * 
     * @param g 
     */
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

    /**
     * returns the preferred size of the panel
     * 
     * Pre: none
     * Post: preferred panel size is calculated and returned
     * 
     * @return Dimension = panel dimension
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(GAME_SIZE * RECT_WIDTH, GAME_SIZE * RECT_HEIGHT);
    }
    
    /**
     * Moves the game ahead one step by reading the previous grid, applying the
     * rules, and creating a new grid.
     * 
     * Pre: none
     * Post: game has been advanced
     */
    public void step() {
        game.takeStep();
        repaint();
    }
    
    /**
     * Kills all cells
     * 
     * Pre: none
     * Post: all cells set to blank
     */
    public void clear() {
        game.killAllCells();
        repaint();
    }
    
    /**
     * Determines whether the grid is empty or not
     * 
     * Pre: none
     * Post: a boolean value describing whether or not the grid is empty has
     * been returned
     * 
     * @return boolean = if the grid is empty or not
     */
    public boolean isEmpty() {
        return game.countPopulation() == 0;
    }
    
    /**
     * Sets the draw colour of the cells
     * 
     * Pre: a Colour to set the draw colour to
     * Post: Colour of cells has been changed
     * 
     * @param c Colour object
     */
    public void setColour(Color c) {
        colour = c;
        repaint();
    }
    
    /**
     * Saves the grid to a file of the user's decision
     * 
     * Pre: none
     * Post: grid has been saved to a file
     */
    public void save() {
        game.save();
    }
    
    /**
     * Loads a file from a path of the user's decision
     * 
     * Pre: none
     * Post: grid has been loaded from a file 
     */
    public void load() {
        game.load();
        repaint();
        saveTemp();
    }
    
    /**
     * Saves the temporary file if temporary file usage is in effect
     * 
     * Pre: none
     * Post: grid has been saved to a temporary file
     */
    public void saveTemp() {
        game.saveTemp();
    }
    
    /**
     * Loads the temporary file if temporary file usage is in effect
     * 
     * Pre: none
     * Post: a grid has been loaded from a temporary file
     */
    public void loadTemp() {
        game.loadTemp();
        repaint();
    }
    
    /**
     * Counts and returns the current population of the grid
     * 
     * Pre: none
     * Post: population has been returned
     * 
     * @return = int number of live cells in the grid
     */
    public int countPopulation() {
        return game.countPopulation();
    }
}
