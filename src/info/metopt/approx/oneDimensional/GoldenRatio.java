package info.metopt.approx.oneDimensional;

import info.metopt.approx.Method;

import java.util.function.Function;

/**
 * Implementation of the {@link OneDimensionalMethod} interface based on golden ratio method.
 */
public class GoldenRatio extends AbstractOneDimensionalMethod {
    private double fx1;
    private double fx2;
    private double epsilonN;

    public GoldenRatio(Function<Double, Double> function, double left, double right, double epsilon) {
        this(function, left, right, epsilon, false);
    }

    public GoldenRatio(Function <Double, Double> function, double left, double right, double epsilon, boolean isLog) {
        super(function, left, right, epsilon, isLog);
    }

    public GoldenRatio() {
        super();
    }

    @Override
    public Double start() {
        this.x1 = evaluateFirst();
        this.x2 = evaluateSecond();
        this.fx1 = evaluate(x1);
        this.fx2 = evaluate(x2);
        epsilonN = Method.range(left, right) / 2.0;
        makeIterations();
        return result;
    }

    @Override
    public boolean makeIteration() {
        numberIteration++;
        if (Method.compare(epsilon, epsilonN)) {
            return false;
        }
        if (Method.compare(fx2, fx1)) {
            right = x2;
            x2 = x1;
            fx2 = fx1;
            x1 = right - Method.range(left, right) * GOLDEN_PHI;
            fx1 = evaluate(x1);
        } else {
            left = x1;
            x1 = x2;
            fx1 = fx2;
            x2 = left + Method.range(left, right) * GOLDEN_PHI;
            fx2 = evaluate(x2);
        }
        evaluationsNumber++;
        epsilonN *= GOLDEN_PHI;
        return true;
    }

    @Override
    public double evaluateFirst() {
        return left + (1 - GOLDEN_PHI) * Method.range(left, right);
    }

    @Override
    public double evaluateSecond() {
        return left + GOLDEN_PHI * Method.range(left, right);
    }

    @Override
    public Double getCurrentX() {
        return (left + right) / 2.0;
    }

    @Override
    public OneDimensionalMethod of(Function<Double, Double> function, double left, double right, double epsilon, boolean isLog) {
        return new GoldenRatio(function, left, right, epsilon, isLog);
    }
}
