import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Number Game!");
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 10;
        int score = 0;

        do {
            int targetNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            System.out.println("I have generated a number between " + minRange + " and " + maxRange + ". Can you guess it?");

            for (int attempts = 1; attempts <= maxAttempts; attempts++) {
                System.out.print("Enter your guess (Attempt " + attempts + "): ");
                int userGuess = scanner.nextInt();

                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    score += maxAttempts - attempts + 1;
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Too low. Try again.");
                } else {
                    System.out.println("Too high. Try again.");
                }

                if (attempts == maxAttempts) {
                    System.out.println("Sorry, you've run out of attempts. The correct number was: " + targetNumber);
                }
            }

            System.out.print("Do you want to play again? (yes/no): ");
        } while (scanner.next().equalsIgnoreCase("yes"));

        System.out.println("Thanks for playing! Your total score is: " + score);
        scanner.close();
    }
}