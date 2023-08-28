
 
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
    public String get(String key) {
    	// TODO(1)
    	//Calculate the index
        int index = 0;
        //if (contains(key))
        index = hash(key);
        //else 
          //  return null;
        //Loop to get element value using key and linear probe = 1 
        //If the element doesn’t exist, return null
            for (int i = index; map[i] != null; i++ ){
                if (map[i].getKey() == key){
                    return map[i].getValue();
                }
            }
        return null;
    }

    
    // Function to insert key-value pair 
    public void put(String key, String value) { 
        // TODO(2)      
        //Check and call rehash()  if the array is full	
       
        if (isFull()){
            rehash();
        }
        //Loop to find the new position
        int newIndex = hash(key);
        for (int i = newIndex; i < maxSize; i = (i + 1) % maxSize){
            if (map[i]== null){
                newIndex = i;
            }
            //else if (map[i].getKey() == key)
              //  return;
        }
        //Insert the key, value into the map
        Pair pair = new Pair(key,value);
        map[newIndex] = pair;   
        nElems++;
        //If the key exists, the data size does not grow.      


    }
    
    
    /// Function to rehash when the table is full
    public void rehash()  {   
		// TODO(3)
        // create a new table twice the size of the old one,
        // then insert all elements from the old hash table to new table.
        int newSize = maxSize * 2;
        int originalSize = maxSize;
        Pair[] newMap = new Pair[newSize];
        Pair[] storeMap = map;
        map = newMap;
        maxSize = newSize;
        nElems = 0;
        //map = ;
       // nElems = 0;
   // loop through the current key array and rehas
   for (int i = 0; i < originalSize; i++) {
       if (map[i] != null) {
            // int newIndex = hash(storeMap[i].getKey());
            // while (map[newIndex] != null){
            //     newIndex = (newIndex + 1) % (maxSize);
            // }
            // map[newIndex] = map[i];
            // nElems +=1;
            put(storeMap[i].getKey(), storeMap[i].getValue());
       }
   }

   // assign the new arrays to the original variables
   //map = newMap;
//maxSize = maxSize * 2;
   //nElems = getSize();
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


