package info.metopt.approx.oneDimensional;

import info.metopt.approx.AbstractMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Abstract class for {@link OneDimensionalMethod}.
 */
public abstract class AbstractOneDimensionalMethod extends AbstractMethod<Double> implements OneDimensionalMethod {
    public static final double GOLDEN_PHI = (Math.sqrt(5.0) - 1.0) / 2.0;

    protected double left;
    protected double right;
    protected double x1;
    protected double x2;
    private List<Double> rights;
    private List<Double> lefts;
    private List<Double> ratios;
    private List<Double> lens;

    public AbstractOneDimensionalMethod() {
    }

    public AbstractOneDimensionalMethod(Function<Double, Double> function, double left, double right, double epsilon, boolean isLog) {
        super(function, epsilon, isLog);
        this.left = left;
        this.right = right;
        this.epsilon = epsilon;
        this.isLog = isLog;
        rights = new ArrayList<>();
        lefts = new ArrayList<>();
        ratios = new ArrayList<>();
        lens = new ArrayList<>();
    }

    /**
     * Simple constructor. <var>islog</var> default is false.
     *
     * @param function optimized function from {@link Double} to {@link Double}.
     * @param left     left border of the segment.
     * @param right    right border of the segment.
     * @param epsilon  precision value.
     */
    public AbstractOneDimensionalMethod(Function<Double, Double> function, double left, double right, double epsilon) {
        this(function, left, right, epsilon, false);
    }

    public List<Double> getRights() {
        return rights;
    }

    public List<Double> getLefts() {
        return lefts;
    }

    public List<Double> getRatios() {
        return ratios;
    }

    public List<Double> getLens() {
        return lens;
    }

    @Override
    public void log() {
        if (!isLog) {
            return;
        }
        rights.add(right);
        lefts.add(left);
        if (lens.size() > 0) {
            ratios.add(((right - left) / lens.get(lens.size() - 1)));
        }
        lens.add(right - left);
    }

    public void printLog() {
        super.printLog();
        evaluationsNumber--;
        System.out.println("Количество вычислений функции: " + evaluationsNumber);
        System.out.println("правая граница(" + rights.size() + "): ");
        printList(rights);
        System.out.println("левая граница(" + lefts.size() + "): ");
        printList(lefts);
        System.out.println("длины отрезков(" + lens.size() + "): ");
        printList(lens);
        System.out.println("отношения отрезков(начиная со второго тк для первого нет)(" + ratios.size() + "): ");
        printList(ratios);
    }
}
