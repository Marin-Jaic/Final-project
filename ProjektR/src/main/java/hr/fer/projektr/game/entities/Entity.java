package hr.fer.projektr.game.entities;

/**
 * Base class for all entities.
 * Contains both position and dimensions of an entity.
 */
public abstract class Entity {
    /**
     * Position in the x-axis.
     * A double between 1 and 0.
     */
    private volatile double positionX;

    /**
     * Position in the y-axis.
     * A double between 1 and 0.
     */
    private volatile double positionY;

    /**
     * Size in the x-axis.
     * A double between 1 and 0.
     */
    private double width;

    /**
     * Size in the y-axis.
     * A double between 1 and 0.
     */
    private double height;

    /**
     * The default constructor for entities.
     * @param positionX . A double between 1 and 0
     * @param positionY position in the y-axis. A double between 1 and 0
     * @param width size in the x-axis. A double between 1 and 0
     * @param height size in the y-axis. A double between 1 and 0
     */
    
    private final EntityType type;
    
    public Entity(double positionX, double positionY, double width, double height, EntityType type) {
        //TODO Add an if checking that all arguments are between 0 and 1 (if deemed necessary)
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
        this.type = type;
    }

    public double getLeftX() {
        return positionX;
    }
    public double getRightX() {
        return this.getLeftX() + this.getWidth();
    }

    public double getTopY() {
        return positionY - this.getHeight();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public void setBottomPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getBottomY(){
        return positionY;
    }
    
    public void setHeight(double height) {
    	this.height = height;
    }
    
    public EntityType getEntityType() {
    	return type;
    }

    /**
     * samo za testiranje
     * @return formatiranu duljinu i visinu entitya
     */
    @Override
    public String toString(){
        return String.format("(width:%s, height:%s, x:%s, y:%s)", getWidth(), getHeight(), getLeftX(), getTopY());
    }
}
