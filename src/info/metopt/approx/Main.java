package info.metopt.approx;

public class Main {

    public static void main(String[] args) {
        Dichotomy dichotomy = new Dichotomy(-2.0, 3.0, 1e-5);
        Fibonacci fibonacci = new Fibonacci(-2.0, 3.0, 1e-5);
        Parabola parabola = new Parabola(-2.0, 3.0, 1e-5, true);
        GoldenRatio goldenRatio = new GoldenRatio(-2.0, 3.0, 1e-5);
        Brent brent = new Brent(-2.0, 3.0, 1e-5);
        dichotomy.start();
        fibonacci.start();
        parabola.start();
        goldenRatio.start();
        brent.start();
    }
}
