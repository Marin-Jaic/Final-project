package hr.fer.projektr.game.entities;

import hr.fer.projektr.game.GameState;

/**
 * Class representing the cactus enemy.
 * The cactus spawns at the bottom of the right side of the screen.
 * The cactus can have varying sizes, described by the Enum CactusType.
 */
public class Cactus extends Enemy{

    /**
     * The argumentless constructor.<br/>
     * For testing only.<br/>
     * Creates a standard cactus.
     */
    public Cactus() {
        this(CactusType.STANDARD);
    }


    /**
     * This constructor takes one argument, the CactusType that is to be created.
     * @param cactusType enum constant of CactusType, which represents the type (height and width) of the cactus.
     */
    public Cactus(CactusType cactusType){
    	// TODO
    	// EntityType
        super(GameState.INITIAL_CACTUS_POSITION_X, GameState.INITIAL_CACTUS_POSITION_Y, cactusType.width, cactusType.height, cactusType.entityType);
    }


}
