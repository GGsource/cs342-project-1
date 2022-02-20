public class GenericQueue<T> extends GenericList<T> {
    //Queue adds to the back of a list | FIRST IN FIRST OUT
    GenericList<T>.Node<T> tail;

    //DONE: Generic Queue Constructor -
    public GenericQueue(T initVal) {
        Node<T> initHead = new Node<>(initVal); //Creates a new head node with given val
        this.setHead(initHead); //Sets new node as head node
        tail = getHead(); //Sets head to also be tail, as there's only 1 value
        this.setLength(1); //Set length to 1 as there's only 1 value
    }

    //DONE: Add Method Override -
    //Queues add to the end of list so Add() must be unique for queues
    @Override
    void add(T data) {
        tail.next = new Node<>(data); //Add new node to back of the list
        tail = tail.next; //Move the tail to new location
        this.setLength(getLength() + 1); //Adjust length to account for new node
    }

    //DONE: Remove Tail Method -
    //retrieves and removes the tail of the list using the tail data member
    public T removeTail() {
        //Save the data to retrieve it at the end
        T tailData = tail.data;
        //Now we must find the second to last node to be new tail
        Node<T> traversalNode = getHead();
        while (traversalNode != null && traversalNode.next != tail) {
            traversalNode = traversalNode.next;
        }
        if (traversalNode == null) {
            //If we ended up at a null node then list was size 1
            setHead(null);
            tail = null;
            return tailData;
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

    //DONE: Enqueue Method -
    //when using a queue, enqueue is expected instead of add, so obfuscate its use
    public void enqueue(T data) {
        add(data);
    }
    //DONE: Dequeue Method -
    //when using a queue, dequeue is expected instead of delete, so obfuscate its use
    public T dequeue() {
        return delete();
    }

}
