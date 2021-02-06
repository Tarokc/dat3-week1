/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import entity.Animal;
import java.util.Random;
import javax.persistence.LockModeType;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author Mathias
 */
@Path("animals_db")
public class AnimalFromDB {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalFromDB
     */
    public AnimalFromDB() {
    }

    @GET
    @Path("animals")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimals() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }
    }

    @GET
    @Path("animalbyid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByID(@PathParam("id") int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Animal animal = em.find(Animal.class, id);
            return new Gson().toJson(animal);
        } finally {
            em.close();
        }
    }

    @GET
    @Path("animalbytype/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByType(@PathParam("type") String type) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.type = :type", Animal.class);
            query.setParameter("type", type);
            List<Animal> animal = query.getResultList();

            return new Gson().toJson(animal);
        } finally {
            em.close();
        }
    }
    
    @GET
    @Path("random_animal")
    @Produces(MediaType.APPLICATION_JSON)
    public String randomAnimal() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animals = query.getResultList();
            System.out.println(animals.size());
            int randomNumber = new Random().nextInt(animals.size());
            System.out.println(randomNumber);
            return new Gson().toJson(animals.get(randomNumber));
        }
        finally {
            em.close();
        }
    }

    /**
     * PUT method for updating or creating an instance of AnimalFromDB
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
