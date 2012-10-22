import java.util.Random;


/** 
 * Starts the hangman game. Allows the user to choose between a test or 
 * graphical interface. Randomly chooses between normal and evil hangman.
 *  
 * @authors Philip Raath / Andrew Canastar collaboration
 * @version 10.20.12 
 */

public class StartHangman {
	/**
	 * @param args
	 */
	public StartHangman() {}
	
	public void run()
	{
		HangmanControllerInterface hangController;
		//randomly choose evil or normal game play mode
		if(new Random().nextBoolean()){
			hangController = new HangToughController();}
		else{hangController = new HangmanController();}
		hangController.startUserInterface();
		hangController.startGame();
		hangController.playRound();
	}
	
	public static void main(String[] args) 
	{
		StartHangman startHanging = new StartHangman();
		startHanging.run();
	}

}
