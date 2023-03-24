package hr.fer.projektr.game;

import hr.fer.projektr.game.entities.Enemy;
import hr.fer.projektr.game.entities.Player;
import hr.fer.projektr.game.generators.Generator;
import hr.fer.projektr.game.utility.Physics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class representing a game state.
 * Completely separate from a way of playing the game.
 * Holds information about entities (player and present enemies), default values and other miscellaneous info.
 */
public class GameState {

    //Ovo su konstante jednom kad napravimo da su colideri razliciti od velicine slike

    //Constants relating to the player
    public static final double PLAYER_HEIGHT = 0.1;
    public static final double PLAYER_WIDTH = PLAYER_HEIGHT * 40/86;
    public static final double PLAYER_DUCKING_HEIGHT = PLAYER_HEIGHT * 49/86;
    public static final double PLAYER_DUCKING_WIDTH = PLAYER_WIDTH * 110/40;
    public static final double INITIAL_JUMP_SPEED = -2.;
    public static final double PLAYER_LEFT_OFFSET = 0.02;
    public static final double PLAYER_POSITION_X = 0 + PLAYER_WIDTH * 40/80 + PLAYER_LEFT_OFFSET;
    public static final double INITIAL_PLAYER_POSITION_Y = 1;
    public static final double PLAYER_DUCKING_POSITION_X = PLAYER_POSITION_X - (PLAYER_DUCKING_WIDTH - PLAYER_WIDTH) / 2;



    //Constants relating to the cactus enemies
    public static final double INITIAL_CACTUS_POSITION_X = 1;
    public static final double INITIAL_CACTUS_POSITION_Y = 1;
    public static final double STANDARD_CACTUS_HEIGHT = PLAYER_HEIGHT * 66 / 86;
    public static final double STANDARD_CACTUS_WIDTH = STANDARD_CACTUS_HEIGHT * 10/66;
    public static final double SMALL_CACTUS_HEIGHT = STANDARD_CACTUS_HEIGHT * 56/66;
    public static final double SMALL_CACTUS_WIDTH = SMALL_CACTUS_HEIGHT * 8/56;
    public static final double LARGE_CACTUS_HEIGHT = STANDARD_CACTUS_HEIGHT * 92/66;
    public static final double LARGE_CACTUS_WIDTH = LARGE_CACTUS_HEIGHT * 14/92;
    public static final double LONG_CACTUS_HEIGHT = STANDARD_CACTUS_HEIGHT * 156/66;
    public static final double LONG_CACTUS_WIDTH = LONG_CACTUS_HEIGHT * 10/156;


    //Constants relating to the bird enemies
    public static final double BIRD_HEIGHT = PLAYER_HEIGHT * 52/86;
    public static final double BIRD_WIDTH = BIRD_HEIGHT * 84/52;
    public static final double INITIAL_BIRD_POSITION_X = 1;
    public static final double MIN_BIRD_Y = 1;
    public static final double MAX_BIRD_Y = 1-0.5*GameState.INITIAL_JUMP_SPEED*GameState.INITIAL_JUMP_SPEED/GameState.GRAVITY;
    public static final double CENTER_BIRD_Y = 1 - PLAYER_DUCKING_HEIGHT - 0.02;
    public static final double MAX_CENTER_BIRD_Y = (MAX_BIRD_Y+MIN_BIRD_Y)/2;

    //Constants relating to the bird enemies
    public static final double COIN_HEIGHT = PLAYER_HEIGHT * 52/86;
    public static final double COIN_WIDTH = COIN_HEIGHT;
    public static final double INITIAL_COIN_POSITION_X = 1;
    public static final double MIN_COIN_Y = 1;
    public static final double MAX_COIN_Y = 1-0.5*GameState.INITIAL_JUMP_SPEED*GameState.INITIAL_JUMP_SPEED/GameState.GRAVITY;
    public static final double CENTER_COIN_Y = 1 - PLAYER_DUCKING_HEIGHT - 0.02;
    public static final double COIN_SCORE_VALUE = 100;

    //Constants relating to the game world
    public static final double GRAVITY = 5;
    public static final double INITIAL_GAME_SPEED = 0.4;
    public static final int SPEED_INCREASE_SCORE_THRESHOLD = 50;
    public static final double SPEED_INCREASE_AMOUNT = 0.02;
    public static final double SCORE_TO_DISTANCE_RAN_RATIO = 6.;
    public final double STEP_DURATION;

    //Enemy spawn constants
    public static final int STANDARD_CACTUS_IN_N = 300;
    public static final int SMALL_CACTUS_IN_N = 250;
    public static final int LARGE_CACTUS_IN_N = 200;
    public static final int LONG_CACTUS_IN_N = 50;
    public static final int BIRD_IN_N = 175;
    public static final int COIN_IN_N = 25;


    /*
    //Ovo su konstante gdje su collideri proporcija spritea
    //Constants relating to the player
    public static final double INITIAL_PLAYER_POSITION_X = 0;
    public static final double INITIAL_PLAYER_POSITION_Y = 1;
    public static final double PLAYER_HEIGHT = 0.13;
    public static final double PLAYER_WIDTH = PLAYER_HEIGHT * 80/86;
    public static final double PLAYER_CROUCH_HEIGHT = PLAYER_HEIGHT * 49/86;
    public static final double PLAYER_CROUCH_WIDTH = PLAYER_WIDTH * 110/80;
    public static final double INITIAL_JUMP_SPEED = -1.;


    //Constants relating to the cactus enemies
    public static final double INITIAL_CACTUS_POSITION_X = 1;
    public static final double INITIAL_CACTUS_POSITION_Y = 1;
    public static final double STANDARD_CACTUS_HEIGHT = PLAYER_HEIGHT * 66 / 86;
    public static final double STANDARD_CACTUS_WIDTH = STANDARD_CACTUS_HEIGHT * 30/66;
    public static final double SMALL_CACTUS_HEIGHT = STANDARD_CACTUS_HEIGHT * 56/66;
    public static final double SMALL_CACTUS_WIDTH = SMALL_CACTUS_HEIGHT * 26/56;
    public static final double LARGE_CACTUS_HEIGHT = STANDARD_CACTUS_HEIGHT * 92/66;
    public static final double LARGE_CACTUS_WIDTH = LARGE_CACTUS_HEIGHT * 46/92;
    public static final double LONG_CACTUS_HEIGHT = STANDARD_CACTUS_HEIGHT * 156/66;
    public static final double LONG_CACTUS_WIDTH = LONG_CACTUS_HEIGHT * 30/156;


    //Constants relating to the bird enemies
    public static final double BIRD_HEIGHT = PLAYER_HEIGHT * 52/86;
    public static final double BIRD_WIDTH = BIRD_HEIGHT * 84/52;
    public static final double INITIAL_BIRD_POSITION_X = 1;
    public static final double DEFAULT_INITIAL_BIRD_POSITION_Y = 0;
    public static final double MIN_BIRD_Y = 1;
    public static final double MAX_BIRD_Y = 1-0.5*GameState.INITIAL_JUMP_SPEED*GameState.INITIAL_JUMP_SPEED/GameState.GRAVITY;
    public static final double CENTER_BIRD_Y = 1 - PLAYER_CROUCH_HEIGHT - 0.02;


    //Constants relating to the game world
    public static final double GRAVITY = 1.3;
    public static final double INITIAL_GAME_SPEED = 0.2;
    public static final int SPEED_INCREASE_SCORE_THRESHOLD = 100;
    public static final double SPEED_INCREASE_AMOUNT = 0.01;
    public static final double SCORE_TO_DISTANCE_RAN_RATIO = 6.;
    public final double STEP_DURATION;

    */

    /**
     * The player in this game state
     */
    private Player player;

    /**
     * Present enemies in this game state
     */
    private List<Enemy> enemies;

    /**
     * The current game speed (how fast is everything moving)
     */
    private double gameSpeed;

    /**
     * The current score
     */
    private double distanceRan;

    private int coinsCollected;
    private boolean isRunning;
    private Set<Enemy> toBeRemoved;
    private Generator generator;

    /**
     * Constructor for the game state, initializes everything by itself.
     */

    public GameState(double stepDuration) {
        this.STEP_DURATION = stepDuration;
    }
    public Player getPlayer() {
    	return player;
    }
    
    public List<Enemy> getEnemies(){
        return enemies;
    }

    public void removeEnemy(int k){
        enemies.remove(k);
    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    /**
     * za svrhe testiranja, ne znam koliko ce biti potrebno kasnije
     * @return brzinu igre
     */
    public double getSpeed(){
        return gameSpeed;
    }


    /**
     * Runs a single step of the game state simulation.
     * For example moves the enemies, deals with the player movement and checks for collisions with the player.
     */
    public void step(){

        if (!isRunning){
            return;
        }

        Physics.playerUpdate(this);
        Physics.moveEnemies(this);

        if (Physics.checkCollisions(this)){
            isRunning = false;
        }

        generator.updateList();
        distanceRan += gameSpeed * STEP_DURATION;
        if (this.getScore() > 100 + SPEED_INCREASE_SCORE_THRESHOLD / SPEED_INCREASE_AMOUNT * (gameSpeed - INITIAL_GAME_SPEED)){
            gameSpeed += SPEED_INCREASE_AMOUNT;
        }

        for (Enemy enemy: this.toBeRemoved){
            this.enemies.remove(enemy);
        }
        //System.out.println(gameSpeed);
    }

    public void start(long seed){
        this.player = new Player();
        this.enemies = new ArrayList<>();
        this.toBeRemoved = new HashSet<>();
        this.gameSpeed = INITIAL_GAME_SPEED;
        this.generator = new Generator(this, seed);
        this.distanceRan = 0.;
        this.isRunning = true;
        this.coinsCollected = 0;
    }
    public boolean isOver(){
        return !isRunning;
    }

    public int getScore() {
        return (int) (distanceRan * SCORE_TO_DISTANCE_RAN_RATIO + coinsCollected * COIN_SCORE_VALUE);
    }

    public void coinCollected(){
        this.coinsCollected++;
    }

    public void addToBeRemoved(Enemy enemy){
        this.toBeRemoved.add(enemy);
    }
}
