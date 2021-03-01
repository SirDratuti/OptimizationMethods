package info.metopt.approx;

public class Dichotomy extends AbstractMethod {

    private double beta;

    private double result;

    public Dichotomy(double left, double right, double epsilon) {
        this.left = left;
        this.right = right;
        this.epsilon = epsilon;
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
        double fx1 = Method.evaluate(x1);
        double fx2 = Method.evaluate(x2);
        if (Method.compare(fx2, fx1)) {
            right = evaluateRight(x2);
        } else {
            left = evaluateLeft(x1);
        }
        log();

        double epsilonN = Method.range(left, right) / 2.0;
        if (Method.compare(epsilon, epsilonN)) {
            finish();
        } else {
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
    public double evaluateLeft(double value) {
        return value;
    }

    @Override
    public double evaluateRight(double value) {
        return value;
    }

    @Override
    public void finish() {
        result = (left + right) / 2.0;
    }

    @Override
    public void log() {
        System.out.println(left + " " + right);
    }
}
