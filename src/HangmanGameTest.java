
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

/** 
 * 
 *  
 * @authors Philip Raath / Andrew Canastar collaboration
 * @version 10.20.12 
 */

public class HangmanGameTest
{
    int totalGuesses = 0; 
    int totalBlanks = 0;
    private List<String> words;
    private Set<String> threeLetterWords;

    @Before
    public void setUp(){
    	this.words = new ArrayList<String>();
    	this.threeLetterWords = new TreeSet<String>();
    	this.words.add("one");
    	this.words.add("two");
    	this.words.add("three");
    	this.words.add("four");
    	this.words.add("five");
    	this.words.add("six");
    	this.threeLetterWords.add("one");
    	this.threeLetterWords.add("two");
    	this.threeLetterWords.add("six"); 	
    }
    
    /**
     * Tests if checkGuess() returns true or false, fails if false.
     */
    @Test
    public void validateGuessTest()
    {

        HangmanGame newHang = new HangmanGame();
        assert(newHang instanceof HangmanGame );
        
        // test to verify if input is an appropriate char
       try {
    	   newHang.validateGuess('~');
    	   fail ("checkGuess() no illegalArgumentException for out of range char.");
       }
       catch (IllegalArgumentException e)
       {
    	   assertTrue(true);
       }
       catch (Exception e)
       {
    	   fail ("checkGuess() threw improper exception for out of range char.");
       }
        
        // test to verify that guess has not already been made
    }
    
    /**
     * Tests if chooseWord() returns a null value, fails if so.
     * I would like to rewrite this to test for appropriate exceptions thrown when
     * bad data is inserted in the parameter, but we need to write the class method
     * first.
     */
    @Test
    public void chooseWordTest()
    {
        // testing for exception, parameter needs to be an int
        HangmanGame newHang = new HangmanGame();
        assertNotNull( newHang.chooseWord( 5 ) );
        assertEquals(5, newHang.chooseWord(5).length());
    }
    
    /**
     * Tests if updateGuessList() returns null, fails if so.
     */
    @Test
    public void updateGuessListTest()
    {
        HangmanGame newHang = new HangmanGame();
        assertNotNull( newHang.updateGuessList( 'a' ) );
        assert( newHang.updateGuessList( 'a' ) instanceof Set);     
        StringBuffer checkElements = new StringBuffer();
        Iterator<Character> iterator = newHang.updateGuessList('b').iterator();
        while( iterator.hasNext() ) 
        {
        	checkElements.append( iterator.next() );
        }
        assertTrue( checkElements.toString().equals("ab"));
    }
    
    /**
     * In progress.
     */
    @Test
    public void decrementGuessLimitTest()
    {
        int totalGuesses = 5;
        HangmanGame newHang = new HangmanGame(totalGuesses);
        int tempGuesses = totalGuesses;
        totalGuesses = newHang.decrementGuessLimit();
        assertEquals( 1, tempGuesses - totalGuesses );
    }
    
    /**
     * Tests if updateViewList() returns null, fails if so.
     */
    @Test
    public void updateViewListTest()
    {
        String ferret = "ferret";
    	HangmanGame newHang = new HangmanGame(ferret);
        assert(newHang instanceof HangmanGame );
        assertNotNull( newHang.updateViewList(' ') );
        List<Character> viewList = new ArrayList<Character>();

        viewList = newHang.updateViewList(' '); // test for correct instantiation
        assert(ferret.length() == viewList.size());
        for (int i = 0; i<viewList.size(); i++){
            assert(viewList.get(i).equals('-'));
        }
        viewList = newHang.updateViewList('f'); //test a letter that occurs once
        assert('f'== viewList.get(0));  
        assert('-'== viewList.get(1));
        assert('-'== viewList.get(2));
        assert('-'== viewList.get(3));
        assert('-'== viewList.get(4));
        assert('-'== viewList.get(5));
        
        viewList = newHang.updateViewList('e'); // test a letter that occurs twice
        assert('f'== viewList.get(0));  
        assert('e'== viewList.get(1));
        assert('-'== viewList.get(2));
        assert('-'== viewList.get(3));
        assert('e'== viewList.get(4));
        assert('-'== viewList.get(5));
        
        viewList = newHang.updateViewList('i'); // test a letter that doesn't occur
        assert('f'== viewList.get(0));  
        assert('e'== viewList.get(1));
        assert('-'== viewList.get(2));
        assert('-'== viewList.get(3));
        assert('e'== viewList.get(4));
        assert('-'== viewList.get(5)); 
       
    }
    
    /**
     * In progress.
     */
    @Test
    public void decrementBlanksTest()
    {
        // test in void, is this correct?
        HangmanGame newHang = new HangmanGame("ferret");
        int numBlanks = 6;
        int tempBlanks = numBlanks;
        tempBlanks = newHang.decrementBlanks();
        assertEquals(1, numBlanks - tempBlanks);
    }
    
}
