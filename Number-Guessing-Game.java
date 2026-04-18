import java.util.Random;
import java.util.Scanner;

class numberGuessingGame {

    static int generateSecretNumber() {
        return new Random().nextInt(100) + 1;
    }

    static int getGuess(Scanner scanner) {
        while (true) {
            System.out.print("Enter your guess (1-100): ");
            try {
                int guess = Integer.parseInt(scanner.nextLine().trim());
                if (guess >= 1 && guess <= 100) return guess;
                System.out.println("Please enter a number between 1 and 100.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }
    }

    static String checkGuess(int guess, int secret) {
        if (guess == secret) return "correct";
        return guess < secret ? "low" : "high";
    }

    static void printHint(String result, int attemptsLeft) {
        switch (result) {
            case "low":  System.out.println("Too low!  " + attemptsLeft + " attempt(s) left."); break;
            case "high": System.out.println("Too high! " + attemptsLeft + " attempt(s) left."); break;
        }
    }

    static void playGame(Scanner scanner) {
        int secret = generateSecretNumber();
        int maxAttempts = 10;

        System.out.println("\n=== Number Guessing Game ===");
        System.out.println("Guess the number between 1 and 100. You have " + maxAttempts + " attempts.\n");

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            System.out.print("Attempt " + attempt + "/" + maxAttempts + " — ");
            int guess = getGuess(scanner);
            String result = checkGuess(guess, secret);

            if (result.equals("correct")) {
                System.out.println("Correct! You guessed it in " + attempt + " attempt(s)!");
                return;
            }

            printHint(result, maxAttempts - attempt);
        }

        System.out.println("\nGame over! The number was " + secret + ".");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        playGame(scanner);
        scanner.close();
    }
}
