/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vanham_life;

import java.io.*;

/**
 *
 * @author Kyle
 */
public class TempFileManager {
    private int[][] loadedGrid;
    
    private File temp;
    
    /**
     * Constructor - Creates and initializes a TempFileManager object with the
     * given grid size and a blank grid
     * 
     * Pre: a grid size(one dimension of a square)
     * Post: A TempFileManager object has been created
     * 
     * @param gridSize = the size of grid which will be written to the temp file
     */
    public TempFileManager(int gridSize) {
        try {
            temp = File.createTempFile("life", ".tmp");
            System.out.println("Temp file : " + temp.getAbsolutePath());
        } catch (IOException exception) {
            System.out.println("Problem with input/output.");
            System.err.println("IOException: " + exception.getMessage());
        }
        
        temp.deleteOnExit();
        
        try {

            /* write objects */
            FileOutputStream out = new FileOutputStream(temp);
            ObjectOutputStream writeLife = new ObjectOutputStream(out);

            writeLife.writeObject(new int[gridSize][gridSize]);

            writeLife.close();
            out.close();

            System.out.println("Default data written to temp file.");

        } catch (FileNotFoundException e) {
            System.out.println("File could not be found.");
            System.err.println("FileNotFoundException: "
                    + e.getMessage());
        } catch (IOException e) {
            System.out.println("Problem with input/output.");
            System.err.println("IOException: " + e.getMessage());
        }
    }
    
    /**
     * Saves the given data to the temp file
     * 
     * Pre: int[][] data loaded with 1's and 0's
     * Post: int[] data has been saved to the temp file
     * 
     * @param grid = int[][] grid loaded with 1's and 0's
     */
    public void saveTemp(int[][] grid) {
        try {
            FileOutputStream out = new FileOutputStream(temp);
            ObjectOutputStream writeLife = new ObjectOutputStream(out);

            writeLife.writeObject(grid);

            writeLife.close();
            out.close();

            System.out.println("Data written to temp file.");

        } catch (FileNotFoundException e) {
            System.out.println("File could not be found.");
            System.err.println("FileNotFoundException: "
                    + e.getMessage());
        } catch (IOException e) {
            System.out.println("Problem with input/output.");
            System.err.println("IOException: " + e.getMessage());
        }
    }
    
    /**
     * Loads the data from the temp file
     * 
     * Pre: none
     * Post: int[][] data has been loaded from the temp file and returned
     * 
     * @return int[][] grid loaded with 1's and 0's
     */
    public int[][] loadTemp() {
        try {
            FileInputStream in = new FileInputStream(temp);
            ObjectInputStream readLife = new ObjectInputStream(in);

            loadedGrid = (int[][])readLife.readObject();

            readLife.close();
            in.close();

            System.out.println("Data read from temp file.");

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
        return loadedGrid;
    }
}
