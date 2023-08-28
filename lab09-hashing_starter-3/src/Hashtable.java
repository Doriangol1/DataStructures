
 
 /*  Linear Probing Hash Table */ 
public class Hashtable {
	private int nElems, maxSize;       
  
   public Pair[] map = null;	// stores hash table elements
 
    // Constructor 
    public Hashtable(int capacity) {
      nElems = 0;
        maxSize = capacity;
    	map = new Pair[maxSize];
		for(int i = 0; i < maxSize ; i++)
			map[i]=null;
    }  
 
 
    // Function to check if hash table is empty 
    public boolean isEmpty() 
    {
        return getSize() == 0;
    }
    
    
    // Function to get size of hash table 
    public int getSize() 
    {
        return nElems;
    }
 

    // Function to check if hash table is full 
    public boolean isFull() 
    {
        return nElems == maxSize;
    }
 

    // Function to check if hash table contains a key
    public boolean contains(String key) 
    {
        return get(key) !=  null;
    }
 
    
    // Function to get hash-code/hash-value for a given key 
    public int hash(String key) 
    {
        return Math.abs(key.hashCode()) % maxSize;
    }    
 
    
    // Function to get value for a given key 
    public String get(String key) 
    {
    	// TODO(1)
    	//Calculate the index
        //Loop to get element value using key and linear probe = 1 
        //If the element doesn’t exist, return null
    
        return null;
    }

    
    // Function to insert key-value pair 
    public void put(String key, String value) { 
        // TODO(2)      
        //Check and call rehash()  if the array is full	
        //Loop to find the new position
        //Insert the key, value into the map
        //If the key exists, the data size does not grow.           

    }
    
    
    /// Function to rehash when the table is full
    public void rehash()  
    {   
		// TODO(3)
        // create a new table twice the size of the old one,
        // then insert all elements from the old hash table to new table.
    	
  
        
    }
     
 
    // Function to print HashTable 
    public void printHashTable()
    {
        System.out.println("\nHash Table: Key, Value ");
        for (int i = 0; i < maxSize; i++)
            if (map[i] != null)
            	System.out.println(map[i].getKey()+", "+map[i].getValue());
        System.out.println();
    }   
}

class Pair{

	private String key;
	private String value;

	public Pair(String key, String value){
		this.key = key;
		this.value = value;
	}

	public String getKey(){
		return key;
	}

	public String getValue(){
		return value;
	}

}


