package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.BankCustomer;
import utils.EMF_Creator;
import facades.CustomerFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("bankcustomer")
public class BankCustomerResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final CustomerFacade FACADE =  CustomerFacade.getFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCustomerById(@PathParam("id") int id) {
        return GSON.toJson(FACADE.getCustomerById(id));
    }
    
    
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCustomers() {
        List<BankCustomer> customers = FACADE.getAllBankCustomers();
        return GSON.toJson(customers);
    }
}
