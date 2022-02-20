import java.util.Iterator;

/*
You must also create a class to contain logic for iterating through your
data structure(head to tail). Call this class GLLIterator (it should
be in its own file). GLLIterator should be a generic class since it
provides the logic to iterate through a generic linked list.

It should implement the java interface Iterator<E> (java.util.Iterator). You
will have to implement two inherited methods: public boolean hasNext(),
checks to see if there is another value in the data structure and returns
true or false, and 
*/

public class GLLIterator<E> implements Iterator<E> {
    GenericList<E>.Node<E> curNode;

    public GLLIterator(GenericList<E>.Node<E> givenNode){
        curNode = givenNode;
    }
    
    @Override
    public boolean hasNext() {
        // DONE:
        if (curNode != null && curNode.next != null)
            return true;
        return false;
    }

    /*
    public I next(), returns the current value in the data
    structure and advances to the next item. This is the class that will be
    returned when the iterator() method is called from the Iterable<T> interface.
    */
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
