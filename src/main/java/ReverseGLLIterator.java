import java.util.Iterator;

/*
You will create another class ReverseGLLIterator (in its own file) which will
be identical to the GLLIterator class except that the hasNext() and next()
methods will have logic to iterate from the list in reverse (tail to head).
This is the class that will be returned when the descendingIterator() method
is called in the GenericList class.
*/
//FIXME: student headers for these files
public class ReverseGLLIterator<E> implements Iterator<E> {
    GenericList<E>.Node<E> curNode; //copy of original list, will be destroyed to make reversedList
    GenericStack<E> reversedList = null; //Using a stack we ensure it is reversed as its built

    //Constructor
    public ReverseGLLIterator(GenericList<E>.Node<E> givenHeadNode) {
        //DONE:
        //Save the given node in order to maintain the original in tact
        curNode = givenHeadNode;
        //send the given listen into a stack to reverse it
        if (curNode != null) {
            reversedList = new GenericStack<>(curNode.data);
            curNode = curNode.next;
            while (curNode != null) {
                reversedList.push(curNode.data);
                curNode = curNode.next;
            }
        }
        //Set curNode to the new list's head to use in the methods below
        curNode = reversedList.getHead();
    }

    @Override
    public boolean hasNext() {
        // DONE:
        if (curNode != null)
            return true;
        return false;
    }

    @Override
    public E next() {
        // DONE:
        //Save our data value to return
        E ourData = curNode.data;
        //Advance to next node
        curNode = curNode.next;
        //return our data value
        return ourData;
    }
    
}
