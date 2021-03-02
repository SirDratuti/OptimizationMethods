package info.metopt.approx;

import static java.lang.Math.sqrt;

public class Brent extends AbstractMethod {

    private double K = (3.0 - sqrt(5)) / 2.0;
    private double x, v, w;

    private double d, e;

    public Brent(double left, double right, double epsilon) {
        this(left, right, epsilon, false);
    }

    public Brent(double left, double right, double epsilon, boolean isLog) {
        super(left, right, epsilon, isLog);
    }

    @Override
    public double start() {
        x = left + K * (Method.range(left, right));
        v = x;
        w = x;
        d = Method.range(left, right);
        e = d;
        log();
        makeIteration();
        return result;
    }

    @Override
    public void makeIteration() {
        double g, tol;
        while (true) {
            g = e;
            e = d;
            tol = epsilon * Math.abs(x) + epsilon / 10.0;
            if (Math.abs(x - (left + right) / 2.0) + Method.range(left, right) / 2.0 <= 2.0 * tol) {
                finish();
                break;
            }

            boolean skip = false;
            double u = left;


            if (notEquals(x, w, v) && notEquals(Method.evaluate(x), Method.evaluate(w), Method.evaluate(v))) {
                Parabola parabola = new Parabola(left, right, epsilon);
                double a0 = parabola.evaluate_a0(x, w, v, Method.evaluate(x), Method.evaluate(w), Method.evaluate(v));
                double a1 = parabola.evaluate_a1(x, w, v, Method.evaluate(x), Method.evaluate(w), Method.evaluate(v));
                double a2 = parabola.evaluate_a2(x, w, v, Method.evaluate(x), Method.evaluate(w), Method.evaluate(v));
                u = (x + w - a1 / a2) / 2.0;
                if (Method.compare(u, left) && Method.compare(right, u) && Math.abs(u - x) < g / 2.0) {
                    u = x - Math.signum(x - (left + right) / 2.0) * tol;
                    skip = true;
                }
            }

            if (!skip) {
                if (Method.compare((left + right) / 2.0, x)) {
                    u = x + K * (right - x);
                    e = left - x;
                } else {
                    u = x - K * (x - left);
                    e = x - right;
                }
            }

            if (Method.compare(tol, Math.abs(u - x))) {
                u = x + Math.signum(u - x) * tol;
            }
            d = Math.abs(u - x);
            double fu = Method.evaluate(u);
            double fx = Method.evaluate(x);
            if (Method.compare(fx, fu)) {
                if (Method.compare(u, x)) {
                    left = x;
                } else {
                    right = x;
                }
                v = w;
                w = x;
                x = u;

            } else {
                if (Method.compare(u, x)) {
                    right = u;
                } else {
                    left = u;
                }
                if (Method.compare(Method.evaluate(w), Method.evaluate(u))) {
                    v = w;
                    w = u;
                } else if (Method.compare(Method.evaluate(v), Method.evaluate(u)) || v == x || v == w) {
                    v = u;
                }
            }
            log();
        }
        finish();
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
    public double getCurrentX() {
        return (left + right) / 2.0;
    }


    private boolean notEquals(double a, double b, double c) {
        return (a != b) && (a != c) && (b != c);
    }

}
