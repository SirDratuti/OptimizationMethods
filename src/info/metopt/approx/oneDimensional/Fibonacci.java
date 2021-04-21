package info.metopt.approx.oneDimensional;

import info.metopt.approx.Method;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * Implementation of the {@link OneDimensionalMethod} interface based on Fibonacci method.
 */
public class Fibonacci extends AbstractOneDimensionalMethod {

    private ArrayList<Double> fibonacci = new ArrayList<>();
    private int iterations;
    private int iterationNumber = 1;

    public Fibonacci() {
        super();
    }

    public Fibonacci(Function<Double, Double> function, double left, double right, double epsilon) {
        this(function, left, right, epsilon, false);
    }

    public Fibonacci(Function<Double, Double> function, double left, double right, double epsilon, boolean isLog) {
        super(function, left, right, epsilon, isLog);
        fibonacci.add(1.0);
        fibonacci.add(1.0);
        fibonacci.add(2.0);
        iterations = calculateIterations();
    }

    private int calculateIterations() {
        int count = 0;
        double bound = (right - left) / epsilon;
        while (bound > fibonacci.get(count + 2)) {
            fibonacci.add(fibonacci.get(fibonacci.size() - 1)
                    + fibonacci.get(fibonacci.size() - 2));
            count++;
        }
        return count;
    }

    @Override
    public Double start() {
        makeIterations(iterations);
        return result;
    }

    @Override
    public boolean makeIteration() {
            numberIteration++;
            x1 = evaluateFirst();
            x2 = evaluateSecond();
            double fx1 = evaluate(x1);
            double fx2 = evaluate(x2);
            if (Method.compare(fx1, fx2)) {
                left = x1;
            } else {
                right = x2;
            }
            return true;
    }


    @Override
    public double evaluateFirst() {
        return left +
                (fibonacci.get(iterations - iterationNumber + 1))
                        / (fibonacci.get(iterations - iterationNumber + 3))
                        * Method.range(left, right);
    }

    @Override
    public double evaluateSecond() {
        return left +
                (fibonacci.get(iterations - iterationNumber + 2))
                        / (fibonacci.get(iterations - iterationNumber + 3))
                        * Method.range(left, right);
    }

    @Override
    public Double getCurrentX() {
        return ((x1 + x2) / 2.0);
    }

    @Override
    public OneDimensionalMethod of(Function<Double, Double> function, double left, double right, double epsilon, boolean isLog) {
        return new Fibonacci(function, left, right, epsilon, isLog);
    }

}
