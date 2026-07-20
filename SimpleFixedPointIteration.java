import java.util.function.Function;
import java.util.Scanner;

public class SimpleFixedPointIterationCalculating {

    // Function for fixed-point iteration
    public static double fixedPointIteration(Function<Double, Double> g, double initialGuess, double tolerance, int maxIterations) {
        double x = initialGuess;
        int iteration = 0;
        double previousX;
        double trueValue = Math.cos(initialGuess); // Assuming true value is close to cos(initial guess)

        System.out.println("--------------------------------------------------");
        System.out.println("|  i  |     x_i      |  ε_a (%)  |  ε_t (%)  |");
        System.out.println("--------------------------------------------------");

        while (iteration < maxIterations) {
            previousX = x;
            double newX = g.apply(x);
            double approxError = (iteration == 0) ? 100.0 : Math.abs((newX - previousX) / newX) * 100;
            double trueError = Math.abs((trueValue - newX) / trueValue) * 100;

            // Print iteration data in tabular format
            System.out.printf("| %2d  | %10.6f | %8.2f  | %8.2f  |%n", iteration, newX, approxError, trueError);

            // Check for convergence
            if (approxError < tolerance) {
                System.out.println("--------------------------------------------------");
                return newX;
            }

            x = newX;
            iteration++;
        }

        System.out.println("--------------------------------------------------");
        throw new RuntimeException("Fixed-point iteration did not converge within the given iterations.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Example: Solving x = cos(x)
        Function<Double, Double> g = x -> Math.cos(x);

        System.out.print("Enter initial guess: ");
        double initialGuess = scanner.nextDouble();

        System.out.print("Enter tolerance: ");
        double tolerance = scanner.nextDouble();

        System.out.print("Enter maximum iterations: ");
        int maxIterations = scanner.nextInt();

        try {
            double root = fixedPointIteration(g, initialGuess, tolerance, maxIterations);
            System.out.println("Approximate root: " + root);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
