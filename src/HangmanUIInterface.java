
import java.util.*; 

/** 
 * 
 *  
 * @authors Philip Raath / Andrew Canastar collaboration
 * @version 10.20.12 
 */

 interface HangmanUIInterface 
{ 
    static Scanner scanner = new Scanner(System.in); 
      
    /** 
     * Displays welcome message. 
     */
    void displayWelcome(); 
      
    /** 
     * Request user input for the name of the dictionary. 
     * @return String composed of the filename. 
     */
    String askForDictionaryName(); 
      
    /** 
     * Request user input for current guess. 
     * @throws IOException 
     * @return  char 
     */
    char askGuess(); 
      
    /** 
     * Request user input for the number of guesses allowed. 
     * @return  int 
     */
    int askGuessLimit(); 
      
    /** 
     * Validates that the user-supplied value is an int, returns the int or 0 if invalid data. 
     * @param testInt - a String representing the user-supplied integer value. 
     * @return int 0 if the String parameter cannot be parsed as an int. 
     */
    int validateAnInt( String testInt ); 
      
    /** 
     * Request length of word. 
     * @param maxWordLength - the integer value of the longest word in the current dictionary 
     * @return  int length of the word 
     */
    int askWordLength( int maxWordLength ); 
      
    /** 
     * Display current state of game consisting of guesses left, array of previously guess letters, 
     * and current pattern of blanks and correct guesses. 
     *  
     * @param int numberOfGuessesRemaining 
     * @param Set previousGuesses a set that contains all previous guesses with no duplicate values 
     * @param ArrayList<Character> currentPattern contains a pattern of letters and blanks 
     */
    void displayCurrentState(int numberOfGuessesRemaining,  
                                    Set<Character> previousGuesses,  
                                    ArrayList<Character> currentPattern); 
      
    /** 
     * Returns a char 'y' or 'n' if the first character in a user's response is 'y' or 'n'. 
     * Otherwise it continually requests input until the user's response is a 'y' or 'n'. 
     * @param yesNo - String from Scanner.next() 
     * @return char either 'y' or 'n' 
     */
    char validateYesOrNo( String yesNo ); 
      
    /** 
     * Report that current guess is correct. 
     * @param char currentGuess 
     */
    void displayCorrectGuess(char currentGuess); 
      
    /** 
     * Report that current guess is incorrect. 
     * @param char currentGuess 
     */
    void displayIncorrectGuess(char currentGuess); 
      
    /** 
     * Display correct answer. 
     * @param String correctAnswer 
     */
    void displayAnswer(String correctAnswer); 
      
    /** 
     Display win message. 
     */
    void displayWinMessage(); 
      
    /** 
     Display loss message. 
     */
    void displayLossMessage(); 
      
      
    /** 
     * Display previously guessed message 
     * @param previouslyGuessed char that has already been guessed 
     */
    void displayAlreadyGuess(char previouslyGuessed); 
      
    /** 
     Ask user if they wish to play another game. 
     * @return  boolean true if user requests a new game; false otherwise 
     */
    boolean askNewGame(); 
    
    void displayGoodbyeMessage();
    
    void invalidEntry( String message );
      
} 