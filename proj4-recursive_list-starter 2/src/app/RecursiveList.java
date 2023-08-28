package app;

import java.time.temporal.TemporalUnit;
import java.util.Iterator;

public class RecursiveList<T> implements ListInterface<T> {

  private int size;
  private Node<T> head = null;

  public RecursiveList() {
    this.head = null;
    this.size = 0;
  }

  public RecursiveList(Node<T> first) {
    this.head = first;
    this.size = 1;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void insertAtBeginning(T elem) { // insert element at beginning
      //TODO: Implement this method.

      if (elem == null){ // if element is null, throw exception
        throw new NullPointerException();
      }

      else if (isEmpty()){ // if list is empty

        Node<T> newNode = new Node<T>(elem, null); // create new node with null next
        this.head = newNode; // set head to node
        size++;
        //System.out.println(size);
        //RecursiveList list = new RecursiveList(newNode);
      }

      else { // if not empty or null
        
        Node<T> newNode = new Node<T>(elem, this.head); //create new node with head as next
        this.head = newNode; // set head to new node
        size++; // increase size

      }   
  }

  @Override
  public void insertAtEnd(T elem) { // insert element at end
      //TODO: Implement this method.

      if (elem == null){ // if element is null, throw exception
        throw new NullPointerException();
      }

      else if (isEmpty()){ // if list is empty

        Node<T> newNode = new Node<T>(elem, null); // create new node with null next
        this.head = newNode; // set head to new node
        size++; // increase size
  
      }
      else {

        Node<T> newNode = new Node<T>(elem, null); // create new node with null next
        Node<T> currentNode = head; // create temporary current node, initialize to head
        size++; // increase size
        currentNode = lastFinder(currentNode); // set currentNode to last Node using lastFinder()
        currentNode.setNext(newNode); // set next of last node to new node
  
        }
      }
          
  

  @Override
  public void insertAt(int index, T elem) { // insert elem at any position
      //TODO: Implement this method.

      size++; // increase size initially

      if ( index < 0){
        size--;
        throw new IndexOutOfBoundsException("index out of bounds");
      }
      else if ( index >= size){
        size--;
        throw new IndexOutOfBoundsException("index out of bounds");
      }
      else if (elem == null){ // if element is null, throw exception
        size--;
        throw new NullPointerException();
      }

      else if (isEmpty()){ // if list is empty

        Node<T> newNode = new Node<T>(elem, null); 
        this.head = newNode;
        
      }

      else if(index == 0){ //if attempting to add at beginning

        insertAtBeginning(elem); // insert at beginning using method
        size--; // decrease size to adjust for "second" insert call

      }

      else if(index == size-1){ // if attempting to add at the end

        insertAtEnd(elem); // insert at end using method
        size--; // decrease size

      }

      else { // if inserting in middle

        Node<T> newNode = new Node<T>(elem, null);  // create new node with null next
        Node<T> current = head; // current node, starting at head
        Node<T> futureNext = current.getNext(); // set variable futureNext to next of current
        current = previousFinder(current, 0, index); // set current to the node before index using helper method
        futureNext = current.getNext(); // set futureNext to current next
        newNode.setNext(futureNext); // set next of new node to futureNext
        current.setNext(newNode); // set next of current to newNode
        
      }
      
    }
  

  @Override
  public T removeFirst() { // remove first element
    T removedItem = null;
      //TODO: Implement this method.

    if (isEmpty()){
      throw new IllegalStateException();
    }
    removedItem = head.getData(); // set removed item to element of head
    head = head.getNext(); // point head to the next node
    size--; // decrease size
    return removedItem; // return removed item

  }

  @Override
  public T removeLast() { // remove last element
    T removedItem = null;
      //TODO: Implement this method.
      if (isEmpty()){
        throw new IllegalStateException();
      }
    Node<T> currentNode = head; // set current node to head
    Node<T> removedNode = head;
    removedNode = lastFinder(removedNode);
    removedItem = removedNode.getData(); // set removed item to the data of the last element using helper method lastFinder();
    //currentNode = previousFinderForRem(currentNode, 0, size-1); // set current node to the node before the last node
    System.out.println("node to be removed " + removedItem);
    currentNode = head;
    currentNode = previousFinder(currentNode, 0, size);
    System.out.println("NOde before last one: " + currentNode.getData());
    currentNode.setNext(null); // assign currentNode next to null
    size--; // decrease size

    return removedItem; // return item removed

  }

  @Override
  public T removeAt(int i) {
    T removedItem = null;
      //TODO: Implement this method.
      
      if ( i < 0){
        throw new IndexOutOfBoundsException("index out of bounds");
      }
      else if ( i >= size){
        throw new IndexOutOfBoundsException("index out of bounds");
      }
      else if (isEmpty()){
        throw new IllegalStateException();
      }

    else if (i==0)
      removedItem = removeFirst();

    else if (i == size-1)
      removedItem = removeLast();
    
    else {

    removedItem = getAt(i); // set removed item to tlement at index i using helper method getAt(i)
    Node<T> currentNode = head; // set current node to begin at head
    Node<T> next = head.getNext(); // initialize next to next of head
    //currentNode = previousFinderForRem(currentNode, 0, i); // set current node to the previous node (index = i -1)
    currentNode = previousFinder(currentNode, 0, i);
    next = currentNode.getNext().getNext(); // set next to next of next of current (current node is the previous node, next is the removed node, and next is the desiredfinal next)
    currentNode.setNext(next); // set next of previous node to next
    size--; // decrease size

    }
    return removedItem; // return removed item
  }

  public Node<T> previousFinderForRem(Node<T> currentNode, int index, int i){ // helper method to recursivley find the previous node ( the node before removed node)
    
    if (index == i-1){ // if index is equal to one before i
      return currentNode; // return current node
    }
    else { // otherwise
      index++; // increase index
    }
    if (index==size) // if reached end
      throw new ItemNotFoundException(); // throw exception item not found

    else { // otherwise

      currentNode = currentNode.getNext(); // set currentNode to next
      currentNode = previousFinderForRem(currentNode,index, i); // recurse the method with new currentNode

    }
    return currentNode; // return final current node, which should be one before removed node
  }

  public Node<T> previousFinder(Node<T> currentNode, int index, int i){ // helper method to find previous node, this time for adding 
    System.out.println( " index " + index);
    System.out.println( " i " + i);
    if (i == size){
    if (index == i-1){ // if index is one before desired index
      return currentNode; // return the node
    }

    else { // otherwise add index
      index++;
    //}

    currentNode = currentNode.getNext(); // set current to next
    currentNode = previousFinder(currentNode,index, i); // recurse method with new current
    }
  }
  else{
    if (index == i-1){ // if index is one before desired index
      return currentNode; // return the node
    }

    else { // otherwise add index
      index++;
    //}

    currentNode = currentNode.getNext(); // set current to next
    currentNode = previousFinder(currentNode,index, i); // recurse method with new current
    }
  }
    return currentNode; // return final previous node
  }

  public Node<T> lastFinder(Node<T> currentNode){ // helper method to find last node
    
    if (currentNode.getNext()==null){ // if next of current node is null
      return currentNode; // return node
    }
    else { 

      currentNode = currentNode.getNext(); // set current to next
      currentNode = lastFinder(currentNode); // recurse with new current

    }

    return currentNode; // return final node
  }

  public Node<T> findCurrent(Node<T> current,int index, int i){ // helper method to find current node
    
    if (index == i){ // if index is equal to desired index i
      return current; // return the node
    }

    else {
      index++; // increase index otherwise
    }
 
    current = current.getNext(); // set current to next
    current = findCurrent(current,index, i); // recurse with new current

    return current; // reutrn final node
  }

  @Override
  public T getFirst() { // get first element
    T item = null;
      //TODO: Implement this method.
    if (isEmpty()){
      throw new IllegalStateException();
    }
    item = head.getData(); // set item to data of head
    return item; // return T item
  }

  @Override
  public T getLast() { // get last element
    T item = null;
      //TODO: Implement this method.

    if (isEmpty()){ // if empty list
      throw new IllegalStateException(); // throw exception
    }

    else { // if not empty
      item = lastFinder(head).getData(); // set item to data of last node using helper method
    }

    return item; // return final T item
  }

  @Override
  public T getAt(int i) { // method to get element at index
    T item = null;
     //TODO: Implement this method.

     Node<T> currentNode = head; // initialize current node to head
     
     if ( i < 0){
      throw new IndexOutOfBoundsException("index out of bounds");
    }
    else if ( i >= size){
      throw new IndexOutOfBoundsException("index out of bounds");
    }

    else if (isEmpty()){ // if empty list
      throw new IllegalStateException(); // throw exception
    }

    else if (i == 0) // if attemptoing to get first node
      item = getFirst();
    
    else { // if not empty or first node
      item = findCurrent(currentNode, 0, i).getData(); // set item to data of current node using helper method
    }

    return item; // return item
  }

  @Override
  public void removeElement(T elem) { // method to remove an element
    //TODO: Implement this method.
    if (elem == null){ // if element is null, throw exception
      throw new NullPointerException();
    }
    else {
      int index = indexOf(elem); // find index of element
      removeAt(index); // use removeAt(index) to remove element at index
    }
  }

  @Override
  public int indexOf(T elem) { // find index of element
    int index = -1;
      //TODO: Implement this method.

    index = 0; // set index to 0
    if (elem == null){ // if element is null, throw exception
      throw new NullPointerException();
    }

    else {
    Node<T> currentNode = head; // initialize current node to head
    index = indexFinder(currentNode, elem, index); // use helper method to find index
    }
    return index; //return index
  }
  
  public int indexFinder(Node<T> currentNode, T elem, int index){ // recursive method to find index of element
    
    if (elem == currentNode.getData()){ // if element is equal to data of current node
      return index; // return the index
    }
    
    if (currentNode.getNext() == null){
      if (elem == currentNode.getData())
        return index;
      else
        throw new ItemNotFoundException();
    }

    else {
      index++;
      System.out.println( "reached here, " + index);
      System.out.println("size is " + size);
      currentNode = currentNode.getNext(); // set current node to next
      index = indexFinder(currentNode, elem,index); // recurse with new current node
    }
      
    return index; // return index
  }

  @Override
  public boolean isEmpty() { // method to determine if list is empty
    boolean empty = false;
    //TODO: Implement this method.

    if (size == 0) // if size of list is 0
      empty = true;  // set boolean empty to true
    
    return empty; //return boolean
  }


  public Iterator<T> iterator() { // create iterator
    Iterator<T> iter = null;
      //TODO: Implement this method.

    iter = new LinkedNodeIterator<T>(head); // initialize with first node equal to head
      
    return iter; // return iterator
  }
}
