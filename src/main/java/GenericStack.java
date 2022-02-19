public class GenericStack<T> extends GenericList<T> {
    //Stack adds to the front of a list | LAST IN FIRST OUT
    GenericList<T>.Node<T> tail;

    public GenericStack(T initVal) {
        //Create new head
        Node<T> initHead = new Node<>(initVal);
        //Set the new head
        this.setHead(initHead);
        //Set tail
        tail = getHead();
        //Set length
        this.setLength(1);
    }

    @Override
    void add(T data) {
        //DONE:
        //Create a new node to add
        Node<T> newHead = new Node<>(data);
        //Hook up new node in front of current head
        newHead.next = getHead();
        //change head to point to our new node
        setHead(newHead);
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

    //Obfuscations for Stack
    public void push(T data) {
        add(data);
    }
    public T pop() {
        return delete();
    }


}
