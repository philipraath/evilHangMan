
import java.util.*;

/** 
 * 
 *  
 * @authors Philip Raath / Andrew Canastar collaboration
 * @version 10.20.12 
 */

public class HangmanGame implements HangmanGameInterface
{
	private String targetWord;
	private List<Character> viewList;
	private SortedSet<Character> guessList = new TreeSet<Character>(); 
	private DictionaryInterface dictionary = new Dictionary();
	private int guessesLeft, numBlanks;
	private Set<String> wordSet;
	
    public HangmanGame()
    {
    }

    public HangmanGame(String targetWord, int guessesLeft)
    {
    	this.targetWord = targetWord;
    	this.viewList = new ArrayList<Character>(targetWord.length());
    	this.numBlanks = targetWord.length();
    	for(int i = 0; i<numBlanks; i++)
    	{
    		viewList.add('-');
    	}
    	this.guessesLeft = guessesLeft;
    }
    
    public HangmanGame(Set<String> wordSet, int guessesLeft)
    {
    	this.wordSet = wordSet;
    	Iterator<String> iterator = wordSet.iterator();
    	int targetLength = iterator.next().length();
    	this.viewList = new ArrayList<Character>(targetLength);
    	this.numBlanks = targetLength;
    	for(int i = 0; i<numBlanks; i++)
    	{
    		viewList.add('-');
    	}
    	StringBuilder str = new StringBuilder();
    	for(int i = 0; i<viewList.size(); i++)
    	{
    		str.append(viewList.get(i));
    	}
    	this.targetWord = str.toString();
    	this.guessesLeft = guessesLeft;
    	
    }
    
    public HangmanGame(String targetWord) //for testing purposes
    {
    	this.targetWord = targetWord;
    	this.viewList = new ArrayList<Character>(targetWord.length());
    	this.numBlanks = targetWord.length();
    	for(int i = 0; i<numBlanks; i++)
    	{
    		viewList.add('-');
    	}
    }
    
    public HangmanGame(int guessesLeft) //for testing purposes
    {
    	this.guessesLeft = guessesLeft;
    }
    
    public void setTargetWord(String pattern)
    {
    	this.targetWord = pattern;
    }
    
    public void setWordSet(Set<String> currentWordSet)
    {
    	this.wordSet = currentWordSet;
    }
    
    /**
     * Returns the current wordSet. Used in hangTough version to select
     * a word to be displayed to the user on losing.
     * @return Set<String> wordSet of remaining words.
     */
    @Override
    public Set<String> getWordSet()
    {
    	return wordSet;
    }
    
    public int getGuessesLeft()
    {
    	return this.guessesLeft;
    }
    
    public void assignPatternMap(char currentGuess)
    {
    	Map<String, Set<String>> currentPattern = dictionary.newSortedSet(targetWord, currentGuess, wordSet);
    	
    	Set<String> keySet = currentPattern.keySet();
    	Iterator<String> iterator = keySet.iterator();
    	String key = (String) iterator.next();
    	setTargetWord(key);
    	
    	Set<String> returnedSet = currentPattern.get(key);
    	setWordSet(returnedSet);
    }
    
    @Override
    public boolean validateGuess(char guess) throws IllegalArgumentException
    {
    	
    	boolean isValidChar = false; 
    	if(Character.isLetter(guess))
        {
        	isValidChar = true;
        }
        else 
        {
        	throw new IllegalArgumentException("Please enter a letter.");
        }
    return isValidChar;  
    }
    
    public boolean checkAlreadyGuessed(char guess)
    {
    	boolean alreadyGuessed = false;
    	if(guessList.contains(guess))
    	{
    		alreadyGuessed = true;
    	}
    	else
    	{
    		alreadyGuessed = false;
    	}
    	
    	return alreadyGuessed;
    }
    
    public boolean checkCorrectGuess(char guess)
    {
//    	System.out.println("current char = " + guess);
    	boolean correct = false;
    	if(validateGuess(guess))
    	{
    		if(targetWord.indexOf(guess)!=-1)
    		{
    			correct = true;
    		}
    		else
    		{
    			correct = false;
    		}
    	}
		return correct;
    }
    
    /**
     * Should be implemented as static
     */
    @Override
    public String chooseWord( int numLetters )
    {
    	return dictionary.selectWord(dictionary.getWordSet(numLetters));

    }
    
    @Override
    public Set<Character> updateGuessList( char letter )
    {
        guessList.add( letter );
    	return guessList;
    }
    
    @Override
    public int decrementGuessLimit()
    {
    	return --guessesLeft;
    }
    
    public List<Character> updateViewList()
    {
    	int targetLength = targetWord.length();
    	System.out.println(targetLength);
    	
    	char[] targetWordArray = targetWord.toCharArray();
    	for(int i = 0; i< targetLength; i++)
    	{
    		if(targetWordArray[i]!=viewList.get(i))
    		{
    			viewList.set(i, targetWordArray[i]);
    		}
    	}
    	return viewList;
    }
    public List<Character> updateViewList(char input)
    {
    	
        int targetLength = targetWord.length();
    	for (int index = 0; index<targetLength; index++)
    	{
    		if(targetWord.charAt(index)==input){
    			viewList.set(index, input);
    		}
    	}
    	return viewList;
    }
    
    @Override
    public int decrementBlanks()
    {
    	int temp = 0;
    	for(Character character : targetWord.toCharArray())
    	{
    		if(character == '-'){temp++;}
    	}
    	return numBlanks=temp;
    }
    
    public int getNumBlanks()
    {
    	return numBlanks;
    }
    
    public Set<Character> getGuessList()
    {
    	return guessList;
    }
    
    public List<Character> getViewList()
    {
    	return viewList;
    }

}
