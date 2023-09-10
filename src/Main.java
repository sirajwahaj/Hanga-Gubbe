
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // To choose the secret word--------------------------------------------------------------------
        String secretWord = obtainSecretWord();
        char[] correctLetters = new char[secretWord.length()];
        for (int i = 0; i < secretWord.length(); i++) {
            correctLetters[i] = '_';
        }

        // Wrong letters stored in this variable  -------------------------------------------------------
        char[] wrongLetters = new char[7];
        Scanner scanner = new Scanner(System.in);

        // Here are the number of unsuccessful attempts allowed ---------------------------------------
        int attemptsLeft = 7;

        while (attemptsLeft > 0){
        // To show the current status of the game ---------------------------------------------------
            displayStatus(wrongLetters, correctLetters, attemptsLeft);

        // Get the user guess -----------------------------------------------------------------------
            System.out.print("Guess:");
            String guessedLetter = scanner.next().toUpperCase();
            char letter = guessedLetter.charAt(0);

        // Validate the user input ------------------------------------------------------------------
            if(guessedLetter.length() !=1 || !Character.isLetter(guessedLetter.charAt(0))){
                System.out.println("Please enter a single letter!");
                continue;
            }

            if(isAlreadyGuessed(correctLetters, wrongLetters, letter)){
                System.out.println("Already guessed " + guessedLetter +"!");
            }

        // Check the user guess and update the correct words and wrong words accordingly-----------------
            boolean found = false;
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == letter) {
                    correctLetters[i]= letter;
                    found = true;
                }
            }

        // Control the wrong attempts-----------------------------------------------------------------
            if (found) {
                System.out.println("Correct guess!");
            } else {
                wrongLetters[7 - attemptsLeft] = letter;
                System.out.println("Wrong guess!");
                attemptsLeft--;
            }

        // Check if the user won the game -------------------------------------------------------------
            if (new String(correctLetters).equals(secretWord)) {
                System.out.println("Congratulations! You guessed the secret word: " + secretWord);
                break;
            }

            System.out.println("\n");
        }
        if (attemptsLeft == 0) {
            System.out.println("You ran out of attempts. The secret word was: " + secretWord);
        }
    }

        public static void displayStatus(char[] wrongLetters, char[] correctLetters, int attemptsLeft){
            System.out.println("Guess the secret word: " + new String(correctLetters));
            System.out.println("Attempts left - " + attemptsLeft);
            System.out.print("Wrong letters - " );
            for (char wrongLetter : wrongLetters) {
                if(wrongLetter != 0) System.out.print(wrongLetter + " ");
            }
            System.out.println();
        }

        public static boolean isAlreadyGuessed(char[] correctLetters, char[] wrongLetters, char letter){
            String strLetter = Character.toString(letter);
            String strWrongLetters = new String(wrongLetters);
            String strCorrectLetters = new String(correctLetters);
            return strWrongLetters.contains(strLetter) || strCorrectLetters.contains(strLetter);
        }

        public static String obtainSecretWord(){
            String[] words = new String[]{"Sunshine", "Elephant", "Butterfly", "Adventure",
                    "Delicious", "Mountains", "Watermelon", "Sunshine", "Chocolate",
                    "Happiness", "Strawberry", "Universe", "Symphony", "Serendipity",
                    "Friendship", "Beautiful", "Sunshine", "Tranquility", "Butterfly",
                    "Whispering", "Brilliant", "Sparkling", "Elegance", "Captivating",
                    "Exploration"};

            Random random = new Random();
            int randomIndex = random.nextInt(words.length);
            return words[randomIndex].toUpperCase();
        }
}