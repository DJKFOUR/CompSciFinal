package vanham_lifeRating;

import vanham_life.*;
import java.io.*;

/**
 *
 * @author Kyle
 */
public class LightLifeGrid {
    
    private static final int GAME_SIZE = 20;
    private Life game;

    public LightLifeGrid() {
        game = new Life(GAME_SIZE);
    }

    public void step() {
        game.takeStep();
    }

    public void clear() {
        game.killAllCells();
    }

    public boolean isEmpty() {
        return game.countPopulation() == 0;
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
    }
    
    public int countPopulation() {
        return game.countPopulation();
    }
}
