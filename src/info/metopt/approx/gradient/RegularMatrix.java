package info.metopt.approx.gradient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegularMatrix implements Matrix {
    private final List<List<Double>> matrix;

    public RegularMatrix(final List<List<Double>> matrix) {
        this.matrix = List.copyOf(matrix);
    }

    public List<List<Double>> toList() {
        return matrix;
    }

    public List<Double> getRow(final int index) {
        return matrix.get(index);
    }

    public Double get(final int indexRow, final int indexColumn) {
        return matrix.get(indexRow).get(indexColumn);
    }

    public int getN() {
        return matrix.size();
    }

    public int getM() {
        return  matrix.size() > 0 ? matrix.get(0).size() : 0;
    }

    public static Vector vectorMultiply(final RegularMatrix matrix, final Vector vector) {
        if (matrix.getM() != vector.size()) {
            throw new IllegalArgumentException("sizes not same");
        }
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < matrix.getN(); i++) {
                result.add(vector.scalarMultiply(new Vector(matrix.getRow(i))));
        }
        return new Vector(result);
    }

    public Vector vectorMultiply(final Vector vector) {
        return vectorMultiply(this, vector);
    }

    @Override
    public String toString() {
        return "RegularMatrix{" + System.lineSeparator() +
                toList().stream().map(list -> list.stream().map(String::valueOf).collect(Collectors.joining(" "))).collect(Collectors.joining(System.lineSeparator()))
                + System.lineSeparator() + '}';
    }
}
