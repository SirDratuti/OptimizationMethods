package info.metopt.approx;

public interface Method {


    double evaluate(double argument);

    void makeIteration();

    double range(double left, double right);

    double evaluateLeft();

    double evaluateRight();

    boolean checkEpsilon();

    boolean compare(double first, double second, double epsilon);

}
