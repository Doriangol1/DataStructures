package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

public class BinarySearchTree<T extends Comparable<T>> implements
	BSTInterface<T> {

	protected BSTNode<T> root; // create useful variables.
	int size = 0;
	BSTNode<T> currentNode;
	BSTNode<T> parentNode;


	public boolean isEmpty() { // check if tree ie empty
		return root == null;
	}

	public int countSmaller(BSTNode<T> root, T target){
		int count = 0;
		BSTNode<T> cur = root;
		while (cur != null){
			if (cur.getData().equals(target)){
				count = countSmallerHelper(cur.getLeft(), count);
				break;
			}
			else if (cur.getData().compareTo(target) < 0){
				count++;
				count = countSmallerHelper(cur.getLeft(), count);
				break;
			}
			else {
				cur = cur.getRight();
			}
		}
		return count;
	}
	public int countSmallerHelper(BSTNode<T> node, int count){
		if (node == null)
			return count;
		count++;
		countSmallerHelper(node.getLeft(), count);
		countSmallerHelper(node.getRight(), count);
		return count;
	}
	

	public int getSize() { // get tree size
		// TODO
		return size;
	}

	public boolean contains(T t) { // check if tree contains element t
		// TODO
		currentNode = root; // start at root
		if (t == null)
			throw new NullPointerException(); // throw if root is null
		
		return containsHelper(currentNode, t); // use recursive helper method

	}

	public boolean containsHelper(BSTNode<T> current, T t){ // recursive helper method to check if tree contains t
		
		if (current == null) // if end of tree reached
			return false;
		
		else if (current.getData().compareTo(t) == 0) // else if current node has t
			return true;
		
		else if (current.getData().compareTo(t) < 0) // if current node is less than t
			return containsHelper(current.getRight(), t); // repeat with right node
		
		else 
			return containsHelper(current.getLeft(), t); // repeat with left node
			
	}


	public boolean removeElement(T t) {
		// TODO
		currentNode = root; // intialize current and parent
		parentNode = null;

		if (t == null)
			throw new NullPointerException(); // throw excep if null

		while (currentNode != null){ // while not null
			if (currentNode.getData().equals(t)){ // if cuurent node's data equals t
				if (currentNode.getLeft() == null && currentNode.getRight() == null){ // remove when leaf node

					if (parentNode == null)
						root = null;

					else if (parentNode.getLeft() == currentNode) 
						parentNode.setLeft(null);
					else 
						parentNode.setRight(null);
				}

				else if (currentNode.getRight() == null){ // remove when node only has left child
					if (parentNode == null)
						root = currentNode.getLeft();
					else if (parentNode.getLeft() == currentNode)
						parentNode.setLeft(currentNode.getLeft());
					else 
						parentNode.setRight(currentNode.getLeft());
				}

				else if ( currentNode.getLeft() == null){ // remove when node only has right node
					if (parentNode == null)
						root = currentNode.getRight();
					else if ( parentNode.getLeft() == currentNode)
						parentNode.setLeft(currentNode.getRight());
					else
						parentNode.setRight(currentNode.getRight());
				}

				else { // remove internal node
					T sucElement = getLowestValueFromSubtree(currentNode.getRight());
					removeElement(sucElement);
					currentNode.setData(sucElement);
				}

				size--; 
				return true; // return if node was removed
			}

			else if (currentNode.getData().compareTo(t) < 0){ // if current node's data is less than element t 
				parentNode = currentNode;
				currentNode = currentNode.getRight(); // go right
			}

			else { 
				parentNode = currentNode;
				currentNode = currentNode.getLeft(); // go left
			}

		}
		
		return false; // node was not removed
	}

	public T getHighestValueFromSubtree(BSTNode<T> node) { // method to get highest value
		// node must not be null
		
		if (node.getRight() == null) { // if most right element reached
			return node.getData();
		} else {
			return getHighestValueFromSubtree(node.getRight()); // recursively move right
		}
	}

	public T getLowestValueFromSubtree(BSTNode<T> node) { // get lowest value
		// TODO
		if (node == null) // check for null
			return null;

		if (node.getLeft() == null) // if left most element reached
			return node.getData();
		else
			return getLowestValueFromSubtree(node.getLeft()); // recurse left
	}

	private BSTNode<T> removeRightmostFromSubtree(BSTNode<T> node) { // remove highest value
		// node must not be null

		if (node.getRight() == null) { // if right most reached, get left
			return node.getLeft();
		} 
		
		else {

			node.setRight(removeRightmostFromSubtree(node.getRight())); // recurse right

			if (node.getRight() != null){ // if has right node
				node.getRight().setParent(node); // set parent to node
				size--;
			}

			return node; // return node removed
		}
	}

	public BSTNode<T> removeLeftmostFromSubtree(BSTNode<T> node) {
		// TODO

		if (node.getLeft() == null) { // if left most node reached
			return node.getRight();  // get right of node
		} 
		
		else {

			node.setLeft(removeLeftmostFromSubtree(node.getLeft())); // recurse left
			
			if (node.getLeft() != null){ // if node has left child
				node.getLeft().setParent(node);
				size--;
			}

			return node;
		}
	}

	public T getElement(T t) { // get element t in tree
		// TODO
		if (t == null)
			throw new NullPointerException();

		currentNode = root; // start at root

		while (currentNode != null){ // go through tree

			if (t.equals(currentNode.getData())){ // if current node equals target element
				return currentNode.getData();
			}

			else if (t.compareTo(currentNode.getData()) < 0){ // if cuurent node larger than t
				currentNode = currentNode.getLeft(); // go left
			}

			else{
				currentNode = currentNode.getRight(); // go right
			}
		}
		return null;
	}

	public void addElement(T t) { // add element to BST tree
		// TODO

		BSTNode<T> newNode = new BSTNode<T>(t, null, null); // initialize new node with t data
		if (t == null)
			throw new NullPointerException();

		if (root == null) // if empty tree
			root = newNode;

		else {
			currentNode = root; // start at root

			while (currentNode != null){ // go through list

				if (t.compareTo(currentNode.getData()) < 0){ // if t less than current

					if (currentNode.getLeft() == null){ // if t does not have left
						currentNode.setLeft(newNode);  // set left to new node
						currentNode = null;
					}

					else {
						currentNode = currentNode.getLeft(); // go left otherwise
					}
				}

				else { // t is more or eequal to current node

					if (currentNode.getRight() == null){ // if no right node
						currentNode.setRight(newNode); // set new node to right node of current node
						currentNode = null;
					}

					else {
						currentNode = currentNode.getRight(); // go right otherwise
					}
				}
			}
		}
		size++;
	}

	@Override
	public T getMin() { // get minimium value in tree
		// TODO
		if (isEmpty()) // if empty
			return null;

		return getLowestValueFromSubtree(root); // use helper method starting at root
	}


	@Override
	public T getMax() { // get max value in tree
		// TODO
		if (isEmpty()) // if empty
			return null;

		return getHighestValueFromSubtree(root); // use helper method starting at root
	}

	@Override
	public int height() { // get height
		// TODO
		return heightHelper(root); // use helper method starting at root
	}

	public int heightHelper (BSTNode<T> current){ // helper method to get height
		
		if (current == null) // end reached
			return -1;
		
		int leftHeight = heightHelper(current.getLeft()); // check height of left subtree
		int rightHeight = heightHelper(current.getRight()); // check height of right subtree

		if (leftHeight > rightHeight) // get higher one and add to return
			return 1 + leftHeight;
		else 
			return 1 + rightHeight;

		
	}

	public Iterator<T> preorderIterator() { // create iterator for a preorder traversal
		// TODO

		Queue<T> queue = new LinkedList<T>(); // create queue
		preorderTraverse(queue, root); // use helper method to add to queue

		return queue.iterator(); // return iterator

	}

	private void preorderTraverse(Queue<T> queue, BSTNode<T> node) { // traverse list in preorder

		if (node != null) { // while end is not reached

			queue.add(node.getData()); // add current node
			preorderTraverse(queue, node.getLeft()); // go left
			preorderTraverse(queue, node.getRight()); // go right

		}

	}


	public Iterator<T> inorderIterator() { // create iterator for in order traversal
		Queue<T> queue = new LinkedList<T>();

		inorderTraverse(queue, root); // use helper method
		return queue.iterator(); // return iterator

	}

	private void inorderTraverse(Queue<T> queue, BSTNode<T> node) { // helper method to iterare through tree and add to queue
		
		if (node != null) { // as long as end is not reached

			inorderTraverse(queue, node.getLeft()); // go left
			queue.add(node.getData()); // add current
			inorderTraverse(queue, node.getRight()); // go right
		}
	}

	public Iterator<T> postorderIterator() { // create iterator for postorder traversal
		// TODO
		Queue<T> queue = new LinkedList<T>();
		postorderTraverse(queue, root); // helper method
		return queue.iterator();
	}
	
	private void postorderTraverse(Queue<T> queue, BSTNode<T> node) {

		if (node != null) {
			postorderTraverse(queue, node.getLeft()); // go left
			postorderTraverse(queue, node.getRight()); // go right
			queue.add(node.getData()); // add current
		}

	}
	

	@Override
	public boolean equals(BSTInterface<T> other) { // method to check if two trees are equal in values and structures
		// TODO

		if (other == null) // throw excep if null
			throw new NullPointerException();

		if (other.isEmpty() && !isEmpty() || !other.isEmpty() && isEmpty()){ // if exactly one of them is empty
			return false;
			}

		if (!sameValues(other)) // if not the same values
			return false;

		currentNode = root; // intialize and create variables to store roots and queues
		BSTNode<T> otherNode = other.getRoot();
		Queue<T> queue = new LinkedList<T>();
		Queue<T> otherQueue = new LinkedList<T>();

		preorderTraverseforRange(queue, currentNode); // use helper method to add queues to element in preorder traversal
		preorderTraverseforRange(otherQueue, otherNode);

		while (!queue.isEmpty()){ // while queue has elements in it

			if (!queue.peek().equals(otherQueue.peek())){ //if elements at front of queue are not equal
				return false;
			}

			queue.remove(queue.peek()); // remove front of both queues
			otherQueue.remove(otherQueue.peek());
		}

		return true; 
	}
	
	@Override
	public boolean sameValues(BSTInterface<T> other) { //method to check if two trees have the same values regardless of structure
		// TODO


		if (other.isEmpty() && !isEmpty() || !other.isEmpty() && isEmpty()){ // if exactly one of the trees is empty
			return false;
		}

		currentNode = root; // intialize and create variables for both roots and queues.
		BSTNode<T> otherNode = other.getRoot();
		Queue<T> queue = new LinkedList<T>();
		Queue<T> otherQueue = new LinkedList<T>();

		preorderTraverseforRange(queue, currentNode); // helper method to add to queues while traversing treees
		preorderTraverseforRange(otherQueue, otherNode);

		while (!queue.isEmpty()){ // while queue is not empty

			if (!otherQueue.contains(queue.peek())){ // if second queue does not have the front of the first queue
				return false;
			}
			queue.remove(queue.peek()); // remove first from queue
		}

		return true;
	}

	
	@Override
	public int countRange(T min, T max) { // method to count num of elements in range of min and max
    	// TODO
		currentNode = root; // intialize variables
		int count = 0;
	
		Queue<T> queue = new LinkedList<T>();
		preorderTraverseforRange(queue, currentNode); // create and add elements to queue from tree

		while (!queue.isEmpty()){ // while queue is not empty

			if (queue.peek().compareTo(min) > 0 && queue.peek().compareTo(max) < 0) // if element fits range
				count++; // add to count

			queue.remove(queue.peek()); // remove first from queue
		}

		return count; // return count of elements in range 
  }

  private void preorderTraverseforRange(Queue<T> queue, BSTNode<T> node) { // helper method to traverse and add to queue

	if (node != null) { // while end is not reached
		queue.add(node.getData()); // add current to queue
		preorderTraverse(queue, node.getLeft()); // add left
		preorderTraverse(queue, node.getRight()); // add right
	}

}
  


	@Override
	public BSTNode<T> getRoot() {
        // DO NOT MODIFY
		return root;
	}

	public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
		// header
		int count = 0;
		String dot = "digraph G { \n";
		dot += "graph [ordering=\"out\"]; \n";
		// iterative traversal
		Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
		queue.add(root);
		BSTNode<T> cursor;
		while (!queue.isEmpty()) {
			cursor = queue.remove();
			if (cursor.getLeft() != null) {
				// add edge from cursor to left child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getLeft().getData().toString() + ";\n";
				queue.add(cursor.getLeft());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}
			if (cursor.getRight() != null) {
				// add edge from cursor to right child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getRight().getData().toString() + ";\n";
				queue.add(cursor.getRight());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}

		}
		dot += "};";
		return dot;
	}

	public static void main(String[] args) {
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			BSTInterface<String> tree = new BinarySearchTree<String>();
			for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
				tree.addElement(s);
			}
			Iterator<String> iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.preorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.postorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();

			System.out.println(tree.removeElement(r));

			iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
		}

		BSTInterface<String> tree = new BinarySearchTree<String>();
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			tree.addElement(r);
		}
		System.out.println(tree.getSize());
		System.out.println(tree.height());
		System.out.println(tree.countRange("a", "g"));
		System.out.println(tree.countRange("c", "f"));
	}
}