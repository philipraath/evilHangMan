
import java.util.Map;
import java.util.Set;

/** 
 * 
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

	String findLongestListKey( 
            Map<String, Set<String>> patternTable);

	Map<String, Set<String>> addKeyValuePair(
			Map<String, Set<String>> patternTable,
			Map<String, Set<String>> newKeyValuePair);

	public Map<String, Set<String>> newSortedSet(String currentPattern,    
            char currentGuess,  
            Set<String> currentDictionary);

	Set<String> getDictionary(); 
	
	
	
	
}
