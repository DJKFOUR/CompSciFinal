/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vanham_lifeRating;

/**
 *
 * @author vanhk5054
 */
public class Generation implements Comparable{
    private int generationNum;
    private int population;
    
    /**
     * Constructor - Creates and initializes a Generation object with the given
     * generation number and population
     * 
     * Pre: a generation number and population of the generation
     * Post: a Generation object has been created
     * 
     * @param gen int = generation number
     * @param popn int = population of the generation
     */
    public Generation(int gen, int popn) {
        generationNum = gen;
        population = popn;
    }
    
    /**
     * Returns the population of the generation
     * 
     * Pre: none
     * Post: population has been returned
     * 
     * @return int = population
     */
    public int getPopulation() {
        return population;
    }
    
    /**
     * Compares two Generation objects based on their population size
     * 
     * pre: a Generation object
     * Post: an int value describing the relationship between this object and
     * the passed object has been returned
     * 
     * @param o Generation = the object to compare with
     * @return int = an int value describing the relationship between this
     * object and the passed object
     */
    @Override
    public int compareTo(Object o) {
        Generation testObj = (Generation)o;
        if (population > testObj.getPopulation()) {
            return 1;
        } else if (population < testObj.getPopulation()) {
            return -1;
        } else {
            return 0;
        }
    }
    
    /**
     * Returns a String representing the object
     * 
     * Pre: none
     * Post: a String that represents the object has been returned
     * 
     * @return String = Generation string
     */
    @Override
    public String toString() {
        return "Generation: " + generationNum + "   Population: " + population;
    }
}
