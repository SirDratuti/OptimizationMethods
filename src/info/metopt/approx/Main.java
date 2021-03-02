package info.metopt.approx;

public class Main {

    public static void main(String[] args) {
        Dichotomy dichotomy = new Dichotomy(-2.0, 3.0, 1e-5, true);
        Fibonacci fibonacci = new Fibonacci(-2.0, 3.0, 1e-5, false);
        Parabola parabola = new Parabola(-2.0, 3.0, 1e-5);
        GoldenRatio goldenRatio = new GoldenRatio(-2.0, 3.0, 1e-5 );
        Brent brent = new Brent(-2.0, 3.0, 1e-5);
        System.out.println(dichotomy.start());
        System.out.println(fibonacci.start());
        System.out.println(parabola.start());
        System.out.println(goldenRatio.start());
        System.out.println(brent.start());
        for (double eps = 1e-1; eps > 1e-7; eps *= 1e-1) {
            dichotomy = new Dichotomy(-2.0, 3.0, eps);
            dichotomy.start();
            System.out.println("эпсилон: " + String.format("%.7f", eps) + " количество вычислений: " + dichotomy.getEvaluationsNumber() + " x: " + dichotomy.getResult() + " f(x): " + Method.evaluate(dichotomy.getResult()));
        }
    }
}
