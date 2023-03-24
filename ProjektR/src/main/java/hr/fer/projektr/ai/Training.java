package hr.fer.projektr.ai;


import hr.fer.projektr.Main;
import hr.fer.projektr.game.GameSimulator;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.Scanner;

public class Training {

    private int populationSize;
    private NeuralNetwork[] population;

    public Training(int populationSize) {
        this.populationSize = populationSize;
        this.population = new NeuralNetwork[populationSize];
    }

    public void train(int numOfIterations, double desiredFitness, int changeSeedInterval, double mutationChance) {
        System.out.println("Zapocinjem treniranje...");
        var random = new Random();
        var seed = random.nextLong();

        for (int i = 0; i < populationSize; i++) {
            NeuralNetwork network = new NeuralNetwork(
                    9,
                    new Layer(7, ActivationFunctionAppliers.Sigmoid),
                    new Layer(3, ActivationFunctionAppliers.Net)
            );

            network.initializeNetwork();
            population[i] = network;
        }

        double[] fitness = GameSimulator.simulate(population, seed);

        int currIteration = 0;
        double currBestFitness = fitness[NetworkUtil.findBestPlayer(population, fitness)];

        while (currIteration < numOfIterations && currBestFitness < desiredFitness) {
            if (currIteration % changeSeedInterval == 0) {
                seed = random.nextLong();
            }

            NeuralNetwork[] nextGeneration = new NeuralNetwork[populationSize];
            int bestFitnessInd = NetworkUtil.findBestPlayer(population, fitness);

            nextGeneration[0] = population[bestFitnessInd]; //elitism

            int index = 1;
            while (index < nextGeneration.length) {
                NeuralNetwork[] parents = NetworkUtil.pickParents(population, fitness);
                NeuralNetwork child = NetworkUtil.crossParents(parents);
                NetworkUtil.mutate(child, mutationChance);

                //if(NetworkUtil.populationContainsNetwork(nextGeneration, child)) continue;

                nextGeneration[index++] = child;
            }
            //NetworkUtil.mutate(nextGeneration[0], 0.05);

            population = nextGeneration;
            fitness = GameSimulator.simulate(population, seed);

            currIteration++;
            bestFitnessInd = NetworkUtil.findBestPlayer(population, fitness);
            currBestFitness = fitness[bestFitnessInd];

            if (currIteration % 10 == 0) {
                System.out.println("Treniranje u tijeku: iteracija = " + currIteration + ", highest fitness = " + currBestFitness);
            }
            if (currIteration % 100 == 0) {
                System.out.println("Saving AI backup just in case...");
                try {
                    FileOutputStream fos = new FileOutputStream(Main.getCurrentPath() + "/AI.ser");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(population[bestFitnessInd]);
                    oos.writeObject(currBestFitness);
                    fos.close();
                    oos.close();
                } catch (Exception ignored) {
                    System.out.println("Backup saving failed...");
                }
            }
        }

        System.out.println();
        System.out.println("Treniranje završilo:");
        System.out.println("iteracija = " + currIteration);
        System.out.println("high score = " + currBestFitness);


        var bestPlayerIndex = NetworkUtil.findBestPlayer(population, fitness);
        var bestPlayer = population[bestPlayerIndex];

        var scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Do you wish to save current AI?(y/n): ");
        if (scanner.nextLine().equals("y")) {
            System.out.println("Saving...");
            try {
                FileOutputStream fos = new FileOutputStream(Main.getCurrentPath() + "/AI.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(bestPlayer);
                oos.writeObject(fitness[bestPlayerIndex]);
                fos.close();
                oos.close();
            } catch (Exception ignored) {
                System.out.println("Saving failed...");
            }
        }

        System.out.println();
        System.out.print("Do you wish to play current AI?(y/n): ");
        if (scanner.nextLine().equals("y")) {
            System.out.print("Starting game with a unit of " + fitness[bestPlayerIndex] + " fitness.");
            GameSimulator.play(bestPlayer, seed);
        }
    }
}


