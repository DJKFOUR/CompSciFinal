/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private static Life loadedGame;

    public static void save(Life game) {
        saveFile(getSaveFile(), game);
    }

    public static Life load() {
        return loadFile(getLoadFile());
    }

    private static void saveFile(File f, Life game) {
        try {
            File lifeFile = f;

            /* write objects */
            FileOutputStream out = new FileOutputStream(lifeFile);
            ObjectOutputStream writeLife = new ObjectOutputStream(out);

            writeLife.writeObject(game);

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

    private static Life loadFile(File f) {
        try {
            File lifeFile = f;

            /* read objects */
            FileInputStream in = new FileInputStream(lifeFile);
            ObjectInputStream readLife = new ObjectInputStream(in);

            loadedGame = (Life) readLife.readObject();

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

        return loadedGame;
    }

    private static File getSaveFile() {
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

    private static File getLoadFile() {
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
}
