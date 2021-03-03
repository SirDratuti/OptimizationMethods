package info.metopt.approx;

import java.util.ArrayList;
import java.util.List;

public class Parabola extends AbstractMethod {

    private double fx1;
    private double fx2;
    private double fx3;
    private List<Double> a0s;
    private List<Double> a1s;
    private List<Double> a2s;
    private double x3;
    private double currentX;
    double prefX;
    double fCurrentX;
    int iterationNumber = 0;

    public Parabola(double left, double right, double epsilon) {
        this(left, right, epsilon, false);
    }

    public Parabola(double left, double right, double epsilon, boolean isLog) {
        super(left, right, epsilon, isLog);
        a0s = new ArrayList<>();
        a1s = new ArrayList<>();
        a2s = new ArrayList<>();
    }

    @Override
    public double start() {
        this.x1 = left;
        this.x3 = right;
        this.x2 = (right + left) / 2;
        fx1 = evaluate(x1);
        fx2 = evaluate(x2);
        fx3 = evaluate(x3);
        makeIterations();
        return result;
    }

    public static double evaluate_a0(double x1, double x2, double x3, double fx1, double fx2, double fx3) {
        return fx1;
    }

    public static double evaluate_a1(double x1, double x2, double x3, double fx1, double fx2, double fx3) {
        return (fx2 - fx1) / (x2 - x1);
    }

    public static double evaluate_a2(double x1, double x2, double x3, double fx1, double fx2, double fx3) {
        return 1 / (x3 - x2) * ((fx3 - fx1) / (x3 - x1) - (fx2 - fx1) / (x2 - x1));
    }

    public static double evaluateCurrentX(double x1, double x2, double x3, double fx1, double fx2, double fx3) {
        double a1 = evaluate_a1(x1, x2, x3, fx1, fx2, fx3);
        double a2 = evaluate_a2(x1, x2, x3, fx1, fx2, fx3);
        return (x1 + x2 - (a1 / a2)) / 2;
    }

    @Override
    public boolean makeIteration() {
        currentX = evaluateCurrentX(x1, x2, x3, fx1, fx2, fx3);
        if (iterationNumber > 0 && !Method.compare(Math.abs(currentX - prefX), epsilon)) {
            return false;
        }
        fCurrentX = evaluate(currentX);
        if (!Method.compare(x1, currentX) && !Method.compare(currentX, x2) && !Method.compare(x2, x3) && Method.compare(fCurrentX, fx2)) {
            x1 = currentX;
            fx1 = fCurrentX;
        } else if (!Method.compare(x1, currentX) && !Method.compare(currentX, x2) && !Method.compare(x2, x3) && !Method.compare(fCurrentX, fx2)) {
            x3 = x2;
            fx3 = fx2;
            x2 = currentX;
            fx2 = fCurrentX;
        } else if (!Method.compare(x1, x2) && !Method.compare(x2, currentX) && !Method.compare(currentX, x3) && Method.compare(fx2, fCurrentX)) {
            x1 = x2;
            fx1 = fx2;
            x2 = currentX;
            fx2 = fCurrentX;
        } else if (!Method.compare(x1, x2) && !Method.compare(x2, currentX) && !Method.compare(currentX, x3) && !Method.compare(fx2, fCurrentX)) {
            x3 = currentX;
            fx3 = fCurrentX;
        }
        prefX = currentX;
        right = x3;
        left = x1;
        iterationNumber++;
        return true;
    }

    @Override
    public double evaluateFirst() {
        return 0;
    }

    @Override
    public double evaluateSecond() {
        return 0;
    }

    @Override
    public double getCurrentX() {
        return currentX;
    }

    @Override
    public void log() {
        if (!isLog) {
            return;
        }
        super.log();
        a0s.add(evaluate_a0(x1, x2, x3, fx1, fx2, fx3));
        a1s.add(evaluate_a1(x1, x2, x3, fx1, fx2, fx3));
        a2s.add(evaluate_a2(x1, x2, x3, fx1, fx2, fx3));
    }

    @Override
    public void finish() {
        super.finish();
        if (!isLog) {
            return;
        }
        System.out.println("коэффициенты a0(" + a0s.size() + "):");
        super.printList(a0s);
        System.out.println("коэффициенты a1(" + a1s.size() + "):");
        super.printList(a1s);
        System.out.println("коэффициенты a2(" + a2s.size() + "):");
        super.printList(a2s);
    }
}