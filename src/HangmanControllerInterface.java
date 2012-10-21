
import java.util.Set;

/** 
 * 
 *  
 * @authors Philip Raath / Andrew Canastar collaboration
 * @version 10.20.12 
 */

public interface HangmanControllerInterface
{
    /**
     * Instantiates a user interface.
     */
    void startUserInterface();
    
    /**
     * Obtains a word of the correct length from a Dictionary.
     * 
     * @param int wordLength
     * @return String
     */
    String getWord( int wordLength );
    
    /**
     * Instantiates a new Hangman game.
     * 
     * @param String word ; the word guessed against in the Hangman game
     * @param int guessLimit ; the maximum number of incorrect guesses in the game
     */
    void startGame();
    
    /**
     * Plays through a single round of the game
     */
    void playRound();
    
    /**
     * Cycles playRound() and ends the game when the user either wins or loses
     */
    void endGame();

	/**
	 * Returns a set of words from the game dictionary based on the length parameter.
	 * @param wordLength - length of valid words for the game.
	 * @return Set<String> of words equal in length to wordLength.
	 */
    Set<String> getWordSet(int wordLength);

	/**
	 * In a game of hangTough, returns a random word from the
	 * remaining wordSet to be given as the target word to the user.
	 * @param wordSet - Set<String> of words
	 * @return String word
	 *//*
	String getWord(Set<String> wordSet);*/

    
    
}
