package hr.fer.projektr.game;

import hr.fer.projektr.game.entities.Enemy;
import hr.fer.projektr.game.entities.Player;

import java.util.List;

public class GameInterface {
    private final GameState gameState;

    public GameInterface() {
        this(60);
    }

    public GameInterface(int fps) {
        this.gameState = new GameState(1./fps);
    }
    public void step(){
        gameState.step();
    }

    public void input(boolean duck, boolean jump){
        gameState.getPlayer().influencePlayer(duck, jump);
    }

    public Player getPlayer(){
        return gameState.getPlayer();
    }

    public List<Enemy> getEnemies(){
        return gameState.getEnemies();
    }

    public void start(long seed){
        gameState.start(seed);
    }
    public boolean isOver(){
        return gameState.isOver();
    }

    public int getScore(){
        return gameState.getScore();
    }

    public double getGameSpeed() {
        return gameState.getSpeed();
    }

    public GameState getGameState() {
        return gameState;
    }
}
