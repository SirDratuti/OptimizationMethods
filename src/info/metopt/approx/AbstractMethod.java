package info.metopt.approx;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMethod implements Method {
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
        fxs.add(Method.evaluate(this.getCurrentX()));
    }

    void printList(List<Double> list) {
        for (double e : list) {
            System.out.print(e + " ");
        }
        System.out.println();
    }

    @Override
    public void finish() {
        result = getCurrentX();
        if (!isLog) {
            return;
        }
        System.out.println("МЕТОД: " + this.getClass());
        System.out.println("ИТОГОВЫЙ X: " + result);
        System.out.println("ИТОГОВЫЙ f(X): " + Method.evaluate(result));
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
