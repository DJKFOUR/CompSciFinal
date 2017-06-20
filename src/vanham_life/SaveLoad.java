package vanham_life;

import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author vanhk5054
 */
public class SaveLoad {

    private static JFileChooser fileChooser;
    private static final FileNameExtensionFilter filter = new FileNameExtensionFilter("Life file", new String[]{"life"});
    private static int[][] loadedGrid;
    private File temp;
    
    public SaveLoad() {
        try {
            temp = File.createTempFile("life", ".tmp");
            System.out.println("Temp file : " + temp.getAbsolutePath());
        } catch (IOException exception) {
            System.out.println("Problem with input/output.");
            System.err.println("IOException: " + exception.getMessage());
        }
        temp.deleteOnExit();
    }

    public void save(int[][] grid) {
        saveFile(getSaveFile(), grid);
    }

    public int[][] load() {
        return loadFile(getLoadFile());
    }

    private void saveFile(File f, int[][] grid) {
        try {
            File lifeFile = f;

            /* write objects */
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
        }
    }

    private int[][] loadFile(File f) {
        try {
            File lifeFile = f;

            /* read objects */
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
        }

        return loadedGrid;
    }

    private File getSaveFile() {
        int returnValue;
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
            return saveFile;
        } else {
            return null;
        }
    }

    private File getLoadFile() {
        int returnValue;
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile;
        } else {
            return null;
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

            System.out.println("Data written to file.");

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

        return loadedGrid;
    }
}
