
import java.util.*;

/** 
 * Stores and modifies the data associated with the hangman game.
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
	
    /**
     * Default constructor
     */
	public HangmanGame()
    {
    }

    /**
     * Constructs the state of a normal hangman game. 
     * @param targetWord - String, the target word the user is guessing against
     * @param guessesLeft - int, the number of guesses remaining
     */
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
    
	/**
     * Constructs the state of an evil hangman game. 
     * @param wordSet - Set<String>, the set of words the user is guessing against
     * @param guessesLeft - int, the number of guesses remaining
     */
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
    
    /**
     * A Constructor used only in testing.
     * @param targetWord
     */
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
    
    /**
     * A setter method for guesses left. Only for testing purposes
     * @param guessesLeft
     */
	public HangmanGame(int guessesLeft) //for testing purposes
    {
    	this.guessesLeft = guessesLeft;
    }
    
    /**
     * A setter method to update the target word as the game progresses in evil hangman.
     * @param pattern - String, the current pattern used by the game.
     */
	public void setTargetWord(String pattern)
    {
    	this.targetWord = pattern;
    }
    
    /**
     * A setter method to update the valid word list as the game progresses in evil hangman.
     * @param currentWordSet - Set<String> the current wordSet used by the game
     */
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
    
    /**
     * Returns the number of remaining guesses.
     * @return int - the number of guesses remaining.
     */
    public int getGuessesLeft()
    {
    	return this.guessesLeft;
    }
    
    /**
     * Sets the current pattern and set of words for the current iteration of the game.
     * @param char - currentGuess, the user's last guess
     */
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
    
    /**
     * Checks whether the guess is already found in the list of guesses.
     * @return alreadyGuessed - boolean true if the Set of guesses contains the current guess.
     */          
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
    
    /**
     * Checks whether the guess is found in the target word or pattern.
     * @param char - the current guess
     * @return correct - boolean true if the char is found in the target pattern.
     */
    public boolean checkCorrectGuess(char guess)
    {
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
     * Selects a word from the current list of valid words to be used.
     * @param int - the length of the word.
     * @return String - the word obtained from the dictionary.
     */
    @Override
    public String chooseWord( int numLetters )
    {
    	return dictionary.selectWord(dictionary.getWordSet(numLetters));

    }
    
    /**
     * Adds the latest guess to the list of guesses.
     * @param char - the current user guess.
     * @return guessList - Set<Character> of all the guesses.
     */
    @Override
    public Set<Character> updateGuessList( char letter )
    {
        guessList.add( letter );
    	return guessList;
    }
    
    /**
     * Reduces the number of guesses left by 1 and returns this value
     * @return guessesLeft - int number of guesses left.
     */
    @Override
    public int decrementGuessLimit()
    {
    	return --guessesLeft;
    }
    
    /**
     * Modifies the pattern of '-' and letters to be displayed to the user. Used
     * in the evil version of hangman.
     * @return viewList - List<Character> of chars.
     */
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
    
    /**
     * Modifies the pattern of '-' and letters to be displayed to the user. Used
     * in the normal version of hangman.
     * @param char - the char added to be used to modify the viewList.
     * @return viewList - List<Character> of chars.
     */
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
    
    /**
     * Decreases the number of blanks in the pattern.
     * @return numBlanks - int the number of blanks remaining.
     */
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
    
    /**
     * Returns the number of '-' left in the pattern.
     * @return numBlanks - int the number of blanks left.
     */
    public int getNumBlanks()
    {
    	return numBlanks;
    }
    
    /**
     * Returns the guessList.
     * @return guessList - the Set of guesses made by the user.
     */
    public Set<Character> getGuessList()
    {
    	return guessList;
    }
    
    /**
     * Returns the pattern or viewList for display.
     * @return viewList - the pattern of remaining '-' and letters remaining in the target word.
     */
    public List<Character> getViewList()
    {
    	return viewList;
    }

}
