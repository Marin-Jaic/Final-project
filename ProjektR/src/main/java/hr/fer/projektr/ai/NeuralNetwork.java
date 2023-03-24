package hr.fer.projektr.ai;

import org.ejml.simple.SimpleMatrix;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * Class representing a neural network
 */
public class NeuralNetwork implements Serializable {
    private final int inputSize;
    private final Layer[] layers;
    /**
     * Constructor
     * @param inputSize int Number of inputs to the network
     * @param layers Layer[] containing hidden layers and output layer
     */
    NeuralNetwork(int inputSize, Layer... layers) {
        if (inputSize < 1) throw new IllegalArgumentException("Neural network must have at least one input");
        if (layers.length < 1) throw new IllegalArgumentException("Neural network must have at least one layer");

        this.inputSize = inputSize;
        this.layers = layers;
    };

    /**
     * inputSize getter
     * @return int number of inputs to the network
     */
    public int getInputSize() {
        return inputSize;
    }

    /**
     * layers getter
     * @return Layer[] all the layers of the network
     */
    public Layer[] getLayers() {
        return layers;
    }

    /**
     * Initializes the network by calling {@code initializeLayer} on every layer
     */
    void initializeNetwork() {
        layers[0].initializeLayer(inputSize);

        for(int i = 1; i < layers.length; i++) {
            layers[i].initializeLayer(layers[i-1].getLayerSize());
        }
    }

    /**
     * Computes the forward propagation of the neural network
     * @param input SimpleMatrix[][1] vector of doubles representing the input for which to calculate forward propagation
     * @return int index of output layer neuron with the highest value (the decision based on input)
     */
    public int computeForwardProp(SimpleMatrix input) {
        if (input.numCols() != 1) throw new IllegalArgumentException("Input must be a one column matrix");

        SimpleMatrix prevLayerOutput = input;
        SimpleMatrix currLayerOutput;

        // compute all layer outputs
        for(Layer l : layers) {
            ActivationFunctionApplier activationFunction = l.getActivationFunction();
            currLayerOutput = activationFunction.apply(l.getWeights().mult(prevLayerOutput).plus(l.getBiases()));

            prevLayerOutput = currLayerOutput;
        }

        // find the decision from final layer
        int ind = 0;
        double highestVal = prevLayerOutput.get(0,0);
        for (int i = 0; i < prevLayerOutput.numRows(); i++) {
            if (prevLayerOutput.get(i, 0) > highestVal) {
                highestVal = prevLayerOutput.get(i, 0);
                ind = i;
            }
        }
        return ind;
    }

    @Override
    public String toString() {
        return "NeuralNetwork{" +
                "layers=" + Arrays.toString(layers) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NeuralNetwork that = (NeuralNetwork) o;
        return inputSize == that.inputSize && Arrays.equals(layers, that.layers);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(inputSize);
        result = 31 * result + Arrays.hashCode(layers);
        return result;
    }
}
