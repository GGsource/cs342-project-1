import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public abstract class GenericList<T> implements Iterable<T>{
    private Node<T> head;
    private int length;

    public void print() {
        //Prints the items in the list, one value per line.
        //If the list is empty, print "Empty list".
        //DONE:
        Node<T> traverser = head;
        System.out.println("Printing List of size " + length + ":");
        while (traverser != null) {
            System.out.println(traverser.data);
            traverser = traverser.next;
        }
        System.out.println("End of list!");
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
        //DONE:
        ArrayList<T> returnArrayList = new ArrayList<>();
        while (length > 0) {
            returnArrayList.add(delete());
        }
        //returnArrayList should nown be full, and our GenericList should be empty
        return returnArrayList;
    }

    public T get(int index) {
        //returns the value at the specified index
        //or null if the index is out of bounds.
        //DONE:
        Node<T> traverserNode = head;
        int curNdx = 0;
        while (curNdx != index && traverserNode != null) {
            traverserNode = traverserNode.next;
            curNdx++;
        }
        //We should now be at the correct node, or at null
        if (traverserNode == null)
            return null;
        return traverserNode.data;
    }

    public T set(int index, T element) {
        //replace the element at specified position in the list with the specified
        //element and return the element previously at the specified position.
        //Return null if index is out of bounds
        //DONE:

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

    public Iterator<T> iterator() {
        //DONE:
        GLLIterator<T> newGLLIterator = new GLLIterator<>(head);
        return newGLLIterator;
    }

    public ListIterator<T> listIterator(int index) {
        //returns a list-iterator of the elements of this
        //list starting at the specified position.
        //DONE:
        GLListIterator<T> newGLListIterator = new GLListIterator<>(index, head);
        return newGLListIterator;
    }

    public Iterator<T> descendingIterator() {
        //returns an iterator over the elements of the list in reverse order( tail to head)
        //DONE:
        ReverseGLLIterator<T> newReverseGLLIterator = new ReverseGLLIterator<>(head);
        return newReverseGLLIterator;
    }

    public class Node<T2> {
        public T2 data;
        public Node<T2> next = null;

        public Node(T2 val) {
            this.data = val;
        }
    }
}