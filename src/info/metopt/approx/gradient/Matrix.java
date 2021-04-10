package info.metopt.approx.gradient;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
    private final List<List<Double>> matrix;

    public Matrix(final List<List<Double>> matrix) {
        this.matrix = List.copyOf(matrix);//TODO
    }

    public List<List<Double>> toList() {
        return matrix;//TODO
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
        return matrix.get(0).size();//TODO
    }

    public static Vector vectorMultiply(final Matrix matrix, final Vector vector) {
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
}
