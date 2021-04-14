package info.metopt.approx;

import info.metopt.approx.gradient.*;
import info.metopt.approx.oneDimensional.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Matrix A = new DiagonalMatrix(10_000, 10);

        Vector b = Vector.getRandomVector(10_000, 6);

        double c = 0;

        Vector startX = Vector.getRandomVector(10_000, 4);

        double epsilon = 1e-5;

        GradientDescentMethod gradientDescentMethod = new GradientDescentMethod(A, b, c, startX, epsilon, 2/10.0, 5000, true);
        SteepestDescentMethod steepestDescentMethod = new SteepestDescentMethod(A, b, c, startX, epsilon, new Dichotomy(), 2/10.0, 1000, true);
        ConjugateGradientMethod conjugateGradientMethod = new ConjugateGradientMethod(A, b, c, startX, epsilon,true);
        gradientDescentMethod.start();
        steepestDescentMethod.start();
        conjugateGradientMethod.start();
    }
}
