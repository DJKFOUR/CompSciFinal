/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vanham_life;

import java.io.Serializable;

/**
 *
 * @author vanhk5054
 */
public class Life implements LifeInterface, Serializable{
    private int[][] grid;
    
    /**
     * Constructor - initializes an empty blank grid
     * @param size sets grid dimensions
     */
    public Life(int s) {
        grid = new int[s][s];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                grid[row][col] = 0;
            }
        }
    }
    
    /**
     * Constructor - initializes a grid
     * @param size sets grid dimensions
     */
    public Life(int[][] g) {
        grid = g;
    }
    
    /**
     * Return cell state at specified Row and Column
     * @param row
     * @param col
     * @return 
     */
    public int getCell(int row, int col) {
        return grid[row][col];
    }
    
    /**
     * Set cell at specified Row and Column to specified Value
     * @param row
     * @param col
     * @param value 
     */
    public void setCell(int row, int col, int value) {
        grid[row][col] = value;
    }
    
    public int getSize() {
        return grid.length;
    }
    
    /**
     * Set all grid cells to blank
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
     * Loads a start pattern to the grid
     * @param startGrid a int [][] loaded with 1's and 0's
     */
    @Override
    public void setPattern(int[][] startGrid) {
        grid = new int[startGrid.length][startGrid[0].length];
        for (int row = 0; row < startGrid.length; row++) {
            for (int col = 0; col < startGrid[0].length; col++) {
                grid[row][col] = startGrid[row][col];
            }
        }
    }
    
    /**
     * Counts how many adjacent cells are alive
     * @param cellRow = row address of test cell
     *  0 < cellRow < gridSize - 1
     * @param cellCol = column address of test cell
     * 0 < cellCol < gridSize - 1
     * @return int count of adjacent live cells
     */
    @Override
    public int countNeighbours(int cellRow, int cellCol) {
        int numNeighbours = 0;
        if (0 < cellRow && cellRow < grid.length - 1 && //DON'T COUNT FOR BORDER NEIGHBOURS
                0 < cellCol && cellCol < grid[0].length -1) {
            if (grid[cellRow - 1][cellCol] == 1) { //Above
                numNeighbours++;
            }
            if (grid[cellRow + 1][cellCol] == 1) { //Below
                numNeighbours++;
            }
            if (grid[cellRow][cellCol + 1] == 1) { //Right
                numNeighbours++;
            }
            if (grid[cellRow][cellCol - 1] == 1) { //Left
                numNeighbours++;
            }
            if (grid[cellRow - 1][cellCol + 1] == 1) { //Diag up and right
                numNeighbours++;
            }
            if (grid[cellRow + 1][cellCol + 1] == 1) { //Diag down and right
                numNeighbours++;
            }
            if (grid[cellRow - 1][cellCol - 1] == 1) { //Diag up and left
                numNeighbours++;
            }
            if (grid[cellRow + 1][cellCol - 1] == 1) { //Diag down and left
                numNeighbours++;
            }
        }
        return numNeighbours;
    }
    
    /**
     * @param cellRow = row address of test cell     * 
     *  0 < cellRow < gridSize - 1
     * @param cellCol = column address of test cell
     * 0 < cellCol < gridSize - 1
     * @return int = state of cell,  1 for live, 0 for dead
     */
    @Override
    public int applyRules(int cellRow, int cellCol) {
        if (countNeighbours(cellRow, cellCol) < 2) { //Underpopulation, alive cells die, dead cells stay dead
            return 0;
        } else if (countNeighbours(cellRow, cellCol) == 2) { //Sustained, alive cells atay alive, dead cells stay dead
            if (grid[cellRow][cellCol] == 1) { //Alive
                return 1;
            } else { //Dead
                return 0;
            }
        } else if (countNeighbours(cellRow, cellCol) > 3) { //Overpopulation, alive cells die, dead cells stay dead
            return 0;
        } else { //Equal to 3. Birth, alive cells stay alive, dead cells become alive
            return 1;
        }
    }
    
    /**
     * Moves the game ahead one step by reading the
     * previous grid, applying the rules, and creating
     * a new grid.
     */
    @Override
    public void takeStep() {
        int[][] nextGen = new int[grid.length][grid[0].length];
        for (int row = 0; row < nextGen.length; row++) {
            for (int col = 0; col < nextGen[0].length; col++) {
                nextGen[row][col] = applyRules(row,col);
            }
        }
        setPattern(nextGen);
    }
    
    public int countPopulation() {
        int population = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    population++;
                }
            }
        }
        return population;
    }
    
    /**
     * Creates a string representation of the grid
     * @return String
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
