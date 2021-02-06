package tester;

import entity.Employee;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Employee("xa12tt", "Kurt", "Wonnegut", new BigDecimal(335567)));
            em.persist(new Employee("hyu654", "Hanne", "Olsen", new BigDecimal(435867)));
            em.persist(new Employee("uio876", "Jan", "Olsen", new BigDecimal(411567)));
            em.persist(new Employee("klo999", "Irene", "Petersen", new BigDecimal(33567)));
            em.persist(new Employee("jik666", "Tian", "Wonnegut", new BigDecimal(56567)));
            em.getTransaction().commit();
            
            //Complete all these small tasks. Your will find the solution to all, but the last,
            //In this document: https://en.wikibooks.org/wiki/Java_Persistence/JPQL#JPQL_supported_functions
            
            //1) Create a query to fetch all employees with a salary > 100000 and print out all the salaries
            TypedQuery<Employee> salaryQuery = em.createQuery("SELECT e FROM Employee e WHERE e.salary > 100000", Employee.class);
            List<Employee> employees = salaryQuery.getResultList();
            System.out.println("\n1: salary > 100000");
            for (Employee e : employees) {
                System.out.println(e.getSalary());
            }
            
            //2) Create a query to fetch the employee with the id "klo999" and print out the firstname
            Employee employee = em.find(Employee.class, "klo999");
            System.out.println("\n2: id = klo999");
            System.out.println(employee.getFirstName());
            
            //3) Create a query to fetch the highest salary and print the value
            Query highestSalaryQuery = em.createQuery("SELECT MAX(e.salary) FROM Employee e");
            BigDecimal highestSalary = (BigDecimal)highestSalaryQuery.getSingleResult();
            System.out.println("\n3: highest salary");
            System.out.println(highestSalary);
            
            //4) Create a query to fetch the firstName of all Employees and print the names
            List<String> employeeNames = em.createQuery("SELECT e.firstName FROM Employee e").getResultList();
             System.out.println("\n4: Employee firstName");
            for (String name : employeeNames) {
                System.out.println(name);
            }
           
            //5 Create a query to calculate the number of employees and print the number
            Long employeeCount = (Long)em.createQuery("SELECT COUNT(e) FROM Employee e").getSingleResult();
            System.out.println("\n5: Number of employees");
            System.out.println(employeeCount);
            
            //6 Create a query to fetch the Employee with the higest salary and print all his details
            TypedQuery<Employee> highestSalaryEmployeeQuery = em.createQuery("SELECT e FROM Employee e WHERE e.salary = " + highestSalary, Employee.class);
            Employee highestSalaryEmployee = highestSalaryEmployeeQuery.getSingleResult();
            System.out.println("\n6: Employee deatails for highest salary");
            System.out.println(highestSalaryEmployee.getFirstName() + "\n"
                               + highestSalaryEmployee.getLastName() + "\n"
                               + highestSalaryEmployee.getId() + "\n"
                               + highestSalaryEmployee.getSalary());
            
        } finally {
            em.close();
            emf.close();
        }
    }

}
