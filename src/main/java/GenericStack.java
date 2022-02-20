public class GenericStack<T> extends GenericList<T> {
    //Stack adds to the front of a list | LAST IN FIRST OUT
    GenericList<T>.Node<T> tail;

    //DONE: Generic Stack Constructor -
    public GenericStack(T initVal) {
        Node<T> initHead = new Node<>(initVal); //Create new head
        this.setHead(initHead); //Set the new head
        tail = getHead(); //Set tail
        this.setLength(1); //Set length
    }

    ///DONE: Add Method Override -
    //Stacks add to the front of list so Add() must be unique for stacks
    @Override
    void add(T data) {
        Node<T> newHead = new Node<>(data); //Create a new node to add
        newHead.next = getHead(); //Hook up new node in front of current head
        setHead(newHead); //change head to point to our new node
        this.setLength(getLength() + 1); //Adjust length to account for new node
    }

    //DONE: Remove Tail Method -
    //retrieves and removes the tail of the list using the tail data member
    public T removeTail() {
        //Save the data to retrieve it at the end
        T tailData = tail.data;
        //Now we must find the second to last node to be new tail
        Node<T> traversalNode = getHead();
        while (traversalNode.next != tail) {
            traversalNode = traversalNode.next;
            //REMOVEME:
            System.out.println("Traversin Stack..");
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

    //DONE: Push Method -
    //when using a stack, push is expected instead of add, so obfuscate its use
    public void push(T data) {
        add(data);
    }
    //DONE: Pop Method -
    //when using a stack, pop is expected instead of delete, so obfuscate its use
    public T pop() {
        return delete();
    }


}
