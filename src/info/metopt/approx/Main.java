package info.metopt.approx;

import info.metopt.approx.gradient.*;
import info.metopt.approx.oneDimensional.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        /*
         * condition factor
         */
        double k = 50;

        int n = 10000;

        Matrix matrixA = new RegularMatrix(List.of(List.of(128.0, 0.0), List.of(0.0, 2.0)));

        Vector b = new Vector(List.of(2.0, 4.0));

        double c = 17;

        Vector startX = new Vector(List.of(10.0, 10.0));

        double epsilon = 1e-4;

        double startAlpha = 5;


        GradientDescentMethod gradientDescentMethod = new GradientDescentMethod(matrixA, b, c, startX, epsilon, startAlpha, 5000, true);
        SteepestDescentMethod steepestDescentMethod = new SteepestDescentMethod(matrixA, b, c, startX, epsilon, new Brent(), startAlpha, 1000, true);
        ConjugateGradientMethod conjugateGradientMethod = new ConjugateGradientMethod(matrixA, b, c, startX, epsilon, true);
        gradientDescentMethod.start();
        steepestDescentMethod.start();
        conjugateGradientMethod.start();


        matrixA = new DiagonalMatrix(n, k);

        b = Vector.getRandomVector(n, 6);

        c = 0;

        startX = Vector.getRandomVector(n, 4);

        gradientDescentMethod = new GradientDescentMethod(matrixA, b, c, startX, epsilon, startAlpha, 5000, true);
        steepestDescentMethod = new SteepestDescentMethod(matrixA, b, c, startX, epsilon, new Dichotomy(), startAlpha, 1000, true);
        conjugateGradientMethod = new ConjugateGradientMethod(matrixA, b, c, startX, epsilon, true);
        gradientDescentMethod.start();
        steepestDescentMethod.start();
        conjugateGradientMethod.start();


        /*
         * test for steepest method and different one dimensional methods
         */
        List<OneDimensionalMethod> oneDimensionalMethods = List.of(new Dichotomy(), new GoldenRatio(), new Fibonacci(), new Parabola(), new Brent());
        for (OneDimensionalMethod oneDimensionalMethod : oneDimensionalMethods) {
            steepestDescentMethod = new SteepestDescentMethod(matrixA, b, c, startX, epsilon, oneDimensionalMethod, startAlpha, 1000, true);
            steepestDescentMethod.start();
        }

    }
}
