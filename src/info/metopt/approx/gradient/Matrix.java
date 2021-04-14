package info.metopt.approx.gradient;
import java.util.List;

public interface Matrix {
    List<List<Double>> toList();

    List<Double> getRow(final int index);

    Double get(final int indexRow, final int indexColumn);

    int getN();

    int getM();

    Vector vectorMultiply(final Vector vector);
}
