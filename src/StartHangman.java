import java.util.Random;


/** 
 * 
 *  
 * @authors Philip Raath / Andrew Canastar collaboration
 * @version 10.20.12 
 */

// always check length of word choice, it may not exist
// change dictionary interface to return set of words
// 
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
