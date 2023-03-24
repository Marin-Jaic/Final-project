package hr.fer.projektr.ai;

import org.ejml.simple.SimpleMatrix;

import java.io.Serializable;

/**
 * Interface to apply activation function to each element of the matrix
 */
@FunctionalInterface
public interface ActivationFunctionApplier extends Serializable {
    SimpleMatrix apply(SimpleMatrix matrix);
}
