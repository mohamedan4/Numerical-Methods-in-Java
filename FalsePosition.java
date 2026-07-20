import java.util.Scanner;

public class FalsePosition {

    public static double f(double x) {
        return x * x - 4; // Example function: f(x) = x^2 - 4 (roots at x = ±2)
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("(a) değerini giriniz: ");
        double a = scanner.nextDouble();
        System.out.println("(b) değerini giriniz: ");
        double b = scanner.nextDouble();

        System.out.print("Enter tolerance (ε): ");
        double tol = scanner.nextDouble();
        System.out.print("Enter maximum iterations: ");
        int maxIterations = scanner.nextInt();

        if (f(a) * f(b) >= 0) {
            System.out.println("Invalid interval: f(a) and f(b) must have opposite signs.");
            return;
        }

        double root = falsePositionMethod(a, b, tol, maxIterations);
        System.out.println("Approximated root: " + root);
    }

    public static double falsePositionMethod(double a, double b, double tol, int maxIterations) {
        double c = a;
        for (int i = 0; i < maxIterations; i++) {

            c = (a * f(b) - b * f(a)) / (f(b) - f(a));

            if (Math.abs(f(c)) < tol) {
                break;
            }

            if (f(a) * f(c) < 0) {
                b = c;
            } else {
                a = c;
            }
        }
        return c;
    }
}
