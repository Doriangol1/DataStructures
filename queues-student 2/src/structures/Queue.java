package structures;

import java.util.NoSuchElementException;

/**************************************************************************************
 * NOTE: before starting to code, check support/structures/UnboundedQueueInterface.java
 * for detailed explanation of each interface method, including the parameters, return
 * values, assumptions, and requirements
 ***************************************************************************************/
public class Queue<T> implements UnboundedQueueInterface<T> {

	Node<T> front; 
	Node<T> rear; 
	int size;
	Node<T> copyFront; 
	Node<T> copyRear; 
	int copySize;

	public Queue() {		
            // TODO 1
			front = new Node<T>(null, null);
			rear = new Node<T>(null, null);
			size = 0;
    }
	
	public Queue(Queue<T> other) {
            // TODO 2
			front = other.front;
			rear= other.rear;
			size = other.size ;
			// for (Node<T> cur = front; cur!=null;cur.getNext()){

			// }
	}
	
	@Override
	public boolean isEmpty() {
            // TODO 3
			if (size == 0)
				return true;
            return false;
	}

	@Override
	public int getSize() {
            // TODO 4
            return size;
	}

	@Override
	public void enqueue(T element) {
		Node<T> newNode = new Node<T>(element);
		if (isEmpty()){
			front = newNode;
			rear = newNode;
			size += 1;
		}
		else {
			
			rear.setNext(newNode);
			rear = newNode;
			size += 1;
		}
		System.out.println("element added: " + element);
            // TODO 5
	}

	@Override
	public T dequeue() throws NoSuchElementException {
            // TODO 6
			T removedItem;
			if (isEmpty()){
				throw new NoSuchElementException();
			}
			else {
				removedItem = front.getData();
				front = front.getNext();
				size -= 1;
			}
            return removedItem;
	}

	@Override
	public T peek() throws NoSuchElementException {
            // TODO 7
            return front.getData();
	}

	
	@Override
	public UnboundedQueueInterface<T> reversed() {
            // TODO 8
			Queue<T> newQueue = new Queue<T>();
			Node<T> cur = front;
			newQueue = reverseHelper(newQueue, cur);
			return newQueue;
			}
			

	public Queue<T> reverseHelper(Queue<T> newQueue, Node<T> cur){
		
		if (cur == null)
			return newQueue;
		else {
			newQueue = reverseHelper(newQueue, cur.getNext());
			System.out.println((cur.getData()));
			newQueue.enqueue(cur.getData());
		}
		
		
		return newQueue;
		// if (copySize == 0){
		// 	copyFront = cur;
		// 	copyRear = cur;
		// 	copySize += 1;
		// }
		// else{
		// 	copyRear.setNext(cur);
		// 	copyRear = cur;
		// 	copySize += 1
		// }
			
	}
	public Node<T> getFront(){
		return front;
	}
	public Node<T> getRear(){
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
	public void setNext(Node<T> next) {
		this.next = next;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Boolean hasNext(){
		if (next == null)
			return false;
		return true;
	}
	public Boolean hasData(){
		if (data == null)
			return false;
		return true;
	}
	public Node<T> getNext(){
		return next;
	}
	public T getData(){
		return data;
	}
}


