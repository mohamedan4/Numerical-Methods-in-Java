package MohamedMohamedIssa2023123341_Say04;

import java.util.function.Function;
import java.util.Scanner;

public class NewtonRaphson {

    public static double newtonRaphson(Function<Double, Double> f, Function<Double, Double> df, double initialGuess, double tolerance, int maxIterations) {
        double x = initialGuess;
        int iteration = 0;
        double previousX;
        double trueValue = initialGuess;

        System.out.println("--------------------------------------------------");
        System.out.println("|  i  |     x_i      |  ε_a (%)  |  ε_t (%)  |");
        System.out.println("--------------------------------------------------");

        while (iteration < maxIterations) {
            previousX = x;
            double newX = x - f.apply(x) / df.apply(x);
            double approxError = (iteration == 0) ? 100.0 : Math.abs((newX - previousX) / newX) * 100;
            double trueError = Math.abs((trueValue - newX) / trueValue) * 100;


            System.out.printf("| %2d  | %10.6f | %8.2f  | %8.2f  |%n", iteration, newX, approxError, trueError);


            if (Math.abs(f.apply(newX)) < tolerance) {
                System.out.println("--------------------------------------------------");
                return newX;
            }

            x = newX;
            iteration++;
        }

        System.out.println("--------------------------------------------------");
        throw new RuntimeException("Newton-Raphson method did not converge within the given iterations.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        Function<Double, Double> f = x -> x * x - 4;
        Function<Double, Double> df = x -> 2 * x;

        System.out.print("Enter initial guess: ");
        double initialGuess = scanner.nextDouble();

        System.out.print("Enter tolerance: ");
        double tolerance = scanner.nextDouble();

        System.out.print("Enter maximum iterations: ");
        int maxIterations = scanner.nextInt();

        try {
            double root = newtonRaphson(f, df, initialGuess, tolerance, maxIterations);
            System.out.println("Approximate root: " + root);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }
}
