package info.metopt.approx;

public class Main {

    public static void main(String[] args) {
        Dichotomy method = new Dichotomy(-2.0,3.0,1e-5);
        Fibonacci fibonacci = new Fibonacci(-2.0, 3.0, 1e-5);
        //System.out.println(method.start());
        System.out.println();
        System.out.println(fibonacci.start());
    }
}
