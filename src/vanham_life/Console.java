/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vanham_life;

import java.util.Scanner;

/**
 *
 * @author vanhk5054
 */
public class Console {

    public static void main(String[] args) {
        
        /*
        NOTE: When creating grids, preset or by user, always leave 1 unit
              padding to ensure neighbour counting is correct.
        */
        
        //Could make the grid over-sized and not print the edge.^ (Simple method)
        
        boolean run = true;
        int response;
        int[][]blinker = new int[5][5];
        int[][]glider = new int[10][10];
        Life game = new Life(10, false);
        Scanner in = new Scanner(System.in);
        
        //Set Blinker pattern
        blinker[2][1] = 1;
        blinker[2][2] = 1;
        blinker[2][3] = 1;
        
        //Set Glider pattern
        glider[1][3] = 1;
        glider[2][1] = 1;
        glider[2][3] = 1;
        glider[3][2] = 1;
        glider[3][3] = 1;
        
        //User input and console game
        do {
            System.out.println("Please select an option.");
            System.out.println("1.Take a step");
            System.out.println("2.Take ten steps");
            System.out.println("3.Create a blinker (Clears grid)");
            System.out.println("4.Create a glider (Clears grid)");
            System.out.println("5.QUIT");
            response = in.nextInt();

            switch(response) {
                case 1:
                    game.takeStep();
                    System.out.println(game);
                    break;
                case 2:
                    for (int i = 0; i < 10; i++) {
                        game.takeStep();
                        System.out.println(game);
                    }
                    break;
                case 3:
                    game.setPattern(blinker);
                    System.out.println(game);
                    break;
                case 4:
                    game.setPattern(glider);
                    System.out.println(game);
                    break;
                case 5:
                    run = false;
                    break;
                default:
                    System.out.println("Sorry. That is not an option.");
            }
        } while (run);
    }
}
