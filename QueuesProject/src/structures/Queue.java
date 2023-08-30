package structures;

import java.util.NoSuchElementException;

/**************************************************************************************
 * NOTE: before starting to code, check support/structures/UnboundedQueueInterface.java
 * for detailed explanation of each interface method, including the parameters, return
 * values, assumptions, and requirements
 ***************************************************************************************/
public class Queue<T> implements UnboundedQueueInterface<T> {

	Node<T> front; // create variables
	Node<T> rear; 
	int size;
	

	public Queue() {		// constructor method, initialize variables
            // TODO 1
			front = null;
			rear = null;
			size = 0;
    }
	
	public Queue(Queue<T> other) { // copy constructor
            // TODO 2
			front = other.front;
				
			for (Node<T> cur = front; cur != null;cur = cur.getNext()) { // traverse through queue
				enqueue(cur.getData()); // add current node
			 }

	}
	
	@Override
	public boolean isEmpty() { // check if queue is empty
            // TODO 3
			if (size == 0) // if size is 0
				return true;
            return false;
	}

	@Override
	public int getSize() { // return size of queue
            // TODO 4
            return size;
	}

	@Override
	public void enqueue(T element) { // enqueue elelemnt at end of queue
		Node<T> newNode = new Node<T>(element); // create and initialize node for element

		if (isEmpty()){ // if queue is empty
			front = newNode; // set both front and rear pointers to new node
			rear = newNode;
			size += 1; // increase size
		}

		else { // if queue is not empty
			rear.setNext(newNode); // set next of current rear to new node
			rear = newNode; // set rear pointer to new node
			size += 1; // increase size
		}
            // TODO 5
	}

	@Override
	public T dequeue() throws NoSuchElementException { // dequeue nodes from front
            // TODO 6
			T removedItem;

			if (isEmpty()){
				throw new NoSuchElementException(); // throw exception if queue is empty
			}

			else { // otherwise
				removedItem = front.getData(); // get data of front node
				front = front.getNext(); // set front node to next of current front node
				size -= 1; // decrease size
			}

            return removedItem; // return removed item
	}

	@Override
	public T peek() throws NoSuchElementException { // look at first element of queue
            // TODO 7
			if (isEmpty()) 
				throw new NoSuchElementException(); // throw exception if empty

            return front.getData(); // return data of first node
	}

	
	@Override
	public UnboundedQueueInterface<T> reversed() { // reverse queue
            // TODO 8
			Queue<T> newQueue = new Queue<T>(); // create new queue
			Node<T> cur = front; // set current to front of original queue

			newQueue = reverseHelper(newQueue, cur); // set new queue to reveresed of original queue using helper method

			return newQueue; // return new queue

			}
			

	public Queue<T> reverseHelper(Queue<T> newQueue, Node<T> cur){ // helper method to recurse and reverse queue
		
		if (cur == null){ // if reached end of queue
			return newQueue; // return the new queue
		}

		else {

			newQueue = reverseHelper(newQueue, cur.getNext()); // go to last element
			newQueue.enqueue(cur.getData()); // add curtent node, traversing down the queue 
			
		}
		
		
		return newQueue; // return the new queue
			
	}

	public Node<T> getFront(){ // return front of queue
		return front;
	}

	public Node<T> getRear(){ // return rear of queue
		return rear;
	}

}

class Node<T> {
	public T data;
	public Node<T> next;
	public Node(T data) { this.data=data;}
	public Node(T data, Node<T> next) {
		this.data = data; this.next=next;
	}

	public void setNext(Node<T> next) { //set next of node
		this.next = next;
	}

	public void setData(T data) { // set data of node
		this.data = data;
	}

	public Boolean hasNext(){ // check if node has a next element
		if (next == null)
			return false;

		return true;
	}

	public Boolean hasData(){ // check if element is not null
		if (data == null)
			return false;
		return true;
	}

	public Node<T> getNext(){ // get next node
		return next;
	}

	public T getData(){ // get data of node
		return data;
	}
}


