/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.CustomerDTO;
import entities.BankCustomer;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        CustomerFacade cf = CustomerFacade.getFacade(emf);
        cf.addCustomer(new BankCustomer("Mathias", "Hvid", "AB12", 4444, 1, "Pays well"));
        cf.addCustomer(new BankCustomer("Jack", "Hagedorn", "QQ42", 69, 3, "No money"));
    }
    
    public static void main(String[] args) {
        populate();
    }
}
