import java.util.Iterator;

public class HeapMain {

   public static void main(String[] args){

      PriorityQueueADT<Employee> empQueue = new MaxHeapArray<Employee>();

      Employee[] emps = {new Employee("Ada", "Tech", 150.00),    
                         new Employee("Bill", "Sales", 180.00),  
                         new Employee("Aditia", "Admin", 180.00),
                         new Employee("Eduardo", "Tech", 170.00),
                         new Employee("Zelda", "Tech", 190.00)};
      for(Employee nextEmp : emps)
          empQueue.enqueue(nextEmp);
          
       empQueue.printInorder();
       System.out.println(" ");

       Employee curEmp = null;
       try{
         curEmp = empQueue.dequeue();
       } catch(Exception ex){
         System.out.println(ex);
       }
       System.out.println(curEmp);
       System.out.println(" ");
       empQueue.printInorder();
   }    
}

