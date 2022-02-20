import java.util.Iterator;

/*
You will create another class ReverseGLLIterator (in its own file) which will
be identical to the GLLIterator class except that the hasNext() and next()
methods will have logic to iterate from the list in reverse (tail to head).
*/
public class ReverseGLLIterator<E> implements Iterator<E> {
    GenericList<E>.Node<E> curNode; //copy of original list, will be destroyed to make reversedList
    GenericStack<E> reversedList = null; //Using a stack we ensure it is reversed as its built

    //DONE: Reverse GLL Iterator Constructor -
    //This is the class that will be returned when the descendingIterator()
    //method is called in the GenericList class.
    public ReverseGLLIterator(GenericList<E>.Node<E> givenHeadNode) {
        //copy given node in order to maintain the original in tact
        curNode = givenHeadNode;
        //send the given listen into a STACK to reverse its order
        if (curNode != null) {
            reversedList = new GenericStack<>(curNode.data);
            curNode = curNode.next;
            while (curNode != null) {
                reversedList.push(curNode.data);
                curNode = curNode.next;
            }
            //We now have a reverse of the original list
        }
        //Set curNode to the new list's head to use in the methods below
        curNode = reversedList.getHead();
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
