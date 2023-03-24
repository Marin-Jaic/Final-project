package hr.fer.projektr.game.generators;

import hr.fer.projektr.game.GameState;
import hr.fer.projektr.game.entities.*;

import java.util.Random;

/**
 * Abstract class modeling an object that generates other objects, or in other words, a generator.
 */
public class Generator {

    private final GameState gameState;

    /**
     * Nasumicno generirani broj.
     * Na svaki tick se smanji za jedan.
     * Kad se smanji na 0, resetira se i stvara se novi neprijatelj
     */
    private int ticks;

    private final Random rand;

    /**
     * konstruktor
     * @param gameState ...
     */
    public Generator(GameState gameState, long seed){
        this.gameState = gameState;
        rand = new Random(seed);
        generateTicks();
    }

    /**
     * Mice prepreke koje se vise ne vide<br/>
     * Dodaje nove neprijatelje u listu<br/>
     * Poziva se na svakom stepu
     */
    public void updateList(){

        removeEnemies();

        if ( ticks>0 ) {
            ticks--;
        }
        else{
            int noEnemy=rand.nextInt(GameState.SMALL_CACTUS_IN_N + GameState.STANDARD_CACTUS_IN_N + GameState.LARGE_CACTUS_IN_N + GameState.LONG_CACTUS_IN_N + GameState.BIRD_IN_N + GameState.COIN_IN_N);
            if (noEnemy < GameState.SMALL_CACTUS_IN_N){
                gameState.addEnemy(new Cactus(CactusType.SMALL));
            }
            else if (noEnemy < GameState.SMALL_CACTUS_IN_N + GameState.BIRD_IN_N){
                gameState.addEnemy(new Cactus(CactusType.STANDARD));
            }
            else if (noEnemy < GameState.SMALL_CACTUS_IN_N + GameState.BIRD_IN_N + GameState.LARGE_CACTUS_IN_N){
                gameState.addEnemy(new Cactus(CactusType.LARGE));
            }
            else if (noEnemy< GameState.SMALL_CACTUS_IN_N + GameState.BIRD_IN_N + GameState.LARGE_CACTUS_IN_N + GameState.LONG_CACTUS_IN_N){
                gameState.addEnemy(new Cactus(CactusType.LONG));
            }
            else if (noEnemy < GameState.SMALL_CACTUS_IN_N + GameState.STANDARD_CACTUS_IN_N + GameState.LARGE_CACTUS_IN_N + GameState.LONG_CACTUS_IN_N + GameState.BIRD_IN_N) {
                int birdY=rand.nextInt(4);
                if (birdY==1) gameState.addEnemy(new Bird(GameState.MIN_BIRD_Y));
                else if (birdY==2) {
                    gameState.addEnemy(new Bird(GameState.CENTER_BIRD_Y));
                }
                else if (birdY==3) {
                    gameState.addEnemy(new Bird(0.01, GameState.MAX_BIRD_Y));
                    gameState.addEnemy(new Bird(GameState.CENTER_BIRD_Y));
                }
                else gameState.addEnemy(new Bird(GameState.MAX_BIRD_Y));
            }
            else/* if (noEnemy < GameState.SMALL_CACTUS_IN_N + GameState.STANDARD_CACTUS_IN_N + GameState.LARGE_CACTUS_IN_N + GameState.LONG_CACTUS_IN_N + GameState.BIRD_IN_N + GameState.COIN_IN_N)*/{
                int coinY=rand.nextInt(3);
                if (coinY==1) gameState.addEnemy(new Coin(GameState.MIN_COIN_Y));
                else if (coinY==2) gameState.addEnemy(new Coin(GameState.CENTER_COIN_Y));
                else gameState.addEnemy(new Coin(GameState.MAX_COIN_Y));

            }
            generateTicks();
        }
    }

    /**
     * Ako se prepreka vise ne vidi, mice se iz liste neprijatelja;
     */
    private void removeEnemies(){
        for (Enemy enemy: gameState.getEnemies()){
            if ( enemy.getLeftX() + enemy.getWidth() <= 0 - enemy.getWidth() ) {
                gameState.addToBeRemoved(enemy);
            }
        }
    }

    private void generateTicks(){
        ticks = rand.nextInt((int) (50 / gameState.getSpeed()))+ (int)(3/gameState.STEP_DURATION);
    }
}
