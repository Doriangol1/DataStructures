public interface PriorityQueueADT<T> {
   // Adds element to this priority queue.
   public void enqueue(T item);
   
  // Throws QueueUnderflowException if empty,
  // Otherwise, removes element with highest priority
  // and returns it.
   public T dequeue() throws QueueUnderflowException;

  // Throws QueueUnderflowException if empty,
  // Otherwise, returns element with highest priority
  // without removing it from the queue.   
   public T peek() throws QueueUnderflowException;
  // returns true if queue is empty, false otherwise.
   public boolean isEmpty();
  // the amount of data in the queue.
   public int size();
   
   public void printInorder();
}
