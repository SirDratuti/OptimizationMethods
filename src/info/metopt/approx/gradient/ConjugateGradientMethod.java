package info.metopt.approx.gradient;

import info.metopt.approx.Method;

public class ConjugateGradientMethod extends AbstractGradientMethod {
    private Vector vectorP;
    private Vector gradientVector;
    double gradientNormSquare;

    public ConjugateGradientMethod(Matrix A, Vector b, double c, Vector startX, double epsilon, boolean isLog) {
        super(A, b, c, startX, epsilon, isLog);
    }

    public ConjugateGradientMethod(Matrix A, Vector b, double c, Vector startX, double epsilon) {
        super(A, b, c, startX, epsilon, false);
    }

    @Override
    public Vector start() {
        x = startX;
        gradientVector = evaluateGradient(x);
        gradientNormSquare = gradientVector.scalarMultiply(gradientVector);
        vectorP = evaluateGradient(x).numberMultiply(-1);
        makeIterations();
        return x;
    }

    @Override
    public boolean makeIteration() {
        if (Method.compare(epsilon, gradientVector.norm())) {
            return false;
        }
        Vector ap = A.vectorMultiply(vectorP);
        alpha = gradientNormSquare / ap.scalarMultiply(vectorP);
        Vector nextX = x.sum(vectorP.numberMultiply(alpha));
        Vector nextGradientVector = gradientVector.sum(ap.numberMultiply(alpha));
        double nextGradientNormSquare = nextGradientVector.scalarMultiply(nextGradientVector);
        double beta;
        if (numberIteration % x.size() == 0) {
            beta = 0;
        } else {
            beta = nextGradientNormSquare / gradientNormSquare;
        }
        vectorP = nextGradientVector.numberMultiply(-1).sum(vectorP.numberMultiply(beta));
        gradientVector = nextGradientVector;
        gradientNormSquare = nextGradientNormSquare;
        x = nextX;
        //System.out.println(x);
        return true;
    }

}
