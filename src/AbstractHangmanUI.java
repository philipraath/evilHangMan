
import java.util.ArrayList; 
import java.util.Scanner; 
import java.util.Set; 

/** 
 * Describes an implementation of the HangmanUIInterface. It leaves
 * the implementation of interface methods to subclasses. Its methods validate
 * user input based on limitations acceptable char and int values. These acceptable
 * values are either final variables or obtained via its methods. These
 * validation methods handle exceptions due to poor user input by requesting new
 * input with a recommendation on the correct parameters.
 *  
 * @authors Philip Raath / Andrew Canastar collaboration
 * @version 10.20.12 
 */
  
public abstract class AbstractHangmanUI implements HangmanUIInterface { 
  
    // private final char LOWEST_LEGIT_CHAR = 'a'; 
    // private final char HIGHEST_LEGIT_CHAR = 'z'; 
    private final int ALPHABET_LENGTH = 26; 
      
    private static Scanner scanner = new Scanner(System.in); 
      
    /** 
     * Attempts Integer.parseInt(String) on a String passed to the method. It returns 
     * 0 if the String cannot be parsed. 
     * @param - String representation of the integer. 
     * @throws NumberFormatException if the integer cannot be parsed. 
     * @return int set to 0 if invalid; otherwise a parsed integer  
     */
    public int validateAnInt( String testInt ) 
    { 
        int isInt = 0; 
        try
        { 
            isInt = Integer.parseInt( testInt ); 
        } 
        catch ( NumberFormatException e )  
        { 
            isInt = 0; 
        } 
        return isInt; 
    } 
      
    /** 
     * Returns true if the user-specified word length is less than or equal to the 
     * longest word in the hangman dictionary and greater than 0. 
     * @param userLength - the length specified by the user 
     * @param maxWordLength - the longest word length found in the dictionary 
     * @throws NumberFormatException  
     * @return true if the userLength is >0 and <= maxWordLength  
     */
    public int validateUserWordLength( int userLength, int maxWordLength ) 
    { 
        while( userLength <= 0 || userLength > maxWordLength ) 
        { 
            System.out.print( "Invalid entry, please " + 
                    "be sure to type an integer greater than 0 and" + 
                    " less than " + maxWordLength + " this time."); 
            System.out.print( "\nTry again: " ); 
            userLength = validateAnInt( scanner.next() ); 
        } 
        return userLength;   
    } 
      
    /** 
     * Returns true if a user-specified char is within the range specified by LOWEST_LEGIT_CHAR 
     * and HIGHEST_LEGIT_CHAR. 
     * @param Character - the char to be validated 
     * @return true if the char is within the specified range of chars 
     */
    public boolean validateCharacter( char Character ) 
    { 
        return false; 
    } 
      
    /** 
     * Returns boolean value specified by whether a user-specified int is <= ALPHABET_LENGTH. 
     * @param totalGuesses - total number of guesses specified by the user. 
     * @return true if totalGuesses <= ALPHABET_LENGTH 
     */
    public int validateGuessLimit( int totalGuesses ) 
    { 
        while( totalGuesses <= 0 || totalGuesses > ALPHABET_LENGTH ) 
        { 
            System.out.print( "Invalid entry, please " + 
                    "be sure to type an integer greater than 0 and" + 
                    " less than " + ALPHABET_LENGTH + " this time."); 
            System.out.print( "\nTry again: " ); 
            totalGuesses = validateAnInt( scanner.next() ); 
        } 
        return totalGuesses; 
    } 
      
    /**
     * Returns a char of either 'y' or 'n' from any input that starts with 'y' or 'n'
     * or continues to prod the user until it can.
     * @param yesNo - String obtained from user input via scanner.next()
     * @return testResponse - char that should either be 'y' or 'n'
     */
    public char validateYesOrNo( String yesNo ) 
    { 
        char testResponse = Character.toLowerCase(yesNo.charAt(0)); 
        while(testResponse!='y' && testResponse!='n') 
        { 
            System.out.println("Please restrict your response to 'y' for yes or 'n' for no!"); 
            System.out.println("please try again: "); 
            testResponse = Character.toLowerCase(scanner.next().charAt(0)); 
        } 
        return testResponse;  
    } 
    /** 
     * Displays welcome message. 
     */
    public abstract void displayWelcome(); 
      
    /** 
     * Request user input for current guess. 
     * @return  char 
     */
    public abstract char askGuess(); 
      
    /** 
     * Request user input for the number of guesses allowed. 
     * @return  int 
     */
    public abstract int askGuessLimit(); 
      
    /** 
     * Request length of word. 
     * @param maxWordLength - the integer value of the longest word in the current dictionary 
     * @return  int length of the word 
     */
    public abstract int askWordLength( int maxWordLength ); 
      
    /** 
     * Display current state of game consisting of guesses left, array of previously guess letters, 
     * and current pattern of blanks and correct guesses. 
     *  
     * @param int numberOfGuessesRemaining 
     * @param Set previousGuesses a set that contains all previous guesses with no duplicate values 
     * @param ArrayList<Character> currentPattern contains a pattern of letters and blanks 
     */
    public abstract void displayCurrentState(int numberOfGuessesRemaining,  
                                            Set<Character> previousGuesses,  
                                            ArrayList<Character> currentPattern); 
      
    /** 
     * Report that current guess is correct. 
     * @param char currentGuess 
     */
    public abstract void displayCorrectGuess(char currentGuess); 
      
    /** 
     * Report that current guess is incorrect. 
     * @param char currentGuess 
     */
    public abstract void displayIncorrectGuess(char currentGuess); 
      
    /** 
     * Display correct answer. 
     * @param String correctAnswer 
     */
    public abstract void displayAnswer(String correctAnswer); 
      
    /** 
     Display win message. 
     */
    public abstract void displayWinMessage(); 
      
    /** 
     Display loss message. 
     */
    public abstract void displayLossMessage(); 
       
    /** 
     * Display previously guessed message 
     * @param previouslyGuessed char that has already been guessed 
     */
    public abstract void displayAlreadyGuess(char previouslyGuessed); 
      
    /** 
     Ask user if they wish to play another game. 
     * @return  boolean true if user requests a new game; false otherwise 
     */
    public abstract boolean askNewGame(); 
      
  
} 