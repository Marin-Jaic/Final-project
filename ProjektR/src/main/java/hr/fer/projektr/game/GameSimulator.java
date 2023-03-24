package hr.fer.projektr.game;

import hr.fer.projektr.ai.NeuralNetwork;
import hr.fer.projektr.game.entities.EntityType;
import hr.fer.projektr.ui.DinosaurGame;
import org.ejml.simple.SimpleMatrix;

import java.util.Random;

public class GameSimulator {

    private static final long DEFAULT_SEED = 3;
    private static final int NUMBER_OF_GAMES = 10;

    public static double[] simulate(NeuralNetwork[] population, long seed) {
        var fitness = new double[population.length];

        for(int i = 0; i < population.length; i++) {
            for(int j = 0; j < NUMBER_OF_GAMES; j++) {
                var game = new GameInterface();
                game.start(seed + j);
                while(!game.isOver()) {
                    switch(population[i].computeForwardProp(getInputMatrix(game))) {
                        case 0 -> game.input(false, false);
                        case 1 -> game.input(false, true);
                        case 2 -> game.input(true, false);
                        default -> throw new UnsupportedOperationException("Output value must be 0, 1 or 2");
                    }
                    game.step();
                }
                fitness[i] += game.getScore();
            }
        }

        for(int i = 0; i < fitness.length; i++) {
            fitness[i] /= NUMBER_OF_GAMES;
        }
        return fitness;
    }

    public static void play(NeuralNetwork unit) {
        play(unit, new Random().nextLong());
    }

    public static void play(NeuralNetwork unit, long seed) {
        var game = new GameInterface();
        var gameRender = new DinosaurGame(game);
        gameRender.setVisible(true);
        game.start(seed);

        while(!game.isOver()) {
            switch(unit.computeForwardProp(getInputMatrix(game))) {
                case 0 -> game.input(false, false);
                case 1 -> game.input(false, true);
                case 2 -> game.input(true, false);
                default -> throw new UnsupportedOperationException("Output value must be 0, 1 or 2");
            }
        }
    }

    private static SimpleMatrix getInputMatrix(GameInterface game) {
        var player = game.getPlayer();
        var enemies = game.getEnemies();

//      sensors[0] = brzina igre iliti igraca
//      sensors[1] = dno igraca
//      sensors[2] = brzina pada
//      sensors[3] = širina igača
//      sensors[4] = udaljenost od prvog enemia
//      sensors[5] = prvi enemi dno
//      sensors[6] = prvi enemi visina
//      sensors[7] = prvi enemi sirina
//      sensors[8] = udaljenost prvog i drugog enemia

//      sensors[9] = drui enemi dno
//      sensors[10] = drugi enemi visina
//      sensors[11] = drugi enemi sirina
        var sensors = new double[9];
        sensors[0] = game.getGameSpeed();
        sensors[1] = player.getBottomY();
        sensors[2] = player.getVerticalSpeed();
        sensors[3] = player.getWidth();

        var enemiesSize = enemies.size();
        if(enemiesSize == 2) {
            //var enemy = enemies.get(1).getRightX() < player.getLeftX() ? enemies.get(1) : enemies.get(0);
            var enemy = enemies.get(0).getRightX() < player.getLeftX() ? enemies.get(1) : enemies.get(0);
            sensors[4] = enemy.getLeftX() - player.getRightX();
            sensors[5] = enemy.getBottomY();
            sensors[6] = enemy.getHeight();
            sensors[7] = enemy.getWidth();
            sensors[8] = enemy.getEntityType() == EntityType.COIN ? 1: 0;

        } else if(enemiesSize == 1) {
            var enemy = enemies.get(0);
            if(!(enemy.getRightX() < player.getLeftX())) {
                sensors[4] = enemy.getLeftX() - player.getRightX();
                sensors[5] = enemy.getBottomY();
                sensors[6] = enemy.getHeight();
                sensors[7] = enemy.getWidth();
                sensors[8] = enemy.getEntityType() == EntityType.COIN ? 1 : 0;
            } else {
                sensors[4] = 1 - player.getRightX();
                sensors[5] = 0;
                sensors[6] = 0;
                sensors[7] = 0;
                sensors[8] = 0;
            }
        } else if(enemiesSize > 2) {
            var enemy = enemies.get(0).getRightX() < player.getLeftX() ? enemies.get(1) : enemies.get(0);
            sensors[4] = enemy.getLeftX() - player.getRightX();
            sensors[5] = enemy.getBottomY();
            sensors[6] = enemy.getHeight();
            sensors[7] = enemy.getWidth();
            sensors[8] = enemy.getEntityType() == EntityType.COIN ? 1 : 0;
        } else {
            sensors[4] = 1 - player.getRightX();
            sensors[5] = 0;
            sensors[6] = 0;
            sensors[7] = 0;
            sensors[8] = 0;
        }

        return new SimpleMatrix(9, 1, false, sensors);
    }
}
