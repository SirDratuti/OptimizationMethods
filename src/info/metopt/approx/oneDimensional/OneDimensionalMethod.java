package info.metopt.approx.oneDimensional;

import info.metopt.approx.Method;

import java.util.function.Function;

/**
 * Interface extending this interface {@link Method} for one-dimensional functions from {@link Double} to {@link Double}
 */
public interface OneDimensionalMethod extends Method<Double> {

    /**
     * evaluate new left border.
     *
     * @return new left border
     */
    double evaluateFirst();

    /**
     * Evaluate new left border.
     *
     * @return new left border
     */
    double evaluateSecond();

    /**
     * Returns a new method {@link OneDimensionalMethod} with the given parameters.
     *
     * @param left  border of the segment on which the minimum is located.
     * @param right border of the segment on which the minimum is located.
     * @return new Method.
     */
    OneDimensionalMethod of(Function<Double, Double> function, double left, double right, double epsilon, boolean isLog);
}
