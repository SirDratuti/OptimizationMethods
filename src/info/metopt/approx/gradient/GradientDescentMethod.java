package info.metopt.approx.gradient;

import info.metopt.approx.Method;

public class GradientDescentMethod extends AbstractGradientMethod {

    double fx;
    double startAlpha = 100;

    public GradientDescentMethod(Matrix A, Vector b, double c, Vector startX, double epsilon, boolean isLog) {
        super(A, b, c, startX, epsilon, isLog);
    }

    public GradientDescentMethod(Matrix A, Vector b, double c, Vector startX, double epsilon) {
        super(A, b, c, startX, epsilon, false);
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
        if (Method.compare(epsilon, gradientVector.norm())) {
            return false;
        }
        if (numberIteration % 100 == 0) {
            alpha = startAlpha;
        }
        Vector y = x.sum(gradientVector.numberMultiply(-alpha/gradientVector.norm()));
        double fy = evaluate(y);
        if (!(fy < fx)) {
            while (fy >= fx) {
                alpha /= 2.0;
                y = x.sum(gradientVector.numberMultiply(-alpha / gradientVector.norm()));
                fy = evaluate(y);
            }
        }
        x = y;
        fx = fy;
        return true;
    }
}
