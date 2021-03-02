package info.metopt.approx;

public class Main {

    public static void main(String[] args) {
        Dichotomy dichotomy = new Dichotomy(-2.0, 3.0, 1e-5, true);
        Fibonacci fibonacci = new Fibonacci(-2.0, 3.0, 1e-5, true);
        Parabola parabola = new Parabola(-2.0, 3.0, 1e-5, true);
        GoldenRatio goldenRatio = new GoldenRatio(-2.0, 3.0, 1e-5, true);
        Brent brent = new Brent(-2.0, 3.0, 1e-5, true);
        System.out.println(dichotomy.start());
        System.out.println(fibonacci.start());
        System.out.println(parabola.start());
        System.out.println(goldenRatio.start());
        System.out.println(brent.start());
    }
}
