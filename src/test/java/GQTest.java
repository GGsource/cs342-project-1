import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.ListIterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class GQTest {
    private GenericQueue<String> testQueue;
    private static int paramNdx;

    @BeforeAll
    static void setUp() {
        paramNdx = 0;
    }
    
    @BeforeEach
    void init() {
        testQueue = new GenericQueue<>("Alabama"); //Has 1 item
        testQueue.add("Belgium"); //2 items
        testQueue.add("China"); //3 items
        testQueue.add("Durango"); //4 items
        testQueue.add("Ethiopia"); //5 items
    }

    //Queue Method Tests
    @Test
    void addSizeTest() {
        int startingLen = testQueue.getLength(); //Starts with 5 items
        testQueue.add("Florence"); //6 items
        testQueue.add("Galacia"); //7 items
        testQueue.add("Honduras"); //8 items
        testQueue.add("India"); //9 items
        testQueue.add("Japan"); //10 items
        testQueue.add("Kansas"); //11 items
        testQueue.add("Lombary"); //12 items
        testQueue.add("Montenegro"); //13 items
        testQueue.add("Nepal"); //14 items
        testQueue.add("Oriat"); //15 items
        //10 items were added here
        assertEquals(startingLen + 10, testQueue.getLength(), "Length did not match up...");
    }
    @ParameterizedTest
    @ValueSource(strings = {"Alabama", "Belgium", "China", "Durango", "Ethiopia"})
    void addValueParamTest(String location) {
        assertEquals(location, testQueue.get(paramNdx), "Value was incorrect for parameterized test...");
        paramNdx++;
    }

    @Test
    void removeTailTest() {
        //Before we call removeTail, Tail is set to "Ethiopia"
        assertEquals("Ethiopia", testQueue.tail.data, "Tail is not Ethiopia node...");
        testQueue.removeTail();
        //After removal, tail should return "Durango"
        assertEquals("Durango", testQueue.tail.data, "Tail is not Durango node...");
        //There should also be nothing after "Durango"'s node
        assertEquals(null, testQueue.tail.next, "Old tail node is still present...");
    }

    @Test
    void enqueueTest() {
        int startingLen = testQueue.getLength();
        testQueue.enqueue("Finland");
        assertEquals(startingLen + 1, testQueue.getLength(), "enqueue failed to increment length...");
        assertEquals("Finland", testQueue.tail.data, "enqueue did not add the correct value...");
    }
    
    @Test
    void dequeueTest() {
        //Store the original head to compare
        String oldHeadString = testQueue.getHead().data;
        //dequeue - remove the head
        testQueue.dequeue();
        //confirm the head is now different
        assertNotEquals(oldHeadString, testQueue.getHead().data, "Head is still the same as before...");
        //confirm head is specifically "Belgium"
        assertEquals("Belgium", testQueue.getHead().data, "New head was not set to expected value...");
    }

    //Constructor Tests
    @Test
    void queueConstructorSizeTest() {
        //Call constructor
        testQueue = new GenericQueue<>("Athens");
        //Make sure it has exactly 1 item
        assertEquals(1, testQueue.getLength(), "Constructor does not have size 1 at initialization...");
    }
    @Test
    void queueConstructorValueTest() {
        //Call constructor
        testQueue = new GenericQueue<>("Australia");
        //Make sure it is the correct string
        assertEquals("Australia", testQueue.getHead().data, "Constructor was not intialized to expected value...");
    }
    @Test
    void queueConstructorHeadTailTest() {
        //Call constructor
        testQueue = new GenericQueue<>("Amazon");
        //Make sure head and tail point to the same node
        assertEquals(testQueue.getHead(), testQueue.tail, "Head and tail are not pointing to the same value at initialization...");
    }

    //forEach loop test
    @Test
    void forEachLoopTest() {
        String[] expectedLocations = {"Alabama", "Belgium", "China", "Durango", "Ethiopia"};
        String[] actualLocations = new String[testQueue.getLength()];
        int ourNdx = 0;
        for (String location: testQueue) {
            actualLocations[ourNdx] = location;
            ourNdx++;
        }
        //actualLocations is now populated with locations from this forEach loop
        assertArrayEquals(expectedLocations, actualLocations, "Values from for loop are not same as expected...");
    }

    //Iterators tests
    @Test
    void iteratorTest() {
        String[] expectedArray = {"Alabama", "Belgium", "China", "Durango", "Ethiopia"};
        String[] actualArray = new String[testQueue.getLength()];
        int ndx = 0;

        Iterator<String> myIt = testQueue.iterator();
        while (myIt.hasNext()) {
            actualArray[ndx] = myIt.next();
            ndx++;
        }
        //actualArray should now be populated with data from our iterator
        assertArrayEquals(expectedArray, actualArray, "Incorrect values returned by iterator...");
    }
    @Test
    void descendingIteratorTest() {
        String[] expectedArray = {"Ethiopia", "Durango", "China", "Belgium", "Alabama"};
        String[] actualArray = new String[testQueue.getLength()];
        int ndx = 0;

        Iterator<String> myIt = testQueue.descendingIterator();
        while (myIt.hasNext()) {
            actualArray[ndx] = myIt.next();
            ndx++;
        }
        //actualArray should now be populated with data from our descendingIterator
        assertArrayEquals(expectedArray, actualArray, "Incorrect values returned by descendingIterator...");
    }
    @Test
    void listIteratorNextTest() {
        //Get a list iterator starting at index 2
        int chosenNdx = 2;
        ListIterator<String> myListIt = testQueue.listIterator(chosenNdx);
        String[] expectedStrings = {"China", "Durango", "Ethiopia"}; //index 2 through 4
        String[] actualStrings = new String[testQueue.getLength() - chosenNdx];
        int ndx = 0;

        while (myListIt.hasNext()) {
            actualStrings[ndx] = myListIt.next();
            ndx++;
        }
        //actualStrings should now hold correct values from index 2 onwards
        assertArrayEquals(expectedStrings, actualStrings, "Incorrect values returned by listIterator going forward...");
        
    }
    @Test
    void listIteratorPreviousTest() {
        //Get a list iterator starting at index 2
        int chosenNdx = 2;
        ListIterator<String> myListIt = testQueue.listIterator(chosenNdx);
        String[] expectedStrings = {"China", "Belgium", "Alabama"}; //index 2 through 4
        String[] actualStrings = new String[testQueue.getLength() - chosenNdx];
        int ndx = 0;

        while (myListIt.hasPrevious()) {
            actualStrings[ndx] = myListIt.previous();
            ndx++;
        }
        //actualStrings should now hold correct values from index 2 onwards
        assertArrayEquals(expectedStrings, actualStrings, "Incorrect values returned by listIterator going backward...");
        
    }
    @Test
    void listIteratorNextIndexTest() {
        int chosenNdx = 2;
        ListIterator<String> myListIt = testQueue.listIterator(chosenNdx);
        boolean statementA = chosenNdx + 1 == myListIt.nextIndex();
        assertTrue(statementA, "nextIndex returned incorrect index...");
    }
    @Test
    void listIteratorPreviousIndexTest() {
        int chosenNdx = 2;
        ListIterator<String> myListIt = testQueue.listIterator(chosenNdx);
        boolean statementB = chosenNdx -1 == myListIt.previousIndex();
        assertTrue(statementB, "previousIndex returned incorrect index...");
    }

    //Node Constructor Tests
    @Test
    void nodeConstructorValueTest() {
        GenericList<String>.Node<String> testNode = testQueue.new Node<String>("Qatar");
        assertEquals("Qatar", testNode.data);
    }
    @Test
    void nodeConstructorNullNextTest() {
        GenericList<String>.Node<String> testNode = testQueue.new Node<String>("Rwanda");
        //next should be null as this is a lone node
        assertEquals(null, testNode.next);
    }
    @Test
    void nodeConstructorPopulatedNextTest() {
        //However in something populated like testQueue, head should have a valid next
        assertNotEquals(null, testQueue.getHead().next);
        //Next for head should be belgium
        assertEquals("Belgium", testQueue.getHead().next.data);
    }
}
