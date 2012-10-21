
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
		HangmanControllerInterface hangController = new HangToughController();
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
