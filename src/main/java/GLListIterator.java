import java.util.ListIterator;

/*
You will also need to create a class to contain the logic for the list-iterator,
call it GLListIterator (in its own file). It should implement the Java interface
ListIterator<E> (java.util.ListIterator).
*/

public class GLListIterator<E> implements ListIterator<E> {
    DoublyLinkedList<E>.DLNode<E> curNode = null;
    int curNdx = 0;

    //Private Helper Class Doubly Linked List -
    //ListIterators need to traverse both directions, so I have implemented a new
    //List type for this iterator to use, as well as a dedicated node.
    private class DoublyLinkedList<F> {
        private DLNode<F> dHead = null;

        //Private Helper Doubly Linked List Constructor -
        //Takes a normal node from a GenericList and creates a new DoublyLinkedList
        //made out of DLNodes that can point to next AND previous nodes.
        private DoublyLinkedList(GenericList<F>.Node<F> originalHead) {
            dHead = new DLNode<>(originalHead.data); //New head node for doublylinkedlist
            DLNode<F> dprevNode = dHead; //start prev off at head. Cur will begin at next, so this will point to a previous node always
            //copy the original node to leave the head unchanged by our traversal
            GenericList<F>.Node<F> curTraverseNode = originalHead.next;
            while (curTraverseNode != null) {
                //create a new dNode for current node from original list
                dprevNode.next = new DLNode<>(curTraverseNode.data);
                //set the "previous" field for the next dnode to be the node we are currently on
                dprevNode.next.previous = dprevNode;
                //Move both nodes forward to traverse
                curTraverseNode = curTraverseNode.next;
                dprevNode = dprevNode.next;
            }
            
        }

        //Private Helper Get Node At Index Method -
        //our List Iterator needs to begin at the specified index
        //so this method helps us get there.
        private DLNode<F> getNodeAtIndex(int givenNdx) {
            DLNode<F> traversalNode = dHead; //copy head to leave it in tact
            int traversalNdx = 0; //to track when we arrive at givenNdx
            while (traversalNode != null && traversalNdx < givenNdx) {
                traversalNode = traversalNode.next;
                traversalNdx++;
            }
            //We now know which node is at the given index
            return traversalNode;
        }

        //Private Helper Class DLNode -
        //List Iterator needs two-way traversal, so creating a new node type that
        //is capable of going to the previous node can solve this.
        private class DLNode<G> {
            public G data;
            public DLNode<G> next = null;
            public DLNode<G> previous = null;

            //Private Helper DLNode Constructor -
            //Sets data field equal to val it was initialized with
            public DLNode(G val) {
                this.data = val;
            }
        }
    }


    //DONE: GL List Iterator Constructor -
    //This is the class that will be returned when the listIterator(int index)
    //method is called in the GenericList class.
    public GLListIterator(int ndx, GenericList<E>.Node<E> givenHead) {
        DoublyLinkedList<E> dlCopyList = new DoublyLinkedList<>(givenHead); //new DLList
        curNode = dlCopyList.getNodeAtIndex(ndx); //get the specified node
        curNdx = ndx; //keep track of our index
        //curNode should now point to correct index OR to null
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

    //DONE: Has Previous Method Override -
    //checks to see if there is another value in the data
    //structure and returns true or false
    @Override
    public boolean hasPrevious() {
        if (curNode != null)
            return true; //If it's not null then it has a data value
        return false;
    }

    //DONE: Previous Method Override -
    //returns the current value in the data
    //structure and advances to the next item.
    @Override
    public E previous() {
        E ourData = curNode.data; //Save our data value to return
        curNode = curNode.previous; //Advance to next node
        return ourData; //return our data value
    }

    //DONE: Next Index Method -
    //Returns the next index
    @Override
    public int nextIndex() {
        return curNdx + 1;
    }

    //DONE: Previous Index Method -
    //Returns the previous index
    @Override
    public int previousIndex() {
        return curNdx - 1;
    }

    //Optional Methods to ignore
    @Override
    public void add(E e) {
    }
    @Override
    public void remove() {
    }
    @Override
    public void set(E e) {
    }
}
