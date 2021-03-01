package info.metopt.approx;

import java.util.ArrayList;

public class Fibonacci extends AbstractMethod {

    private double result;

    private ArrayList<Double> fibonacci = new ArrayList<>();
    private final int iterations;
    private int iterationNumber = 1;

    public Fibonacci(double left, double right, double epsilon) {
        this.left = left;
        this.right = right;
        this.epsilon = epsilon;
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
        makeIteration();
        return result;
    }

    @Override
    public void makeIteration() {
        for (; iterationNumber <= iterations; iterationNumber++) {
            x1 = evaluateFirst();
            x2 = evaluateSecond();
            double fx1 = Method.evaluate(x1);
            double fx2 = Method.evaluate(x2);
            if (Method.compare(fx1, fx2)) {
                left = evaluateLeft(x1);
            } else {
                right = evaluateRight(x2);
            }
        }
        finish();
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
    public double evaluateLeft(double value) {
        return value;
    }

    @Override
    public double evaluateRight(double value) {
        return value;
    }

    @Override
    public void finish() {
        result = ((x1 + x2) / 2.0);
    }

    @Override
    public void log() {

    }
}
