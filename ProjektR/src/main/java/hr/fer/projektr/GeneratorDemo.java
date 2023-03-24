package hr.fer.projektr;

import hr.fer.projektr.game.GameState;
import hr.fer.projektr.game.entities.Enemy;
import hr.fer.projektr.game.generators.Generator;
import hr.fer.projektr.game.utility.Physics;

/**
 * Za testiranje generatora.
 * Mozda sadrzi koristan kod za tick, kad se uvede sat.
 */
public class GeneratorDemo {
    public static void main(String[] args){

        GameState state=new GameState(1./60);
        Generator generator=new Generator(state, 1);

        for (int k=0; k<500; k++){

            Physics.moveEnemies(state);
            generator.updateList();

            System.out.println(String.valueOf(k) + " ".repeat(5-noOfDigits(k)) + state.getEnemies());

        }
    }

    private static int noOfDigits(int k){
        if (k<10) return 1;
        return 1+noOfDigits(k%10);
    }
}
