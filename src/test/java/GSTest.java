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

public class GSTest {
    private GenericStack<Double> testStack;
    private static int paramNdx;

    @BeforeAll
    static void setUp() {
        paramNdx = 0;
    }
    
    @BeforeEach
    void init() {
        testStack = new GenericStack<>(5.0); //Has 1 item
        testStack.add(8.0); //2 items
        testStack.add(13.0); //3 items
        testStack.add(21.0); //4 items
        testStack.add(34.0); //5 items
    }

    //Stack Method Tests
    @Test
    void addSizeTest() {
        int startingLen = testStack.getLength(); //Starts with 5 items
        testStack.add(55.0); //6 items
        testStack.add(89.0); //7 items
        testStack.add(144.0); //8 items
        testStack.add(233.0); //9 items
        testStack.add(377.0); //10 items
        testStack.add(610.0); //11 items
        testStack.add(987.0); //12 items
        testStack.add(1597.0); //13 items
        testStack.add(2584.0); //14 items
        testStack.add(4181.0); //15 items
        //10 items were added here
        assertEquals(startingLen + 10, testStack.getLength(), "Length did not match up after adding new items..");
    }
    @ParameterizedTest
    @ValueSource(doubles = {34.0, 21.0, 13.0, 8.0, 5.0})
    void addValueParamTest(Double numba) {
        assertEquals(numba, testStack.get(paramNdx));
        paramNdx++;
    }

    @Test
    void removeTailTest() {
        //Before we call removeTail, Tail is set to 34.0
        assertEquals(5.0, testStack.tail.data, "Tail is not correct node...");
        testStack.removeTail();
        //After removal, tail should return 21.0
        assertEquals(8.0, testStack.tail.data, "Tail is not correct node after removeTail...");
        //There should also be nothing after 21.0 node
        assertEquals(null, testStack.tail.next, "Old tail node is still present...");
    }

    @Test
    void pushTest() {
        int startingLen = testStack.getLength();
        testStack.push(45.5);
        assertEquals(startingLen + 1, testStack.getLength(), "push failed to increment length...");
        assertEquals(45.5, testStack.getHead().data, "push did not add the correct value...");
    }
    
    @Test
    void popTest() {
        //Store the original head to compare
        Double oldHeadDouble = testStack.getHead().data;
        //pop - remove the head
        testStack.pop();
        //confirm the head is now different
        assertNotEquals(oldHeadDouble, testStack.getHead().data, "Head is still the same as before...");
        //confirm head is specifically 21.0
        assertEquals(21.0, testStack.getHead().data, "New head was not set to expected value...");
    }

    //Constructor Tests
    @Test
    void stackConstructorSizeTest() {
        //Call constructor
        testStack = new GenericStack<>(1.06);
        //Make sure it has exactly 1 item
        assertEquals(1, testStack.getLength(), "Constructor does not have size 1 at initialization...");
    }
    @Test
    void stackConstructorValueTest() {
        //Call constructor
        testStack = new GenericStack<>(3.33);
        //Make sure it is the correct value
        assertEquals(3.33, testStack.getHead().data, "Constructor was not intialized to expected value...");
    }
    @Test
    void stackConstructorHeadTailTest() {
        //Call constructor
        testStack = new GenericStack<>(9.845);
        //Make sure head and tail point to the same node
        assertEquals(testStack.getHead(), testStack.tail, "Head and tail are not pointing to the same value at initialization...");
    }

    //forEach loop test
    @Test
    void forEachLoopTest() {
        Double[] expectedNumbas = {34.0, 21.0, 13.0, 8.0, 5.0};
        Double[] actualNumbas = new Double[testStack.getLength()];
        int ourNdx = 0;
        for (Double numba : testStack) {
            actualNumbas[ourNdx] = numba;
            ourNdx++;
        }
        //actualNumbas is now populated with numbas from this forEach loop
        assertArrayEquals(expectedNumbas, actualNumbas, "Values from for loop are not same as expected...");
    }

    //Iterators tests
    @Test
    void iteratorTest() {
        Double[] expectedArray = {34.0, 21.0, 13.0, 8.0, 5.0};
        Double[] actualArray = new Double[testStack.getLength()];
        int ndx = 0;

        Iterator<Double> myIt = testStack.iterator();
        while (myIt.hasNext()) {
            actualArray[ndx] = myIt.next();
            ndx++;
        }
        //actualArray should now be populated with data from our iterator
        assertArrayEquals(expectedArray, actualArray, "Incorrect values returned by iterator...");
    }
    @Test
    void descendingIteratorTest() {
        Double[] expectedArray = {5.0, 8.0, 13.0, 21.0, 34.0};
        Double[] actualArray = new Double[testStack.getLength()];
        int ndx = 0;

        Iterator<Double> myIt = testStack.descendingIterator();
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
        ListIterator<Double> myListIt = testStack.listIterator(chosenNdx);
        Double[] expectedDoubles = {13.0, 8.0, 5.0}; //index 2 through 4
        Double[] actualDoubles = new Double[testStack.getLength() - chosenNdx];
        int ndx = 0;

        while (myListIt.hasNext()) {
            actualDoubles[ndx] = myListIt.next();
            ndx++;
        }
        //actualDoubles should now hold correct values from index 2 onwards
        assertArrayEquals(expectedDoubles, actualDoubles, "Incorrect values returned by listIterator going forward...");
        
    }
    @Test
    void listIteratorPreviousTest() {
        //Get a list iterator starting at index 2
        int chosenNdx = 2;
        ListIterator<Double> myListIt = testStack.listIterator(chosenNdx);
        Double[] expectedDoubles = {13.0, 21.0, 34.0}; //index 2 through 4
        Double[] actualDoubles = new Double[testStack.getLength() - chosenNdx];
        int ndx = 0;

        while (myListIt.hasPrevious()) {
            actualDoubles[ndx] = myListIt.previous();
            ndx++;
        }
        //actualDoubles should now hold correct values from index 2 onwards
        assertArrayEquals(expectedDoubles, actualDoubles, "Incorrect values returned by listIterator going backward...");
        
    }
    @Test
    void listIteratorNextIndexTest() {
        int chosenNdx = 2;
        ListIterator<Double> myListIt = testStack.listIterator(chosenNdx);
        boolean statementA = chosenNdx + 1 == myListIt.nextIndex();
        assertTrue(statementA, "nextIndex returned incorrect index...");
    }
    @Test
    void listIteratorPreviousIndexTest() {
        int chosenNdx = 2;
        ListIterator<Double> myListIt = testStack.listIterator(chosenNdx);
        boolean statementB = chosenNdx -1 == myListIt.previousIndex();
        assertTrue(statementB, "previousIndex returned incorrect index...");
    }

    //Node Constructor Tests
    @Test
    void nodeConstructorValueTest() {
        GenericList<Double>.Node<Double> testNode = testStack.new Node<Double>(59.37);
        assertEquals(59.37, testNode.data);
    }
    @Test
    void nodeConstructorNullNextTest() {
        GenericList<Double>.Node<Double> testNode = testStack.new Node<Double>(66.299);
        //next should be null as this is a lone node
        assertEquals(null, testNode.next);
    }
    @Test
    void nodeConstructorPopulatedNextTest() {
        //However in something populated like testStack, head should have a valid next
        assertNotEquals(null, testStack.getHead().next);
        //Next for head should be belgium
        assertEquals(21.0, testStack.getHead().next.data);
    }
}
