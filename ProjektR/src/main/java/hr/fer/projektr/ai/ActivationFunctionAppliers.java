package hr.fer.projektr.ai;

import java.io.Serializable;

/**
 * Class with most common activation function implementations
 */
public class ActivationFunctionAppliers implements Serializable {

    /**
     * Linear ActivationFunctionApplier which essentially does nothing to the matrix
     */
    public static ActivationFunctionApplier Net = matrix -> matrix;

    /**
     * Sigmoid ActivationFunctionApplier which applies sigmoid function to each element of the matrix
     */
    public static ActivationFunctionApplier Sigmoid = matrix -> {
        for (int i = 0; i < matrix.numRows(); i++) {
            for (int j = 0; j < matrix.numCols(); j++) {
                double val = matrix.get(i, j);
                val = 1 / (1 + Math.pow(Math.E, -val));
                matrix.set(i, j, val);
            }
        }
        return matrix;
    };

}
