package vanham_lifeRating;

import vanham_life.*;

/**
 *
 * @author Kyle
 */
public class LightLifeGrid {
    
    private Life game;

    public void step() {
        game.takeStep();
    }

    public void clear() {
        game.killAllCells();
    }

    public boolean isEmpty() {
        return game.countPopulation() == 0;
    }

    public void load() {
        game = SaveLoad.load();
    }
    
    public int countPopulation() {
        return game.countPopulation();
    }
}
