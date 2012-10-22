
import java.util.ArrayList;  
import java.util.Set; 

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/** 
 * Textbox output to request information from the user and update the user on the
 * state of the game.
 * 
 * @authors Philip Raath / Andrew Canastar collaboration
 * @version 10.20.12 
 */
  
public class HangmanGUI extends AbstractHangmanUI { 
      
  UIManager manager = new UIManager();
  
  /** 
   * Constructor for objects of class HangmanUI 
   */
  public HangmanGUI(){} 
    
  @Override
  /** 
   * Displays welcome message. 
   */
  public void displayWelcome() {
		JOptionPane.showMessageDialog(null, "Welcome to Hangman!!!");
  } 
    
  @Override
  /**
   * Request user input for the name of the dictionary. 
   * @return String composed of the filename. 
   */
  public String askForDictionaryName() { 
	  String response = JOptionPane.showInputDialog( "Please type " +
			  										"in the name of the text file\n " +
			  										"you would like to use as your dictionary\n" +
													"(I'll add the .txt): ");
	  return response + ".txt";
  } 
    
  @Override
  /** 
   * Request user input for current guess. 
   * @throws IOException 
   * @return  char 
   */
  public char askGuess()  
  { 
	  String response = JOptionPane.showInputDialog("Please enter a guess: ");
	  return validateCharacter( Character.toLowerCase( response.charAt(0) ) );
  } 
    
  @Override
  /** 
   * Request user input for the number of guesses allowed. 
   * @return  int 
   */
  public int askGuessLimit() 
  { 
	  String response = JOptionPane.showInputDialog("Please enter the number of guesses allowed: ");
	  return validateGuessLimit( validateAnInt( response) );
  } 
    
    
  @Override
  /** 
   * Request length of word. 
   * @param maxWordLength - the integer value of the longest word in the current dictionary 
   * @return  int length of the word 
   */
  public int askWordLength( int maxWordLength ) 
  { 
	  String response = JOptionPane.showInputDialog("Please enter the desired length of word: ");
	  return validateUserWordLength( validateAnInt( response), maxWordLength); 
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
	  JOptionPane.showMessageDialog(null, "Guesses remaining: " + numberOfGuessesRemaining +
			  						"\nPrevious guesses: " + previousGuesses.toString() +
			  						"\nCurrent: " + currentPattern.toString());
  } 
    
  @Override
  /** 
   * Report that current guess is correct. 
   * @param char currentGuess 
   */
  public void displayCorrectGuess(char currentGuess) 
  { 
    JOptionPane.showMessageDialog(null, "You have chosen wisely!!! There is at least one " + currentGuess + "."); 
  } 
    
  @Override
  /** 
   * Report that current guess is incorrect. 
   * @param char currentGuess 
   */
  public void displayIncorrectGuess(char currentGuess) 
  { 
	  JOptionPane.showMessageDialog(null, "You have chosen poorly!!! There are no " + currentGuess + "'s."); 
  } 
    
  @Override
  /** 
   * Display correct answer. 
   * @param String correctAnswer 
   */
  public void displayAnswer(String correctAnswer) 
  { 
    JOptionPane.showMessageDialog(null, "The correct answer was: " + correctAnswer + "."); 
  } 
    
  @Override
  /** 
   Display win message. 
   */
  public void displayWinMessage() 
  { 
    JOptionPane.showMessageDialog(null, "Congragulations! You win!"); 
  } 
    
  @Override
  /** 
   Display loss message. 
   */
  public void displayLossMessage() 
  { 
	  JOptionPane.showMessageDialog(null, "You Lose! So sorry. Stop crying..."); 
  } 
    
  public void displayAlreadyGuess(char previouslyGuessed) 
  { 
	  JOptionPane.showMessageDialog(null, "You have already guessed " + previouslyGuessed + "."); 
  } 
    
  @Override
  /** 
   Ask user if they wish to play another game. 
   * @return  boolean true if user requests a new game; false otherwise 
   */
  public boolean askNewGame() 
  { 
      String userResponse = JOptionPane.showInputDialog("Would you like to play another game? (Y or N)?: "); 
      char response = validateYesOrNo( ( Character.valueOf( userResponse.charAt( 0 ) ).toString() ) ); 
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
  /**
   * Displays good-bye when the game is ended.
   */
  public void displayGoodbyeMessage() {
	  JOptionPane.showMessageDialog(null, "Goodbye!");
	
  } 
  
  @Override
  /**
   * Displays an error message when validation of user input fails.
   */
  public String invalidEntry( String errorMessage )
  {
	  String response = JOptionPane.showInputDialog(errorMessage);
	  return response;
  }
  
} 