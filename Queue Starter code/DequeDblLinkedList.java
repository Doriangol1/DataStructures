class DequeDblLinkedList<T> {
    
    private Node<T> front;
    private Node<T> rear;
    
    /*
     * Adds element at the beginning of the queue
    */
    public void enqueueFront(T item){
        System.out.println("adding at front: "+item);
     //TODO 1: implement this method.
        
        if (item == null){
            throw new NullPointerException();
        }
        //create new node
        Node<T> newNode = new Node<T>();
        //set the value;
        newNode.setValue(item);

        if (front == null){
            front = newNode;
            rear = newNode;
        }
        else {
            //set the link to the next node
            front.setPrev(newNode);
            newNode.setNext(front);
            //check the first node and take action
            front = newNode;    

        }
        System.out.println("front: " + front.getValue());

    }
    
    /*
     * Adds element at the end of the queue
    */

    public void enqueueRear(T item){
        System.out.println("adding at rear: "+item);
     //TODO 2: implement this method.
        //create new node
        if (item == null){
            throw new NullPointerException();
        }
        Node<T> newNode = new Node<T>();
        //set the value;
        newNode.setValue(item);

        if (front == null){
            front = newNode;
            rear = newNode;
        }
        else {
            rear.setNext(newNode);
            newNode.setPrev(rear);
            rear = newNode;    

        }
       System.out.println("rear: " + rear.getValue());
       System.out.println("front 2: " + front.getValue());
    }
     
    /*
     * Remove an item from the beginning of the queue
    */
    public void dequeueFront(){
        if(front == null){
            System.out.println("Deque underflow!! unable to remove.");
            return;
        } 
        
     //TODO 3: finish implementing this method.      




    }
     
    /*
     * Remove an item from the rear of the queue
    */
    public void dequeueRear(){
        if(rear == null){
            System.out.println("Deque underflow!! unable to remove.");
            return;
        }
     //TODO 4: finish implementing this method. 
        
        
        
        
    }

}