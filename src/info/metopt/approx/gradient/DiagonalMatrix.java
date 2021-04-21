package info.metopt.approx.gradient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Implementation of the matrix interface for a diagonal matrix. Only the diagonal is stored.
 */
public class DiagonalMatrix implements Matrix {

    List<Double> diagonal;

    /**
     * Simple constructor.
     *
     * @param diagonal {@link List<Double>} values of diagonal.
     */
    public DiagonalMatrix(List<Double> diagonal) {
        this.diagonal = diagonal;
    }

    public DiagonalMatrix(int n, double k) {
        diagonal = new ArrayList<>(n);
        diagonal.add(1.0);
        Random random = new Random();
        for (int i = 1; i < n - 1; ++i) {
            diagonal.add(1.0 + (k - 1.0) * random.nextDouble());
        }
        diagonal.add(k);
        Collections.sort(diagonal);
    }

    @Override
    public List<List<Double>> toList() {
        List<List<Double>> list = new ArrayList<>(getN());
        for (int indexRow = 0; indexRow < getN(); ++indexRow) {
            list.add(getRow(indexRow));
        }
        return list;
    }

    @Override
    public List<Double> getRow(int index) {
        List<Double> row = new ArrayList<>(getN());
        for (int i = 0; i < getN(); ++i) {
            row.add(get(index, i));
        }
        return row;
    }

    @Override
    public Double get(int indexRow, int indexColumn) {
        return indexRow == indexColumn ? diagonal.get(indexRow) : 0.0;
    }

    @Override
    public int getN() {
        return diagonal.size();
    }

    @Override
    public int getM() {
        return diagonal.size();
    }

    @Override
    public Vector vectorMultiply(Vector vector) {
        List<Double> list = new ArrayList<>(getN());
        for (int index = 0; index < getN(); ++index) {
            list.add(diagonal.get(index) * vector.getX(index));
        }
        return new Vector(list);
    }

    public List<Double> getDiagonal() {
        return diagonal;
    }

    @Override
    public String toString() {
        return "DiagonalMatrix{" + System.lineSeparator() +
                toList().stream().map(list -> list.stream().map(String::valueOf).collect(Collectors.joining(" "))).collect(Collectors.joining(System.lineSeparator()))
                + System.lineSeparator() + '}';
    }
}