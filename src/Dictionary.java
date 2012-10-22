
import java.io.FileInputStream;
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.io.LineNumberReader;  
import java.io.Reader;  
import java.util.Hashtable;  
import java.util.Iterator;  
import java.util.Map;  
import java.util.Random; 
import java.util.Scanner;
import java.util.Set;  
import java.util.TreeSet;  

/** 
 * The Dictionary is used by different versions 
 *  
 * @authors Philip Raath / Andrew Canastar collaboration
 * @version 10.20.12 
 */

public class Dictionary extends AbstractDictionary {  
    
    private Set<String> dictionary = new TreeSet<String>();  
    private Set<String> validWordSet = new TreeSet<String>();  
    private Map<String, Set<String>> patternTable = new Hashtable<String, Set<String>>(); 
    
    private static Scanner scanner = new Scanner( System.in );
            
    /**
     * Default Dictionary constructor.
     */
    public Dictionary(){  
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("dictionary.txt");  
        Reader reader = new InputStreamReader(inputStream);  
        LineNumberReader lineReader = new LineNumberReader(reader);  
            
        try
        {  
            String line = null;  
            while (( line = lineReader.readLine()) != null){  
                dictionary.add(line);  
            }  
        }  
        catch(Exception e)  
        {  
            System.err.println(e.getMessage());  
        }  
    } 
    
    /**
     * Dictionary constructor with String fileName parameter. Can handle the 
     * fileName String in any format and includes enough data validation to 
     * prevent a FileNotFoundException being thrown from any text input.
     * @param fileName - a String supplied by the user
     */
    public Dictionary( String fileName )
    {
    	{
    		InputStream inputStream = null;
    		if( fileName.indexOf( '/' ) == -1 && fileName.indexOf( '\\' ) == -1 )
    		{
    			inputStream = this.getClass().getClassLoader().getResourceAsStream( fileName );  
    	        while ( inputStream == null )
    	        {
    	        	System.out.print( "A file by that name was not found.\n "
    	        			+ "Please try a different file name or type 'q' to quit:");
    	        	String choice = scanner.next();
    	        	if ( Character.toLowerCase( choice.charAt(0) ) == 'q' )
    	        	{
    	        		System.out.println( "Ok. Good-bye." );
    	        		System.exit(0);
    	        	}
    	        	else
    	        	{
    	        		inputStream = this.getClass().getClassLoader().getResourceAsStream( choice + ".txt" );
    	        	}
    	        }
    			Reader reader = new InputStreamReader(inputStream);  
    	        LineNumberReader lineReader = new LineNumberReader(reader);  
    	            
    	        try
    	        {  
    	            String line = null;  
    	            while (( line = lineReader.readLine()) != null){  
    	                dictionary.add(line);  
    	            }  
    	        }  
    	        catch(Exception e)  
    	        {  
    	            System.err.println(e.getMessage());  
    	        }  
    		}
    		else
    		{
    			try 
    			{
    				inputStream = new FileInputStream( fileName );
    			  
    		        while ( inputStream == null )
    		        {
    		        	System.out.print( "A file by that name was not found.\n "
    		        			+ "Please try a different file name or type 'q' to quit:");
    		        	String choice = scanner.next();
    		        	if ( Character.toLowerCase( choice.charAt(0) ) == 'q' )
    		        	{
    		        		System.out.println( "Ok. Good-bye." );
    		        		System.exit(0);
    		        	}
    		        	else
    		        	{
    		        		inputStream = new FileInputStream( choice + ".txt" );
    		        	}
    		        }
    				Reader reader = new InputStreamReader(inputStream);  
    		        LineNumberReader lineReader = new LineNumberReader(reader);  
    		            
    		        String line = null;  
    		        while (( line = lineReader.readLine()) != null)
    		        {  
    		                dictionary.add(line);  
    		        }  
    	        }  
    	        catch(Exception e)  
    	        {  
    	            System.err.println(e.getMessage());  
    	        } 
    	    }
    	}
    }
        
    /**
     * Main method for testing purposes.
     * @param args
     */
    public static void main(String[] args){  
        Dictionary testDictionary = new Dictionary();  
        Iterator<String> iterator = testDictionary.dictionary.iterator();  
        int maxLength = 0;  
        while(iterator.hasNext()){  
            if(iterator.next().length()>maxLength)  
            {  
                maxLength = iterator.next().length();  
            }  
        }  
            
    }  
    
    /**
     * Obtains a wordSet based on the word length supplied by the user.
     * @param - wordLength - the length
     * @return Set<String> - the valid word set.
     * @throws IllegalArgumentException.
     */
    @Override
    public Set<String> getWordSet(int wordLength) throws IllegalArgumentException   
    {  
        int longestWord = lengthLongestWord(dictionary);  
        if(wordLength>0 && wordLength<=longestWord )  
        {  
            Iterator<String> iterator = dictionary.iterator();  
            String word = null;  
            while(iterator.hasNext()){  
                word = iterator.next();  
                if(word.length()==wordLength){  
                    validWordSet.add(word);  
                        
                }  
            }  
            return validWordSet;  
        }  
        else
        {  
            throw new IllegalArgumentException("Please enter a word length between 0 and " + longestWord);  
        }  
    }  
    
    /**
     * Randomly selects a word from the valid word list.
     * @param validWordSet - Set<String> of valid words.
     * @return String - the word chosen from the list.
     */
    @Override
    public String selectWord(Set<String> validWordSet) {  
        int randomNumber = generateRandomNumber(validWordSet);  
        String selectedWord = (String) validWordSet.toArray()[randomNumber];  
        return selectedWord;  
    }  
        
    /**
     * Returns the length of the longest word found in the dictionary.
     * return int - the length of the longest word.
     */
    @Override
    public int lengthLongestWord( Set<String> dictionary ) {  
        int maxWordLength = 0;  
        Iterator<String> iterator = dictionary.iterator();  
        while( iterator.hasNext() ) {  
            int currLength = iterator.next().length();  
            if ( currLength > maxWordLength ) {  
                maxWordLength = currLength;  
            }  
        }  
        return maxWordLength;  
    }  
        
    /**
     * Returns a random number based on the size of the current valid word set.
     * @param validWordSet - to be used in obtaining a random number.
     * @return randomNumber - the random value between 0 and the size of the list inclusive.
     */
    public int generateRandomNumber(Set<String> validWordSet){  
        double randomNumberDouble = Math.random();  
        int randomNumber = (int) (randomNumberDouble * (validWordSet.size()-1));  
        return randomNumber;  
    }  
        
    /**
     * Setter for changing the Set<String> of words representing the valid dictionary.
     * @param dictionary
     */
    @Override
    public void setDictionary(Set<String> dictionary){  
        this.dictionary = dictionary;  
    }  
        
    /** 
     * Getter for the complete set of words from the dictionary file. 
     * @return Set<String> the dictionary 
     */
    @Override
    public Set<String> getDictionary(){  
        return dictionary;  
    }  
    
    /**  
     * Adds a String to a Set<String> referenced by a key parameter in the  
     * Map<String, Set<String>> parameter.   
     * @param newWord - String to be added to the Map<String, Set<String>>  
     * @param key - String identifying the key that references the target Set<String>  
     * @param patternTable - Map<String, Set<String>> to which the newWord will be added.  
     * @return Map<String, Set<String>> - the updated patternTable  
     */
    @Override
    public Map<String, Set<String>> addWord(String newWord,   
                                            String key,  
                                            Map<String, Set<String>> patternTable )  
    {  
        patternTable.get( key ).add( newWord );  
        return patternTable;  
    }  
    
    /**  
     * Returns a String containing a pattern of hyphens and the guessed char.   
     * @param currentGuess - char passed in as a guess from the user.  
     * @param currentValidWord - String passed from the valid word set.  
     * @return pattern of hyphens and the guessed char.  
     */
    @Override
    public String generatePattern(char currentGuess, String currentValidWord, String currentPattern) {  
        StringBuilder str = new StringBuilder();
    	char[] validArray = currentValidWord.toCharArray(); 
        char[] patternArray = currentPattern.toCharArray();
        int length = validArray.length;  
        for( int i = 0; i < length; i++ )  
        {  
            if( patternArray[i] == '-' && currentGuess == validArray[i] )   
            {  
                str.append( currentGuess );  
            } 
            else
            {
            	str.append( patternArray[i] );
            }
        } 
        return str.toString();  
    }  
    
    /**  
     * Instantiates the Map<String, Set<String>> patternTable with a single key, value pair  
     * where the key = String hyphens of same length as the user-specified   
     * word length; the value = an empty Set<String>.  
     * @param pattern - a String of underscores  
     * @param patternSet - an empty Set<String>  
     * @return Map<String, Set<String>> patternTable    
     */
    @Override
    public Map<String, Set<String>> initializeHashtable(String pattern,  
                                                        Set<String> patternSet)   
    {  
        patternTable.put(pattern, patternSet);  
        return patternTable;  
    }  
    
    /**  
     * Adds a new key-value pair to the Map<String, Set<String>> patternTable.  
     * @param pattern - String pattern of underscores and a target char  
     * @param patternSet - a word that fits the pattern  
     * @return the updated Map<String, Set<String>>  
     */ 
    @Override
    public Map<String, Set<String>> newKeyValuePair(String pattern,  
                                                    String patternWord)  
    {  
        Set<String> patternSet = new TreeSet<String>();  
        patternSet.add( patternWord );  
        patternTable.put(pattern, patternSet);  
        return patternTable;  
    }  
    
    /**
     * Adds a new <String, Set<String>> pair to a Map of the same type. Used
     * to add novel patterns and the words that fit them to the patternTable.
     * @param patternTable - the current list of patterns (the keys) and the Set<String> of
     * pattern words.
     * @param newKeyValuePair - Map<String, Set<String>> to add to the patternTable
     * @return tempTable - the updated Map<S,S<S>> to be used for reassignment of the patternTable
     */
    @Override
    public Map<String, Set<String>> addKeyValuePair(  
                                Map<String, Set<String>> patternTable,  
                                Map<String, Set<String>> newKeyValuePair) {  
        Map<String, Set<String>> tempTable = new Hashtable<String, Set<String>>(); 
        tempTable = patternTable; 
        tempTable.putAll(newKeyValuePair); 
        return tempTable; 
    } 
  
    /**
     * Finds the key with the Set<String> value that has the largest size(). If two keys
     * have equal sized lists, then one is randomly chosen to be the longest.
     * @param patternTable - the completed Map<String, Set<String>>.
     * @return longestKey - String with the largest size Set<String>. 
     */
    @Override
    public String findLongestListKey( 
            Map<String, Set<String>> patternTable) { 
        Set<String> longestList = new TreeSet<String>(); 
        Set<String> keySet = patternTable.keySet(); 
        Random randomBoolean = new Random(); 
        String longestKey = null;
        for (String key : keySet){ 
            if( patternTable.get(key).size()>longestList.size()){ 
                longestList = patternTable.get(key); 
                longestKey = key;
            } 
            else if (patternTable.get(key).size()==longestList.size()){ 
                if(randomBoolean.nextBoolean()){ 
                    longestList = patternTable.get(key); 
                } 
            } 
        } 
        return longestKey; 
    } 
      
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
    @Override
    public Map<String, Set<String>> newSortedSet(String currentPattern,    
                                    char currentGuess,  
                                    Set<String> currentDictionary) 
    { 
    	patternTable.clear();
    	String longestSetKey = null;
        Set<String> emptySet = new TreeSet<String>(); 
        Map<String, Set<String>> patternTable = initializeHashtable(currentPattern, emptySet); 
        for(String word : currentDictionary){ 
            String generatedPattern = generatePattern(currentGuess, word, currentPattern); 
            if(patternTable.keySet().contains(generatedPattern)){ 
                this.patternTable = addWord(word, generatedPattern, patternTable); 
            } 
            else 
            { 
                Map<String, Set<String>> newKeyValuePair = newKeyValuePair(generatedPattern, word); 
                this.patternTable = addKeyValuePair(this.patternTable, newKeyValuePair); 
            } 
        } 
        longestSetKey = findLongestListKey(patternTable);
        Map<String, Set<String>> newDictionary = new Hashtable<String, Set<String>>();
        newDictionary.put(longestSetKey, patternTable.get(longestSetKey));
        return newDictionary; 
    } 
    
}