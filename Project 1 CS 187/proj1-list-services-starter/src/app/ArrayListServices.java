package app;
import java.util.ArrayList;

/**
 *  This class provides methods that perform operations on a list of generic objects.
 *  The Objects are assumed to have an appropriate implementation of the equals method.
 */
public class ArrayListServices <T> {

   /**
    * This method takes an ArrayList as a parameter and returns a new ArrayList that 
    * does not contain any duplicate data. For example, if this list was passed in: 
    * [A, B, D, A, A, E, B], the method would return a list containing: [A, B, D, E]. 
    * The ordering of the data does not matter. 
    * Assume that the parameter is not null, but it may be an empty ArrayList, in which case 
    * your method returns a new, empty ArrayList.
    * Also note that the ArrayList that is returned must be a new ArrayList which is not the 
    * same as the ArrayList passed in. In other words, the parameter must not be changed by your method code.
    */
   public ArrayList<T> deDuplicate(ArrayList<T> inputList){
      //Write your comments on how you implemented the method.
      /**
       First, I intialized an arraylist that is equivalent to the original 
       arraylist. I will use it to store the deduplicated elements, called 
       noDup. Then, I intialized variables i and j that I will use for the
       for-loops later. Then, I set up a for-loop with a nested for-loop 
       inside of it, in order to compare each element with the rest of the 
       elements. If an element is found to equal another element in the 
       arraylist, one of the elements is removed. Once one of the elements
       is removed, j is set to equal to i. Otherwise, one element would be
       skipped if an element is removed but the index stays the same. Finally,
       the new arraylist with no duplicates is returned.
       
      **/
      
      //TODO: Implement this method.
      // initialize variables
      ArrayList<T> noDup = new ArrayList<T> ();
      int i;
      int j;
      
      noDup = inputList; // set instance equivalent to parameter arraylist.
      // go through each elelment 
      for (i=0;i<noDup.size();i++){
         // and compare it with the rest of the elements
         for (j=i+1; j<noDup.size(); j++){
             // if two equivalent elements found, remove from list  
            if (noDup.get(i).equals(noDup.get(j))){
                  noDup.remove(i);
                  j=i;
            }
         }


      }
      return noDup; // return new arraylist with no duplicates 
   }

   /**
    * This method takes two ArrayLists as parameters and returns a new ArrayList that 
    * contains the intersection of the data in the ArrayLists passed in. The intersection 
    * contains the elements that occur in both lists.
    * For example, if these lists were passed in: [A, B, D, A, A, E, B], [B, E, C], the method 
    * would return a list containing: [B, E]. The ordering of the data does not matter. Note that 
    * the result does not contain any duplicates.
    * Assume that the parameters are not null, but one or both may be an empty ArrayList, in which 
    * case your method returns a new, empty ArrayList.
    * Also note that the ArrayList that is returned must be a new ArrayList which is not the same as 
    * the ArrayList passed in. In other words, the parameter must not be changed by your method code.
    */
   public ArrayList<T> getSetIntersection(ArrayList<T> listA, ArrayList<T> listB){
      //Write your comments on how you implemented the method.
      /**
      First, I intialized an arraylist. I will use it to store the common elements, hence the name 
      comList. Then, I intialized variables i, j that I will use for the
      for-loops later. I then deduplicate both lists using the previous method. Using 
      the nested for-loops, I'm able to iterate through both arraylist.
      Specifically, I go through each element in listA and check if 
      it's equal to each element in listB.  If two elements are found to be
      equal, it would be added to the comList. Finally,
      the program returns the comList.
      **/
      
      //TODO: Implement this method.

      //initialize variables
      ArrayList<T> comList = new ArrayList<T>();
      int i;
      int j;
      

      this.deDuplicate(listA);
      this.deDuplicate(listB);


      
      // go through each element in listA
      for (i=0; i<listA.size(); i++){
         // compare with each element in listB
         for (j=0; j<listB.size();j++){
            
            if (listA.get(i).equals(listB.get(j))){
               
               comList.add(listA.get(i));
            }
         }

      }
      return comList; // return new arraylist
   }
   

   //}

   /**
    *  This method takes two ArrayLists as parameters and returns a new ArrayList that 
    * contains the set difference of the data in the ArrayLists passed in. The set difference 
    * contains the elements that occur only in one or the other list, but not in both.
    * For example, if these lists were passed in: [A, B, D, A, A, E, B], [B, E, C], the method 
    * would return a list containing: [A, C]. The ordering of the data does not matter. Note 
    * that the result does not contain any duplicates.
    * Assume that the parameters are not null, but one or both may be an empty ArrayList. In the 
    * case where one list is empty, your method returns a new ArrayList that contains all of the 
    * elements on the other list- with no duplicates. In the case where both lists are empty, your 
    * method returns a new, empty ArrayList.
    * Also note that the ArrayList that is returned must be a new ArrayList which is not the same 
    * as the ArrayList passed in. In other words, the parameter must not be changed by your method code.
    */
   public ArrayList<T> getSetDifference(ArrayList<T> listA, ArrayList<T> listB){
      //Write your comments on how you implemented the method.
      /**
       Using 2 for-loops, I go through each element in listA and iterate through
       the entire listB arraylist to check if it exists in that list. If the
       element is nof found in listB, I then use another for-loop to check if 
       that element already exists in diffList (arraylist standing for difference list).
       I then do the same algorithm in the other direction, checking through
       each element in listB and comparing with each element in listA, then 
       checking for duplicates. That way, if the element is not found in
       the opposite list, and has not already been added to the new arrayList,
       it would be added. Finally, diffList is returned. 
      **/
      
      //TODO: Implement this method.

      ArrayList<T> diffList = new ArrayList<T>();
      int i;
      int j;
      boolean duplicate = false; // will be used to determine if variable is a duplicate
      boolean found = false; // will be used to determine if element is found in the opposite list
      
      // iterating through each element in listA
      for (i=0; i<listA.size(); i++){

         found = false;
         duplicate = false;
         // comparing with each element in listB
         for (j=0; j<listB.size();j++){

            if (listA.get(i).equals(listB.get(j))){
               found = true;            // found is set to true if it is found in opposite list
            }
         }
         // if not found in the other list...
         if (found==false){
               // iterate through new arraylist to check for duplicates
               for (int k=0; k<diffList.size(); k++){

                  if (listA.get(i).equals(diffList.get(k)))
                     duplicate = true;
               }
               // if not a duplicate, element is added.
               if (duplicate == false)
                  diffList.add(listA.get(i));
            
            }

      }
      // iterating through each element in listB
      for (i=0; i<listB.size(); i++){

         found = false;
         duplicate = false;
         // Comparing with each element in listA
         for (j=0; j<listA.size();j++){

            if (listB.get(i).equals(listA.get(j))){
               found = true; // set to true if found in opposite list
            }
         }
         // if it's not in the other list..
         if (found==false){
               // check if it has already been added
               for (int k=0; k<diffList.size(); k++){

                  if (listB.get(i).equals(diffList.get(k)))
                     duplicate = true;
               }
               // if not found in other list and not a duplicate, add to new list
               if (duplicate == false)
                  diffList.add(listB.get(i));
            
            }

      }
      return diffList; // return new arraylist
   }
   

}