package info.metopt.approx;

import info.metopt.approx.oneDimensional.OneDimensionalMethod;

public interface Method<T> {

    double compareEpsilon = 1e-10;

    double evaluate(T argument);

    T start();

    boolean makeIteration();

    void makeIterations();

    void makeIterations(long n);

    static double range(double left, double right) {
        return right - left;
    }

    T getCurrentX();

    void finish();

    //true if first >= second
    //false else
    static boolean compare(double first, double second) {
        return first - second >= compareEpsilon;
    }

    static boolean equal(double first, double second) {
        return Math.abs((first - second)) <= compareEpsilon;
    }

    void log();
}
