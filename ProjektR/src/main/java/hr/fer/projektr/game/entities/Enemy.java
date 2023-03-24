package hr.fer.projektr.game.entities;

/**
 * Parent class for all enemies.
 */
/*
Here mostly so (at least at the time of writing) we can create a collection of enemies.
If confused, see the Entity class.
 */
public abstract class Enemy extends Entity{
    /**
     * Constructor
     * @param positionX the initial position on the x-axis
     * @param positionY the initial position on the y-axis
     * @param width width of the Enemy
     * @param height width of the Enemy
     */
    public Enemy(double positionX, double positionY, double width, double height, EntityType type) {
        super(positionX, positionY, width, height, type);
    }


}
