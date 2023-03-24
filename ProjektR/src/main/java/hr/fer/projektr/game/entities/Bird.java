package hr.fer.projektr.game.entities;

import hr.fer.projektr.game.GameState;

/**
 * Class representing the bird enemy.
 * The bird spawns on the right side of the screen, at (open to change) three varying heights.
 */
public final class Bird extends Enemy{
    /**
     * This constructor takes one argument, the initial height of the bird.
     * @param positionY a double number between 0 and 1, representing the height at which the bird is to be spawned.
     */
    public Bird(double positionY){
        super(GameState.INITIAL_BIRD_POSITION_X, positionY, GameState.BIRD_WIDTH, GameState.BIRD_HEIGHT, EntityType.BIRD);
    }
    
    public Bird(double offsetX, double positionY) {
    	super(GameState.INITIAL_BIRD_POSITION_X + offsetX, positionY, GameState.BIRD_WIDTH, GameState.BIRD_HEIGHT, EntityType.BIRD);
    }
}
