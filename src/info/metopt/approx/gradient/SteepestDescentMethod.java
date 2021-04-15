package info.metopt.approx.gradient;

import info.metopt.approx.Method;
import info.metopt.approx.oneDimensional.*;

import java.util.function.Function;

public class SteepestDescentMethod extends AbstractGradientMethod {
    int maxIterationNumber = 5000;
    double maxAlpha = 100;

    double fx;
    Vector prefx;
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
        if (Method.compare(epsilon, gradientVector.norm()) || Method.compare(epsilon, alpha * gradientVector.norm()) || numberIteration > maxIterationNumber) {
            return false;
        }
        Function<Double, Double> alphaFunction = (Double alpha) -> function.apply(x.sum(gradientVector.numberMultiply(-alpha)));
        alpha = oneDimensionalMethod.of(alphaFunction, 0, maxAlpha, epsilon, false).start();
        prefx = x;
        System.out.println(x);
        x = x.sum(gradientVector.numberMultiply(-alpha));
        return true;
    }

}
