package info.metopt.approx;

public class GoldenRatio extends AbstractMethod {

    private static final double PHI2 = (Math.sqrt(5.0) - 1.0) / 2.0;
    private static final double PHI1 = 1 - PHI2;

    private double fx1;
    private double fx2;
    private double epsilonN;

    private double result;

    public GoldenRatio(double left, double right, double epsilon) {
        this.left = left;
        this.right = right;
        this.epsilon = epsilon;
    }

    @Override
    public double start() {
        this.x1 = evaluateFirst();
        this.x2 = evaluateSecond();
        this.fx1 = Method.evaluate(x1);
        this.fx2 = Method.evaluate(x2);
        epsilonN = Method.range(left, right) / 2.0;
        makeIteration();
        return result;
    }

    @Override
    public void makeIteration() {
        if (Method.compare(epsilon, epsilonN)) {
            finish();
        } else {
            if (Method.compare(fx2, fx1)) {
                right = x2;
                x2 = x1;
                fx2 = fx1;
                x1 = right - Method.range(left, right) * PHI2;
                fx1 = Method.evaluate(x1);
            } else {
                left = x1;
                x1 = x2;
                fx1 = fx2;
                x2 = left + Method.range(left, right) * PHI2;
                fx2 = Method.evaluate(x2);
            }
            epsilonN *= PHI2;
            makeIteration();
        }
    }

    @Override
    public double evaluateFirst() {
        return left + PHI1 * Method.range(left, right);
    }

    @Override
    public double evaluateSecond() {
        return left + PHI2 * Method.range(left, right);
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