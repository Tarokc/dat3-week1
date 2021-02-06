/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import javax.persistence.EntityManagerFactory;
import entity.Customer;
import facade.CustomerFacade;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author Mathias
 */
public class EntityTested {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
        facade.addCustomer("Mathias", "Hvid");
        facade.addCustomer("Jack", "Hagedorn");
        
        List<Customer> customers = facade.allCustomers();
        for (Customer c : customers) {
            System.out.println(c.toString());
        }
        
        Customer customer = facade.findByID(1);
        System.out.println(customer);
        System.out.println(new Customer("Mathias", "Hvid").toString());
    }
}
