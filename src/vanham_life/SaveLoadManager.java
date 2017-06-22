package vanham_life;

import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author vanhk5054
 */
public class SaveLoadManager {

    private static JFileChooser fileChooser;
    private static final FileNameExtensionFilter FILTER = new FileNameExtensionFilter("Life file", new String[]{"life"});
    private static int[][] loadedGrid;
    
    /**
     * Saves the grid to a file of the user's decision
     * 
     * Pre: none
     * Post: grid has been saved to a file
     * 
     * @param grid = grid to save to a file
     */
    public static void save(int[][] grid) {
        saveFile(getSaveFile(), grid); //save to the file returned from the
                                       //getSaveFile method
    }
    
    /**
     * Loads a file from a path of the user's decision
     * 
     * Pre: none
     * Post: grid has been loaded from a file
     * 
     * @return int[][] = grid loaded with 1's and 0's gotten from the file
     */
    public static int[][] load() {
        return loadFile(getLoadFile()); //load to the file returned from the
                                        //getLoadFile method
    }
    
    /**
     * Saves the data to the file
     * 
     * Pre: a file to use and int[][] data to write
     * Post: int[][] data has been written to the file
     * 
     * @param f = file to use
     * @param grid = data to use (grid loaded with 1's and 0's)
     */
    private static void saveFile(File f, int[][] grid) {
        try {
            File lifeFile = f;
            FileOutputStream out = new FileOutputStream(lifeFile);
            ObjectOutputStream writeLife = new ObjectOutputStream(out);

            writeLife.writeObject(grid);

            writeLife.close();
            out.close();

            System.out.println("Data written to file.");

        } catch (FileNotFoundException e) {
            System.out.println("File could not be found.");
            System.err.println("FileNotFoundException: "
                    + e.getMessage());
        } catch (IOException e) {
            System.out.println("Problem with input/output.");
            System.err.println("IOException: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception.");
            System.err.println("IOException: " + e.getMessage());
        }
    }
    
    /**
     * Loads the data from a file
     * 
     * Pre: a file to use
     * Post: Data has been read from the file and returned
     * 
     * @param f = file to use
     * @return int[][] grid loaded with 1's and 0's
     */
    private static int[][] loadFile(File f) {
        try {
            File lifeFile = f;
            FileInputStream in = new FileInputStream(lifeFile);
            ObjectInputStream readLife = new ObjectInputStream(in);

            loadedGrid = (int[][])readLife.readObject();

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
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception.");
            System.err.println("IOException: " + e.getMessage());
        }
        return loadedGrid;
    }
    
    /**
     * Prompts the user to select a file path to save to with a JFileChooser
     * 
     * Pre: none
     * Post: a file path has been selected
     * 
     * @return File = File object with selected path
     */
    private static File getSaveFile() {
        int returnValue;
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(FILTER);
        fileChooser.addChoosableFileFilter(FILTER);
        fileChooser.setAcceptAllFileFilterUsed(false);
        returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getAbsolutePath();
            if (!fileName.endsWith(".Life")) { //Add *.Life extension if it
                                               //doesn't exist
                fileName = selectedFile.getAbsolutePath() + ".Life";
            }
            File saveFile = new File(fileName);
            return saveFile;
        } else {
            return null;
        }
    }
    
    /**
     * Prompts the user to select a file path to load from with a JFileChooser
     * 
     * Pre: none
     * Post: a file path has been selected
     * 
     * @return File = File object with selected path
     */
    private static File getLoadFile() {
        int returnValue;
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(FILTER);
        fileChooser.addChoosableFileFilter(FILTER);
        fileChooser.setAcceptAllFileFilterUsed(false);
        returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile;
        } else {
            return null;
        }
    }
}
