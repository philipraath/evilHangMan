
import java.util.ArrayList;  
import java.util.Set; 

/** 
 * 
 *  
 * @authors Philip Raath / Andrew Canastar collaboration
 * @version 10.20.12 
 */

public class HangmanController implements HangmanControllerInterface 
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
      
    public HangmanController() 
    { 
        this.userInterface = new HangmanGUI(); 
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
     * Obtains a word of the correct length from a Dictionary. 
     *  
     * @param int wordLength 
     * @return String 
     */
    public String getWord( int wordLength ) 
    { 
        Set<String> wordSet = dictionary.getWordSet(wordLength); 
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
        this.hangGame = new HangmanGame(getWord(wordLength), guessLimit); 
    } 
      
    @Override
    /** 
     * Plays through a single round of the game 
     */
    public void playRound() 
    { 
        userInterface.displayCurrentState(hangGame.getGuessesLeft(), hangGame.getGuessList(), (ArrayList<Character>) hangGame.getViewList()); 
//      hangGame.validateGuess(userInterface.askGuess()); 
        currentGuess = userInterface.askGuess(); 
        currentGuess = Character.toLowerCase(currentGuess); 
        if(hangGame.checkAlreadyGuessed(currentGuess)) 
        { 
            userInterface.displayAlreadyGuess(currentGuess); 
        } 
        else if(hangGame.checkCorrectGuess(currentGuess)){ 
            userInterface.displayCorrectGuess(currentGuess); 
            hangGame.updateViewList(currentGuess); 
            hangGame.decrementBlanks(); 
        } 
        else
        { 
            userInterface.displayIncorrectGuess(currentGuess); 
            hangGame.decrementGuessLimit(); 
        } 
        hangGame.updateGuessList(currentGuess); 
          
        if(hangGame.getGuessesLeft()!=0 && hangGame.getNumBlanks()!=0) 
        { 
            playRound(); 
        } 
        else if(hangGame.getGuessesLeft()==0 && hangGame.getNumBlanks()!=0) 
        { 
            userInterface.displayLossMessage(); 
            userInterface.displayAnswer(word); 
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
            System.out.println("Ok. Goodbye."); 
            System.exit(0); 
        } 
    }

	@Override
	public Set<String> getWordSet(int wordLength) {
		// TODO Auto-generated method stub
		return null;
	} 
      
      
      
      
  
} 