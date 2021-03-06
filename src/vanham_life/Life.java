package vanham_life;

import java.io.Serializable;

/**
 *
 * @author vanhk5054
 */
public class Life implements LifeInterface, Serializable {

    private int[][] grid;
    private TempFileManager tempMGMT;
    private boolean includeTemp;

    /**
     * Constructor - initializes an empty blank grid
     * 
     * Pre: int size, boolean includeTemp
     * Post: a Life object is created with the given size
     * 
     * @param size height/length of square grid
     * @param includeTemp use temporary file system or not
     */
    public Life(int size, boolean includeTemp) {
        this.includeTemp = includeTemp;
        grid = new int[size][size];
        if (includeTemp) {
            tempMGMT = new TempFileManager(size);
        }
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                grid[row][col] = 0;
            }
        }
    }

    /**
     * Constructor - initializes an empty blank grid
     * 
     * Pre: int[][] grid, boolean includeTemp
     * Post: a Life object is created with the given size
     * 
     * @param grid a int[][] loaded with 1's and 0's to create a Life object
     * with
     * @param includeTemp use temporary file system or not
     */
    public Life(int[][] grid, boolean includeTemp) {
        this.includeTemp = includeTemp;
        if (includeTemp) {
            tempMGMT = new TempFileManager(grid.length);
        }
        this.grid = grid;
    }

    /**
     * Return cell state at specified Row and Column
     * 
     * Pre: a row and column to get the cell state of
     * Post: cell state is returned in integer form(1=alive, 0=dead)
     * 
     * @param row = row to use
     * @param col = column to use
     * @return cell state in integer form
     */
    public int getCell(int row, int col) {
        return grid[row][col];
    }

    /**
     * Set cell at specified Row and Column to specified Value
     * 
     * Pre: a row and column to set the cell state of, a value to set the cell
     * to
     * Post: cell at position (row,col) is set to the specified value
     *
     * @param row = row to use
     * @param col = column to use
     * @param value = value to set the cell to
     */
    public void setCell(int row, int col, int value) {
        grid[row][col] = value;
    }
    
    
    /**
     * Returns the size of the grid (one dimension of the square)
     * 
     * Pre: none
     * Post: grid height/length is returned
     * 
     * @return grid size
     */
    public int getSize() {
        return grid.length;
    }

    /**
     * Kills all cells
     * 
     * Pre: none
     * Post: all cells set to blank
     */
    @Override
    public void killAllCells() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                grid[row][col] = 0;
            }
        }
    }

    /**
     * Loads a pattern to the grid
     *
     * @param newGrid a int[][] loaded with 1's and 0's too set the grid to
     */
    @Override
    public void setPattern(int[][] newGrid) {
        grid = new int[newGrid.length][newGrid[0].length];
        for (int row = 0; row < newGrid.length; row++) {
            for (int col = 0; col < newGrid[0].length; col++) {
                grid[row][col] = newGrid[row][col];
            }
        }
    }

    /**
     * Counts how many adjacent cells are alive
     * 
     * Pre: a row and column to count the neighbours of
     * Post: tally of neighbours has been returned
     *
     * @param row = row address of test cell 0 < cellRow < gridSize - 1
     * @param col = column address of test cell 0 < cellCol < gridSize - 1
     * @retur n int count of adjacent live cells
     */
    @Override
    public int countNeighbours(int row, int col) {
        int numNeighbours = 0;
        try {
            if (getCell(row - 1, col) == 1) { //Above
                numNeighbours++;
            }
            if (getCell(row + 1, col) == 1) { //Below
                numNeighbours++;
            }
            if (getCell(row, col + 1) == 1) { //Right
                numNeighbours++;
            }
            if (getCell(row, col - 1) == 1) { //Left
                numNeighbours++;
            }
            if (getCell(row - 1, col + 1) == 1) { //Diag up and right
                numNeighbours++;
            }
            if (getCell(row + 1, col + 1) == 1) { //Diag down and right
                numNeighbours++;
            }
            if (getCell(row - 1, col - 1) == 1) { //Diag up and left
                numNeighbours++;
            }
            if (getCell(row + 1, col - 1) == 1) { //Diag down and left
                numNeighbours++;
            }
        } catch (ArrayIndexOutOfBoundsException e) { //Catch errors generated 
                                                     //by edges searching for
                                                     //neighbours out of bounds
        }
        return numNeighbours;
    }

    /**
     * Applies the rules of the game to the cell
     * 
     * Pre: a row and column of the cell to use
     * Post: new cell state has been returned
     * 
     * @param row = row address of test cell * 0 < cellRow < gridSize - 1
     * @param col = column address of test cell 0 < cellCol < gridSize - 1
     * @return int = state of cell, 1 for live, 0 for dead
     */
    @Override
    public int applyRules(int row, int col) {
        if (countNeighbours(row, col) < 2) { //Underpopulation, alive cells die,
                                             //dead cells stay dead
            return 0;
        } else if (countNeighbours(row, col) == 2) { //Sustained, alive cells 
                                                     //stay alive, dead cells
                                                     //stay dead
            if (getCell(row, col) == 1) { //Alive
                return 1;
            } else { //Dead
                return 0;
            }
        } else if (countNeighbours(row, col) > 3) { //Overpopulation, alive
                                                    //cells die, dead cells stay
                                                    //dead
            return 0;
        } else { //Equal to 3. Birth, alive cells stay alive, dead cells become
                 //alive
            return 1;
        }
    }

    /**
     * Moves the game ahead one step by reading the previous grid, applying the
     * rules, and creating a new grid.
     * 
     * Pre: none
     * Post: game has been advanced
     */
    @Override
    public void takeStep() {
        int[][] nextGen = new int[grid.length][grid[0].length];
        for (int row = 0; row < nextGen.length; row++) {
            for (int col = 0; col < nextGen[0].length; col++) {
                nextGen[row][col] = applyRules(row, col);
            }
        }
        setPattern(nextGen);
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
        int population = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (getCell(row, col) == 1) {
                    population++;
                }
            }
        }
        return population;
    }
    
    /**
     * Saves the grid to a file of the user's decision
     * 
     * Pre: none
     * Post: grid has been saved to a file
     */
    public void save() {
        SaveLoadManager.save(grid);
    }
    
    /**
     * Loads a file from a path of the user's decision
     * 
     * Pre: none
     * Post: grid has been loaded from a file 
     */
    public void load() {
        int[][] newGrid = SaveLoadManager.load();
        if (newGrid != null) {
            setPattern(newGrid);
        } else {
            System.out.println("No grid retrieved"); //Couldn't get exception
                                                     //handler to work
        }
    }
    
    /**
     * Saves the temporary file if temporary file usage is in effect
     * 
     * Pre: none
     * Post: grid has been saved to a temporary file
     */
    public void saveTemp() {
        if (includeTemp) {
            tempMGMT.saveTemp(grid);
        } else {
            System.out.println("Temp file not in use");
        }
    }
    
    /**
     * Loads the temporary file if temporary file usage is in effect
     * 
     * Pre: none
     * Post: a grid has been loaded from a temporary file
     */
    public void loadTemp() {
        if (includeTemp) {
            setPattern(tempMGMT.loadTemp());
            saveTemp();
        } else {
            System.out.println("Temp file not in use");
        }
    }

    /**
     * Creates a string representation of the grid
     * 
     * Pre: none
     * Post: a string representing the object has been returned
     *
     * @return String = Life String 
     */
    @Override
    public String toString() {
        String out = "";
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                out += grid[row][col] + " ";
            }
            out += "\n";
        }

        return out;
    }
}
