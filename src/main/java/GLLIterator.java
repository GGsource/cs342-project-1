import java.util.Iterator;

/*
You must also create a class to contain logic for iterating through your
data structure(head to tail). Call this class GLLIterator (it should
be in its own file). GLLIterator should be a generic class since it
provides the logic to iterate through a generic linked list.
*/

public class GLLIterator<E> implements Iterator<E> {
    GenericList<E>.Node<E> curNode;

    //DONE: GLLIterator Constructor -
    //takes the given node parameter and sets curNode equal to it.
    //This is the class that will be returned when the iterator()
    //method is called from the Iterable<T> interface.
    public GLLIterator(GenericList<E>.Node<E> givenNode){
        curNode = givenNode;
    }

    //DONE: Has Next Method Override -
    //checks to see if there is another value in the data
    //structure and returns true or false
    @Override
    public boolean hasNext() {
        if (curNode != null)
            return true; //If it's not null then it has a data value
        return false;
    }

    //DONE: Next Method Override -
    //returns the current value in the data
    //structure and advances to the next item.
    @Override
    public E next() {
        E ourData = curNode.data; //Save our data value to return
        curNode = curNode.next; //Advance to next node
        return ourData; //return our data value
    }
}
