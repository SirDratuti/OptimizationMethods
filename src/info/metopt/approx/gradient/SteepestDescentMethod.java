package info.metopt.approx.gradient;

import info.metopt.approx.Method;
import info.metopt.approx.oneDimensional.*;

import java.util.function.Function;

/**
 * Implementation of the {@link GradientMethod} interface based on <a href="https://youtu.be/ELUI00I0NqM?t=101">the steepest descent method</a>.
 */
public class SteepestDescentMethod extends AbstractGradientMethod {
    protected int maxIterationNumber = 5000;
    protected double maxAlpha = 100;

    protected double fx;
    protected Vector prefx;
    private OneDimensionalMethod oneDimensionalMethod = null;
    private static OneDimensionalMethod DEFAULT_ONE_DIMENSIONAL_METHOD = new Brent();

    public SteepestDescentMethod(Matrix A, Vector b, double c, Vector startX, double epsilon, boolean isLog) {
        super(A, b, c, startX, epsilon, isLog);
    }

    public SteepestDescentMethod(Matrix A, Vector b, double c, Vector startX, double epsilon, OneDimensionalMethod oneDimensionalMethod, boolean isLog) {
        super(A, b, c, startX, epsilon, isLog);
        this.oneDimensionalMethod = oneDimensionalMethod;
    }

    public SteepestDescentMethod(Matrix A, Vector b, double c, Vector startX, double epsilon, OneDimensionalMethod oneDimensionalMethod, double maxAlpha, boolean isLog) {
        super(A, b, c, startX, epsilon, isLog);
        this.maxAlpha = maxAlpha;
        this.oneDimensionalMethod = oneDimensionalMethod;
    }

    /**
     * Constructor with basic values.
     *
     * @param A                    {@link Matrix} of coefficients of quadratic form. Element with row index <var>i</var> and column index <var>j</var> is the coefficient of x_i*x_j.
     * @param b                    {@link Vector} of coefficients of quadratic form. Element with row index <var>i</var> is the coefficient of x_i.
     * @param c                    onstant of quadratic form.
     * @param startX               the point {@link Vector} from which we start looking for the minimum. The closer the point is to the minimum, the fewer iterations will be needed.
     * @param epsilon              precision value.
     * @param oneDimensionalMethod {@link OneDimensionalMethod} by which the optimal alpha step is calculated
     * @param maxAlpha             maximum alpha step value
     * @param maxIterationNumber   maximum number of iterations.
     * @param isLog                log flag.
     */
    public SteepestDescentMethod(Matrix A, Vector b, double c, Vector startX, double epsilon, OneDimensionalMethod oneDimensionalMethod, double maxAlpha, int maxIterationNumber, boolean isLog) {
        super(A, b, c, startX, epsilon, isLog);
        this.maxAlpha = maxAlpha;
        this.oneDimensionalMethod = oneDimensionalMethod;
        this.maxIterationNumber = maxIterationNumber;
    }

    public SteepestDescentMethod(Matrix A, Vector b, double c, Vector startX, double epsilon) {
        super(A, b, c, startX, epsilon, false);
    }

    @Override
    public Vector start() {
        x = startX;
        prefx = startX;
        alpha = 1;
        if (oneDimensionalMethod == null) {
            oneDimensionalMethod = DEFAULT_ONE_DIMENSIONAL_METHOD;
        }
        fx = evaluate(x);
        makeIterations();
        return x;
    }

    @Override
    public boolean makeIteration() {
        Vector gradientVector = evaluateGradient(x);
        if (Method.compare(epsilon, gradientVector.norm()) || Method.compare(epsilon, alpha * gradientVector.norm())
                || numberIteration > maxIterationNumber || (numberIteration > 1 && Math.abs(evaluate(x) - evaluate(prefx)) <= (epsilon / 1000))) {
            return false;
        }
        Function<Double, Double> alphaFunction = (Double alpha) -> function.apply(x.sum(gradientVector.numberMultiply(-alpha)));
        alpha = oneDimensionalMethod.of(alphaFunction, 0, maxAlpha, epsilon, false).start();
        prefx = x;
        x = x.sum(gradientVector.numberMultiply(-alpha));
        return true;
    }

}
