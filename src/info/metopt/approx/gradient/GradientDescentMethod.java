package info.metopt.approx.gradient;

import info.metopt.approx.Method;

public class GradientDescentMethod extends AbstractGradientMethod {

    double fx;
    double startAlpha = 100;
    private int maxIterationNumber;

    public GradientDescentMethod(Matrix A, Vector b, double c, Vector startX, double epsilon, boolean isLog) {
        super(A, b, c, startX, epsilon, isLog);
    }

    public GradientDescentMethod(Matrix A, Vector b, double c, Vector startX, double epsilon) {
        super(A, b, c, startX, epsilon, false);
    }

    public GradientDescentMethod(Matrix A, Vector b, double c, Vector startX, double epsilon, double startAlpha) {
        this(A, b, c, startX, epsilon, startAlpha, 5000, false);
    }

    public GradientDescentMethod(Matrix A, Vector b, double c, Vector startX, double epsilon, double startAlpha, int maxIterationNumber, boolean isLog) {
        super(A, b, c, startX, epsilon, isLog);
        this.startAlpha = startAlpha;
        this.maxIterationNumber = maxIterationNumber;
    }

    @Override
    public Vector start() {
        x = startX;
        alpha = startAlpha;
        fx = evaluate(x);
        makeIterations();
        return x;
    }

    @Override
    public boolean makeIteration() {
        Vector gradientVector = evaluateGradient(x);
        if (Method.compare(epsilon, gradientVector.norm()) || numberIteration >= maxIterationNumber) {
            return false;
        }
        if (numberIteration % 100 == 0) {
            alpha = startAlpha;
        }
        Vector currentP = x.sum(gradientVector.numberMultiply(-alpha/gradientVector.norm()));
        double fy = evaluate(currentP);
        if (!(fy < fx)) {
            while (fy >= fx) {
                alpha /= 2.0;
                currentP = x.sum(gradientVector.numberMultiply(-alpha / gradientVector.norm()));
                fy = evaluate(currentP);
            }
        }
        x = currentP;
        fx = fy;
        return true;
    }
}
