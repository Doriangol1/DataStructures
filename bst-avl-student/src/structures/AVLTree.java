	package structures;
	import java.lang.Math;

import javax.print.attribute.standard.RequestingUserName;

	public class AVLTree <T extends Comparable<T>> implements BSTInterface<T> {
		protected BSTNode<T> root;
		private int size;
		private BSTNode<T> currentNode;

		public AVLTree() {
			root = null;
			size = 0;
		}

		public boolean isEmpty() {
			// DO NOT MODIFY
			return root == null;
		}

		public int size() {
			// DO NOT MODIFY
			return size;
		}

		public BSTNode<T> getRoot() {
			// DO NOT MODIFY
			return root;
		}
		
		public void printTree() {
			System.out.println("------------------------");
			if (root != null) root.printSubtree(0);
		}

		public boolean remove(T element) {
			// Do not need to implement this method.
			return false;
		}

		public T get(T element) {
			// Do not need to implement this method.
			return null;
		}

		public int height() {
			return height(this.root);
		}

		public int height(BSTNode<T> node) {
			// return the node height
			return node != null ? node.getHeight() : -1;
		}
		
		public void updateHeight(BSTNode<T> node) {
			// TODO: update node height to 1 + the maximal height between left and right subtree
			
			node.setHeight(1 + Math.max((height(node.getLeft())),(height(node.getRight()))));
			
		}
		
		public int balanceFactor(BSTNode<T> node) {
			// TODO: compute the balance factor by substracting left subtree height by right subtree height
			return height(node.getRight()) - height(node.getLeft());
		}

		public BSTNode<T> rotateLeft(BSTNode<T> node) {
			// TODO: implement left rotation algorithm

			BSTNode<T> originalChild = node.getRight(); // create and intialize original right child
			BSTNode<T> rightLeftChild = originalChild.getLeft(); // create and intialize original child's left child

			originalChild.setLeft(node); // set left child or original child to node
			originalChild.setParent(node.getParent()); // set parent of original child to parent of node

			
			if (node.getParent() != null){ // if parent exists
				
				if (node.getParent().getLeft() == node) // if node is lefdt child
					node.getParent().setLeft(originalChild); // replace left child
				else
					node.getParent().setRight(originalChild); // otherwise, replace right child
			}

			node.setParent(originalChild); // set parent to the original child
			node.setRight(rightLeftChild); // and set the node's right child to the orignal child's left child

			if (rightLeftChild != null){ // if rightleft child exists
				rightLeftChild.setParent(node); // set its parent to node
			}

			if (root != null && root == node) // if original node is the root
				root = originalChild; // set the orignal child to root

			updateHeight(node); // update heights
			updateHeight(originalChild);

			return node.getParent(); // return node's parent
		}

	

		
		public BSTNode<T> rotateRight(BSTNode<T> node) {
			// TODO: implement right rotation algorithm
			BSTNode<T> originalChild = node.getLeft(); // create and intialize original left child
			BSTNode<T> leftRightChild = node.getLeft().getRight(); // create and intialize original child's right child
			
		
			originalChild.setRight(node); // set node to original child's right child
			originalChild.setParent(node.getParent()); // set the original child's parents to the node's parent

			if (node.getParent() != null){ // if parent exists

				if (node.getParent().getLeft() == node) // if node was orignally the left child
					node.getParent().setLeft(originalChild); // replace parent's left child with original child
				else
					node.getParent().setRight(originalChild); // otherwise, replace with right child

			}
			
			node.setParent(originalChild); // update parent of node to original child
			node.setLeft(leftRightChild); // set its left child to original left child's right child
			
			if (leftRightChild != null) // if left child of original right child exists
				leftRightChild.setParent(node); // set its parent to node

			if (root != null && node == root) // if node was orignially the root
				root = originalChild; // change root pointer to original child
			
			updateHeight(node); // update heights
			updateHeight(originalChild);

			return node.getParent(); // return node's parent
		}


		// When inserting a new node, updating the height of each node in the path from root to the new node.
		// Check the balance factor of each updated height and run rebalance algorithm if the balance factor
		// is less than -1 or larger than 1 

		public void add(T t) {
			// TODO
		if (t == null)
			throw new NullPointerException();

		BSTNode<T> newNode = new BSTNode<T>(t, null, null); // initialize new node with t data
		
		if (root == null){// if empty tree

			root = newNode;
			newNode.setParent(null);
			size++;
			return;

		}
		
			currentNode = root; // start at root

			while (currentNode != null){ // go through list

				if (t.compareTo(currentNode.getData()) < 0){ // if t less than current

					if (currentNode.getLeft() == null){ // if t does not have left
						currentNode.setLeft(newNode);  // set left to new node
						newNode.setParent(currentNode); // set new node's parent to current node
						currentNode = null;
						
					}

					else {
						currentNode = currentNode.getLeft(); // go left otherwise
						
					}
				}

				else { // t is more or equal to current node

					if (currentNode.getRight() == null){ // if no right node
						currentNode.setRight(newNode); // set new node to right node of current node
						newNode.setParent(currentNode); // set new node's parent to current node
						currentNode = null;
					}

					else {
						currentNode = currentNode.getRight(); // go right otherwise
						
					}
				}
			}
			
			currentNode = newNode.getParent(); // start at new node's parent

			while (currentNode != null){ // traverse up the tree

				updateHeight(currentNode); // update height
			 	treeRebalance(currentNode); // rebalance
				currentNode = currentNode.getParent(); // go up

			 }
		
		size++; // increase size
	}

	// Check the balance factor of each updated height and run rebalance algorithm if the balance factor
		// is less than -1 or larger than 1 with following algorithm
		// 1. if the balance factor is less than -1
		//    1a. if the balance factor of left child is less than or equal to 0, apply right rotation
	    //    1b. if the balance factor of left child is larger than 0, apply left rotation on the left child,
		//        then apply right rotation
		// 2. if the balance factor is larger than 1
		//    2a. if the balance factor of right child is larger than or equal to 0, apply left rotation
	    //    2b. if the balance factor of right child is less than 0, apply right rotation on the right child,
		//        then apply left rotation

	public void treeRebalance( BSTNode<T> currentNode){ // helper method to rebalance, as directed above
		
		
		if (balanceFactor(currentNode) > 1 ){

			if (balanceFactor(currentNode.getRight()) < 0){
				rotateRight(currentNode.getRight());
			}
			rotateLeft(currentNode);
		}

		else if (balanceFactor(currentNode) < -1 ){

			if (balanceFactor(currentNode.getLeft()) > 0){
				rotateLeft(currentNode.getLeft());
			}
			rotateRight(currentNode);
		}

	}
}

	
