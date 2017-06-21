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
    private static int[][] loadedGrid;
    
    private File temp;
    
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
    
    public void saveTemp(int[][] grid) {
        try {

            /* write objects */
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

    public int[][] loadTemp() {
        try {

            /* read objects */
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
