package info.metopt.approx.oneDimensional;

import java.util.function.Function;

public class OneDimensionalTest {
    public static void main(String[] args) {
        Function<Double, Double> function = (x -> x * x + Math.exp(-0.35 * x));
        Dichotomy dichotomy = new Dichotomy(function, -2.0, 3.0, 1e-6);
        GoldenRatio goldenRatio = new GoldenRatio(function, -2.0, 3.0, 1e-6);
        Fibonacci fibonacci = new Fibonacci(function, -2.0, 3.0, 1e-6);
        Parabola parabola = new Parabola(function, -2.0, 3.0, 1e-6);
        Brent brent = new Brent(function, -2.0, 3.0, 1e-6);

        System.out.println(dichotomy.start());
        System.out.println(dichotomy.getNumberIteration());
        System.out.println(goldenRatio.start());
        System.out.println(goldenRatio.getNumberIteration());
        System.out.println(fibonacci.start());
        System.out.println(fibonacci.getNumberIteration());
        System.out.println(parabola.start());
        System.out.println(parabola.getNumberIteration());
        System.out.println(brent.start());
        System.out.println(brent.getNumberIteration());
    }
}
