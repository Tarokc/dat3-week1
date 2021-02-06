/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Mathias
 */
public class MakeTestData {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new BankCustomer("Mathias", "Hvid", "AB12", 2222, 3, "pays well"));
            em.persist(new BankCustomer("Morten", "Jensen", "QQ69", 12, 1, "peys poorly"));
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
    }
}
