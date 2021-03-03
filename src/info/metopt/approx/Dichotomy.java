package info.metopt.approx;

import java.util.ArrayList;
import java.util.List;

public class Dichotomy extends AbstractMethod {

    private double beta;
    private List<Double> x1s;
    private List<Double> x2s;
    private List<Double> fx1s;
    private List<Double> fx2s;

    public Dichotomy(double left, double right, double epsilon) {
        this(left, right, epsilon, false);
    }

    public Dichotomy(double left, double right, double epsilon, boolean isLog) {
        super(left, right, epsilon, isLog);
        beta = epsilon;
        this.x1 = evaluateFirst();
        this.x2 = evaluateSecond();
        x1s = new ArrayList<>();
        x2s = new ArrayList<>();
        fx1s = new ArrayList<>();
        fx2s = new ArrayList<>();
    }

    @Override
    public void log() {
        if (!isLog) {
            return;
        }
        super.log();
        x1s.add(x1);
        x2s.add(x2);
        fx1s.add(evaluate(x1));
        fx2s.add(evaluate(x2));
        evaluationsNumber -= 2;
    }

    @Override
    public void finish() {
        super.finish();
        if (!isLog) {
            return;
        }
        System.out.println("значения x1(" + x1s.size() + "):");
        printList(x1s);
        System.out.println("значения fx1(" + x1s.size() + "):");
        printList(fx1s);
        System.out.println("значения x2(" + x2s.size() + "):");
        printList(x2s);
        System.out.println("значения fx2(" + x2s.size() + "):");
        printList(fx2s);
    }

    @Override
    public double start() {
        makeIterations();
        return result;
    }

    @Override
    public boolean makeIteration() {
        x1 = evaluateFirst();
        x2 = evaluateSecond();
        double fx1 = evaluate(x1);
        double fx2 = evaluate(x2);
        if (Method.compare(fx2, fx1)) {
            right = x2;
        } else {
            left = x1;
        }

        double epsilonN = Method.range(left, right) / 2.0;
        return !Method.compare(epsilon, epsilonN);
    }


    @Override
    public double evaluateFirst() {
        return (left + right - beta) / 2.0;
    }

    @Override
    public double evaluateSecond() {
        return (left + right + beta) / 2.0;
    }

    @Override
    public double getCurrentX() {
        return (left + right) / 2.0;
    }

}
