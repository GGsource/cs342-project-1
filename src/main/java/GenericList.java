import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public abstract class GenericList<T> implements Iterable<T>{
    private Node<T> head;
    private int length;

    public void print() {
        //Prints the items in the list, one value per line.
        //If the list is empty, print "Empty list".
        //TODO:
    }
    
    abstract void add(T data);
    
    public T delete() {
        //Return first value of the list and delete the node.
        //If empty return null.
        //DONE:
        //First save the value to return
        T hdData = head.data;
        //Set the new head to be the next item
        head = head.next;
        //Adjust length
        length -= 1;
        //return saved value
        return hdData;
    }

    public ArrayList<T> dumpList() {
        //this method stores and returns all values currently in the list
        // into an ArrayList and returns it. At the end of
        //this method your list should be empty.
        //TODO:

        return null;
    }

    public T get(int index) {
        //returns the value at the specified index
        //or null if the index is out of bounds.
        //TODO:

        return null;
    }

    public T set(int index, T element) {
        //replace the element at specified position in the list with the specified element
        //and return the element previously at the specified position.
        //Return null if index is out of bounds
        //TODO:

        return null;
    }

    public int getLength() {
        return length;
    }
    public void setLength(int newLength) {
        length = newLength;
    }
    public Node<T> getHead() {
        return head;
    }
    public void setHead(Node<T> newHead) {
        head = newHead;
    }

    public ListIterator<T> listIterator(int index) {
        //returns a list-iterator of the elements of this list starting at the specified position.
        //TODO:
        return null;
    }

    public Iterator<T> descendingIterator() {
        //returns an iterator over the elements of the list in reverse order( tail to head)
        //TODO:
        return null;
    }

    public class Node<T2> {
        public T2 data;
        public Node<T2> next;

        public Node(T2 val) {
            this.data = val;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}