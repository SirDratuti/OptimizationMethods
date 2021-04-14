package info.metopt.approx;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class AbstractMethod<T> implements Method<T> {

    protected Function<T, Double> function;

    protected double epsilon;
    protected int numberIteration = 0;
    protected T result;
    protected Double fResult;
    protected boolean isLog;
    private List<T> xs;
    private List<Double> fxs;
    protected long evaluationsNumber;

    public AbstractMethod() {}


    public AbstractMethod(Function<T, Double> function, double epsilon, boolean isLog) {
        this.function = function;
        this.epsilon = epsilon;
        this.isLog = isLog;
        xs = new ArrayList<>();
        fxs = new ArrayList<>();
        evaluationsNumber = 0;
    }

    public AbstractMethod(Function<T, Double> function, double epsilon) {
        this(function, epsilon, false);
    }

    public T getResult() {
        return result;
    }

    public List<T> getXs() {
        return xs;
    }

    public List<Double> getFxs() {
        return fxs;
    }

    public long getEvaluationsNumber() {
        return evaluationsNumber;
    }

    @Override
    public double evaluate(T argument) {
        return function.apply(argument);
    }

    @Override
    public void log() {
        if (!isLog) {
            return;
        }
        xs.add(this.getCurrentX());
        fxs.add(evaluate(this.getCurrentX()));
        evaluationsNumber--;
    }

    protected <K> void printList(List<K> list) {
        for (K e : list) {
            System.out.println(e);
        }
        System.out.println();
    }

    @Override
    public void makeIterations() {
        //log();
        for (; makeIteration(); ++numberIteration) {
            //log();
        }
        finish();
    }

    @Override
    public void makeIterations(long n) {
        log();
        for (int i = 1; i < n; ++i, ++numberIteration) {
            makeIteration();
            log();
        }
        finish();
    }

    @Override
    public void finish() {
        result = getCurrentX();
        if (isLog) {
            printLog();
        }
    }

    public void printLog() {
        System.out.println("МЕТОД: " + this.getClass());
        //System.out.println("ИТОГОВЫЙ X: " + result);
        System.out.println("ИТОГОВЫЙ f(X): " + evaluate(result));
//        System.out.println("иксы(" + xs.size() + "):");
//        printList(xs);
//        System.out.println("значения функции(" + fxs.size() + "):");
//        printList(fxs);
    }
}