package hr.fer.projektr.ai;

import org.ejml.simple.SimpleMatrix;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

/**
 * Class representing a single layer of neural network
 * Each Layer has a matrix of weights and biases and an activation function
 */
public class Layer implements Serializable {

    private int layerSize;
    private ActivationFunctionApplier activationFunction;
    private SimpleMatrix weights;
    private SimpleMatrix biases;
    private boolean isInitialized;

    /**
     * Constructor
     * @param layerSize int number of neurons in the layer
     * @param activationFunction ActivationFunction to be applied in neurons of this layer
     */
    public Layer(int layerSize, ActivationFunctionApplier activationFunction) {
        if (layerSize < 1) throw new IllegalArgumentException("layerSize can't be less than 1");

        this.layerSize = layerSize;
        this.activationFunction = activationFunction;
        this.isInitialized = false;
    }

    public Layer(int layerSize, SimpleMatrix weights, SimpleMatrix biases, ActivationFunctionApplier activationFunction){
        if (layerSize < 1) throw new IllegalArgumentException("layerSize can't be less than 1");

        this.layerSize = layerSize;
        this.weights = weights;
        this.biases = biases;
        this.activationFunction = activationFunction;
        this.isInitialized = true;
    }

    public Layer(int layerSize, double[][] weights, double[][] biases, ActivationFunctionApplier activationFunction){
        if (layerSize < 1) throw new IllegalArgumentException("layerSize can't be less than 1");

        this.layerSize = layerSize;
        this.weights = new SimpleMatrix(weights);
        this.biases = new SimpleMatrix(biases);
        this.activationFunction = activationFunction;
        this.isInitialized = true;
    }

    /**
     * Returns the number of neurons in the layer
     * @return int number of neurons
     */
    public int getLayerSize() {
        return layerSize;
    }

    public ActivationFunctionApplier getActivationFunction() {
        return activationFunction;
    }

    public SimpleMatrix getWeights() {
        return weights;
    }

    public SimpleMatrix getBiases() {
        return biases;
    }

    /**
     * Confirms if the layer has been initialized by neural network
     * @return boolean true if layer is initialized, false otherwise
     */
    public boolean isInitialized() {
        return isInitialized;
    }

    /**
     * Fill weights and biases matrices with random values from uniform distribution in [-2.4/n, 2.4/n]
     * where n is the number of inputs to the layer (prevLayerSize)
     * @param prevLayerSize int number of inputs to this layer
     */
    public void initializeLayer(int prevLayerSize) {
        if (prevLayerSize < 1) throw new IllegalArgumentException("PrevLayerSize can't be less than 1");

        weights = new SimpleMatrix(new double[layerSize][prevLayerSize]);
        biases = new SimpleMatrix(new double[layerSize][1]);

        initializeMatrix(weights, prevLayerSize);
        initializeMatrix(biases, prevLayerSize);

        isInitialized = true;
    }

    /**
     * Helper method to initialize matrix random values from uniform distribution in [-2.4/n, 2.4/n]
     * @param matrix double[][]
     * @param prevLayerSize int number of inputs to this layer
     */
    private void initializeMatrix(SimpleMatrix matrix, int prevLayerSize) {
        Random rand = new Random();
        double max = 2.4 / prevLayerSize;
        double min = - max;

        for (int i = 0; i < matrix.numRows(); i++) {
            for (int j = 0; j < matrix.numCols(); j++) {
                matrix.set(i, j, rand.nextDouble(max - min + 1) + min);
            }
        }
    }

    public void setWeights(SimpleMatrix weights) {
        this.weights = weights;
    }

    public void setBiases(SimpleMatrix biases) {
        this.biases = biases;
    }

    @Override
    public String toString() {
        return "Layer{" +
                "weights=" + weights +
                ", biases=" + biases +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Layer layer = (Layer) o;
        return layerSize == layer.layerSize &&
                isInitialized == layer.isInitialized &&
                Objects.equals(activationFunction, layer.activationFunction) &&
                weights.isIdentical(layer.getWeights(), 0) &&
                weights.isIdentical(layer.getBiases(), 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(layerSize, activationFunction, weights, biases, isInitialized);
    }
}
