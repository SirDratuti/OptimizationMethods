package info.metopt.approx.gradient;

import info.metopt.approx.Method;

public interface GradientMethod extends Method<Vector> {
    Vector evaluateGradient(Vector argument);
}
