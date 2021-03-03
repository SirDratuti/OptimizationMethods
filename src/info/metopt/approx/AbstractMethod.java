package info.metopt.approx;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMethod implements Method {
    public static final double GOLDEN_PHI = (Math.sqrt(5.0) - 1.0) / 2.0;

    double left;
    double right;
    double x1;
    double x2;
    double epsilon;
    double result;
    boolean isLog;
    private List<Double> rights;
    private List<Double> lefts;
    private List<Double> ratios;
    private List<Double> lens;
    private List<Double> xs;
    private List<Double> fxs;
    long evaluationsNumber;

    public AbstractMethod(double left, double right, double epsilon, boolean isLog) {
        this.left = left;
        this.right = right;
        this.epsilon = epsilon;
        this.isLog = isLog;
        rights = new ArrayList<>();
        lefts = new ArrayList<>();
        ratios = new ArrayList<>();
        lens = new ArrayList<>();
        xs = new ArrayList<>();
        fxs = new ArrayList<>();
        evaluationsNumber = 0;
    }

    public AbstractMethod(double left, double right, double epsilon) {
        this(left, right, epsilon, false);
    }

    public double getResult() {
        return result;
    }

    public List<Double> getRights() {
        return rights;
    }

    public List<Double> getLefts() {
        return lefts;
    }

    public List<Double> getRatios() {
        return ratios;
    }

    public List<Double> getLens() {
        return lens;
    }

    public List<Double> getXs() {
        return xs;
    }

    public List<Double> getFxs() {
        return fxs;
    }

    public long getEvaluationsNumber() {
        return evaluationsNumber;
    }

    @Override
    public double evaluate(double argument) {
        evaluationsNumber++;
        return (argument * argument) + Math.exp(-0.35 * argument);
    }

    @Override
    public void log() {
        if (!isLog) {
            return;
        }
        rights.add(right);
        lefts.add(left);
        if (lens.size() > 0) {
            ratios.add(((right - left) / lens.get(lens.size() - 1)));
        }
        lens.add(right - left);
        xs.add(this.getCurrentX());
        fxs.add(evaluate(this.getCurrentX()));
        evaluationsNumber--;
    }

    void printList(List<Double> list) {
        for (double e : list) {
            System.out.println(e);
        }
        System.out.println();
    }

    @Override
    public void makeIterations() {
        while (makeIteration()) {
            log();
        }
        finish();
    }

    @Override
    public void makeIterations(long n) {
        for (long i = 0; i < n; ++i) {
            makeIteration();
            log();
        }
        finish();
    }

    @Override
    public void finish() {
        result = getCurrentX();
        if (!isLog) {
            return;
        }
        System.out.println("МЕТОД: " + this.getClass());
        System.out.println("ИТОГОВЫЙ X: " + result);
        System.out.println("ИТОГОВЫЙ f(X): " + evaluate(result));
        evaluationsNumber--;
        System.out.println("Количество вычислений функции: " + evaluationsNumber);
        System.out.println("правая граница(" + rights.size() + "): ");
        printList(rights);
        System.out.println("левая граница(" + lefts.size() + "): ");
        printList(lefts);
        System.out.println("длины отрезков(" + lens.size() + "): ");
        printList(lens);
        System.out.println("отношения отрезков(начиная со второго тк для первого нет)(" + ratios.size() + "): ");
        printList(ratios);
        System.out.println("иксы(" + xs.size() + "):");
        printList(xs);
        System.out.println("значения функции(" + fxs.size() + "):");
        printList(fxs);
    }
}
