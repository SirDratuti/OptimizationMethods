package info.metopt.approx.gradient;

import java.util.List;

/**
 * Algebraic two-dimensional matrix of {@link Double} interface
 */
public interface Matrix {
    /**
     * Returns a two-dimensional sheet of values
     *
     * @return a two-dimensional sheet of values
     */
    List<List<Double>> toList();

    /**
     * Returns a matrix row.
     *
     * @param index of row.
     * @return a matrix row.
     */
    List<Double> getRow(final int index);

    /**
     * Returns the element of the matrix with <var>indexRow</var> and <var>indexColumn</var>.
     *
     * @param indexRow    index of row.
     * @param indexColumn index of row.
     * @return return the element of the matrix.
     */
    Double get(final int indexRow, final int indexColumn);

    /**
     * Returns the number of rows in the matrix.
     *
     * @return the number of rows in the matrix.
     */
    int getN();

    /**
     * Returns the number of columns in the matrix.
     *
     * @return the number of columns in the matrix.
     */
    int getM();

    /**
     * Matrix-vector multiplication.
     *
     * @param vector {@link Vector} by which we multiply
     * @return new {@link Vector} multiplication result
     */
    Vector vectorMultiply(final Vector vector);
}
