package sorting;

import structures.Queue;

/**
 * A class containing methods to sort queues and merge sorted queues.
 * 
 * "Sorted" means in ascending order: the front of the queue is the smallest
 * element, and the rear of the queue is the largest.
 * 
 * e1 is less than or equal to e2 if and only if (e1.compareTo(e2) <= 0)
 *
 */
public class MergeSorter<T extends Comparable<T>> {
	/**
	 * Returns a new queue containing the elements from the input queue
	 * in sorted order.
	 * 
	 * Implement this method recursively:
	 * 
	 *   In the base case, return the queue with no further work.
	 *
	 *   Otherwise:
	 * 
	 *     First, call split to split the input queue into two smaller output queues.
	 * 
	 *     Then, recursively mergeSort each of these two smaller queues. 
	 * 
	 *     Finally, return the result of merging these two queues.
	 * 
	 * @param queue an input queue
	 * @return a sorted copy of the input queue
	 */
	public Queue<T> mergeSort(Queue<T> queue) { // sort queue using merge sort
		Queue<T> input = new Queue<T>(queue); 
		Queue<T> output1 = new Queue<T>();	// output queue 1		
		Queue<T> output2 = new Queue<T>();	// output queue 2
        	// TODO 1

		if (input.getSize() == 1 || input.getSize() == 0) // if size of input queue is 1 or 0
			return input; // return the queue
		
		split(input, output1, output2); // split input into output1, output2 using helper method
		output1 = mergeSort(output1); // recursively split the first half of queue
		output2 = mergeSort(output2); // recursively split the secodn half of the queue
		
	
		input = merge(output1, output2); // merge halves into one queue
        return input; // return the sorted queue
	}

	/**
	 * Split elements from the input queue into the output queues, roughly
	 * half and half.
	 * 
	 * @param input a queue
	 * @param output1 a queue into which about half of the elements in input should go
	 * @param output2 a queue into which the other half of the elements in input should go
	 */
	void split(Queue<T> input, Queue<T> output1, Queue<T> output2) { // helper method to split queue
        	// TODO 2
		int size = input.getSize();
		int index=0;
		
		if (size == 1){ // if size of queue is 1
			output1.enqueue(input.dequeue()); // add to output 1
		}

		else { // if size is not 1

			for ( int i = 0; i<size/2;i++) { // go through first half of queue
				output1.enqueue(input.dequeue()); // dequeue from queue and enqueue into output1
				index++; // increase index
			}

			for (int j=index; j< size;j++) { // go through second half of queue
				output2.enqueue(input.dequeue()); // dequeue from queue and enqueue to output2
			}

		}
		
	}
	
	/**
	 * Merge sorted input queues into an output queue in sorted order,
	 * and returns that queue. To do so: while there are still elements
	 * in at least one of the input queues, compare the front elements
	 * and pick the smaller among the two, dequeue it, and enqueue it
	 * to the output queue. Remember, the Queue class has a peek method
	 * which allows you to check the front element without removing it. 
	 * 
	 * @param input1 a sorted queue
	 * @param input2 a sorted queue
	 * @return a sorted queue consisting of all elements from input1 and input2
	 */
	Queue<T> merge(Queue<T> input1, Queue<T> input2) { //method to merge two halves into a single sorted queue
		Queue<T> output = new Queue<T>();
        	// TODO 3

			while (input1.getSize() > 0 || input2.getSize() > 0){ // while either halves still contains ta least one element

				if (!input1.isEmpty() && !input2.isEmpty()) { // if both have at least one element

					if (input1.peek().compareTo(input2.peek()) < 0){ // if front of first queue is smaller than front of second queue
					output.enqueue(input1.dequeue()); // enqueue front of first queue
					}

					else { // otherwise
					output.enqueue(input2.dequeue()); // enqueue front of second queue
					}
				}

				else { // if only one queue has nodes left 

					if (input1.isEmpty()) // if first queue is empty
						output.enqueue(input2.dequeue()); // add nodes of second queue
					else // otherwise
						output.enqueue(input1.dequeue()); // add nodes of first queue
				}
			}
            return output; // return final, single, sorted, queue
	}
}
