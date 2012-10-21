
import java.util.*;

/** 
 * 
 *  
 * @authors Philip Raath / Andrew Canastar collaboration
 * @version 10.20.12 
 */

public interface HangmanGameInterface
{
    boolean validateGuess(char guess);
    
    /**
     * Should be implemented as static
     * Selects a word from the current dictionary
     * @param int numLetters - reflects word length
     * @return String word
     */
    String chooseWord( int numLetters );
    
    /**
     * adds the current guess to list of previously guessed
     * characters
     * @param letter
     * @return Set updateGuessList 
     */
    Set<Character> updateGuessList( char letter );
    
    /**
     * decrements the number of guesses available
     * @return guessLimit - number of guesses left
     */
    int decrementGuessLimit();
    
    /**
     * iterates through current target word pattern and places
     * correct guess in appropriate slots
     * @param input - current correct guess
     * @return List updateViewList
     */
    List<Character> updateViewList(char input);
    
    /**
     * Iterates through the target word and counts the number of blanks.
     * @return numBlanks - current number of '-' characters
     * 			in the target word
     */
    int decrementBlanks();
    
    /**
     * @return number of guesses left
     */
    int getGuessesLeft();
    
    /**
     * @return guessList - holds previous guesses
     */
    Set<Character> getGuessList();
    
    /**
     * returns the current pattern of characters and blanks
     * for the target word
     * @return List viewList
     */
    List<Character> getViewList();
	
    /**
     * determines if the current guess is in the target word
     * @param currentGuess
     * @return boolean
     */
    boolean checkCorrectGuess(char currentGuess);
    
    /**
     * determines if the current guess has already been entered
     * @param guess
     * @return boolean
     */
    boolean checkAlreadyGuessed(char guess);
    
    /**    
     * returns the current number of blanks in the target word pattern
     * @return int numBlanks
     */
    int getNumBlanks();
    
    /**
     * Updates viewList to match the current pattern.
     * @return updated viewList
     */
    List<Character> updateViewList();
    
    /**
     * Takes user's guess and obtains Map<String, Set<String>> of the longest 
     * subset of pattern words. Assigns Key to targetWord and assigns the 
     * Value to wordSet.
     * @param currentGuess
     */
    void assignPatternMap(char currentGuess);
    
    /**
     * Returns the current wordSet. Used in hangTough version to select
     * a word to be displayed to the user on losing.
     * @return Set<String> wordSet of remaining words.
     */
    Set<String> getWordSet(); 
    
}
