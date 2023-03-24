package hr.fer.projektr.game.entities;

import hr.fer.projektr.game.GameState;

/**
 * Class representing the player.
 */
public class Player extends Entity {
    /**
     * A boolean representing if the player is currently wanting to jump (for example holding the space bar).
     */
    private boolean isJumping;

    /**
     * A boolean representing if the player is currently wanting to duck (for example holding the down arrow).
     */
    private boolean isDucking;

    /**
     * The vertical speed of the player.
     */
    private double verticalSpeed;

    private boolean hasLanded;
    /**
     * Constructor for the player. Takes no arguments and sets the vertical speed to 0.
     */
    public Player() {
        super(GameState.PLAYER_POSITION_X, GameState.INITIAL_PLAYER_POSITION_Y, GameState.PLAYER_WIDTH, GameState.PLAYER_HEIGHT, EntityType.PLAYER);
        this.hasLanded = true;
        this.verticalSpeed = 0;
        this.isDucking = false;
        this.isJumping = false;
    }

    @Override
    public double getHeight() {
        if (isDucking){
            return GameState.PLAYER_DUCKING_HEIGHT;
        }
        else {
            return GameState.PLAYER_HEIGHT;
        }
    }

    @Override
    public double getWidth() {
        if (isDucking){
            return GameState.PLAYER_DUCKING_WIDTH;
        }
        else {
            return GameState.PLAYER_WIDTH;
        }
    }

    @Override
    public double getLeftX() {
        if (isDucking){
            return GameState.PLAYER_DUCKING_POSITION_X;
        }
        else {
            return GameState.PLAYER_POSITION_X;
        }
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public boolean isDucking() {
        return isDucking;
    }

    public void setDucking(boolean ducking) {
        isDucking = ducking;
    }

    public double getVerticalSpeed() {
        return verticalSpeed;
    }

    public void setVerticalSpeed(double verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
    }

    public void influencePlayer(boolean duck, boolean jump){
        this.isDucking = duck;
        this.isJumping = jump;

        if (getVerticalSpeed() == 0. && getBottomY() == GameState.INITIAL_PLAYER_POSITION_Y){
            hasLanded = true;
        }


        if (getVerticalSpeed() != 0. && getBottomY() != GameState.INITIAL_PLAYER_POSITION_Y && this.isDucking && hasLanded){
            this.verticalSpeed -= GameState.INITIAL_JUMP_SPEED/4;
            hasLanded = false;
        }

        //check if allowed to jump
        if (getBottomY() == GameState.INITIAL_PLAYER_POSITION_Y && !this.isDucking && this.isJumping){
            this.verticalSpeed = GameState.INITIAL_JUMP_SPEED;
        }
    }
}
