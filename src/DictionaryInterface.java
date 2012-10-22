
import java.util.Map;
import java.util.Set;

/** 
 * Defines the central methods of the Dictionary class.
 *  
 * @authors Philip Raath / Andrew Canastar collaboration
 * @version 10.20.12 
 */

public interface DictionaryInterface {

	/**
	 * Iterates through a text file of words, collects all words of
	 * the specified word length into a Set<String> and returns this
	 * Set<String>.
	 * @param wordLength
	 * @return Set<String>
	 */
	Set<String> getWordSet( int wordLength );
	
	/**
	 * Returns a word from a Set<String> of words.
	 * @param validWordSet
	 * @return String
	 */
	String selectWord( Set<String> validWordSet );
	
	/**
	 * Returns the length of the longest word in the current dictionary
	 * @param dictionary - Set<String> list of words
	 * @return int - the length of the longest word
	 */
	int lengthLongestWord( Set<String> dictionary );
	
	Map<String, Set<String>> addWord( String newWord,  
            String key, 
            Map<String, Set<String>> patternTable );  

	/** 
	* Returns a String containing a pattern of underscores and the guessed char.  
	* @param currentGuess - char passed in as a guess from the user. 
	* @param currentValidWord - String passed from the valid word set. 
	* @return pattern of underscores and the guessed char. 
	*/
	String generatePattern( char currentGuess, String currentValidWord, String currentPattern ); 

	/** 
	* Instantiates the Map<String, Set<String>> patternTable with a single key, value pair 
	* where the key = String underscores that is the same length as the user-specified  
	* word length; the value = an empty Set<String>. 
	* @param pattern - a String of underscores 
	* @param wordFromPattern - an empty Set<String> 
	* @return Map<String, Set<String>> patternTable   
	*/
	Map<String, Set<String>> initializeHashtable( String pattern, Set<String> wordFromPattern ); 

	/** 
	* Adds a new key-value pair to the Map<String, Set<String>> patternTable. 
	* @param pattern - String pattern of underscores and a target char 
	* @param wordFromPattern - a word that fits the pattern 
	* @return the updated Map<String, Set<String>> 
	*/
	Map<String, Set<String>> newKeyValuePair( String pattern, String wordFromPattern ); 

	
	/**
     * Finds the key with the Set<String> value that has the largest size(). If two keys
     * have equal sized lists, then one is randomly chosen to be the longest.
     * @param patternTable - the completed Map<String, Set<String>>.
     * @return longestKey - String with the largest size Set<String>. 
     */
	String findLongestListKey( 
            Map<String, Set<String>> patternTable);

	/**
     * Adds a new <String, Set<String>> pair to a Map of the same type. Used
     * to add novel patterns and the words that fit them to the patternTable.
     * @param patternTable - the current list of patterns (the keys) and the Set<String> of
     * pattern words.
     * @param newKeyValuePair - Map<String, Set<String>> to add to the patternTable
     * @return tempTable - the updated Map<S,S<S>> to be used for reassignment of the patternTable
     */
	Map<String, Set<String>> addKeyValuePair(
			Map<String, Set<String>> patternTable,
			Map<String, Set<String>> newKeyValuePair);

	/**
     * Returns a new set of words to be used in the hangman game. Used in the evil version
     * of hangman to set the valid words to be the list of words associated with the pattern
     * that has the largest list.
     * @param String - the currentPattern
     * @param char - the guessed char from the last iteration of the game
     * @param currentDictionary - Set<String> with the current list of valid words
     * @return newDictionary - Map<String, Set<String>> with the updated pattern and the Set<String>
     * is the new list of words.
     */
	public Map<String, Set<String>> newSortedSet(String currentPattern,    
            char currentGuess,  
            Set<String> currentDictionary);

	/** 
     * Getter for the complete set of words from the dictionary file. 
     * @return Set<String> the dictionary 
     */
	Set<String> getDictionary(); 
	
	/**
     * Setter for changing the Set<String> of words representing the valid dictionary.
     * @param dictionary
     */
    void setDictionary(Set<String> dictionary);
	
	
	
}
