package info.metopt.approx;

import java.util.ArrayList;

public class Fibonacci extends AbstractMethod {

    private ArrayList<Double> fibonacci = new ArrayList<>();
    private final int iterations;
    private int iterationNumber = 1;

    public Fibonacci(double left, double right, double epsilon) {
        this(left, right, epsilon, false);
    }

    public Fibonacci(double left, double right, double epsilon, boolean isLog) {
        super(left, right, epsilon, isLog);
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
    public double start() {
        makeIterations(iterations);
        return result;
    }

    @Override
    public boolean makeIteration() {
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
    public double getCurrentX() {
        return ((x1 + x2) / 2.0);
    }

}
