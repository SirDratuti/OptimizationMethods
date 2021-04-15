package info.metopt.approx.oneDimensional;

import info.metopt.approx.Method;

import java.util.function.Function;

public class Brent extends AbstractOneDimensionalMethod {

    private double K = 1 - GOLDEN_PHI;
    private double x, v, w;

    private double d, e;

    public Brent(Function<Double, Double> function, double left, double right, double epsilon) {
        this(function, left, right, epsilon, false);
    }

    public Brent(Function<Double, Double> function, double left, double right, double epsilon, boolean isLog) {
        super(function, left, right, epsilon, isLog);
    }

    public Brent() {
        super();
    }

    @Override
    public Double start() {
        x = left + K * (Method.range(left, right));
        v = x;
        w = x;
        d = Method.range(left, right);
        e = d;
        makeIterations();
        return result;
    }

    @Override
    public boolean makeIteration() {
        double g = e;
        e = d;
        double tol = epsilon * Math.abs(x) + epsilon / 10.0;
        if (Math.abs(x - (left + right) / 2.0) + Method.range(left, right) / 2.0 <= 2.0 * tol) {
            return false;
        }
        boolean skip = false;
        double u = left;
        double fx = evaluate(x);
        double fw = evaluate(w);
        double fv = evaluate(v);
        if (notEquals(x, w, v) && notEquals(fx, fw, fv)) {
            double a0 = Parabola.evaluate_a0(x, w, v, fx, fw, fv);
            double a1 = Parabola.evaluate_a1(x, w, v, fx, fw, fv);
            double a2 = Parabola.evaluate_a2(x, w, v, fx, fw, fv);
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
        double fu = evaluate(u);
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
            if (Method.compare(fw, fu)) {
                v = w;
                w = u;
            } else if (v == x || v == w) {
                v = u;
            }
        }
        return true;
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
    public Double getCurrentX() {
        return (left + right) / 2.0;
    }


    private boolean notEquals(double a, double b, double c) {
        return (a != b) && (a != c) && (b != c);
    }

    @Override
    public OneDimensionalMethod of(Function<Double, Double> function, double left, double right, double epsilon, boolean isLog) {
        return new Brent(function, left, right, epsilon, isLog);
    }
}