
import java.util.ArrayList; 
import java.util.Set; 

import javax.swing.JOptionPane;

/** 
 * Integrates the various components of the hangman game: user interface, 
 * game data, and dictionary. Controls the progress of the game.
 *   
 * @authors Philip Raath / Andrew Canastar collaboration
 * @version 10.20.12 
 */

public class HangToughController implements HangmanControllerInterface 
{ 
    /** 
     * Constructor for objects of class HangmanController 
     */
    private int guessLimit; 
    private int wordLength; 
    private String word; 
    private DictionaryInterface dictionary; 
    private HangmanGameInterface hangGame; 
    private HangmanUIInterface userInterface; 
    private char currentGuess; 
      
    public HangToughController() 
    { 
    	if(chooseUIMode())
    	{
    		this.userInterface = new HangmanTUI();
    	}
    	else
    	{
    		this.userInterface = new HangmanGUI();
    	}
    	
    	this.userInterface.displayWelcome();
    	this.dictionary = new Dictionary( userInterface.askForDictionaryName() );  
    } 
      
    @Override
    /** 
     * Instantiates a user interface. 
     */
    public void startUserInterface(){ 
        this.guessLimit = userInterface.askGuessLimit(); 
        this.wordLength = userInterface.askWordLength( dictionary.lengthLongestWord 
                                                        ( dictionary.getDictionary() ) );   
    } 
      
	@Override
	/** 
     * Obtains a set of words of the correct length from a Dictionary. 
     *  
     * @param int wordLength 
     * @return Set<String> 
     */
	public Set<String> getWordSet(int wordLength) {
		Set<String> wordSet = dictionary.getWordSet(wordLength);
		return wordSet;
	} 
	
    @Override
    /** 
     * Obtains a word of the correct length from a set of words. 
     *  
     * @param int wordLength 
     * @return String 
     */
    public String getWord( int wordLength ) 
    { 
    	Set<String> wordSet = hangGame.getWordSet(); 
        word = dictionary.selectWord(wordSet);
        return word; 
    } 
      
    @Override
    /** 
     * Instantiates a new Hangman game. 
     *  
     * @param String word ; the word guessed against in the Hangman game 
     * @param int guessLimit ; the maximum number of incorrect guesses in the game 
     */
    public void startGame() 
    { 
        this.hangGame = new HangmanGame(getWordSet(wordLength), guessLimit); 
    } 
      
    @Override
    /** 
     * Plays through a single round of the game 
     */
    public void playRound() 
    { 
        do
        {
	    	userInterface.displayCurrentState(hangGame.getGuessesLeft(), hangGame.getGuessList(), (ArrayList<Character>) hangGame.getViewList()); 
	        currentGuess = userInterface.askGuess(); 
	        currentGuess = Character.toLowerCase(currentGuess); 
	        if(hangGame.checkAlreadyGuessed(currentGuess)) 
	        { 
	            userInterface.displayAlreadyGuess(currentGuess); 
	        } 
	        else{
		        hangGame.assignPatternMap(currentGuess);
		        if(hangGame.checkCorrectGuess(currentGuess)){ 
		            userInterface.displayCorrectGuess(currentGuess); 
		            hangGame.updateViewList(); 
		            hangGame.decrementBlanks(); 
		        } 
		        else
		        { 
		            userInterface.displayIncorrectGuess(currentGuess); 
		            hangGame.decrementGuessLimit(); 
		        } 
		        hangGame.updateGuessList(currentGuess); 
	        }
        
        } while(hangGame.getGuessesLeft()!=0 && hangGame.getNumBlanks()!=0);
         
        if(hangGame.getGuessesLeft()==0 && hangGame.getNumBlanks()!=0) 
        { 
            userInterface.displayLossMessage(); 
            userInterface.displayAnswer( getWord( wordLength ) ); 
        } 
        else if(hangGame.getGuessesLeft()!=0 && hangGame.getNumBlanks()==0) 
        { 
            userInterface.displayWinMessage(); 
        } 
        else if(hangGame.getGuessesLeft()==0 && hangGame.getNumBlanks()==0) 
        { 
            userInterface.displayWinMessage(); 
        } 
        endGame(); 
    } 
      
    @Override
    /** 
     * Cycles playRound() and ends the game when the user either wins or loses 
     */
    public void endGame() 
    { 
        if( userInterface.askNewGame() ) 
        { 
            StartHangman startHanging = new StartHangman(); 
            startHanging.run(); 
        } 
        else
        { 
            userInterface.displayGoodbyeMessage();
            System.exit(0); 
        } 
    }
    
    @Override
    public boolean chooseUIMode()
    {
  	  String question = "Would you like to use the graphical or command line version of the game?";
  	  String[] options = {"Graphical", "Command Line"};
  	  Boolean returnValue = false;
  	  int response = JOptionPane.showOptionDialog(null, question, null, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
  	  if(response == 0){returnValue = false;}
  	  else {returnValue = true;}
  	  return returnValue;
    }

}  
