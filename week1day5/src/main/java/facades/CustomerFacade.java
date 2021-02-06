package facades;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import dtos.CustomerDTO;
import entities.BankCustomer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class CustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private CustomerFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CustomerFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CustomerDTO getCustomerById(int id){
        EntityManager em = emf.createEntityManager();
        return new CustomerDTO(em.find(BankCustomer.class, id));
    }
    
    public List<CustomerDTO> getCustomerByName(String name) {
        EntityManager em = emf.createEntityManager();
        List<CustomerDTO> customers = em.createNamedQuery("SELECT c FROM BankCustomer c WHERE name = " + name).getResultList();
        return customers;
    }
    
    public BankCustomer addCustomer(BankCustomer cust) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;
        }
        finally {
            em.close();
        }
    }
    
    public List<BankCustomer> getAllBankCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<BankCustomer> query = em.createQuery("SELECT c from BankCustomer c", BankCustomer.class);
            return query.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    
}
