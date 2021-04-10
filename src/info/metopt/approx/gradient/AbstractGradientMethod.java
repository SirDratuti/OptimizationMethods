package info.metopt.approx.gradient;

import info.metopt.approx.AbstractMethod;

public abstract class AbstractGradientMethod extends AbstractMethod<Vector> implements GradientMethod {

    double alpha;
    Vector startX;

    public AbstractGradientMethod(Matrix A, Vector b, double c, Vector startX, double epsilon, boolean isLog) {
        super(argument -> 0.5 * (A.vectorMultiply(argument).scalarMultiply(argument)) + b.scalarMultiply(argument) + c, epsilon, isLog);
        this.A = A;
        this.b = b;
        this.c = c;
        this.startX = startX;
    }

    Vector x;
    Matrix A;
    Vector b;
    double c;

    @Override
    public Vector getCurrentX() {
        return x;
    }

    @Override
    public Vector evaluateGradient(Vector argument) {
        return A.vectorMultiply(argument).sum(b);
    }

    @Override
    public void printLog() {
        super.printLog();
        System.out.println("Количество итераций: " + numberIteration + System.lineSeparator());
    }
}
