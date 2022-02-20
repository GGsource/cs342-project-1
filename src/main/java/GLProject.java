import java.util.ArrayList;

public class GLProject {

	public static void main(String[] args) {
		System.out.println("Welcome to project 1");

		//Initialize a queue with 5
		GenericQueue<Integer> myQueue = new GenericQueue<>(5);
		//Add some more to the queue
		myQueue.enqueue(10);
		myQueue.enqueue(20);
		myQueue.enqueue(40);
		myQueue.enqueue(80);
		myQueue.enqueue(160);
		myQueue.print();
		myQueue.dequeue();
		System.out.println("We just dequeued an item, length should now be 5");
		myQueue.print();

		//Initialize a queue with 5
		GenericStack<String> myStack = new GenericStack<>("Apple");
		//Add some more to the queue
		myStack.push("Banana");
		myStack.push("Carrot");
		myStack.push("Dragonfruit");
		myStack.push("Eggplant");
		myStack.push("Fig");
		myStack.push("Grape");
		myStack.push("Honeydew");
		myStack.print();
		myStack.pop();
		System.out.println("We just popped an item, length should now be 7");
		myStack.print();

		ArrayList<String> noahsArkList = myStack.dumpList();
		System.out.println("We just dumped my stack, it should now be empty!");
		myStack.print();
		System.out.println("Items dumped into ArrayList:");
		noahsArkList.forEach(word->System.out.println(word));

		System.out.print("Printing myQueue index 3: ");
		System.out.println(myQueue.get(3));
		System.out.print("Printing myQueue index 0: ");
		System.out.println(myQueue.get(0));


	}
}
