
import static org.junit.Assert.*; 
  
import java.util.Hashtable; 
import java.util.Iterator; 
import java.util.Map; 
import java.util.Set; 
import java.util.TreeSet; 
  
import org.junit.Before; 
import org.junit.Test; 

/** 
 * 
 *  
 * @authors Philip Raath / Andrew Canastar collaboration
 * @version 10.20.12 
 */

public class DictionaryTest { 
      
    private Set<String> validWordSet; 
    private Set<String> longestWordSet; 
    private Map<String, Set<String>> testTable; 
    private Map<String, Set<String>> testTable2;
    private Set<String> testList; 
    private Set<String> testList2;
      
    @Before
    public void setUp(){ 
        validWordSet = new TreeSet<String>(); 
        validWordSet.add("treat"); 
        validWordSet.add("trust"); 
        validWordSet.add("steal"); 
        validWordSet.add("fired"); 
        validWordSet.add("chair"); 
        validWordSet.add("locus"); 
          
        longestWordSet = new TreeSet<String>(); 
        longestWordSet.add("a"); 
        longestWordSet.add("aa"); 
        longestWordSet.add("aaa"); 
        longestWordSet.add("aaaa"); 
        longestWordSet.add("aaaaa"); 
        longestWordSet.add("aaaaaa"); 
        longestWordSet.add("aaaaaaa"); 
        longestWordSet.add("aaaaaaaa"); 
        longestWordSet.add("aaaaaaaaa"); 
        longestWordSet.add("aaaaaaaaaa"); 
        longestWordSet.add("aaaaaaaaaaa"); 
        longestWordSet.add("aaaaaaaaaaaa"); 
          
        testList = new TreeSet<String>(); 
        testList.add("three"); 
        testList.add("trust"); 
        testList.add("steal"); 
        testList.add("fired"); 
        testList.add("chair"); 
        testList.add("locus");
        
        testList2 = new TreeSet<String>(); 
        testList2.add("one"); 
        testList2.add("two"); 
        testList2.add("three"); 
        testList2.add("four"); 
        testList2.add("five"); 
        testList2.add("six"); 
          
        testTable = new Hashtable<String, Set<String>>(); 
        testTable.put( "testKey", testList ); 
        
        testTable2 = new Hashtable<String, Set<String>>();
        testTable2.put("key1", validWordSet);
        testTable2.put("key2", longestWordSet);
        testTable2.put("key3", testList);
        testTable2.put("key4", testList2);
          
    } 
      
    @Test
    public void lengthLongestWordTest() 
    { 
        Dictionary testDictionary = new Dictionary();    
        int length = testDictionary.lengthLongestWord(longestWordSet); 
        assertEquals(12, length); 
    } 
      
    @Test
    public void getWordSetTest() 
    { 
        // Testing: Set<String> getWordSet( int wordLength ); 
          
        // Are all words in the set of the correct length (compared to the param)? 
          
        // Should cases of zero be handled by this method or beforehand? 
          
        // All words in the set are of the correct length 
          
        // When the int is too large it will return an empty set...a method should be called to 
        // handle the empty set. 
        int wordLength = 3; 
        Dictionary testDictionary = new Dictionary(); 
        assertNotNull(testDictionary); 
        Set<String> validWords = testDictionary.getWordSet(wordLength); 
        assertNotNull(validWords); 
        Iterator<String> iterator = validWords.iterator(); 
        for (int i = 0; i<validWords.size(); i++) 
        { 
            assertEquals(wordLength, iterator.next().length()); 
        } 
          
        try{                                                 
            testDictionary.getWordSet(-1); //test that negative value throws error 
            fail("No illegal argument exception thrown"); 
            } 
        catch (IllegalArgumentException e) 
           { 
               assertTrue(true); 
           } 
        catch (Exception e) 
           { 
               fail ("checkGuess() threw improper exception for out of range value."); 
           } 
          
        try{                                                 
            testDictionary.getWordSet(0); //test that value 0 throws error 
            fail("No illegal argument exception thrown"); 
            } 
        catch (IllegalArgumentException e) 
           { 
               assertTrue(true); 
           } 
        catch (Exception e) 
           { 
               fail ("checkGuess() threw improper exception for out of range value."); 
           } 
          
        try{ 
            testDictionary.getWordSet(46); //test that value greater than 45 throws error 
            fail("No illegal argument exception thrown"); 
            } 
        catch (IllegalArgumentException e) 
           { 
               assertTrue(true); 
           } 
        catch (Exception e) 
           { 
               fail ("checkGuess() threw improper exception for out of range value."); 
           } 
    } 
      
    @Test
    public void selectWordTest() 
    {        
        // Returns a String randomly selected from the Set   
        Dictionary testDictionary = new Dictionary(); 
        assertNotNull(testDictionary); 
        assertTrue(validWordSet.contains(testDictionary.selectWord(validWordSet))); 
    } 
      
    @Test
    public void generateRandomNumberTest() 
    { 
        Dictionary testDictionary = new Dictionary(); 
        assertNotNull(testDictionary); 
        int testValue = testDictionary.generateRandomNumber(validWordSet); 
        assertTrue(testValue >-1 && testValue < validWordSet.size()); 
    } 
      
    @Test
    public void testAddWord() 
    { 
        Dictionary test = new Dictionary(); 
        String testWord  = "test"; 
        String key = "testKey"; 
        Set<String> testArray = testTable.get(key); 
        testArray.add(testWord); 
        Map<String, Set<String>> dummyTable = new Hashtable<String, Set<String>>(); 
        dummyTable.put(key, testArray); 
        testTable = test.addWord(testWord, key, testTable); 
        assertEquals( dummyTable, testTable ); 
    } 
      
    @Test
    public void testGeneratePattern() 
    { 
        Dictionary test = new Dictionary(); 
        char chr = 'a'; // the guessed char
        String initialPattern = "-----"; // the initial pattern for this first set of tests
        // All the following pattern(1-5) will test whether generatePattern updates the current
        // pattern while maintaining its original form.
        String pattern1 = "e----";
        // String pattern2 = "-e---";
        // String pattern3 = "--e--";
        // String pattern4 = "---e-";
        // String pattern5 = "----e";
        // The strings to be passed as the currentValidWord 
        String s = "bbbbb"; 
        String s1 = "abbbb"; 
        String s2 = "babbb"; 
        String s3 = "bbabb"; 
        String s4 = "bbbab"; 
        String s5 = "bbbba"; 
        String s6 = "aabbb"; 
        String s7 = "baabb"; 
        String s8 = "bbaab"; 
        String s9 = "bbbaa"; 
        String s10 = "abbba"; 
        String s11 = "babab"; 
        String s12 = "babba"; 
        // The expected returns from Dictionary.generatePattern() when the currentPattenr is all '-'
        String allBlank = "-----"; 
        String oneFirstPos = "a----"; 
        String oneSecPos = "-a---"; 
        String oneThirdPos = "--a--"; 
        String oneFourPos = "---a-"; 
        String oneLastPos = "----a"; 
        String twoAbreast = "aa---"; 
        String twoAbreast1 = "-aa--"; 
        String twoAbreast2 = "--aa-"; 
        String twoAbreast3 = "---aa"; 
        String justTwo = "a---a"; 
        String justTwo1 = "-a-a-"; 
        String justTwo2 = "-a--a"; 
        // The expected returns from Dictionary.generatePattern() when the currentPattern contains a
        // leading 'e'
        String oneSecPos1 = "ea---"; 
        String oneThirdPos1 = "e-a--"; 
        String oneFourPos1 = "e--a-"; 
        String oneLastPos1 = "e---a"; 
        // The strings are assigned the String returned from Dictionary.generatePattern( char, String, String ) 
        String allBlankTest = test.generatePattern( chr, s, initialPattern ); 
        String oneFirstPosTest = test.generatePattern( chr, s1, initialPattern ); 
        String oneSecPosTest = test.generatePattern( chr, s2, initialPattern ); 
        String oneThirdPosTest = test.generatePattern( chr, s3, initialPattern ); 
        String oneFourPosTest = test.generatePattern( chr, s4, initialPattern ); 
        String oneLastPosTest = test.generatePattern( chr, s5, initialPattern ); 
        String twoAbreastTest = test.generatePattern( chr, s6, initialPattern ); 
        String twoAbreast1Test = test.generatePattern( chr, s7, initialPattern ); 
        String twoAbreast2Test = test.generatePattern( chr, s8, initialPattern ); 
        String twoAbreast3Test = test.generatePattern( chr, s9, initialPattern ); 
        String justTwoTest = test.generatePattern( chr, s10, initialPattern ); 
        String justTwo1Test = test.generatePattern( chr, s11, initialPattern ); 
        String justTwo2Test = test.generatePattern( chr, s12, initialPattern );  
        // The strings are assigned the String returned from Dictionary.generatePattern( char, String, String )
        // The following 4 tests simulate what could happen with pattern 1, a guess of a and the valid words
       	// s2, s3, s4, and s5
        String oneSecPosTest1 = test.generatePattern( chr, s2, pattern1 ); 
        String oneThirdPosTest1 = test.generatePattern( chr, s3, pattern1 ); 
        String oneFourPosTest1 = test.generatePattern( chr, s4, pattern1 ); 
        String oneLastPosTest1 = test.generatePattern( chr, s5, pattern1 );
        // The following 4 tests simulate what could with pattern 1, a guess of 'a' and the valid words
        // s7, s8, s9, s11
        // String twoAbreast1Test1 = test.generatePattern( chr, s7, pattern1 ); 
        // String twoAbreast2Test1 = test.generatePattern( chr, s8, pattern1 ); 
        // String twoAbreast3Test1 = test.generatePattern( chr, s9, pattern1 );
        // String justTwo1Test1 = test.generatePattern( chr, s11, pattern1 );
        // assertEquals for every pair
        assertEquals( allBlank, allBlankTest ); 
        assertEquals( oneFirstPos, oneFirstPosTest ); 
        assertEquals( oneSecPos, oneSecPosTest ); 
        assertEquals( oneThirdPos, oneThirdPosTest ); 
        assertEquals( oneFourPos, oneFourPosTest ); 
        assertEquals( oneLastPos, oneLastPosTest ); 
        assertEquals( twoAbreast, twoAbreastTest ); 
        assertEquals( twoAbreast1, twoAbreast1Test ); 
        assertEquals( twoAbreast2, twoAbreast2Test ); 
        assertEquals( twoAbreast3, twoAbreast3Test ); 
        assertEquals( justTwo, justTwoTest ); 
        assertEquals( justTwo1, justTwo1Test ); 
        assertEquals( justTwo2, justTwo2Test ); 
        assertEquals( oneSecPos1, oneSecPosTest1 ); 
        assertEquals( oneThirdPos1, oneThirdPosTest1 ); 
        assertEquals( oneFourPos1, oneFourPosTest1 ); 
        assertEquals( oneLastPos1, oneLastPosTest1 ); 
    } 
      
    @Test
    public void testInitializeHashtable() 
    { 
        Dictionary test = new Dictionary(); 
        String firstPattern  = "-----"; 
        String key = "-----"; 
        Set<String> testSet = new TreeSet<String>(); 
        Map<String, Set<String>> dummyTable = new Hashtable<String, Set<String>>(); 
        dummyTable.put( key, testSet ); 
        testTable = test.initializeHashtable( firstPattern, testSet); 
        assertEquals( dummyTable, testTable ); 
    } 
      
//    @Test
//    public void testNewKeyValuePair() 
//    { 
//        Dictionary test = new Dictionary(); 
//        String key  = "-a---"; 
//        String patternWord = "taint"; 
//        Set<String> testSet = new TreeSet<String>(); 
//        Map<String, Set<String>> dummyTable = new Hashtable<String, Set<String>>(); 
//        dummyTable.put( key, testSet ); 
//        dummyTable.get( key ).add( patternWord ); 
//        testTable = test.newKeyValuePair( key, patternWord ); 
//        assertEquals( dummyTable, testTable ); 
//    } 
    
    
    @Test 
    public void testFindLongestListKey()
    {
    	Dictionary test = new Dictionary();
    	Map<String, Set<String>> thisTestList = testTable2;
//    	String thisTestKey = "key2";
    	String actualReturnedString = test.findLongestListKey(thisTestList);
    	assertNotNull(actualReturnedString);
    	assertEquals("key2", actualReturnedString);	
//    	thisTestList.remove("key2");
//    	actualReturnedString = test.findLongestListKey(thisTestList);
//    	assertEquals(testList2.size(), actualReturnedString.size());
//    	assertEquals(validWordSet.size(), actualReturnedString.size());
    }
    
    @Test 
    public void testAddKeyValuePair()
    {
    	Dictionary test = new Dictionary();
    	Map<String, Set<String>> thisTestList = testTable2;
    	Map<String, Set<String>> newKeyValuePair = new Hashtable<String, Set<String>>();
    	Set<String> newValueSet = new TreeSet<String>();
    	newValueSet.add("value");
    	newKeyValuePair.put("test", newValueSet);
    	Map<String, Set<String>> actualReturnedMap = new Hashtable<String, Set<String>>();
    	actualReturnedMap = test.addKeyValuePair(thisTestList, newKeyValuePair);
    	assertNotNull(actualReturnedMap);
    	assert(testTable2 != actualReturnedMap);
    	assert(actualReturnedMap.keySet().contains("test"));
    }
    
    @Test
    public void testNewSortedSet()
    {
    	Dictionary test = new Dictionary();
    	String currentPattern = "-----";
    	char currentGuess = 'e';
    	Map<String, Set<String>> actualReturnedMap = test.newSortedSet(currentPattern, currentGuess, validWordSet);
    	Set<String> keySet = actualReturnedMap.keySet();
    	Iterator<String> iterator = keySet.iterator();
    	String key = iterator.next();
    	Set<String> actualReturnedSet = actualReturnedMap.get(key);
    	assertNotNull(actualReturnedSet);
    	Set<String> expectedResult = new TreeSet<String>();
    	expectedResult.add("chair");
    	expectedResult.add("locus");
    	expectedResult.add("trust");
    	assertEquals(expectedResult, actualReturnedSet);
    }
} 