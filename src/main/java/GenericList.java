import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public abstract class GenericList<T> implements Iterable<T>{
    //Data Members
    private Node<T> head;
    private int length;

    //DONE:Print Method -
    //Prints the items in the list, one value per line.
    //If the list is empty, print "Empty list".
    public void print() {
        //Make a copy node of head to traverse without changing head itself
        Node<T> traverser = head;
        //System.out.println("Printing List of size " + length + ":");
        while (traverser != null) {
            System.out.println(traverser.data); //prints data in current node
            traverser = traverser.next; //Moves to next node
        }
        //System.out.println("End of list!");
    }
    
    //Add Method Signature -
    //This is only a signature because its implementation depends on which
    //class is implementing it. When GenericList is extended this method will
    //be required in order to work.
    abstract void add(T data);
    
    //DONE:Delete Method -
    //Return first value of the list and delete the node.
    //If empty return null.
    public T delete() {
        T hdData = head.data; //First save the value to return
        head = head.next; //Set the new head to be the next item
        length -= 1; //Adjust length
        return hdData; //return saved value
    }

    //DONE:Dumplist Method -
    //this method stores and returns all values currently in the list
    //into an ArrayList and returns it. At the end of
    //this method your list should be empty.
    public ArrayList<T> dumpList() {
        //DONE:
        ArrayList<T> returnArrayList = new ArrayList<>();
        while (length > 0) {
            returnArrayList.add(delete());
        }
        //returnArrayList should nown be full, and our GenericList should be empty
        return returnArrayList;
    }

    //DONE: Get Method -
    //returns the value at the specified index
    //or null if the index is out of bounds.
    public T get(int index) {
        Node<T> traverserNode = head; //copy of head to leave original unchanged
        int curNdx = 0; //To keep track of when we arrive at the correct index
        while (curNdx < index && traverserNode != null) {
            traverserNode = traverserNode.next;
            curNdx++;
        }
        //We should now be at the correct node, or at null
        if (traverserNode == null)
            return null; //node is null so we were out of bounds or empty
        return traverserNode.data; //Return our data
    }

    //DONE: Set Method - 
    //replace the element at specified position in the list with the specified
    //element and return the element previously at the specified position.
    //Return null if index is out of bounds 
    public T set(int index, T element) {
        Node<T> traverserNode = head; //copy of head to leave original in tact
        int curNdx = 0; //to track when we arrive at index
        while (curNdx < index && traverserNode != null) {
            traverserNode = traverserNode.next;
            curNdx++;
        }
        //We should now be at the correct node, or at null
        if (traverserNode == null)
            return null;
        T oldVal = traverserNode.data; //Store old value to return
        traverserNode.data = element; //Edit the data to our new given element
        return oldVal; //return old value
    }

    //DONE: Get Length Method -
    //Returns length integer of amount of nodes in list
    public int getLength() {
        return length;
    }
    //DONE: Set Length Method -
    //Alters current length value to new given value
    public void setLength(int newLength) {
        length = newLength;
    }
    //DONE: Get Head Method -
    //Returns the head node of list
    public Node<T> getHead() {
        return head;
    }
    //DONE: Set Head Method -
    //Alters current head node to new given node
    public void setHead(Node<T> newHead) {
        head = newHead;
    }

    //DONE: Iterator Method -
    //Creates a GLLIterator to traverse list
    public Iterator<T> iterator() {
        GLLIterator<T> newGLLIterator = new GLLIterator<>(head);
        return newGLLIterator;
    }

    //DONE: List Iterator Method -
    //returns a list-iterator of the elements of this
    //list starting at the specified position.
    public ListIterator<T> listIterator(int index) {
        GLListIterator<T> newGLListIterator = new GLListIterator<>(index, head);
        return newGLListIterator;
    }

    //DONE: Descending Iterator Method -
    //returns an iterator over the elements of the
    //list in reverse order(tail to head)
    public Iterator<T> descendingIterator() {
        ReverseGLLIterator<T> newReverseGLLIterator = new ReverseGLLIterator<>(head);
        return newReverseGLLIterator;
    }

    //DONE: Node Class -
    //Class defining Nodes
    public class Node<T2> {
        public T2 data; //Holds value of given node
        public Node<T2> next = null; //Points to next node, or null if there is no next

        //Node Constructor -
        //Sets the data field equal to given parameter val at declaration time
        public Node(T2 val) {
            this.data = val;
        }
    }
}