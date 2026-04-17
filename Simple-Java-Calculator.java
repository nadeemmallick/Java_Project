import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

class SimpleJavaCalculator {

    private static final Scanner sc = new Scanner(System.in);
    private static final List<String> history = new ArrayList<>();
    private static final Stack<Double> resultStack = new Stack<>();

    public static void main(String[] args) {
        System.out.println("\n======================================");
        System.out.println("      Welcome to Java Calculator      ");
        System.out.println("======================================");

        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Choose an option: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> basicCalculation();
                case "2" -> showHistory();
                case "3" -> backtrack();
                case "4" -> clearAll();
                case "5" -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }

            System.out.println();
        }

        System.out.println("Calculator closed. Goodbye!");
    }

    private static void printMenu() {
        System.out.println("""
                
                -------- MAIN MENU --------
                1. Basic Calculator
                2. View History
                3. Backtrack Last Result
                4. Clear History / Reset
                5. Exit
                ---------------------------
                """);
    }

    private static void basicCalculation() {
        System.out.println("\n--- Basic Calculator ---");
        double a = readDouble("Enter first number: ");
        System.out.print("Enter operator (+, -, *, /): ");
        String op = sc.nextLine().trim();
        double b = readDouble("Enter second number: ");

        Double result = switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> b != 0 ? a / b : null;
            default -> null;
        };

        if (result == null) {
            System.out.println("Invalid operation or division by zero.");
            return;
        }

        saveResult(a + " " + op + " " + b + " = " + result, result);
        System.out.println("Result: " + result);
    }

    private static void showHistory() {
        System.out.println("\n--- Calculation History ---");
        if (history.isEmpty()) {
            System.out.println("No calculations yet.");
            return;
        }

        for (int i = 0; i < history.size(); i++) {
            System.out.println((i + 1) + ". " + history.get(i));
        }
    }

    private static void backtrack() {
        if (resultStack.isEmpty() || history.isEmpty()) {
            System.out.println("Nothing to backtrack.");
            return;
        }

        String removedHistory = history.removeLast();
        Double removedResult = resultStack.pop();

        System.out.println("Removed last result: " + removedHistory);
        System.out.println("Backtracked value: " + removedResult);

        if (!resultStack.isEmpty()) {
            System.out.println("Current last saved result: " + resultStack.peek());
        } else {
            System.out.println("No saved results remaining.");
        }
    }

    private static void clearAll() {
        history.clear();
        resultStack.clear();
        System.out.println("History and saved results cleared.");
    }

    private static void saveResult(String expression, Double result) {
        history.add(expression);
        resultStack.push(result);
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

}
