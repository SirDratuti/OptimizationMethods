package info.metopt.approx.oneDimensional;

import info.metopt.approx.Method;

import java.util.function.Function;

public class Brent extends AbstractOneDimensionalMethod {

    private static final double EPSILON = 1e-6;
    private final double K = (3.0 - Math.sqrt(5.0)) / 2.0;
    private double x, v, w,fx,fv,fw;

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
        fx = fw = fv = function.apply(x);
        makeIterations();
        return result;
    }

    @Override
    public boolean makeIteration() {
        numberIteration++;
        double g;
        g = e;
        e = d;
        double u;
        if (!(fx == fw || fx == fv || fv == fw)) {
            u = x - (Math.pow((x - w), 2) * (fx - fv) - Math.pow((x - v), 2) * (fx - fw)) / (2 * ((x - w) * (fx - fv) - (x - v) * (fx - fw)));
            if (u >= left + EPSILON && u <= right - EPSILON && Math.abs(u - x) < g / 2) {
                d = Math.abs(u - x);
            } else {
                if (x < (right - left) / 2) {
                    u = left + K * (right - x);
                    d = right - x;
                } else {
                    u = right - K * (x - left);
                    d = x - left;
                }
            }
        } else {
            if (x < (right - left) / 2) {
                u = x + K * (right - x);
                d = right - x;

            } else {
                u = x - K * (x - left);
                d = x - left;
            }
            if (Math.abs(u - x) < EPSILON) {
                u = x + Math.signum(u - x) * EPSILON;
            }
        }
        double fu = function.apply(u);
        if (fu <= fx) {
            if (u >= x) {
                left = x;
            } else {
                right = x;
            }
            v = w;
            w = x;
            x = u;
            fv = fw;
            fw = fx;
            fx = fu;
        } else {
            if (u >= x) {
                right = u;
            } else {
                left = u;
            }
            if (fu <= fw || w == x) {
                v = w;
                w = u;
                fv = fw;
                fw = fu;
            } else {
                if (fu <= fv || v == x || v == w){
                    v = u;
                    fv = fu;
                }
            }
        }
        return(cycleCondition());
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
        return !Method.equal(a, b) && !Method.equal(b, c) && !Method.equal(a, c);
    }

    boolean cycleCondition() {
        return d > EPSILON;
    }

    @Override
    public OneDimensionalMethod of(Function<Double, Double> function, double left, double right, double epsilon, boolean isLog) {
        return new Brent(function, left, right, epsilon, isLog);
    }
}
