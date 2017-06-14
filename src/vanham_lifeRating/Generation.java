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
    int generationNum;
    int population;
    
    public Generation(int gen, int popn) {
        generationNum = gen;
        population = popn;
    }
    
    public int getPopulation() {
        return population;
    }

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
    
    @Override
    public String toString() {
        return "G=" + generationNum + "P=" + population;
    }
}
