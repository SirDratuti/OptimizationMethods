package info.metopt.approx.gradient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Algebraic vector of {@link Double}
 */
public class Vector {
    private List<Double> vector;

    /**
     * Simple constructor.
     *
     * @param vector {@link List<Double>} list values.
     */
    public Vector(final List<Double> vector) {
        this.vector = vector;
    }

    /**
     * Constructor of a vector of length <var>n</var> filled with zeros
     *
     * @param n length of vector.
     */
    public Vector(int n) {
        vector = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            vector.add(0.0);
        }
    }

    /**
     * Return value with <var>index</var>
     *
     * @param index index of element.
     * @return value with <var>index</var>
     */
    public Double getX(final int index) {
        return vector.get(index);
    }

    /**
     * Return list of values.
     *
     * @return {@link List<Double>} of values.
     */
    public List<Double> toList() {
        return vector;
    }

    /**
     * @return length of vector.
     */
    public int size() {
        return vector.size();
    }

    /**
     * Calculates the scalar multiply of two {@link Vector} vectors.
     *
     * @param vector1 {@link Vector} that multiply.
     * @param vector2 {@link Vector} that multiply.
     * @return {@link Double} scalar multiply.
     */
    public static Double scalarMultiply(Vector vector1, Vector vector2) {
        if (vector1.size() != vector2.size()) {
            throw new IllegalArgumentException("vectors should be same size");
        }
        double result = 0.0;
        for (int i = 0; i < vector1.size(); ++i) {
            result += vector1.getX(i) * vector2.getX(i);
        }
        return result;
    }

    /**
     * Calculates the scalar multiply of vectors.
     *
     * @param vector {@link Vector} that multiply.
     * @return {@link Double} scalar multiply.
     */
    public Double scalarMultiply(Vector vector) {
        return scalarMultiply(this, vector);
    }

    /**
     * Sums two vectors.
     *
     * @param vector1 first {@link Vector}.
     * @param vector2 second {@link Vector}.
     * @return new {@link Vector} result of sum.
     */
    public static Vector sum(Vector vector1, Vector vector2) {
        if (vector1.size() != vector2.size()) {
            throw new IllegalArgumentException("vectors should be same size");
        }
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < vector1.size(); ++i) {
            result.add(vector1.getX(i) + vector2.getX(i));
        }
        return new Vector(result);
    }


    /**
     * Sums vectors.
     *
     * @param vector {@link Vector}.
     * @return new {@link Vector} result of sum.
     */
    public Vector sum(Vector vector) {
        return sum(this, vector);
    }

    /**
     * Calculates the norm of the vector.
     *
     * @return the norm of the vector.
     */
    public double norm() {
        return Math.sqrt(this.scalarMultiply(this));
    }

    /**
     * multiplies a <var>vector</var> by a number according to algebraic rules
     *
     * @param vector      that multiply.
     * @param coefficient by multiply.
     * @return new {@link Vector} result of multiplying.
     */
    public static Vector numberMultiply(Vector vector, double coefficient) {
        return new Vector(vector.toList().stream()
                .map(a -> a * coefficient)
                .collect(Collectors.toList()));
    }

    /**
     * multiplies a vector by a number according to algebraic rules
     *
     * @param coefficient by multiply.
     * @return new {@link Vector} result of multiplying.
     */
    public Vector numberMultiply(double coefficient) {
        return numberMultiply(this, coefficient);
    }

    /**
     * Generates a vector of <var>n</var> random values from -<var>maxValue</var> to <var>maxValue</var>
     *
     * @param n        length of vector.
     * @param maxValue maximum absolute value of values.
     * @return generated vector.
     */
    public static Vector getRandomVector(int n, double maxValue) {
        List<Double> list = new ArrayList<>(n);
        Random random = new Random();
        for (int i = 0; i < n; ++i) {
            list.add(-maxValue + 2 * maxValue * random.nextDouble());
        }
        return new Vector(list);
    }

    @Override
    public String toString() {
        return "Vector{" +
                vector.stream().map(String::valueOf).collect(Collectors.joining(", ")) +
                '}';
    }
}
