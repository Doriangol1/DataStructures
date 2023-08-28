public class Employee implements Comparable<Employee>{

  private String name;
  private double salary;
  private String department;

  public Employee(String name, String department, double salary){
      this.name = name;
      this.department = department;
      this.salary = salary;
  }
 
  public String getName() {return name;}
  public double getSalary(){return salary;}
  public void setSalary(double newSalary){salary = newSalary;}
  public String getDepartment() {return department;}
  public String toString(){return name+", "+salary+", "+ department;}
  
  public void increaseSalary(double newSal){
     salary = salary + newSal;
  }
  
  // priority on salary, then name to brake a tie.
  public int compareTo(Employee otherEmp){
     int res = (int)(salary - otherEmp.getSalary());
     if(res == 0)
        res = -1*name.compareTo(otherEmp.getName());
     return res;
  }
}
