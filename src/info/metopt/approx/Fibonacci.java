package info.metopt.approx;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci extends AbstractMethod {

    private ArrayList<Double> fibonacci = new ArrayList<>();
    private final int iterations;
    private int iterationNumber = 1;
    private List<Double> x1s;
    private List<Double> x2s;

    public Fibonacci(double left, double right, double epsilon) {
        this(left, right, epsilon, false);
    }

    public Fibonacci(double left, double right, double epsilon, boolean isLog) {
        super(left, right, epsilon, isLog);
        fibonacci.add(1.0);
        fibonacci.add(1.0);
        fibonacci.add(2.0);
        iterations = calculateIterations();
        x1s = new ArrayList<>();
        x2s = new ArrayList<>();
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
            evaluationsNumber += 2;
            if (Method.compare(fx1, fx2)) {
                left = x1;
            } else {
                right = x2;
            }
            log();
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
    public double getCurrentX() {
        return ((x1 + x2) / 2.0);
    }

    @Override
    public void log() {
        if (!isLog) {
            return;
        }
        super.log();
        x1s.add(x1);
        x2s.add(x2);
    }

    @Override
    public void finish() {
        super.finish();
        if (!isLog) {
            return;
        }
        System.out.println("значения x1(" + x1s.size() + "):");
        printList(x1s);
        System.out.println("значения x2(" + x2s.size() + "):");
        printList(x2s);
    }
}
