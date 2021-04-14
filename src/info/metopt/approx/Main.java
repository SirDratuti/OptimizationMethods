package info.metopt.approx;

import info.metopt.approx.gradient.*;
import info.metopt.approx.oneDimensional.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        /*
         * condition factor
         */
        double k = 10;

        Matrix matrixA = new RegularMatrix(List.of(List.of(128.0, 126.0), List.of(126.0, 128.0)));

        Vector b = new Vector(List.of(-10.0, 30.0));

        double c = 13;

        Vector startX = new Vector(List.of(-10.0, 3.0));

        double epsilon = 1e-5;

        double startAlpha = 2 / k;


        GradientDescentMethod gradientDescentMethod = new GradientDescentMethod(matrixA, b, c, startX, epsilon, startAlpha, 5000, true);
        SteepestDescentMethod steepestDescentMethod = new SteepestDescentMethod(matrixA, b, c, startX, epsilon, new Dichotomy(), startAlpha, 1000, true);
        ConjugateGradientMethod conjugateGradientMethod = new ConjugateGradientMethod(matrixA, b, c, startX, epsilon,true);
        gradientDescentMethod.start();
        steepestDescentMethod.start();
        conjugateGradientMethod.start();

        matrixA = new DiagonalMatrix(10_000, k);

        b = Vector.getRandomVector(10_000, 6);

        c = 0;

        startX = Vector.getRandomVector(10_000, 4);

        gradientDescentMethod = new GradientDescentMethod(matrixA, b, c, startX, epsilon, startAlpha, 5000, true);
        steepestDescentMethod = new SteepestDescentMethod(matrixA, b, c, startX, epsilon, new Dichotomy(), startAlpha, 1000, true);
        conjugateGradientMethod = new ConjugateGradientMethod(matrixA, b, c, startX, epsilon,true);
        gradientDescentMethod.start();
        steepestDescentMethod.start();
        conjugateGradientMethod.start();
    }
}
