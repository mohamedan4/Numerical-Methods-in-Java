import java.util.Scanner;

public class BiSection {
    public static double f(double x) {
        return x * x - 4;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("(a) değerini giriniz: ");
        double a = scanner.nextDouble();
        System.out.println("(b) değerini giriniz: ");
        double b = scanner.nextDouble();

        // iterasyon epsilon değerinden küçük olmamalı
        System.out.println("Enter tolerance (ε): ");
        double tol = scanner.nextDouble();
        System.out.println("Enter maximum iterations: ");
        int maxIterations = scanner.nextInt();


        if (f(a) * f(b) >= 0) {
            System.out.println("Invalid interval: f(a) and f(b) must have opposite signs.");
            return;
        }

        double root = bisectionMethod(a, b, tol, maxIterations);
        System.out.println("Approximated root: " + root);
    }

    public static double bisectionMethod(double a, double b, double tol, int maxIterations) {
        double c = a; // Midpoint
        for (int i = 0; i < maxIterations; i++) {
            c = (a + b) / 2;

            if (Math.abs(f(c)) < tol || (b - a) / 2 < tol) {
                break;
            }

            // (right bracket with middle) or (left bracket with middle)
            if (f(a) * f(c) < 0) {
                b = c;
            } else {
                a = c;
            }
        }
        return c;
    }
}
