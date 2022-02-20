import java.util.ListIterator;

public class GLListIterator<E> implements ListIterator<E> {
    DoublyLinkedList<E>.DLNode<E> curNode = null;
    int curNdx = 0;

    private class DoublyLinkedList<F> {
        private DLNode<F> dHead = null;

        private DoublyLinkedList(GenericList<F>.Node<F> originalHead) {
            //FIXME: comment this miss
            dHead = new DLNode<>(originalHead.data);
            DLNode<F> dprevNode = dHead;
            GenericList<F>.Node<F> curTraverseNode = originalHead.next;
            while (curTraverseNode != null) {
                dprevNode.next = new DLNode<>(curTraverseNode.data);
                dprevNode.next.previous = dprevNode;
                curTraverseNode = curTraverseNode.next;
                dprevNode = dprevNode.next;
            }
            
        }

        private DLNode<F> getNodeAtIndex(int givenNdx) {
            DLNode<F> traversalNode = dHead;
            int traversalNdx = 0;
            while (traversalNode != null && traversalNdx < givenNdx) {
                traversalNode = traversalNode.next;
                traversalNdx++;
            }
            return traversalNode;
        }

        private class DLNode<G> {
            public G data;
            public DLNode<G> next = null;
            public DLNode<G> previous = null;

            public DLNode(G val) {
                this.data = val;
            }
        }
    }


    public GLListIterator(int ndx, GenericList<E>.Node<E> givenHead) {
        DoublyLinkedList<E> dlCopyList = new DoublyLinkedList<>(givenHead);
        curNode = dlCopyList.getNodeAtIndex(ndx);
        curNdx = ndx;
        //curNode should now point to correct index OR to null
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

    @Override
    public boolean hasPrevious() {
        // DONE:
        if (curNode != null)
            return true;
        return false;
    }

    @Override
    public E previous() {
        // DONE:
        //Save our data value to return
        E ourData = curNode.data;
        //Advance to next node
        curNode = curNode.previous;
        //return our data value
        return ourData;
    }

    @Override
    public int nextIndex() {
        // DONE:
        return curNdx + 1;
    }

    @Override
    public int previousIndex() {
        // DONE:
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
