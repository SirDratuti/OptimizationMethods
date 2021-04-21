package info.metopt.approx.gradient;

import info.metopt.approx.Method;

/**
 * Interface extending this interface {@link Method} for multidimensional functions from {@link Vector} to {@link Double}.
 * The implemented methods are based on the function gradient
 */
public interface GradientMethod extends Method<Vector> {
    /**
     * Computes {@link Vector} the gradient of a function with an <var>argument</var>.
     *
     * @param argument f type {@link Vector} to evaluate the function.
     * @return the gradient of a function
     */
    Vector evaluateGradient(Vector argument);
}
