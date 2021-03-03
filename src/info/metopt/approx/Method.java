package info.metopt.approx;

public interface Method {

    double compareEpsilon = 1e-11;

    double evaluate(double argument);

    double start();

    boolean makeIteration();

    void makeIterations();

    void makeIterations(long n);

    static double range(double left, double right){
        return right - left;
    }

    double evaluateFirst();

    double evaluateSecond();

    double getCurrentX();

    void finish();

    //true if first >= second
    //false else
    static boolean compare(double first, double second){
        return first - second >= compareEpsilon;
    }


    void log();

}
