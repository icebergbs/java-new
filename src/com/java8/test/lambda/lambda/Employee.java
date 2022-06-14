package com.java8.test.lambda.lambda;

public class Employee {
    /* @Override
     public int hashCode() {
         final int prime = 31;
         int result = 1;
         result = prime * result + age;
         result = prime * result + ((name == null) ? 0 : name.hashCode());
         long temp;
         temp = Double.doubleToLongBits(salary);
         result = prime * result + (int) (temp ^ (temp >>> 32));
         return result;
     }
     @Override
     public boolean equals(Object obj) {
         if (this == obj)
             return true;
         if (obj == null)
             return false;
         if (getClass() != obj.getClass())
             return false;
         Employee other = (Employee) obj;
         if (age != other.age)
             return false;
         if (name == null) {
             if (other.name != null)
                 return false;
         } else if (!name.equals(other.name))
             return false;
         if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
             return false;
         return true;
     }*/
    private String name;
    private int age;
    private double salary;

    public Employee() {
        super();
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, int age, double salary) {
        super();
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [name=" + name + ", age=" + age + ", salary=" + salary + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
