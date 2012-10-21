
import static org.junit.Assert.*; 
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test; 
  
import java.lang.Boolean; 
  
/** 
 * 
 *  
 * @authors Philip Raath / Andrew Canastar collaboration
 * @version 10.20.12 
 */

public class HangmanTUITest 
{ 
    /** 
     * Default constructor for test class HangmanUITest 
     */
    public HangmanTUITest() 
    { 
    } 
  
    /** 
     * Sets up the test fixture. 
     * 
     * Called before every test case method. 
     */
    @Before
    public void setUp() 
    { 
          
    } 
  
    /** 
     * Tears down the test fixture. 
     * 
     * Called after every test case method. 
     */
    @After
    public void tearDown() 
    { 
    } 
  
    /** 
     * Tests if the char returned by askGuess() is a letter. 
     */
    @Test
    public void askGuessTest() 
    { 
        AbstractHangmanUI hangUI = new HangmanTUI();    
        System.out.println("For this test, please enter a lowercase letter from A to Z."); 
        char testValue = hangUI.askGuess(); 
        assertTrue(Character.isLetter(testValue)); 
        System.out.println("For this test, please enter a word."); 
        testValue = hangUI.askGuess(); 
        assertTrue(Character.isLetter(testValue)); 
    } 
      
    /** 
     * Tests if the value returned by askGuessLimit() is a positive value. 
     */
    @Test
    public void askGuessLimitTest() 
    { 
        AbstractHangmanUI hangUI = new HangmanTUI(); 
        int testValue = hangUI.askGuessLimit(); 
        assertTrue(testValue>0); 
        assertTrue(testValue<27); 
    } 
      
    /** 
     * Tests if the value returned by askWordLength() is a positive value. 
     */
    @Test
    public void askWordLengthTest() 
    { 
        AbstractHangmanUI hangUI = new HangmanTUI(); 
        int testValue = hangUI.askWordLength(15); 
        assertTrue(testValue>0); 
        assertTrue(testValue<16); 
    } 
      
    /** 
     * For now, tests if the value returned by askNewGame() is true or false, 
     * and fails if it is false. 
     * I would like to change this test to check if a boolean is being returned. 
     */
    @Test
    public void askNewGameTest() 
    { 
        AbstractHangmanUI hangUI = new HangmanTUI(); 
        //my hope is that the following test will verify that we are receiving a 
        //boolean. if the final test fails, we don't know if the reason is a false 
        //value or a non boolean value 
        assert(Boolean.valueOf(hangUI.askNewGame()) instanceof Boolean); 
        assertEquals(true, hangUI.askNewGame()); 
    } 
} 