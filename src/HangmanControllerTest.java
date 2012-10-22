
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** 
 * Tests the functions of the hangman controller.
 *  
 * @authors Philip Raath / Andrew Canastar collaboration
 * @version 10.20.12 
 */

public class HangmanControllerTest
{
    /**
     * Default constructor for test class HangmanControllerTest
     */
    public HangmanControllerTest()
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
     * This tests whether a String is returned when calling HangmanController.getWord().
     * It also tests whether the word obtained is of the same length as the int
     * parameter passed to HangmanController.getWord().
     */
    @Test
    public void getWordTest()
    {
        HangmanController hangController = new HangmanController();
        assertNotNull(hangController.getWord(2));
        assertEquals( 5, hangController.getWord( 5 ).length() );
        // could also return a word and make sure that it exists in dictionary
    }    
    
    @Test
    public void chooseUIMode()
    {
    	HangToughController hangController = new HangToughController();
    	
    	assertTrue(hangController.chooseUIMode());
    }
}
