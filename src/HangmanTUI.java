
import java.util.ArrayList; 
import java.util.Scanner; 
import java.util.Set; 

/** 
 * 
 *  
 * @authors Philip Raath / Andrew Canastar collaboration
 * @version 10.20.12 
 */
  
public class HangmanTUI extends AbstractHangmanUI { 
      
  private static Scanner scanner = new Scanner(System.in); 
  
  /** 
   * Constructor for objects of class HangmanUI 
   */
  public HangmanTUI(){} 
    
  @Override
  /** 
   * Displays welcome message. 
   */
  public void displayWelcome(){ 
    System.out.println("Welcome to Hangman!!!"); 
  } 
    
  @Override
  /* 
   * Request user input for the name of the dictionary. 
   * @return String composed of the filename. 
   */
  public String askForDictionaryName() { 
	  System.out.println( "Please type in the name of the text file " +
				"you would like to use as your dictionary\n" +
				"(I'll add the .txt): ");
	  return scanner.next() + ".txt";
  } 
    
  @Override
  /** 
   * Request user input for current guess. 
   * @throws IOException 
   * @return  char 
   */
  public char askGuess()  
  { 
      System.out.println("Please enter a guess: "); 
      return validateCharacter( Character.toLowerCase( scanner.next().charAt(0) ) ); 
  } 
    
  @Override
  /** 
   * Request user input for the number of guesses allowed. 
   * @return  int 
   */
  public int askGuessLimit() 
  { 
      System.out.println("Please enter the number of guesses allowed: "); 
      return validateGuessLimit( validateAnInt( scanner.next()) ); 
  } 
    
    
  @Override
  /** 
   * Request length of word. 
   * @param maxWordLength - the integer value of the longest word in the current dictionary 
   * @return  int length of the word 
   */
  public int askWordLength( int maxWordLength ) 
  { 
    System.out.println("Please enter the desired length of word: "); 
    return validateUserWordLength( validateAnInt( scanner.next()), maxWordLength); 
  } 
    
  @Override
  /** 
   * Display current state of game consisting of guesses left, array of previously guess letters, 
   * and current pattern of blanks and correct guesses. 
   *  
   * @param int numberOfGuessesRemaining 
   * @param Set previousGuesses a set that contains all previous guesses with no duplicate values 
   * @param ArrayList<Character> currentPattern contains a pattern of letters and blanks 
   */
  public void displayCurrentState(int numberOfGuessesRemaining,  
                                    Set<Character> previousGuesses,    
                                    ArrayList<Character> currentPattern) 
  { 
    System.out.println("Guesses remaining: " + numberOfGuessesRemaining); 
    System.out.println("Previous guesses:" + previousGuesses.toString()); 
    System.out.println("Current: " + currentPattern.toString() ); 
  } 
    
  @Override
  /** 
   * Report that current guess is correct. 
   * @param char currentGuess 
   */
  public void displayCorrectGuess(char currentGuess) 
  { 
    System.out.println("You have chosen wisely!!! There is at least one " + currentGuess + "."); 
  } 
    
  @Override
  /** 
   * Report that current guess is incorrect. 
   * @param char currentGuess 
   */
  public void displayIncorrectGuess(char currentGuess) 
  { 
    System.out.println("You have chosen poorly!!! There are no " + currentGuess + "'s."); 
  } 
    
  @Override
  /** 
   * Display correct answer. 
   * @param String correctAnswer 
   */
  public void displayAnswer(String correctAnswer) 
  { 
    System.out.println("The correct answer was: " + correctAnswer + "."); 
  } 
    
  @Override
  /** 
   Display win message. 
   */
  public void displayWinMessage() 
  { 
    System.out.println("Congragulations! You win!"); 
  } 
    
  @Override
  /** 
   Display loss message. 
   */
  public void displayLossMessage() 
  { 
    System.out.println("You lose. So sorry. Stop crying..."); 
  } 
    
  public void displayAlreadyGuess(char previouslyGuessed) 
  { 
    System.out.println("You have already guessed " + previouslyGuessed + "."); 
  } 
    
  @Override
  /** 
   Ask user if they wish to play another game. 
   * @return  boolean true if user requests a new game; false otherwise 
   */
  public boolean askNewGame() 
  { 
      System.out.println("Would you like to play another game? (Y or N)?: "); 
      char response = validateYesOrNo( scanner.next() ); 
      if(response=='y') 
      { 
        return true; 
      } 
      else
      { 
        return false; 
      } 
  }

  @Override
  public void displayGoodbyeMessage() 
  {
	  System.out.println("Ok. Goodbye."); 		
  } 
  
  @Override
  public void invalidEntry( String errorMessage )
  {
	  System.out.println( errorMessage );
  }
  
  
} 