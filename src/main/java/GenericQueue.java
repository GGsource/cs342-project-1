public class GenericQueue<T> extends GenericList<T> {
    //Queue adds to the back of a list | FIRST IN FIRST OUT
    GenericList<T>.Node<T> tail;

    //Constructor
    public GenericQueue(T initVal) {
        //Create new head
        Node<T> initHead = new Node<>(initVal);
        //Set the new head
        this.setHead(initHead);
        //Set tail
        tail = getHead();
        //set length
        this.setLength(1);
    }

    @Override
    void add(T data) {
        //DONE:
        //Add new node to back of the list
        tail.next = new Node<>(data);
        //Move the tail to new location
        tail = tail.next;
        //Adjust length to account for new node
        this.setLength(getLength() + 1);
    }

    public T removeTail() {
        //retrieves and removes the tail of the list using the tail data member
        //DONE:
        //First save the data to retrieve it at the end
        T tailData = tail.data;
        //Now we must find the second to last node to be new tail
        Node<T> traversalNode = getHead();
        while (traversalNode.next != tail) {
            traversalNode = traversalNode.next;
            //REMOVEME:
            System.out.println("Traversin Queue..");
        }
        //traversalNode now points to 2nd to last node, so cut off the old tail
        traversalNode.next = null;
        //set this as new tail
        tail = traversalNode;
        //adjust length
        this.setLength(getLength()-1);
        //return the old tail data
        return tailData;
    }

    //Obfuscations for queue
    public void enqueue(T data) {
        add(data);
    }
    public T dequeue() {
        return delete();
    }

}
