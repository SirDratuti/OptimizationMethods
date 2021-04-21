package info.metopt.approx.gradient;

import info.metopt.approx.AbstractMethod;

/**
 * Abstract class for {@link GradientMethod}.
 */
public abstract class AbstractGradientMethod extends AbstractMethod<Vector> implements GradientMethod {

    protected double alpha;
    protected Vector startX;
    protected Vector x;
    protected Matrix A;
    protected Vector b;
    protected double c;

    /**
     * Constructor with basic values.
     *
     * @param A       {@link Matrix} of coefficients of quadratic form. Element with row index <var>i</var> and column index <var>j</var> is the coefficient of x_i*x_j.
     * @param b       {@link Vector} of coefficients of quadratic form. Element with row index <var>i</var> is the coefficient of x_i.
     * @param c       constant of quadratic form.
     * @param startX  the point {@link Vector} from which we start looking for the minimum. The closer the point is to the minimum, the fewer iterations will be needed.
     * @param epsilon precision value.
     * @param isLog   log flag.
     */
    public AbstractGradientMethod(Matrix A, Vector b, double c, Vector startX, double epsilon, boolean isLog) {
        super(argument -> 0.5 * (A.vectorMultiply(argument).scalarMultiply(argument)) + b.scalarMultiply(argument) + c, epsilon, isLog);
        this.A = A;
        this.b = b;
        this.c = c;
        this.startX = startX;
    }

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
