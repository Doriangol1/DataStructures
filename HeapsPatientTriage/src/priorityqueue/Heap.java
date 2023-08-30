package priorityqueue;

import java.util.Comparator;
import java.util.PriorityQueue;

import javax.naming.SizeLimitExceededException;

public class Heap<T> implements PriorityQueueADT<T> {

  private int numElements;
  private T[] heap;
  private boolean isMaxHeap;
  private Comparator<T> comparator;
  private final static int INIT_SIZE = 5;
  private int size;

  /**
   * Constructor for the heap.
   * @param comparator comparator object to define a sorting order for the heap elements.
   * @param isMaxHeap Flag to set if the heap should be a max heap or a min heap.
   */
  public Heap(Comparator<T> comparator, boolean isMaxHeap) {
      //TODO: Implement this method.

      heap = (T[]) new Object[INIT_SIZE]; //intialize heap array for object type
      this.isMaxHeap = isMaxHeap;
      this.comparator = comparator;
      size = 0;

  }

  /**
   * This results in the entry at the specified index "bubbling up" to a location
   * such that the property of the heap are maintained. This method should run in
   * O(log(size)) time.
   * Note: When enqueue is called, an entry is placed at the next available index in 
   * the array and then this method is called on that index. 
   *
   * @param index the index to bubble up
   * @throws IndexOutOfBoundsException if invalid index
   */
  public void bubbleUp(int index) {
      //TODO: Implement this method.
        
      if ((index > size)|| index < 0){ //invalid indexes
        throw new IndexOutOfBoundsException();
      }

      if (index <= 0){ // reached root
        return;
      }

      if (compareElements(heap[index], heap[(index-1) / 2]) < 0){ // if child is less than parent
        return;
      }

      else { // otherwise, swap parent and child and bubble up
        T temp = heap[index];
        heap[index] = heap [(index-1) / 2];
        heap [(index-1) / 2] = temp;
        bubbleUp((index-1) /2);
      }     
  }


  /**
   * This method results in the entry at the specified index "bubbling down" to a
   * location such that the property of the heap are maintained. This method
   * should run in O(log(size)) time.
   * Note: When remove is called, if there are elements remaining in this
   *  the bottom most element of the heap is placed at
   * the 0th index and bubbleDown(0) is called.
   * 
   * @param index
   * @throws IndexOutOfBoundsException if invalid index
   */
  public void bubbleDown(int index) {
    //TODO: Implement this method.

    if (index < 0 || index > size) // invalid index
      throw new IndexOutOfBoundsException();

    int indexToSwap = index; //initalize variable for the index that will be swapped with either child

    if ( (2 * index + 1) < size) // if left child exists
      if (compareElements(heap[2*index + 1], heap[index]) >= 0) // if left child more than parent
        indexToSwap = (2 * index + 1);

    if((2 * index + 2)< size) // if right child exists
      if (compareElements(heap[2*index + 2], heap[indexToSwap]) >= 0) // if right child more than parent or left child
        indexToSwap = (2 * index + 2);

    if (indexToSwap != index){ // swap parent with either left or right child
      T temp = heap[indexToSwap];
      heap[indexToSwap] = heap[index];
      heap[index] = temp;
      bubbleDown(indexToSwap);
    }
}

  /**
   * Test for if the queue is empty.
   * @return true if queue is empty, false otherwise.
   */
  public boolean isEmpty() {
    boolean isEmpty = false;
      //TODO: Implement this method.

    if (size == 0){ // if size is 0
        isEmpty = true;
    }
    return isEmpty;
  }

  /**
   * Number of data elements in the queue.
   * @return the size
   */
  public int getSize(){
      //TODO: Implement this method.
    return size;
  }

  /**
   * Compare method to implement max/min heap behavior. It changes the value of a variable, compareSign, 
   * based on the state of the boolean variable isMaxHeap. It then calls the compare method from the 
   * comparator object and multiplies its output by compareSign.
   * @param element1 first element to be compared
   * @param element2 second element to be compared
   * @return positive int if {@code element1 > element2}, 0 if {@code element1 == element2}, 
   * negative int otherwise (if isMaxHeap),
   * return negative int if {@code element1 > element2}, 0 if {@code element1 == element2}, 
   * positive int otherwise (if ! isMinHeap).
   */
  public int compareElements(T element1 , T element2) {
    int result = 0;
    int compareSign =  -1;
    if (isMaxHeap) {
      compareSign = 1;
    }
    result = compareSign * comparator.compare(element1, element2);
    return result;
  }

  /**
   * Return the element with highest (or lowest if min heap) priority in the heap 
   * without removing the element.
   * @return T, the top element
   * @throws QueueUnderflowException if empty
   */
  public T peek() throws QueueUnderflowException {
     T data = null;
      //TODO: Implement this method.

      if (isEmpty()) // if empty
        throw new QueueUnderflowException();

      data = heap[0]; // return root
      return data;
  }  

  /**
   * Removes and returns the element with highest (or lowest if min heap) priority in the heap.
   * @return T, the top element
   * @throws QueueUnderflowException if empty
   */
  public T dequeueElement() throws QueueUnderflowException{
    T data = null;
      //TODO: Implement this method.

    if (isEmpty())
      throw new QueueUnderflowException();
   
    data = heap[0]; // set data to be removed
    heap[0] = heap[size-1]; // set index 0 to last element
    bubbleDown(0); // bubble down
    size--; // decrease size

    return data; // return removed data
  }

  /**
   * Enqueue the element.
   * @param the new element
   */
  public void enqueueElement(T newElement) {
      //TODO: Implement this method.
    if (isEmpty()){ // if empty heap
      heap[0] = newElement; // add element to root
      size++;
    }
    else {

      if(size==heap.length) // if max size
        expand(); // use helper method to expand array

      heap[size] = newElement; // set next open slot to new element
      bubbleUp(size); // and bubble up
      size++;

      }
  }

  private void expand(){ // method to expand array

    T[] newArray = (T[])new Object[heap.length*2]; // set new array with double the original size

    for (int i=0;i<size;i++) // add all elements to new array
      newArray[i]=heap[i];

    heap = newArray; // set heap to new array
  }

}