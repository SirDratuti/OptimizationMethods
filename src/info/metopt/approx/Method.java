package info.metopt.approx;

public interface Method {


    static double evaluate(double argument) {
        return (argument * argument) + Math.exp(-0.35 * argument);
    }

    double start();

    void makeIteration();

    double range(double left, double right);

    double evaluateFirst();

    double evaluateSecond();

    double evaluateLeft(double value);

    double evaluateRight(double value);

    void finish();

    //true if first >= second
    //false else
    boolean compare(double first, double second);

    void log();

}
