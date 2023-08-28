package structures;

import static org.junit.Assert.assertArrayEquals;

public class LinearCollisionHandler <K> implements CollisionHandler <K>{
    private int probeLength;

    /**
  * Constructors to set probeLength to 1, or a different length.
  */
    public LinearCollisionHandler(){
        this.probeLength = 1;
    }

    public LinearCollisionHandler(int probeLength){
        this.probeLength = probeLength;
    }

/**
  * Method starts at index and searches linearly until an open spot
  * is found in the array. This could include index itself.
  * index = (index + probeLength) % size
  */
   public int probe(int index, boolean[] activeArray, int M) {
      //TODO: Implement this method.
      int i = 0;
      
      for ( int j = index; i < activeArray.length ; j = (j + probeLength) % activeArray.length) {
         
         if (!activeArray[j]){
              return j;
         }
         
         i++;
       }

         return -1;
      }
    

  /**
* Start at index and search the array linearly until the target
* is found. Then return the array index of the target. 
* Return -1 if not found.
*/
   public int search(int startIndex, K target, K[] keyArray, boolean [] activeArray, int M){
      //TODO: Implement this method.
      int elem = 0;
      int curIndex = startIndex;

      while (elem < keyArray.length){
            if (activeArray[curIndex] && target.equals(keyArray[curIndex]))
               return curIndex;

            else if (!activeArray[curIndex] && keyArray[curIndex] == null)
               break;

            curIndex = (curIndex + probeLength) % keyArray.length;
            elem++;
      }

      return -1;
   }
}
