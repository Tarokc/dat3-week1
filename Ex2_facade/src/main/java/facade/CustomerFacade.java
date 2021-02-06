/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;
import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
/**
 *
 * @author Mathias
 */
public class CustomerFacade {
    
    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    public CustomerFacade() {
    }

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    
    public Customer findByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer customer = em.find(Customer.class, id);
            return customer;
        }
        finally {
            em.close();
        }
    }
    
    public List<Customer> findByLastName(String lastName) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE lastName = :lastName", Customer.class);
            query.setParameter("lastName", lastName);
            return query.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    public Long getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            Long customerCount = (Long)em.createQuery("SELECT COUNT(c) FROM Customer c").getSingleResult();
            return customerCount;
        }
        finally {
            em.close();
        }
    }
    
    public List<Customer> allCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
            return query.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    public Customer addCustomer(String firstName, String lastName){
        EntityManager em = emf.createEntityManager();
        try {
            Customer customer = new Customer(firstName, lastName);
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        }
        finally {
            em.close();
        }
    }
}
