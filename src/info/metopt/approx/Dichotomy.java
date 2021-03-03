package info.metopt.approx;

public class Dichotomy extends AbstractMethod {

    private double beta;

    public Dichotomy(double left, double right, double epsilon) {
        this(left, right, epsilon, false);
    }

    public Dichotomy(double left, double right, double epsilon, boolean isLog) {
        super(left, right, epsilon, isLog);
        beta = epsilon;
        this.x1 = evaluateFirst();
        this.x2 = evaluateSecond();
    }

    @Override
    public double start() {
        makeIteration();
        return result;
    }

    @Override
    public void makeIteration() {
        x1 = evaluateFirst();
        x2 = evaluateSecond();
        double fx1 = evaluate(x1);
        double fx2 = evaluate(x2);
        evaluationsNumber += 2;
        if (Method.compare(fx2, fx1)) {
            right = x2;
        } else {
            left = x1;
        }

        double epsilonN = Method.range(left, right) / 2.0;
        if (Method.compare(epsilon, epsilonN)) {
            finish();
        } else {
            log();
            makeIteration();
        }
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
