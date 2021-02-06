/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mathias
 */
public class CustomerFacadeTest {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("puTest");
    
    public CustomerFacadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
        facade.addCustomer("Mathias", "Hvid");
        facade.addCustomer("Jack", "Hagedorn");
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of findByID method, of class CustomerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testFindByID() {
        System.out.println("findByID");
        int id = 1;
        CustomerFacade instance = CustomerFacade.getCustomerFacade(emf);
        Customer expResult = new Customer("Mathias", "Hvid");
        Customer result = instance.findByID(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of findByLastName method, of class CustomerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testFindByLastName() {
        System.out.println("findByLastName");
        String lastName = "Hagedorn";
        CustomerFacade instance = CustomerFacade.getCustomerFacade(emf);
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Jack", "Hagedorn"));
        List<Customer> expResult = customers;
        List<Customer> result = instance.findByLastName(lastName);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumberOfCustomers method, of class CustomerFacade.
     */
    /*@org.junit.jupiter.api.Test
    public void testGetNumberOfCustomers() {
        System.out.println("getNumberOfCustomers");
        CustomerFacade instance = new CustomerFacade();
        Long expResult = null;
        Long result = instance.getNumberOfCustomers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of allCustomers method, of class CustomerFacade.
     */
    /*@org.junit.jupiter.api.Test
    public void testAllCustomers() {
        System.out.println("allCustomers");
        CustomerFacade instance = new CustomerFacade();
        List<Customer> expResult = null;
        List<Customer> result = instance.allCustomers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }    */
}
