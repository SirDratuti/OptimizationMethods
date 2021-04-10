package info.metopt.approx.gradient;

import info.metopt.approx.Method;
import info.metopt.approx.oneDimensional.*;

import java.util.function.Function;

public class SteepestDescentMethod extends AbstractGradientMethod {

    double fx;
    private OneDimensionalMethod oneDimensionalMethod = null;
    private static OneDimensionalMethod DEFAULT_ONE_DIMENSIONAL_METHOD = new Brent(x->x, 0, 0, 0);

    public SteepestDescentMethod(Matrix A, Vector b, double c, Vector startX, double epsilon, boolean isLog) {
        super(A, b, c, startX, epsilon, isLog);
    }

    public SteepestDescentMethod(Matrix A, Vector b, double c, Vector startX, double epsilon, OneDimensionalMethod oneDimensionalMethod, boolean isLog) {
        super(A, b, c, startX, epsilon, isLog);
        this.oneDimensionalMethod = oneDimensionalMethod;
    }

    public SteepestDescentMethod(Matrix A, Vector b, double c, Vector startX, double epsilon) {
        super(A, b, c, startX, epsilon, false);
    }

    @Override
    public Vector start() {
        x = startX;
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
        if (Method.compare(epsilon, gradientVector.norm())) {
            return false;
        }
        Function<Double, Double> alphaFunction = (Double alpha) -> function.apply(x.sum(gradientVector.numberMultiply(-alpha)));
        alpha = oneDimensionalMethod.of(alphaFunction, 0, 100, epsilon, false).start();
        x = x.sum(gradientVector.numberMultiply(-alpha));
        return true;
    }

}
