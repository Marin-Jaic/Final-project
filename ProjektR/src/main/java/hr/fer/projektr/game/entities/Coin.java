package hr.fer.projektr.game.entities;

import hr.fer.projektr.game.GameState;

/**
 * Class representing the bird enemy.
 * The bird spawns on the right side of the screen, at (open to change) three varying heights.
 */
public final class Coin extends Enemy{
    /**
     * This constructor takes one argument, the initial height of the bird.
     * @param positionY a double number between 0 and 1, representing the height at which the bird is to be spawned.
     */

    private boolean isCollected = false;
    public Coin(double positionY){
        super(GameState.INITIAL_COIN_POSITION_X, positionY, GameState.COIN_WIDTH, GameState.COIN_HEIGHT, EntityType.COIN);
    }

}
