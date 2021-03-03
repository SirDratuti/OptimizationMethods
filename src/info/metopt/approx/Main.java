package info.metopt.approx;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Dichotomy dichotomy = new Dichotomy(-2.0, 3.0, 1e-5, true);
        Fibonacci fibonacci = new Fibonacci(-2.0, 3.0, 1e-5, true);
        Parabola parabola = new Parabola(-2.0, 3.0, 1e-5, true);
        GoldenRatio goldenRatio = new GoldenRatio(-2.0, 3.0, 1e-5, true);
        Brent brent = new Brent(-2.0, 3.0, 1e-5, true);
        dichotomy.start();
        fibonacci.start();
        parabola.start();
        goldenRatio.start();
        brent.start();
        List<Method> methods = List.of(new Dichotomy(-2.0, 3.0, 1e-5),
                new Fibonacci(-2.0, 3.0, 1e-5),
                new Parabola(-2.0, 3.0, 1e-5),
                new GoldenRatio(-2.0, 3.0, 1e-5),
                new Brent(-2.0, 3.0, 1e-5));
        System.out.println("эпсилон: ");
        for (double eps = 1e-1; eps > 1e-7; eps *= 1e-1) {
            System.out.println(String.format("%.7f", eps));
        }
        System.out.println("количество вычислений для дихотомии:");
        for (double eps = 1e-1; eps > 1e-7; eps *= 1e-1) {
            dichotomy = new Dichotomy(-2.0, 3.0, eps);
            dichotomy.start();
            System.out.println(dichotomy.getEvaluationsNumber());
        }
        System.out.println("x для для дихотомии:");
        for (double eps = 1e-1; eps > 1e-7; eps *= 1e-1) {
            dichotomy = new Dichotomy(-2.0, 3.0, eps);
            dichotomy.start();
            System.out.println(dichotomy.getResult());
        }
        System.out.println("fx для дихотомии:");
        for (double eps = 1e-1; eps > 1e-7; eps *= 1e-1) {
            dichotomy = new Dichotomy(-2.0, 3.0, eps);
            dichotomy.start();
            System.out.println(dichotomy.evaluate(dichotomy.getResult()));
        }
        AbstractMethod m;
        System.out.println("количество вычислений для фибоначи:");
        for (double eps = 1e-1; eps > 1e-7; eps *= 1e-1) {
            m = new Fibonacci(-2.0, 3.0, eps);
            m.start();
            System.out.println(m.getEvaluationsNumber());
        }
        System.out.println("x для для фибоначи:");
        for (double eps = 1e-1; eps > 1e-7; eps *= 1e-1) {
            m = new Fibonacci(-2.0, 3.0, eps);
            m.start();
            System.out.println(m.getResult());
        }
        System.out.println("fx для фибоначи:");
        for (double eps = 1e-1; eps > 1e-7; eps *= 1e-1) {
            m = new Fibonacci(-2.0, 3.0, eps);
            m.start();
            System.out.println(m.evaluate(dichotomy.getResult()));
        }
        System.out.println("количество вычислений для золотого сечения:");
        for (double eps = 1e-1; eps > 1e-7; eps *= 1e-1) {
            m = new GoldenRatio(-2.0, 3.0, eps);
            m.start();
            System.out.println(m.getEvaluationsNumber());
        }
        System.out.println("x для для золотого сечения:");
        for (double eps = 1e-1; eps > 1e-7; eps *= 1e-1) {
            m = new GoldenRatio(-2.0, 3.0, eps);
            m.start();
            System.out.println(m.getResult());
        }
        System.out.println("fx для золотого сечения:");
        for (double eps = 1e-1; eps > 1e-7; eps *= 1e-1) {
            m = new GoldenRatio(-2.0, 3.0, eps);
            m.start();
            System.out.println(m.evaluate(dichotomy.getResult()));
        }
        System.out.println("количество вычислений для брента:");
        for (double eps = 1e-1; eps > 1e-7; eps *= 1e-1) {
            m = new Brent(-2.0, 3.0, eps);
            m.start();
            System.out.println(m.getEvaluationsNumber());
        }
        System.out.println("x для для золотого брента:");
        for (double eps = 1e-1; eps > 1e-7; eps *= 1e-1) {
            m = new Brent(-2.0, 3.0, eps);
            m.start();
            System.out.println(m.getResult());
        }
        System.out.println("fx для золотого брента:");
        for (double eps = 1e-1; eps > 1e-7; eps *= 1e-1) {
            m = new Brent(-2.0, 3.0, eps);
            m.start();
            System.out.println(m.evaluate(dichotomy.getResult()));
        }
        System.out.println("количество вычислений для парабол:");
        for (double eps = 1e-1; eps > 1e-7; eps *= 1e-1) {
            m = new Parabola(-2.0, 3.0, eps);
            m.start();
            System.out.println(m.getEvaluationsNumber());
        }
        System.out.println("x для для парабол:");
        for (double eps = 1e-1; eps > 1e-7; eps *= 1e-1) {
            m = new Parabola(-2.0, 3.0, eps);
            m.start();
            System.out.println(m.getResult());
        }
        System.out.println("fx для парабол:");
        for (double eps = 1e-1; eps > 1e-7; eps *= 1e-1) {
            m = new Parabola(-2.0, 3.0, eps);
            m.start();
            System.out.println(m.evaluate(m.getResult()));
        }
    }
}
