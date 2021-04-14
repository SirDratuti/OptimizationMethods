package info.metopt.approx.gradient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Vector {
    private List<Double> vector;

    public Vector(final List<Double> vector) {
        this.vector = vector;
    }

    public Vector(int n) {
        vector = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            vector.add(0.0);
        }
    }

    public Double getX(final int index) {
        return vector.get(index);
    }

    public List<Double> toList() {
        return vector;
    }

    public int size() {
        return vector.size();
    }

    public static Double scalarMultiply (Vector vector1, Vector vector2) {
        if (vector1.size() != vector2.size()) {
            throw new IllegalArgumentException("vectors should be same size");
        }
        double result = 0.0;
        for (int i = 0; i < vector1.size(); ++i) {
            result += vector1.getX(i) * vector2.getX(i);
        }
        return result;
    }

    public Double scalarMultiply (Vector vector) {
        return scalarMultiply(this, vector);
    }

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

    public Vector sum(Vector vector) {
        return sum(this, vector);
    }

    public double norm() {
        return Math.sqrt(this.scalarMultiply(this));
    }

    public static Vector numberMultiply(Vector vector, double coefficient) {
        return new Vector(vector.toList().stream()
                .map(a -> a*coefficient)
                .collect(Collectors.toList()));
    }

    public Vector numberMultiply(double coefficient) {
        return numberMultiply(this, coefficient);
    }

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
