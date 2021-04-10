package info.metopt.approx.oneDimensional;

import info.metopt.approx.Method;

import java.util.Map;
import java.util.function.Function;

public interface OneDimensionalMethod extends Method<Double> {

    double evaluateFirst();

    double evaluateSecond();

    OneDimensionalMethod of(Function<Double, Double> function, double left, double right, double epsilon, boolean isLog);
}
