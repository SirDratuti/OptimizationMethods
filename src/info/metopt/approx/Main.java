package info.metopt.approx;

import info.metopt.approx.gradient.*;
import info.metopt.approx.oneDimensional.*;

import java.util.List;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
//        Function<Double, Double> function1 = x -> x*x + Math.exp(-0.35 * x);
//        Method<Double> method = new Brent(function1, -2, 3, 1e-5, true);
//        method.start();

        Matrix A = new Matrix(List.of(List.of(1.0, 5.0), List.of(5.0, 26.0)));

        Vector b = new Vector(List.of(0.0, 0.0));

        double c = 13;

        Vector startX = new Vector(List.of(10.0, -21.0));

        double epsilon = 1e-5;

        GradientDescentMethod gradientDescentMethod = new GradientDescentMethod(A, b, c, startX, epsilon, true);
        SteepestDescentMethod steepestDescentMethod = new SteepestDescentMethod(A, b, c, startX, epsilon, new Fibonacci(), true);
        ConjugateGradientMethod conjugateGradientMethod = new ConjugateGradientMethod(A, b, c, startX, epsilon,true);
        System.out.println(gradientDescentMethod.start());
        System.out.println(steepestDescentMethod.start());
        System.out.println(conjugateGradientMethod.start());
    }
}
