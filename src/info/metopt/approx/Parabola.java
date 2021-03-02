package info.metopt.approx;

public class Parabola extends AbstractMethod {

    private double fx1;
    private double fx2;
    private double fx3;
    private double x3;
    private double currentX;
    double prefX;
    double fCurrentX;
    int iterationNumber = 0;

    private double result;

    public Parabola(double left, double right, double epsilon) {
        this.left = left;
        this.right = right;
        this.epsilon = epsilon;
    }

    @Override
    public double start() {
        this.x1 = left;
        this.x3 = right;
        this.x2 = (right + left) / 2;
        fx1 = Method.evaluate(x1);
        fx2 = Method.evaluate(x2);
        fx3 = Method.evaluate(x3);
        makeIteration();
        return result;
    }

    public double evaluate_a0(double x1, double x2, double x3, double fx1, double fx2, double fx3) {
        return fx1;
    }

    public double evaluate_a1(double x1, double x2, double x3, double fx1, double fx2, double fx3) {
        return (fx2 - fx1) / (x2 - x1);
    }

    public double evaluate_a2(double x1, double x2, double x3, double fx1, double fx2, double fx3) {
        return 1 / (x3 - x2) * ((fx3 - fx1) / (x3 - x1) - (fx2 - fx1) / (x2 - x1));
    }

    @Override
    public void makeIteration() {
        double a0 = evaluate_a0(x1, x2, x3, fx1, fx2, fx3);
        double a1 = evaluate_a1(x1, x2, x3, fx1, fx2, fx3);
        double a2 = evaluate_a2(x1, x2, x3, fx1, fx2, fx3);
        currentX = (x1 + x2 - (a1 / a2)) / 2;
        if (iterationNumber > 0 && Math.abs(currentX - prefX) < epsilon) {
            finish();
            return;
        }
        fCurrentX = Method.evaluate(currentX);
        if (x1 < currentX && currentX < x2 && x2 < x3 && fCurrentX >= fx2) {
            x1 = currentX;
            fx1 = fCurrentX;
        } else if (x1 < currentX && currentX < x2 && x2 < x3 && fCurrentX < fx2) {
            x3 = x2;
            fx3 = fx2;
            x2 = currentX;
            fx2 = fCurrentX;
        } else if (x1 < x2 && x2 < currentX && currentX < x3 && fCurrentX <= fx2) {
            x1 = x2;
            fx1 = fx2;
            x2 = currentX;
            fx2 = fCurrentX;
        } else if (x1 < x2 && x2 < currentX && currentX < x3 && fCurrentX > fx2) {
            x3 = currentX;
            fx3 = fCurrentX;
        }
        prefX = currentX;
        iterationNumber++;
        makeIteration();
    }

    @Override
    public double evaluateFirst() {
        return 0;
    }

    @Override
    public double evaluateSecond() {
        return 0;
    }

    @Override
    public double evaluateLeft(double value) {
        return 0;
    }

    @Override
    public double evaluateRight(double value) {
        return 0;
    }

    @Override
    public void finish() {
        result = currentX;
    }

    @Override
    public void log() {
        System.out.println(x1 + " " + x2 + " " + x3);
    }
}
