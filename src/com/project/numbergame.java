package com.project;



import java.util.Random;

import java.util.Scanner;


public class numbergame {

    private static final int MIN = 1;
    private static final int MAX = 100;
    private static final int ATTEMPTS_LIMIT = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        int totalScore = 0;
        int roundsPlayed = 0;

        while (playAgain) {
            roundsPlayed++;
            int roundScore = playRound(scanner);
            totalScore += roundScore;
            System.out.println("Your score for this round: " + roundScore);
            System.out.println("Total score: " + totalScore);
            playAgain = askToPlayAgain(scanner);
        }

        System.out.println("You played " + roundsPlayed + " rounds with a total score of " + totalScore + ".");
        scanner.close();
        System.out.println("Thank you for playing!");
    }

    private static int playRound(Scanner scanner) {
        Random random = new Random();
        int targetNumber = random.nextInt(MAX - MIN + 1) + MIN;
        int attempts = 0;
        boolean guessedCorrectly = false;

        System.out.println("\nI have generated a number between " + MIN + " and " + MAX + ". You have " + ATTEMPTS_LIMIT + " attempts to guess it.");

        while (attempts < ATTEMPTS_LIMIT && !guessedCorrectly) {
            System.out.print("Enter your guess: ");
            int userGuess = getUserGuess(scanner);
            attempts++;

            if (userGuess < targetNumber) {
                System.out.println("Too low!");
            } else if (userGuess > targetNumber) {
                System.out.println("Too high!");
            } else {
                System.out.println("Correct! You guessed the number in " + attempts + " attempts.");
                guessedCorrectly = true;
            }
        }

        if (!guessedCorrectly) {
            System.out.println("You've used all your attempts. The number was: " + targetNumber);
        }

        return calculateScore(attempts, guessedCorrectly);
    }

    private static int getUserGuess(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid integer: ");
            }
        }
    }

    private static int calculateScore(int attempts, boolean guessedCorrectly) {
        if (guessedCorrectly) {
            return (ATTEMPTS_LIMIT - attempts) * 10; 
        } else {
            return 0; 
        }
    }

    private static boolean askToPlayAgain(Scanner scanner) {
        System.out.print("Do you want to play again? (yes/no): ");
        String response = scanner.next();
        return response.equalsIgnoreCase("yes");
    }
}
