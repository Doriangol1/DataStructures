import java.util.Iterator;  

@SuppressWarnings("unchecked")
public class MaxHeapArray<T extends Comparable<T>>
                             implements PriorityQueueADT<T> {

   private T[] heapArray;
   private static final int DEFAULT_SIZE = 10;
   private int size;
   
  public MaxHeapArray () {
     heapArray = (T[])new Comparable[DEFAULT_SIZE];
     size = 0;
  }

  public void enqueue(T item){
      if(size==heapArray.length)
         expand();
      heapArray[size] = item;
      percolateUp(heapArray, size);
      size++;
  }
   
  // Throws QueueUnderflowException if empty,
  // Otherwise, removes element with highest priority
  // and returns it.
  public T dequeue() throws QueueUnderflowException{
      if (isEmpty())
         throw new QueueUnderflowException();
      // remove the root and replace it with the last node added,
      // then percolate down.
      T data = heapArray[0];
      size--;
      heapArray[0] = heapArray[size];
      heapArray[size]=null;
      percolateDown(heapArray, size, 0);
      return data;
  }

  // Throws QueueUnderflowException if empty,
  // Otherwise, returns element with highest priority
  // without removing it from the queue.   
  public T peek() throws QueueUnderflowException {
      if (isEmpty())
         throw new QueueUnderflowException();
      return heapArray[0];
  }

  public boolean isEmpty(){return size==0;}
  
  public int size(){return size;}
   
  private void percolateUp(T[] array, int nodeIndex) {
     while (nodeIndex > 0) {
        int parentIndex = (nodeIndex - 1) / 2;
        if (array[nodeIndex].compareTo(array[parentIndex]) <=0)
           return;
        else {
           swap(array, nodeIndex, parentIndex);
           nodeIndex = parentIndex;
        }
     }
  }

  private void percolateDown(T[] array, int dataSize, int nodeIndex) {
     int childIndex = 2 * nodeIndex + 1;
     T value = array[nodeIndex];
     while (childIndex < dataSize) {
       T maxValue = value;
       int maxIndex = -1;
       for (int i = 0; i < 2 && i + childIndex < dataSize; i++) {
          if (array[i + childIndex].compareTo(maxValue) > 0) {
             maxValue = array[i + childIndex];
             maxIndex = i + childIndex;
          }
       }
       if (maxValue == value) {
          return;
       }
       else {
         swap(array, nodeIndex, maxIndex);
         nodeIndex = maxIndex;
         childIndex = 2 * nodeIndex + 1;
      }
    }
  }
  
  private void swap(T[] array, int nodeIndex, int parentIndex){
     T tempNode = array[nodeIndex];
     array[nodeIndex] = array[parentIndex];
     array[parentIndex] = tempNode;
  }
  
  /*
   *  Assume inputArray has no empty cells.
   * the inputArray is sorted in place in ascending order.
  */
  public void heapsort(T[] inputArray) {
   heapify(inputArray, inputArray.length);
   /* Repeatedly remove the root at index 0, swap with the end of the heap,
       then heapify the rest of the array.
    */
   for (int i = inputArray.length - 1; i > 0; i--) {
      swap(inputArray, 0, i);
      heapify(inputArray, i-1);
   }
 }  
   
 private void heapify(T[] inputArray, int len){
    for (int i = len / 2 - 1; i >= 0; i--)
       percolateDown(inputArray, len, i);
 }
 
  private void expand(){
     T[] newArray = (T[])new Comparable[heapArray.length*2];
     for(int i=0;i<heapArray.length;i++)
         newArray[i]=heapArray[i];
      heapArray = newArray;
  }
  
   public void printInorder() {
      for(int i=0;i<size;i++){
         System.out.print(heapArray[i]+", ");
      }
  }
  
  // use to step through heapify 
  public static void main(String[] args){
     // create a new instance of the MaxHeapArray class
     MaxHeapArray<Integer> maxHeap = new MaxHeapArray<Integer>();
     // create a test input array to be sorted
     Integer[] inputArray = {55, 25, 63, 10, 88, 69, 27, 54};
     maxHeap.heapsort(inputArray);
     for(Integer curInt : inputArray)
        System.out.print(curInt+" ");
  }
 
} //end class