package info.metopt.approx;

/**
 * Function optimization methods interface.
 *
 * @param <T> function argument type.
 */
public interface Method<T> {

    /**
     * Number comparison constant.
     */
    double compareEpsilon = 1e-10;

    /**
     * Calculates the value of the function with the given <var>argument</var>.
     *
     * @param argument of type {@link T} to evaluate the function.
     * @return the value of a function with an argument of type {@link Double}.
     */
    double evaluate(T argument);

    /**
     * Starting method computation.
     *
     * @return minimum argument.
     */
    T start();

    /**
     * Method that starts the next iteration of the method.
     *
     * @return a boolean value, true if the exit condition was not met, else false.
     */
    boolean makeIteration();

    /**
     * Method that starts iterations until the exit condition is met.
     */
    void makeIterations();

    /**
     * Method that starts iterations <var>n</var> iterations.
     *
     * @param n number of iterations
     */
    void makeIterations(long n);

    /**
     * The method calculates the length of the segment.
     *
     * @param left  left border of the segment.
     * @param right right border of the segment.
     * @return length of the segment.
     */
    static double range(double left, double right) {
        return right - left;
    }

    /**
     * Method returns the current approximation of the argument to the minimum.
     *
     * @return current approximation of the argument of type {@link T} to the minimum
     */
    T getCurrentX();

    /**
     * Returns the current iteration number.
     *
     * @return the current iteration number
     */
    int getNumberIteration();

    /**
     * A method that completes the computation of the method and records the results.
     */
    void finish();

    /**
     * Method compares two numbers.
     *
     * @param first  {@link Double} number.
     * @param second {@link Double} number.
     * @return true if first >= second, false else.
     */
    static boolean compare(double first, double second) {
        return first - second >= compareEpsilon;
    }

    /**
     * Method checks two numbers for equality.
     *
     * @param first  {@link Double} number.
     * @param second {@link Double} number.
     * @return true if first == second, false else.
     */
    static boolean equal(double first, double second) {
        return Math.abs((first - second)) <= compareEpsilon;
    }

    /**
     * Logs the current state of the method.
     */
    void log();
}
